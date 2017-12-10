package cheanxin.service;

import cheanxin.domain.PostType;

import java.util.List;
import java.util.Map;

/**
 * 岗位类型service类
 * Created by Jawinton on 16/12/21.
 */
public interface PostTypeService {
    /**
     * 所有岗位类型列表
     * @param enabled
     * @return
     */
    List<PostType> list(boolean enabled);

    /**
     * 获取所有岗位类型map
     * @param enabled
     * @return
     */
    Map<Long, PostType> listPostTypeMap(boolean enabled);

    /**
     * 获取岗位类型详情
     * @param id
     * @return
     */
    PostType findOne(long id);

    /**
     * 判断岗位类型是否存在
     * @param id
     * @return
     */
    boolean isExists(long id);
}
