package cheanxin.service;

import cheanxin.domain.DeptCity;
import cheanxin.domain.User;

import java.util.List;
import java.util.Set;

/**
 * 部门城市service
 * Created by Jawinton on 16/12/21.
 */
public interface DeptCityService {
    /**
     * 批量为部门添加城市
     * @param unsavedDeptCity
     * @return
     */
    List<DeptCity> save(DeptCity unsavedDeptCity);

    /**
     * 查询部门所有的城市列表
     * @param deptId
     * @return
     */
    List<DeptCity> list(long deptId);

    /**
     * 查询部门城市列表
     * @param deptId
     * @param enabled
     * @return
     */
    List<DeptCity> list(long deptId, boolean enabled);

    /**
     * 获取用户管理的城市id列表
     * @param user
     * @return null if empty.
     */
    Set<Long> listUserCityIds(User user);
}
