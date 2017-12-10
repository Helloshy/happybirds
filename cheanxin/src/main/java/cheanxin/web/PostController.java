package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Post;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jawinton on 16/12/14.
 */
@RestController
@RequestMapping("/posts")
public class PostController extends BaseController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService postTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Post> list(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "enabled", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        Boolean enabled = null;
        if (status == 0) {
            enabled = false;
        } else if (status == 1) {
            enabled = true;
        }
        Page<Post> posts = postService.list(name, enabled, page, size);
        return posts;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Post> list(
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled) {
        List<Post> posts = postService.list(enabled);
        return posts;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Post> get(@PathVariable(value = "id") long id) {
        Post post = postService.findOne(id);

        Assert.notNull(post, "Post not found");

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST , consumes = "application/json")
    public ResponseEntity<Post> save(@Valid @RequestBody Post post, Errors errors) {
        assertFieldError(errors);
        Assert.isTrue(postTypeService.isExists(post.getPostTypeId()), "Post type not found.");

        post.setCreatedTime(System.currentTimeMillis() / 1000);
        post.setEditable(true);
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Post post,
            Errors errors) {
        assertFieldError(errors);
        Post savedPost = postService.findOne(id);
        Assert.notNull(savedPost, "Post not found.");
        Assert.isTrue(savedPost.getEditable(), "Post is not editable.");
        Assert.isTrue(postTypeService.isExists(post.getPostTypeId()), "Post type not found.");

        post.setId(id);
        post.setCreatedTime(savedPost.getCreatedTime());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(value = "id") long id) {
        postService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Post> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Post unsavedPost) {
        Post savedPost = postService.findOne(id);

        Assert.notNull(savedPost, "Post not found.");
        Assert.isTrue(savedPost.getEditable(), "Post is not editable.");
        Assert.notNull(unsavedPost.getEnabled(), "Field enabled is empty.");

        savedPost.setEnabled(unsavedPost.getEnabled());
        return new ResponseEntity<>(postService.save(savedPost), HttpStatus.OK);
    }
}
