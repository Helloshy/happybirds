package cheanxin.service.impl;

import cheanxin.data.PostRepository;
import cheanxin.domain.Post;
import cheanxin.domain.PostType;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTypeService postTypeService;

    @Override
    public Post save(Post unsavedPost) {
        return postRepository.save(unsavedPost);
    }

    @Override
    public void remove(long id) {
        postRepository.delete(id);
    }

    @Override
    public Post findOne(long id) {
        return postRepository.findOne(id);
    }

    @Override
    public boolean isExists(long id) {
        return findOne(id) != null;
    }

    @Override
    public Page<Post> list(String name, Boolean enabled, int page, int size) {
        if (enabled == null) {
            return list(name, page, size);
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Post> postPage = name == null || name.trim().isEmpty()
                ? postRepository.findByEnabled(enabled, pageable)
                : postRepository.findByNameIgnoreCaseContainingAndEnabled(name, enabled, pageable);

        setPostType(postPage);

        return postPage;
    }

    @Override
    public Page<Post> list(String name, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Post> postPage = name == null || name.trim().isEmpty()
                ? postRepository.findAll(pageable)
                : postRepository.findByNameIgnoreCaseContaining(name, pageable);

        setPostType(postPage);

        return postPage;
    }

    @Override
    public List<Post> list(boolean enabled) {
        List<Post> postList = postRepository.findByEnabled(enabled);

        setPostType(postList);

        return postList;
    }

    @Override
    public Map<Long, Post> listPostMap(boolean enabled) {
        List<Post> postList = postRepository.findByEnabled(enabled);
        Map<Long, Post> postMap = new HashMap<>();
        for (Post post : postList) {
            if (post == null) {
                continue;
            }
            postMap.put(post.getId(), post);
        }
        return postMap;
    }

    private void setPostType(Iterable<Post> posts) {
        Map<Long, PostType> postTypeMap = postTypeService.listPostTypeMap(true);
        // set post type info.
        for (Post post : posts) {
            PostType postType = postTypeMap.get(post.getPostTypeId());
            if (postType == null) {
                continue;
            }
            post.setPostType(postType.getName());
        }
    }
}
