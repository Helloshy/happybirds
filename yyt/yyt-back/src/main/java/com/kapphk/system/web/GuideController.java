package com.kapphk.system.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Guide;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.service.GuideService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.PropertiesUtil;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.UploadUtil;

/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月22日 下午5:25:51 
*
*/
@Controller
@RequestMapping("/guide")
public class GuideController extends BaseController {

	@Autowired
	private GuideService guideService;
	/**
	 * 跳转到首页轮播图页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String guide(Model model){
		List<Resource> findRes = findRes();
		model.addAttribute("res", findRes);
		model.addAttribute("recordType", 1);
		return Common.BACKGROUND_PATH + "/company/guide/list";
	}
	
	
	
	/**
	 * 新增
	 * @param model
	 * @param recordType(轮播图类型：1为主页轮播图，2为资金页面轮播图)
	 * @return
	 */
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
	public String addPage(Model model,
			@RequestParam(value="recordType",defaultValue="1")Integer recordType){
		 model.addAttribute("recordType", recordType);
		 return Common.BACKGROUND_PATH + "/company/guide/add";
	}
	
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="editPage",method=RequestMethod.GET)
	public String editPage(Model model,Long id){
		try {
			Guide guide = guideService.findById(id);
			model.addAttribute("guide",guide);
			model.addAttribute("fileServer",PropertiesUtil.getProperty("fileServerURL"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.BACKGROUND_PATH + "/company/guide/edit";
	}
	
	
	/**
	 * 获取列表
	 * @param messagePush
	 * @param page
	 * @param rows
	 * @param sort
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> list(Guide guide,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="id desc")String sort){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			PageInfo<Guide> pageInfo = guideService.findByPage(guide, page, rows, sort);
			map.put("rows", pageInfo.getList());
			map.put("page", page);
			map.put("records", pageInfo.getTotal());
			map.put("total", pageInfo.getPages());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增或更新
	 * @param messagePush
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Result saveData(Guide guide){
		Result result = new Result();
		try {
			result = guideService.saveGuide(guide, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(String ids){
		Result result = new Result();
		try {
			result = guideService.delete(ids, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Contents.ERROR);
			result.setMsg("系统错误");
		}
		return result;
	}
	
	@RequestMapping(value="/upload")
	public @ResponseBody Map<String,Object> uploadImg(HttpServletRequest request,HttpServletResponse response,MultipartFile imgFile){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//String url = FileUploadUtils.uploadFile(imgFile.getBytes(), "guide", "2", request);
			String url = UploadUtil.uploadImage(imgFile);
			map.put("error", 0);
			map.put("url", url);
		} catch (Exception e) {
			map.put("error", 1);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 上传轮播图片
	 * @param request
	 * @param response
	 * @param imgFile 上传文件对象
	 * @return 
	 */
	@RequestMapping(value="/uploadGuideImg")
	@ResponseBody
	public Object uploadGuideImg(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="file")MultipartFile imgFile){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
//			String url = UploadUtil.uploadImage(imgFile);
			String url = FileUploadUtils.uploadFile(imgFile.getBytes(), "", "0", request);
			result.put("status", 0);
			result.put("url", url);
		} catch (Exception e) {
			result.put("status", 1);
			e.printStackTrace();
		}
		return result;
	}
}
