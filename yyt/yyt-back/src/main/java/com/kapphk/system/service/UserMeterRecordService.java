package com.kapphk.system.service;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserMeterRecord;
import com.kapphk.yyt.bean.UserNumber;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by shy on 2016/12/8.
 */


public interface UserMeterRecordService extends BaseService<UserMeterRecord,Long> {
    PageInfo<Map<String,Object>> queryByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    Result updateData(UserMeterRecord userMeterRecord, Result result);

    /**
     * 批量更新审核状态
     * @param ids
     * @param result
     * @return
     */
    Result updateData(String ids,Result result);

    /*
    *
    *  调整当前表字
    *
    * */
    Result adjustMeter(Integer addMeter, UserMeterRecord userMeterRecord, Result result);

    Result insertNewRecord(UserMeterRecord userMeterRecord, Result result);

    /**
     * 录入抄表信息
     * @param userMeterRecord
     * @param result
     * @return
     */
    Result importMeterRecord(UserMeterRecord userMeterRecord, Result result);


    PageInfo<Map<String,Object>> queryAuditMeterRecordsByPage(Map<String, Object> param, Integer page, Integer rows, String sort);

    /**
     * 导入Excel
     * @param file
     * @param rs
     * @return
     */
    Result saveImport(Map<String,Object> param ,MultipartFile file, Result rs) throws Exception;
}
