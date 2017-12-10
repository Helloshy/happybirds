package cheanxin.service.impl;

import cheanxin.data.PostAuthorityRepository;
import cheanxin.domain.DeptCity;
import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.DeptCityService;
import cheanxin.service.PostAuthorityService;
import cheanxin.service.UserPostService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class PostAuthorityServiceImpl implements PostAuthorityService {
    @Autowired
    UserPostService userPostService;

    @Autowired
    PostAuthorityRepository postAuthorityRepository;

    @Autowired
    DeptCityService deptCityService;

    @Autowired
    UserService userService;

    @Override
    public List<PostAuthority> list(Collection<Long> postIds) {
        if (postIds == null || postIds.isEmpty()) {
            return new ArrayList<>();
        }
        return postAuthorityRepository.findByPostIdIn(postIds);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        // set user cities.
        List<DeptCity> deptCityList = deptCityService.list(user.getDeptId(), true);
        if (deptCityList != null) {
            Set<Long> cityIds = new HashSet<>();
            for (DeptCity deptCity : deptCityList) {
                cityIds.add(deptCity.getCityId());
            }
            user.setCityIds(cityIds);
        }

        // set user posts.
        List<UserPost> userPostList = userPostService.list(username);
        Collection<Long> postIds = new ArrayList<>();
        if (userPostList != null) {
            for (UserPost userPost : userPostList) {
                if (userPost == null || userPost.getPostId() == null) {
                    continue;
                }
                postIds.add(userPost.getPostId());
            }
        }

        // set user authority list.
        List<PostAuthority> postAuthorityList = list(postIds);
        user.setPostAuthorities(postAuthorityList);

        return user;
    }
}
