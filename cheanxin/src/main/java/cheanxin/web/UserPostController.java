package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Post;
import cheanxin.domain.UserPost;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import cheanxin.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 16/12/14.
 */
@RestController
@RequestMapping("/user_posts")
public class UserPostController extends BaseController {
    @Autowired
    private UserPostService userPostService;

    @RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
    public List<UserPost> update(
            @PathVariable(value = "username") String username,
            @RequestBody Set<Long> postIds) {
        return userPostService.save(username, postIds);
    }
}
