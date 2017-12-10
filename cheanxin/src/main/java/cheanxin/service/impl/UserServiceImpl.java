package cheanxin.service.impl;

import cheanxin.data.UserRepository;
import cheanxin.domain.Dept;
import cheanxin.domain.Post;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.DeptService;
import cheanxin.service.PostService;
import cheanxin.service.UserPostService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeptService deptService;

    @Autowired
    UserPostService userPostService;

    @Autowired
    PostService postService;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    @Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> list(long deptId, String realName, String mobileNumber, String email, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchUser searchUser = new SearchUser();
        searchUser.setDeptId(deptId);
        searchUser.setRealName(realName);
        searchUser.setMobileNumber(mobileNumber);
        searchUser.setEmail(email);
        searchUser.setStatus(status);
        Specification<User> specification = this.getWhereClause(searchUser);
        Page<User> userPage =  userRepository.findAll(specification, pageable);
        if (userPage == null || userPage.getTotalElements() == 0) {
            return userPage;
        }

        // set user dept info and post info.
        Set<String> usernames = new HashSet<>();
        for (User user : userPage) {
            if (user == null || user.getUsername() == null) {
                continue;
            }
            usernames.add(user.getUsername());
        }

        Map<Long, Dept> deptMap = new HashMap<>();
        for (User user : userPage) {
            if (user == null || user.getDeptId() == null) {
                continue;
            }
            deptMap.put(user.getDeptId(), null);
        }
        List<Dept> deptList = deptService.list(deptMap.keySet());
        if (deptList != null) {
            for (Dept dept : deptList) {
                if (dept == null || dept.getId() == null) {
                    continue;
                }
                deptMap.put(dept.getId(), dept);
            }
        }

        Map<String, Collection<Post>> userPostListMap = userPostService.listUserPostListMap(usernames);
        if (userPostListMap != null && !userPostListMap.isEmpty()) {
            for (User user : userPage) {
                if (user == null || user.getUsername() == null) {
                    continue;
                }
                user.setPosts(userPostListMap.get(user.getUsername()));
                user.setDept(deptMap.get(user.getDeptId()));
            }
        }

        return userPage;
    }

    @Override
    public List<User> list(long deptId, long postId, boolean enabled) {
        List<User> userList;
        if (deptId <= 0L) {
            userList = userRepository.findAllByEnabled(enabled);
        } else {
            userList = userRepository.findAllByDeptIdAndEnabled(deptId, enabled);
        }
        if (userList == null) {
            return new ArrayList<>();
        }

        if (postId > 0L) {
            Set<String> usernames = new HashSet<>();
            for (User user : userList) {
                if (user == null || user.getUsername() == null) {
                    continue;
                }
                usernames.add(user.getUsername());
            }

            // 在用户中筛选中拥有postId职位的用户
            List<UserPost> userPostList = userPostService.list(usernames, postId);
            usernames.clear();
            if (userPostList != null) {
                for (UserPost userPost : userPostList) {
                    if (userPost == null || userPost.getUsername() == null) {
                        continue;
                    }
                    usernames.add(userPost.getUsername());
                }
            }

            if (usernames.isEmpty()) {
                return new ArrayList<>();
            }

            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user == null) {
                    iterator.remove();
                    continue;
                }
                if (!usernames.contains(user.getUsername())) {
                    iterator.remove();
                }
            }
        }

        return userList;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return findByMobileNumber(mobileNo) != null;
    }

    private class SearchUser {
        private Long deptId;
        private String realName;
        private String mobileNumber;
        private String email;
        private Integer status;

        public SearchUser() {}

        public Long getDeptId() {
            return deptId;
        }

        public void setDeptId(Long deptId) {
            this.deptId = deptId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }

    /**
     * generate where clause dynamically.
     * @param searchUser
     * @return
     */
    private Specification<User> getWhereClause(final SearchUser searchUser){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchUser.getDeptId() > 0L) {
                    predicate.add(cb.equal(root.get("deptId").as(Long.class), searchUser.getDeptId()));
                }
                if (searchUser.getRealName() != null && !searchUser.getRealName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("realName").as(String.class), searchUser.getRealName() + "%"));
                }
                if (searchUser.getMobileNumber() != null && !searchUser.getMobileNumber().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("mobileNumber").as(String.class), searchUser.getMobileNumber() + "%"));
                }
                if (searchUser.getEmail() != null && !searchUser.getEmail().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("email").as(String.class), searchUser.getEmail() + "%"));
                }
                if (searchUser.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("enabled").as(Integer.class), searchUser.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
