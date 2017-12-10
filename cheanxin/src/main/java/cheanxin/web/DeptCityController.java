package cheanxin.web;

import cheanxin.domain.DeptCity;
import cheanxin.domain.User;
import cheanxin.service.DeptCityService;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jawinton on 17/02/16.
 */
@RestController
@RequestMapping("/dept_cities")
public class DeptCityController extends BaseController {
    @Autowired
    private DeptCityService deptCityService;

    @Autowired
    private DeptService deptService;

    @RequestMapping(method = RequestMethod.PUT ,consumes = "application/json")
    public ResponseEntity<List<DeptCity>> save(
            @RequestBody DeptCity unsavedDeptCity,
            Errors errors,
            @AuthenticationPrincipal User user) {
        assertFieldError(errors);
        Assert.notEmpty(unsavedDeptCity.getCityIds(), "CityIds can not be empty.");
        Assert.notNull(unsavedDeptCity.getDeptId(), "Dept id cannot be null.");
        Assert.notNull(deptService.findOne(unsavedDeptCity.getDeptId()), "Dept not found.");

        long now = System.currentTimeMillis() / 1000;
        unsavedDeptCity.setCreatorUsername(user.getUsername());
        unsavedDeptCity.setCreatedTime(now);
        return new ResponseEntity<>(deptCityService.save(unsavedDeptCity), HttpStatus.CREATED);
    }
}