package cheanxin.service;

import cheanxin.domain.Dept;

import java.util.Collection;
import java.util.List;

/**
 * 部门service
 * Created by Jawinton on 16/12/21.
 */
public interface DeptService {
    /**
     * 添加部门
     * @param unsavedDept
     * @return
     */
    Dept save(Dept unsavedDept);

    /**
     * 添加部门，同时设定部门的上级部门
     * @param unsavedDept
     * @param parentDept
     * @return
     */
    Dept save(Dept unsavedDept, Dept parentDept);

    /**
     * 删除部门
     * @param id
     */
    void remove(long id);

    /**
     * 获取部门详情
     * @param id
     * @return
     */
    Dept findOne(long id);

    /**
     * 获取特定层级的所有启用/禁用的部门列表
     * @param level
     * @param enabled
     * @return
     */
    List<Dept> list(int level, boolean enabled);

    /**
     * 获取部门的所有启用/禁用子部门
     * @param parentDeptId
     * @param enabled
     * @return
     */
    List<Dept> list(long parentDeptId, boolean enabled);

    /**
     * 获取所有启用/禁用的部门列表
     * @param enabled
     * @return
     */
    List<Dept> list(boolean enabled);

    /**
     * 通过部门id列表获取部门详细信息列表
     * @param deptIds
     * @return
     */
    List<Dept> list(Collection<Long> deptIds);

    /**
     * 判断部门是否存在
     * @param id
     * @return
     */
    boolean isExists(long id);
}
