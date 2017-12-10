package cheanxin.service.impl;

import cheanxin.data.UserPostRepository;
import cheanxin.domain.Post;
import cheanxin.domain.UserPost;
import cheanxin.service.PostService;
import cheanxin.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class UserPostServiceImpl implements UserPostService {
    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    PostService postService;

    @Override
    public List<UserPost> list(String username) {
        return list(username, true);
    }

    @Override
    public List<UserPost> list(String username, boolean enabled) {
        return userPostRepository.findByUsernameAndEnabled(username, enabled);
    }

    @Override
    public Map<String, Collection<Post>> listUserPostListMap(Collection<String> usernames) {
        Map<String, Collection<Post>> userPostMap = new HashMap<>();
        List<UserPost> userPostList = userPostRepository.findByUsernameInAndEnabled(usernames, true);
        if (userPostList == null) {
            return userPostMap;
        }
        Map<Long, Post> postMap = postService.listPostMap(true);
        if (postMap == null) {
            return userPostMap;
        }
        for (UserPost userPost : userPostList) {
            if (userPost == null) {
                continue;
            }
            Collection<Post> postList = userPostMap.get(userPost.getUsername());
            if (postList == null) {
                postList = new ArrayList<>();
            }
            postList.add(postMap.get(userPost.getPostId()));
            userPostMap.put(userPost.getUsername(), postList);
        }
        return userPostMap;
    }

    @Override
    public List<UserPost> list(Collection<String> usernames, long postId) {
        return userPostRepository.findByUsernameInAndPostIdAndEnabled(usernames, postId, true);
    }

    @Override
    public List<UserPost> save(Iterable<UserPost> userPosts) {
        return userPostRepository.save(userPosts);
    }

    @Override
    @Transactional
    public List<UserPost> save(String username, Set<Long> postIds) {
        List<UserPost> enabledUserPostList = list(username, true);
        List<UserPost> disabledUserPostList = list(username, false);
        List<UserPost> userPosts = new ArrayList<>();

        for (UserPost userPost : enabledUserPostList) {
            // disabled post
            if (!postIds.contains(userPost.getPostId())) {
                userPost.setEnabled(false);
                userPosts.add(userPost);
            }
        }

        for (UserPost userPost : disabledUserPostList) {
            // enabled post
            if (postIds.contains(userPost.getPostId())) {
                userPost.setEnabled(true);
                userPosts.add(userPost);
            }
        }

        // add not exists user post.
        long now = System.currentTimeMillis() / 1000;
        for (Long postId : postIds) {
            if (postId == null) {
                continue;
            }
            UserPost userPost = new UserPost();
            userPost.setUsername(username);
            userPost.setPostId(postId);
            userPost.setCreatedTime(now);
            if (!enabledUserPostList.contains(userPost) && !userPosts.contains(userPost)) {
                userPost.setEnabled(true);
                userPosts.add(userPost);
            }
        }

        if (userPosts.isEmpty()) {
            return userPosts;
        }
        return save(userPosts);
    }
}
