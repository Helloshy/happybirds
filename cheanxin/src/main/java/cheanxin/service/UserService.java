package cheanxin.service;

import cheanxin.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 用户service类
 * Created by Jawinton on 16/12/21.
 */
public interface UserService {
    /**
     * 获取用户详情
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    User findOne(long id);

    /**
     * 获取用户详情
     * @param mobileNumber
     * @return
     */
    User findByMobileNumber(String mobileNumber);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 分页获取用户列表
     * @param deptId 部门id
     * @param realName 真实姓名
     * @param mobileNumber 手机号
     * @param email
     * @param status
     * @param page
     * @param size
     * @return
     */
    Page<User> list(long deptId, String realName, String mobileNumber, String email, int status, int page, int size);

    /**
     * 获取用户列表
     * @param deptId 部门id
     * @param postId 岗位id
     * @param enabled 是否启用
     * @return
     */
    List<User> list(long deptId, long postId, boolean enabled);

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    boolean isUsernameExists(String username);

    /**
     * 判断手机号是否已经被占用
     * @param mobileNo
     * @return
     */
    boolean isMobileNoExists(String mobileNo);
}