package cheanxin.service.impl;

import cheanxin.data.DeptCityRepository;
import cheanxin.domain.DeptCity;
import cheanxin.domain.User;
import cheanxin.service.DeptCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 16/12/21.
 */
@Service
public class DeptCityServiceImpl implements DeptCityService {
    @Autowired
    DeptCityRepository deptCityRepository;

    @Transactional
    @Override
    public List<DeptCity> save(DeptCity unsavedDeptCity) {
        Set<Long> cityIds = unsavedDeptCity.getCityIds();
        List<DeptCity> enabledDeptCityList = list(unsavedDeptCity.getDeptId(), true);
        List<DeptCity> disabledDeptCityList = list(unsavedDeptCity.getDeptId(), false);
        List<DeptCity> deptCities = new ArrayList<>();

        for (DeptCity deptCity : enabledDeptCityList) {
            // disabled post
            if (!cityIds.contains(deptCity.getCityId())) {
                deptCity.setEnabled(false);
                deptCities.add(deptCity);
            }
        }

        for (DeptCity deptCity : disabledDeptCityList) {
            // enabled post
            if (cityIds.contains(deptCity.getCityId())) {
                deptCity.setEnabled(true);
                deptCities.add(deptCity);
            }
        }

        // add not exists user post.
        for (Long cityId : cityIds) {
            if (cityId == null) {
                continue;
            }
            DeptCity deptCity = new DeptCity();
            deptCity.setDeptId(unsavedDeptCity.getDeptId());
            deptCity.setCityId(cityId);
            deptCity.setCreatedTime(unsavedDeptCity.getCreatedTime());
            deptCity.setCreatorUsername(unsavedDeptCity.getCreatorUsername());
            if (!enabledDeptCityList.contains(deptCity) && !deptCities.contains(deptCity)) {
                deptCity.setEnabled(true);
                deptCities.add(deptCity);
            }
        }

        if (deptCities.isEmpty()) {
            return deptCities;
        }
        return deptCityRepository.save(deptCities);
    }

    @Override
    public List<DeptCity> list(long deptId) {
        return deptCityRepository.findAllByDeptId(deptId);
    }

    @Override
    public List<DeptCity> list(long deptId, boolean enabled) {
        return deptCityRepository.findAllByDeptIdAndEnabled(deptId, enabled);
    }

    @Override
    public Set<Long> listUserCityIds(User user) {
        Assert.notNull(user.getDeptId(), "User dept id is null.");
        List<DeptCity> deptCityList = list(user.getDeptId(), true);
        if (deptCityList == null || deptCityList.isEmpty()) {
            return null;
        }
        Set<Long> cityIds = new HashSet<>(deptCityList.size());
        for (DeptCity deptCity : deptCityList) {
            cityIds.add(deptCity.getCityId());
        }
        return cityIds;
    }
}
