package com.kapphk.web.service.web.imethod.teacher;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.bean.teacher.BeanTeacherArrange;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 动能讲师业务层
 * @author dengwen
 * 2016-10-08 10:38:46
 */
public interface BeanTeacherService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanTeacher bean, String district, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanTeacher bean,List<String> district,MultipartFile file,HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanTeacher bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 获取全部老师
	 * @author dengwen 
	 * 2016-10-8下午2:24:02
	 */
	public List<Map<String, Object>> searchTeacherList(Integer recordType,String type);

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-9上午9:45:52
	 * @param district 
	 * @param bean 
	 */
	public void exportExcel(BeanTeacher bean, String district, HttpServletResponse response);

	/**
	 * 保存预约时间
	 * @author dengwen 
	 * 2016-10-11上午11:39:15
	 */
	public Result saveTa(Result rs, BeanTeacherArrange bean) throws Exception;

	/**
	 * 获取预约时间详情
	 * @author dengwen 
	 * 2016-10-11下午2:03:06
	 */
	public Result arrangeDate(Result rs, BeanTeacherArrange bean) throws Exception;

}
