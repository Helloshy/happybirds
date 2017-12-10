package cheanxin.service.impl;

import cheanxin.data.PostTypeRepository;
import cheanxin.domain.PostType;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class PostTypeServiceImpl implements PostTypeService {
    @Autowired
    PostTypeRepository postTypeRepository;

    @Override
    public List<PostType> list(boolean enabled) {
        return postTypeRepository.findAllByEnabledOrderBySortIndexAsc(enabled);
    }

    @Override
    public Map<Long, PostType> listPostTypeMap(boolean enabled) {
        List<PostType> postTypeList = list(enabled);
        Map<Long, PostType> postTypeMap = new HashMap<>();
        for (PostType postType : postTypeList) {
            postTypeMap.put(postType.getId(), postType);
        }
        return postTypeMap;
    }

    @Override
    public PostType findOne(long id) {
        return postTypeRepository.findOne(id);
    }

    @Override
    public boolean isExists(long id) {
        return findOne(id) != null;
    }
}
