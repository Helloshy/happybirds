package cheanxin.web;

import cheanxin.domain.Dept;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/depts")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Dept> list(
            @RequestParam(value = "level", defaultValue = "1") int level,
            @RequestParam(value = "parentDeptId", defaultValue = "0") long parentDeptId,
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled) {
        Assert.isTrue(level == 1 || parentDeptId == 0, "level and parentDeptId are exclusive.");

        if (level != 1) {
            return deptService.list(level, enabled);
        }

        return deptService.list(parentDeptId, enabled);
    }
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Dept> listAll(
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled) {

        return deptService.list(enabled);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Dept> get(@PathVariable(value = "id") long id) {
        Dept dept = deptService.findOne(id);

        Assert.notNull(dept, "Dept not found");

        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity<Dept> save(
            @Valid @RequestBody Dept dept,
            Errors errors) {
        assertFieldError(errors);

        long now = System.currentTimeMillis() / 1000;
        dept.setCreatedTime(now);
        dept.setModifiedTime(now);
        if (dept.getParentDeptId() == 0L) {
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.CREATED);
        }

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        Assert.notNull(parentDept, "Parent dept not found");

        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Dept> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Dept dept,
            Errors errors) {
        assertFieldError(errors);
        Dept savedDept = deptService.findOne(id);
        Assert.notNull(savedDept, "Dept not found");

        dept.setId(id);
        dept.setCreatedTime(savedDept.getCreatedTime());
        dept.setModifiedTime(System.currentTimeMillis() / 1000);
        if (dept.getParentDeptId() == 0L) {
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.OK);
        }

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        Assert.notNull(parentDept, "Parent dept not found");

        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(value = "id") long id) {
        deptService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Dept> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Dept dept) {
        Dept savedDept = deptService.findOne(id);

        Assert.notNull(savedDept, "Dept not found");
        Assert.notNull(dept.getEnabled(), "Field enabled is empty.");

        savedDept.setEnabled(dept.getEnabled());
        savedDept.setModifiedTime(System.currentTimeMillis() / 1000);
        return new ResponseEntity<>(deptService.save(savedDept), HttpStatus.OK);
    }
}
