package cheanxin.service.impl;

import cheanxin.data.DeptRepository;
import cheanxin.domain.Dept;
import cheanxin.domain.DeptCity;
import cheanxin.service.DeptCityService;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptRepository deptRepository;

    @Autowired
    DeptCityService deptCityService;

    @Override
    public Dept save(Dept unsavedDept, Dept parentDept) {
        if (unsavedDept == null) {
            return null;
        }
        Long parentDeptId = 0L;
        if (parentDept != null) {
            if (parentDept.getId() != null) {
                parentDeptId = parentDept.getId();
            }
            if (parentDept.getLevel() != null) {
                unsavedDept.setLevel(parentDept.getLevel() + 1);
            }
        }

        unsavedDept.setParentDeptId(parentDeptId);
        if (parentDeptId == 0L) {
            unsavedDept.setLevel(1);
        }

        return deptRepository.save(unsavedDept);
    }

    @Override
    public List<Dept> list(int level, boolean enabled) {
        return deptRepository.findAllByLevelAndEnabled(level, enabled);
    }

    @Override
    public List<Dept> list(long parentDeptId, boolean enabled) {
        return deptRepository.findAllByParentDeptIdAndEnabled(parentDeptId, enabled);
    }

    @Override
    public List<Dept> list(boolean enabled) {
        return deptRepository.findAllByEnabled(enabled);
    }

    @Override
    public List<Dept> list(Collection<Long> deptIds) {
        if (deptIds == null || deptIds.isEmpty()) {
            return new ArrayList<>();
        }
        return deptRepository.findAllByIdIn(deptIds);
    }

    @Override
    public Dept save(Dept unsavedDept) {
        return save(unsavedDept, null);
    }

    @Override
    public void remove(long id) {
        deptRepository.delete(id);
    }

    @Override
    public Dept findOne(long id) {
        Dept dept = deptRepository.findOne(id);
        if (dept == null) {
            return null;
        }
        if (!dept.getEnabled()) {
            return null;
        }
        // dept city list.
        List<DeptCity> deptCityList = deptCityService.list(dept.getId(), true);
        if (deptCityList != null) {
            Set<Long> cityIds = new HashSet<>();
            for (DeptCity deptCity : deptCityList) {
                if (deptCity == null) {
                    continue;
                }
                cityIds.add(deptCity.getCityId());
            }
            dept.setCityIds(cityIds);
        }

        return dept;
    }

    @Override
    public boolean isExists(long id) {
        return findOne(id) != null;
    }
}
