package com.airparking.apms.api.groupMonrent.controller;

import com.airparking.apms.api.groupMonrent.entity.GroupMonrent;
import com.airparking.apms.api.groupMonrent.entity.GroupMonrentDetails;
import com.airparking.apms.api.groupMonrent.service.GroupMonrentDetailsService;
import com.airparking.apms.api.groupMonrent.service.GroupMorentService;
import com.airparking.apms.comm.DateFormats;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceException;
import com.airparking.apms.server.ServiceRequest;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.apms.server.handler.AbstractController;
import com.airparking.core.anno.Authorize;
import com.airparking.core.anno.Path;
import com.airparking.core.anno.RequiredParams;
import com.airparking.core.comm.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Controller("groupMonrent")
public class GroupMorentController extends AbstractController {
    @Autowired
    private GroupMorentService groupMorentService;
    @Autowired
    private GroupMonrentDetailsService groupMonrentDetailsService;

    @RequiredParams(params = {"data"})
    @Path("save")
    @Authorize(login = false)
    public ServiceResponse post(ServiceRequest request) {
        String data = request.getString("data");
        Long parkId = request.getLong("parkId");
        try {
            List<Map<String, Object>> groupMonrentList = JsonUtils.toObject(data, List.class);
            if (groupMonrentList!= null && groupMonrentList.size()>0) {
                for (Map<String, Object> map : groupMonrentList) {
                    boolean isNew = false;
                    String gmrid = (String) map.get("gmrid");
                    GroupMonrent groupMonrent = groupMorentService.findByGmrid(gmrid);
                    if (groupMonrent == null){
                        isNew = true;
                        groupMonrent = new GroupMonrent();
                    }
                    groupMonrent.setParkId(parkId);
                    groupMonrent.setGmrid(gmrid);
                    groupMonrent.setName((String) map.get("name"));
                    String statusStr = (String) map.get("status");
                    Byte status = null;
                    if (StringUtils.isNotBlank(statusStr)){
                        if ("到期".equals(statusStr)){
                            status = 1;
                        }else if ("停用".equals(statusStr)){
                            status = 2;
                        }else {
                            status = 0;
                        }
                    }
                    groupMonrent.setStatus(status);
                    groupMonrent.setDeleted(false);
                    groupMonrent.setCreatedDate(DateFormats.parseDate1((String)map.get("operateTime")));
                    groupMonrent.setUpdatedDate(groupMonrent.getCreatedDate());
                    groupMonrent.setCarNum((int)(map.get("carNum")));
                    groupMonrent.setSpaceNum((int)map.get("spaceNum"));
                    groupMonrent.setStartTime(DateFormats.parseDate1((String)map.get("startTime")));
                    groupMonrent.setEndTime(DateFormats.parseDate1((String)map.get("endTime")));
                    groupMonrent.setMobile((String)map.get("mobile"));
                    groupMonrent.setPlateNo((String)map.get("plateNo"));
                    if (isNew){
                        groupMorentService.save(groupMonrent);
                    }else {
                        groupMorentService.update(groupMonrent);
                        List<GroupMonrentDetails> list = groupMonrentDetailsService.findByGmId(groupMonrent.getId());
                        if (list != null && list.size()>0){
                            groupMonrentDetailsService.deleteDetailsByGmId(groupMonrent.getId());
                        }
                    }
                    List<Map<String, Object>> details = (List<Map<String,Object>>) map.get("details");
                    if (details != null && details.size()>0){
                        for (Map<String, Object> detailMap : details){
                            GroupMonrentDetails groupMonrentDetails = new GroupMonrentDetails();
                            groupMonrentDetails.setMrrid((String) detailMap.get("mrrid"));
                            groupMonrentDetails.setSpaceNo((String) detailMap.get("spaceNo"));
                            groupMonrentDetails.setGroupMonrentId(groupMonrent.getId());
                            groupMonrentDetailsService.save(groupMonrentDetails);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return new ServiceResponse(ResponseCode.SUCCESS);

    }
}
