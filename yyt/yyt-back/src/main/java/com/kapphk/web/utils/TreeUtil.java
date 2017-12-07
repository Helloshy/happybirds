package com.kapphk.web.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kapphk.system.bean.Resource;


/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 *
 */
public class TreeUtil {
	
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param typeId 传入的父节点ID
	 * @return String
	 */
	public List<Resource> getChildResources(List<Resource> list,Long praentId) {
		List<Resource> returnList = new ArrayList<Resource>();
		for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext();) {
			Resource t = (Resource) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId()==praentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @author Exia
	 * @param list
	 * @param Resources
	 */
	private void  recursionFn(List<Resource> list, Resource t) {
		List<Resource> childList = getChildList(list, t);// 得到子节点列表
		t.setMenus(childList);
		for (Resource tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				//returnList.add(Resource);
				Iterator<Resource> it = childList.iterator();
				while (it.hasNext()) {
					Resource n = (Resource) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	// 得到子节点列表
	private List<Resource> getChildList(List<Resource> list, Resource t) {
		
		List<Resource> tlist = new ArrayList<Resource>();
		Iterator<Resource> it = list.iterator();
		while (it.hasNext()) {
			Resource n = (Resource) it.next();
			if (n.getParentId() == t.getId()) {
				tlist.add(n);
			}
		}
		return tlist;
	} 
	List<Resource> returnList = new ArrayList<Resource>();
	/**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Resource> getChildResources(List<Resource> list, int typeId,String prefix){
        if(list == null) return null;
        for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext();) {
            Resource node = (Resource) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId()==typeId) {
                recursionFn(list, node,prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }
     
    private void recursionFn(List<Resource> list, Resource node,String p) {
        List<Resource> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            Iterator<Resource> it = childList.iterator();
            while (it.hasNext()) {
                Resource n = (Resource) it.next();
                n.setResourceName(p+n.getResourceName());
                recursionFn(list, n,p+p);
            }
        } else {
            returnList.add(node);
        }
    }

	// 判断是否有子节点
	private boolean hasChild(List<Resource> list, Resource t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	// 本地模拟数据测试
	public void main(String[] args) {
		/*long start = System.currentTimeMillis();
		List<Resource> ResourceList = new ArrayList<Resource>();
		
		ResourceUtil mt = new ResourceUtil();
		List<Resource> ns=mt.getChildResources(ResourceList,0);
		for (Resource m : ns) {
			System.out.println(m.getName());
			System.out.println(m.getChildren());
		}
		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start) + "ms");*/
	}
	
}
