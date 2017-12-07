package com.kapphk.system.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
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
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.CompanyService;
import com.kapphk.system.service.SystemUserService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.bean.Company;
/**
 * 公司控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private SystemUserService userService;
  
    /**
     * 公司信息列表
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/com/list";
    }

    /**
     * 获取公司列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(Company company,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
	    	Long companyId = systemUser.getCompanyId();
	    	if(companyId == 1){
	    		companyId = systemUser.getId();
	    	}
	    	company.setId(companyId);
        	PageInfo<Map<String,Object>> pageInfo = companyService.queryCompanyByPage(company, page, rows, sort);
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
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	SystemUser user =  userService.findById(id);
        	Company company  = companyService.findById(id);
        	model.addAttribute("user", user);
        	model.addAttribute("company", company);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/com/edit";
    }
    
    /**
     * 保存公司信息
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(Company company){
    	Result result = new Result();
        try {
        	companyService.update(company);
        	
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }
    
    /**
     * 上传证书文件
     * @param request
     * @param response
     * @param file
     * @return
     */
	@RequestMapping(value="/upload")
	@ResponseBody
	public Object uploadGuideImg(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="file")MultipartFile file){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String url = FileUploadUtils.uploadFile(file, "cert", request);
			result.put("status", 0);
			result.put("url", url);
		} catch (Exception e) {
			result.put("status", 1);
			e.printStackTrace();
		}
		return result;
	}

}
