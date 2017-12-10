package cheanxin.service;

import cheanxin.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 岗位service类
 * Created by Jawinton on 16/12/21.
 */
public interface PostService {
    /**
     * 保存岗位
     * @param unsavedPost
     * @return
     */
    Post save(Post unsavedPost);

    /**
     * 删除岗位
     * @param id
     */
    void remove(long id);

    /**
     * 获取岗位详情
     * @param id
     * @return
     */
    Post findOne(long id);

    /**
     * 分页获取岗位列表
     * @param name 岗位名
     * @param enabled 是否启用
     * @param page
     * @param size
     * @return
     */
    Page<Post> list(String name, Boolean enabled, int page, int size);

    /**
     * 分页获取岗位列表
     * @param name 岗位名
     * @param page
     * @param size
     * @return
     */
    Page<Post> list(String name, int page, int size);

    /**
     * 获取全部岗位列表
     * @param enabled
     * @return
     */
    List<Post> list(boolean enabled);

    /**
     * 判断岗位是否存在
     * @param id
     * @return
     */
    boolean isExists(long id);

    /**
     * 获取所有启用岗位id和详情映射Map
     * 数据库中保存的岗位数量相对少，因此可以将岗位信息提取到内存中。
     * @param enabled
     * @return
     */
    Map<Long, Post> listPostMap(boolean enabled);
}
