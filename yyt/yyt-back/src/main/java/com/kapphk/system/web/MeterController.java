package com.kapphk.system.web;

import com.github.pagehelper.PageInfo;
import com.kapphk.anotation.SystemLog;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.UserMeterRecordService;
import com.kapphk.web.utils.*;
import com.kapphk.yyt.bean.Community;
import com.kapphk.yyt.bean.Company;
import com.kapphk.yyt.bean.UserMeterRecord;
import com.kapphk.yyt.bean.UserNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**抄表管理控制器
 * Created by shy on 2016/12/8.
 */

@Controller
@RequestMapping("meter")
public class MeterController extends BaseController{

    @Autowired
    private UserMeterRecordService userMeterRecordService;

    /**
     * 跳转到抄表记录列表
     * @param model
     * @return
     */
    @RequestMapping("/record/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/meter/record/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/record/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryByPage(param,page, rows, sort);
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
     * 跳转到抄表记录列表
     * @param model
     * @return
     */
    @RequestMapping("/import/listPage")
    public String listPage2Import(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/meter/import/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/import/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2Import(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryByPage(param,page, rows, sort);
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
     * @return*/

    @RequestMapping(value="/import/editPage",method = RequestMethod.GET)
    public String editPage(Model model,String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id",id);
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                model.addAttribute("resultMap",resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mgs", Result.MSG.ERROR);
        }
        return Common.BACKGROUND_PATH + "/b2b/meter/import/edit";
    }

     /*
     * 抄表录入
     * @param
     * @return*/

    @RequestMapping(value="/import/save")
    public @ResponseBody Object save(@RequestParam("unid")Long unid,
                                     @RequestParam(value ="recordId",required = false)Long recordId,
                                     @RequestParam("currentCount") Integer currentCount,
                                     @RequestParam("recordTime" ) String recordTime){
        Result result = new Result();
        try {
            UserMeterRecord userMeterRecord = new UserMeterRecord();
            userMeterRecord.setId(recordId);
            userMeterRecord.setCurrentCount(currentCount);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(recordTime);
            userMeterRecord.setRecordTime(date);
            userMeterRecord.setUnid(unid);

            if (null != userMeterRecord && null != userMeterRecord.getUnid()){
                SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
                userMeterRecord.setUpdateBy(systemUser.getId());
                result = userMeterRecordService.importMeterRecord(userMeterRecord,result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }


    /*============== adjust ==============*/

    /**
     * 跳转到抄表调整列表
     * @param model
     * @return
     */
    @RequestMapping("/adjust/listPage")
    public String listPage2Adjust(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/meter/adjust/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/adjust/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2Adjust(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryByPage(param,page, rows, sort);
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
     * @return*/

    @RequestMapping(value="/adjust/editPage",method = RequestMethod.GET)
    public String editPage2Adjust(Model model,String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id",id);
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryByPage(param,1,1,null);
            if(!ValidateUtils.isEmpty(pageInfo)&&null!=pageInfo.getList()&&pageInfo.getList().size()>0){
                Map<String,Object> resultMap = pageInfo.getList().get(0);
                model.addAttribute("resultMap",resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mgs", Result.MSG.ERROR);
        }
        return Common.BACKGROUND_PATH + "/b2b/meter/adjust/edit";
    }

     /*   *
     * 调整表字
     * @param
     * @return*/

    @RequestMapping(value="/adjust/update",method=RequestMethod.POST)
    public @ResponseBody Object updateData2Adjust(
           @RequestParam("id") Long id,
            @RequestParam("addMeter") Integer addMeter){
        Result result = new Result();
        try {
            if (null != id && id>0 && null != addMeter ){
                UserMeterRecord userMeterRecord = new UserMeterRecord();
                userMeterRecord.setId(id);
                SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
                userMeterRecord.setUpdateBy(systemUser.getId());
                result =  userMeterRecordService.adjustMeter(addMeter,userMeterRecord,result);
            }else {
                result.setError(Result.MSG.PARAMS_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }


     /*============== audit ==============*/


    /**
     * 跳转到审核记录列表
     * @param model
     * @return
     */
    @RequestMapping("/audit/listPage")
    public String listPage2Audit(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/meter/audit/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/audit/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2Audit(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryAuditMeterRecordsByPage(param,page, rows, sort);
            map.put("rows", pageInfo.getList());
            map.put("page", page);
            map.put("records", pageInfo.getTotal());
            map.put("total", pageInfo.getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


     /*
     * 更新审核状态
     * @param
     * @return*/

    @RequestMapping(value="/audit/update",method=RequestMethod.POST)
    public @ResponseBody Object updateData2Audit(@RequestParam("ids") String ids){
        Result result = new Result();
        try {
            result = userMeterRecordService.updateData(ids,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }

    /*============== batchImport ==============*/


    /**
     * 跳转到导入页面
     * @param model
     * @return
     */
    @RequestMapping("/batchImport/listPage")
    public String listPage2BatchImport(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/b2b/meter/batchImport/list";
    }

    /**
     * 获取列表
     * @param queryParam 查询参数 客户编号/姓名/地址
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/batchImport/list",method= RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> list2BatchImport(
            @RequestParam(required=false)String queryParam,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="update_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("queryParam", ValidateUtils.isBlank(queryParam)? null :queryParam);
        try {
            //查询用户所在公司的客户信息
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("companyId",systemUser.getCompanyId());
            PageInfo<Map<String,Object>> pageInfo = userMeterRecordService.queryAuditMeterRecordsByPage(param,page,rows,sort);
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
     * 下载模板
     */
    @RequestMapping("/batchImport/download")
    public void downloadTemplet(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        InputStream in =null;
        OutputStream os =null;
        try {
            String fileName = "抄表录入模板";
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头
            response.setContentType("application/x-download");
            String path = request.getSession().getServletContext().getRealPath(Common.TEMPLET_EXCEL_PATH);
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            in = new FileInputStream(path);
            int len = -1;
            byte [] buffer = new byte[1024];
             os = response.getOutputStream();
            while ((len=in.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
            os.flush();
            in.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("出现错误");
        }
    }




    /**
     *
     * 导入Excel
     *
     *
     */
    @RequestMapping("/batchImport/saveImport")
    public Result saveImport(MultipartFile file){
        Result rs = new Result();
        Map<String,Object> param = new HashMap<String,Object>();
        try {
            SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
            param.put("operatorId",systemUser.getId());
            param.put("companyId",systemUser.getCompanyId());
            rs =  userMeterRecordService.saveImport(param,file,rs);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(Contents.ERROR);
            rs.setMsg("出现错误");
        }
        return rs;
    }

}
