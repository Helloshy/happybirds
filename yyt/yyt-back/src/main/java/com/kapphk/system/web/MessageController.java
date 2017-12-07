package com.kapphk.system.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kapphk.anotation.SystemLog;
import com.kapphk.base.persistence.BaseController;
import com.kapphk.system.bean.Message;
import com.kapphk.system.bean.Resource;
import com.kapphk.system.service.MessageService;
import com.kapphk.web.utils.Common;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;
/**
 * 消息控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{

    @Autowired
    private MessageService  messageService;

    /**
     * 消息列表页面
     * @param model
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(Model model){
        List<Resource> findRes = findRes();
        model.addAttribute("res", findRes);
        return Common.BACKGROUND_PATH + "/company/message/list";
    }

    /**
     * 获取消息列表
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public @ResponseBody Map<String,Object> list(Message message,
            @RequestParam(defaultValue="1")Integer page,
            @RequestParam(defaultValue="20")Integer rows,
            @RequestParam(defaultValue="create_time desc")String sort){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        	PageInfo<Message> pageInfo = messageService.findByPage(message, page, rows, sort);
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
     * 跳转到新增页面
     * @return
     */
    @RequestMapping(value="/addPage",method=RequestMethod.GET)
    public String addPage(){
        return Common.BACKGROUND_PATH + "/company/message/add";
    }


    /**
     * 跳转到编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model,Long id){
        try {
        	Message message  = messageService.findById(id);
        	model.addAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.BACKGROUND_PATH + "/company/message/edit";
    }
    
    /**
     * 保存消息信息
     * @param message
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody Result save(Message message){
    	Result result = new Result();
        try {
        	if(!ValidateUtils.isBlank(message.getId())){
        		messageService.update(message);
        	}else{
        		message.setCreateTime(new Date());
        		messageService.insert(message);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(MSG.ERROR.getMsg());
            result.setStatus(Contents.ERROR);
        }
        return result;
    }

    /**
     * 批量删除消息
     * @param ids
     * @return
     */
    @SystemLog(description="消息-删除")
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody  Result delete(String ids){
        Result result = new Result();
        try {
        	messageService.deletes(DataUtils.string2List(ids));
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(Contents.ERROR);
            result.setMsg("系统错误");
        }
        return result;
    }
}
