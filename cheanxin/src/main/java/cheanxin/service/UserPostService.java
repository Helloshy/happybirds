package cheanxin.service;

import cheanxin.domain.Post;
import cheanxin.domain.UserPost;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户岗位
 * Created by Jawinton on 16/12/21.
 */
public interface UserPostService {
    /**
     * 用户岗位列表
     * @param username
     * @return
     */
    List<UserPost> list(String username);

    /**
     * 用户岗位列表
     * @param username
     * @param enabled
     * @return
     */
    List<UserPost> list(String username, boolean enabled);

    /**
     * 获取用户名列表的所有岗位信息，并且映射成用户名和岗位详情的Map
     * @param usernames
     * @return
     */
    Map<String, Collection<Post>> listUserPostListMap(Collection<String> usernames);

    /**
     * 在用户列表中筛选特定岗位的用户
     * @param usernames
     * @param postId
     * @return
     */
    List<UserPost> list(Collection<String> usernames, long postId);

    /**
     * 批量保存
     * @param userPosts
     * @return
     */
    List<UserPost> save(Iterable<UserPost> userPosts);

    /**
     * 批量保存用户岗位信息
     * @param username
     * @param postIds
     * @return
     */
    List<UserPost> save(String username, Set<Long> postIds);
}
