package com.kapphk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.mapper.SystemUserMapper;
import com.kapphk.system.service.UserMeterRecordService;
import com.kapphk.web.utils.*;
import com.kapphk.yyt.bean.*;
import com.kapphk.yyt.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**抄表管理相关业务
 * Created by shy on 2016/12/8.
 */

@Service
public class UserMeterRecordServiceImpl extends BaseServiceImpl<UserMeterRecord,Long> implements UserMeterRecordService{

    private static final Integer COUNT_AMOUNT = 1;
    private static final Integer COUNT_BALANCE_AMOUNT = 2;

    @Autowired
    private UserMeterRecordMapper mapper;
    @Autowired
    private CompanyPriceMapper companyPriceMapper;
    @Autowired
    private UserNumberMapper userNumberMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private UserNumberMapMapper userNumberMapMapper;

    @Override
    public void init() {
        super.setMapper(this.mapper);
    }

    @Override
    public PageInfo<Map<String, Object>> queryByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {
        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
       // List<Map<String,Object>> resultMaps = mapper.findMeterRecordsByParam(param);
       List<Map<String,Object>> resultMaps = mapper.findMeterRecordsByQueryParam(param);
        return new PageInfo<>(resultMaps);
    }

    @Override
    public Result updateData(UserMeterRecord userMeterRecord, Result result) {
        if (null == userMeterRecord || null == userMeterRecord.getId()){
            result.setError(Result.MSG.PARAMS_ERROR);
            return result;
        }else {
            Integer updateCount = mapper.updateData(userMeterRecord);
            if (null == updateCount){
                throw new RuntimeException("更新失败");
            }
        }
        return result;
    }

    @Override
    public Result adjustMeter(Integer addMeter, UserMeterRecord userMeterRecord, Result result) {
        if (null == addMeter  || null ==userMeterRecord
                || null ==userMeterRecord.getId()
                || userMeterRecord.getId()<0){
            return result.setError(Result.MSG.PARAMS_ERROR);
        }
        UserMeterRecord userMeterRecordByQuery = mapper.findById(userMeterRecord.getId());
        if (null != userMeterRecordByQuery){
            userMeterRecord.setCurrentCount(userMeterRecordByQuery.getCurrentCount()+addMeter);
            BigDecimal newAmount = countPrice(userMeterRecordByQuery.getAmount(),addMeter,userMeterRecordByQuery.getPrice(),COUNT_AMOUNT);
            userMeterRecord.setAmount(newAmount);
            BigDecimal newBalanceAmount = countPrice(userMeterRecordByQuery.getBalanceAmount(),addMeter,userMeterRecordByQuery.getPrice(),COUNT_BALANCE_AMOUNT);
            userMeterRecord.setBalanceAmount(newBalanceAmount);
            userMeterRecord.setUpdateTime(new Date());
            Integer isUpdate = mapper.updateMeter(userMeterRecord);
            if (isUpdate < 0){
                throw new RuntimeException("更新失败");
            }
        }

        return result;
    }

    /*计算价格*/
    private static BigDecimal countPrice(BigDecimal oldAmount, Integer addMeter, BigDecimal price,Integer type) {
        BigDecimal count = new BigDecimal(addMeter.doubleValue());
        BigDecimal changeAmount = count.multiply(price);
        if (type ==COUNT_AMOUNT){
            //计算新的账户费用
            BigDecimal tempPrice  =  oldAmount.add(changeAmount);
            BigDecimal newPrice = tempPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
            return newPrice;
        }
        else if (type==COUNT_BALANCE_AMOUNT){
            //计算新的账户余额
            BigDecimal tempPrice  =  oldAmount.subtract(changeAmount);
            BigDecimal newPrice = tempPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
            return newPrice;
        }
        return null;
    }

   /* public static void main(String[] args) {
        BigDecimal result =  countPrice(new BigDecimal(-440),-10,new BigDecimal(12.23),COUNT_BALANCE_AMOUNT);
        System.out.println(result);
    }*/

    /**
     * 抄表录入
     * @param userMeterRecord
     * @param result
     * @return
     */
    @Override
    @Deprecated
    public Result insertNewRecord(UserMeterRecord userMeterRecord, Result result) {

        UserMeterRecord previousRecord = mapper.findById(userMeterRecord.getId());
        Integer useCount = userMeterRecord.getCurrentCount() - previousRecord.getCurrentCount();
        if( useCount<0){
            result.setError(Result.MSG.PARAMS_ERROR);
            return result;
        }
        previousRecord.setId(null);
        previousRecord.setUpdateBy(userMeterRecord.getUpdateBy());
        previousRecord.setUpdateTime(new Date());
        previousRecord.setCreateTime(new Date());
        //设置上期表字与当前表字
        previousRecord.setLastCount(previousRecord.getCurrentCount());
        previousRecord.setCurrentCount(userMeterRecord.getCurrentCount());
        //默认未审核
        previousRecord.setState(0);
        previousRecord.setRecordTime(userMeterRecord.getRecordTime());
        //查询账户
        UserNumber userNumber = userNumberMapper.findById(previousRecord.getUnid());
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("companyId",previousRecord.getCompanyId());
        param.put("recordTag",userNumber.getRecordTag());
        param.put("recordTime",userMeterRecord.getRecordTime());
        //查询单价
        CompanyPrice price = companyPriceMapper.findLastPrice(param);
        //计算账户余额和费用
        BigDecimal newAmount =  countPrice(previousRecord.getAmount(),useCount,price.getPrice(),COUNT_AMOUNT);
        previousRecord.setAmount(newAmount);
        previousRecord.setPrice(price.getPrice());
        BigDecimal newBalanceAmount =  countPrice(userNumber.getBalanceAmount(),useCount,price.getPrice(),COUNT_BALANCE_AMOUNT);
        previousRecord.setBalanceAmount(newBalanceAmount);
        mapper.insert(previousRecord);
        userNumber.setUpdateTime(new Date());
        userNumber.setUpdateBy(userMeterRecord.getUpdateBy());
        userNumberMapper.updateBlanceAmount(userNumber);

        return result;
    }

    /**
     * 抄表录入
     * @param userMeterRecord
     * @param result
     * @return
     */
    @Override
    public Result importMeterRecord(UserMeterRecord userMeterRecord, Result result) {
        Map<String, Object> param = new HashMap<>();
        param.put("recordId",userMeterRecord.getId());
        param.put("id",userMeterRecord.getUnid());
        param.put("recordTime",userMeterRecord.getRecordTime());
        param.put("companyId",userMeterRecord.getCompanyId());
        //查询上期记录
        List<Map<String,Object>> resultMaps = mapper.findMeterRecordsByQueryParam(param);
        if (null == resultMaps){
            result.setStatus(Contents.CUS_ERROR);
            result.setMsg("此用户未录用");
        }
        Map<String,Object> map = resultMaps.get(0);
        Integer useCount = (Integer)map.get("current_count") - userMeterRecord.getCurrentCount();
        if( useCount<0){
            result.setError(Result.MSG.PARAMS_ERROR);
            result.setMsg("当前表字小于上期表字");
            return result;
        }
        UserMeterRecord preInsertRecord = new UserMeterRecord();
        preInsertRecord.setUpdateBy(userMeterRecord.getUpdateBy());
        preInsertRecord.setUpdateTime(new Date());
        preInsertRecord.setCreateTime(new Date());

        //设置上期表字与当前表字
        preInsertRecord.setLastCount((Integer)map.get("current_count"));
        preInsertRecord.setCurrentCount(userMeterRecord.getCurrentCount());
        //默认未审核
        preInsertRecord.setState(0);
        preInsertRecord.setRecordTime(userMeterRecord.getRecordTime());

        BigDecimal price =(BigDecimal)map.get("price");
        BigDecimal oldAmount = (BigDecimal)map.get("amount");
        //计算账户余额和费用
        BigDecimal newAmount =  countPrice(oldAmount,useCount,price,COUNT_AMOUNT);
        preInsertRecord.setAmount(newAmount);
        preInsertRecord.setPrice(price);

        //查询账户
        UserNumber userNumber = userNumberMapper.findById(userMeterRecord.getUnid());
        BigDecimal newBalanceAmount =  countPrice(userNumber.getBalanceAmount(),useCount,price,COUNT_BALANCE_AMOUNT);
        preInsertRecord.setBalanceAmount(newBalanceAmount);
        mapper.insert(preInsertRecord);
        userNumber.setUpdateTime(new Date());
        userNumber.setUpdateBy(userMeterRecord.getUpdateBy());
        userNumberMapper.updateBlanceAmount(userNumber);

        return result;
    }

    /**
     * 批量更新审核状态
     * @param ids
     * @param result
     * @return
     */
    @Override
    public Result updateData(String ids, Result result) {


        mapper.updateMany(DataUtils.string2List(ids),1);
        return result;
    }

    @Override
    public PageInfo<Map<String, Object>> queryAuditMeterRecordsByPage(Map<String, Object> param, Integer page, Integer rows, String sort) {

        PageHelper.startPage(page,rows);
        if (!StringUtils.isBlank(sort)){
            PageHelper.orderBy(sort);
        }
        List<Map<String,Object>> resultMaps = mapper.findMeterAuditRecordsByParam(param);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(resultMaps);
        return pageInfo;
    }

    @Override
    public Result saveImport(Map<String,Object> param,MultipartFile file, Result rs) throws Exception{
        if (null == file){
            rs.setStatus(Contents.ERROR);
            rs.setMsg("文件为空");
            return rs;
        }
        //rs = importUserMeterRecord(param,file,rs);
        rs = importUserMeterRecords(param,file,rs);
        return rs;
    }

    /**合法返回后缀名，否则返回空字符串
     * 校验文件后缀是否合法
     * @param file  文件对象
     * @param partten 后缀匹配规则
     * @return
     */
    private static String checkSuffix(MultipartFile file, String partten) {
        if (null != file &&StringUtils.isNotBlank(partten)){
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
            Boolean isMatch = suffix.matches(partten);
            if (isMatch){
                return suffix;
            }
        }
        return "";

    }

    /**
     *  解析Excel
     * @param file
     * @param rs
     * @return
     */
    @Deprecated
    private  Result importUserMeterRecord(Map<String,Object> param,MultipartFile file,Result rs) throws Exception{
        if (null != file){
            //检查文件是否合法
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            Workbook workbook =null;
            Sheet sheet =null;
            Row row =null;
            String value = null;
            Date date = new Date();

            //查询所有的燃气用户
            UserNumber  userNumber = new UserNumber();
            userNumber.setCompanyId((Long) param.get("companyId"));
            List<Map<String,Object>> userNumbers = userNumberMapper.findAllByCompanyId(param);
            Map<String,Map<String,Object>> userNumbersMap = new HashMap<>();
            for (Map<String,Object> map: userNumbers) {
                userNumbersMap.put(""+map.get("id"),map);
            }

            //查询出所有的公司
            List<Company> companys = companyMapper.findAll(new Company());
            Map<String,Company> companysMap = new HashMap<>();
            for (Company c: companys) {
                companysMap.put(""+c.getId(),c);
            }

            //查询所有的系统用户(操作人)
           /* SystemUser su = new SystemUser();
            su.setCompanyId((Long) param.get("companyId"));
            su.setState(1);
            List<SystemUser> systemUsers = systemUserMapper.findAll(su);*/


            List<UserMeterRecord> preInsertList = new ArrayList<>();

            if (fileType.equals("xls")){
                workbook = new HSSFWorkbook(file.getInputStream());
                sheet = workbook.getSheetAt(0);
            }else if(fileType.equals("xlsx")){
                workbook = new XSSFWorkbook(file.getInputStream());
                sheet = workbook.getSheetAt(0);
            }else {
            rs.setStatus(Contents.ERROR);
            rs.setMsg("文件格式不对,只支持.xls或者.xlsx后缀的文件");
            return rs ;
            }
            UserNumber userNumber1 = new UserNumber();
            //循环验证每行每列数据是否合法
            for (int i = 1;i<=sheet.getLastRowNum();i++){
                row = sheet.getRow(i);
                if (null==row){
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行数据为空");
                    return rs;
                }
                UserMeterRecord umr = null;
                Map<String,Object> tempMap = null;
                // 用户燃气编号
                try {
                    value = ValidateUtils.isBlank(row.getCell(1)) ? null : row.getCell(1).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(1).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //验证用户燃气编号是否存在
                if (!ValidateUtils.isBlank(value)) {
                    if(ValidateUtils.isBlank(userNumbersMap.get(value))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行燃气编号有误");
                        return rs;
                    }
                    if (ValidateUtils.isBlank(umr)){
                        //设置用户燃气id
                        umr = new UserMeterRecord();
                        tempMap = userNumbersMap.get(value);
                        umr.setUnid((Long) tempMap.get("id"));
                    }

                }else {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行燃气编号有误");
                    return rs;
                }


                //用户公司名
                try {
                    value = ValidateUtils.isBlank(row.getCell(0)) ? null : row.getCell(0).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(0).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行公司名有误");
                        return rs;
                    }
                }
                if (!ValidateUtils.isBlank(value)){
                    //验证公司名称
                    if (!companysMap.get(tempMap.get("companyId")).getCompanyName().equals(value)){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行公司名有误");
                        return rs;
                    }
                    umr.setCompanyId((Long) tempMap.get("companyId"));
                }else{
                    umr.setCompanyId((Long) tempMap.get("companyId"));
                }

                //验证用户名
                try {
                    value = ValidateUtils.isBlank(row.getCell(2)) ? null : row.getCell(2).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(2).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!ValidateUtils.isBlank(value)){
                    if (!tempMap.get("real_name").equals(value)){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行用户名有误");
                        return rs;
                    }
                    //设置用户id
                    umr.setUid((Long) tempMap.get("uid"));
                }else {
                    umr.setUid((Long) tempMap.get("uid"));
                }

                //上期表字
                try {
                    if (ValidateUtils.isBlank(row.getCell(3))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"上期表字未填");
                        return rs;
                    }
                    Integer lastCount =null;
                    lastCount = (int) row.getCell(3).getNumericCellValue();
                    if (lastCount<0){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"上期表字应为大于等于零的纯数字");
                        return rs;
                    }
                    else{
                        umr.setLastCount(lastCount);
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"上期表字应为大于等于零的纯数字");
                    return rs;
                }

                //当期表字
                try {
                    if (ValidateUtils.isBlank(row.getCell(4))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"当期表字未填");
                        return rs;
                    }
                    Integer currentCount =null;
                    currentCount = (int) row.getCell(4).getNumericCellValue();
                    if (currentCount<0||currentCount-umr.getLastCount()<0){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"当期表字应为大于上期表字");
                        return rs;
                    }
                    else{
                        umr.setCurrentCount(currentCount);
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行当期表字应为大于等于零的纯数字");
                    return rs;
                }

                //用气量
                Integer gasCount =null;
                try {
                    if (ValidateUtils.isBlank(row.getCell(5))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行用气量未填");
                        return rs;
                    }
                    if (gasCount<0||gasCount!=umr.getCurrentCount()-umr.getLastCount()){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行用气量有误");
                        return rs;
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行用气量有误");
                    return rs;
                }
                //单价
                BigDecimal price = null;
                try {
                    if (ValidateUtils.isBlank(row.getCell(6))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行单价未填");
                        return rs;
                    }
                    price = new BigDecimal(row.getCell(6).getNumericCellValue());
                    if (price.compareTo(new BigDecimal(0))<0){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行单价不能为负");
                        return rs;
                    }
                    else{
                        umr.setPrice(price);
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行用气量有误");
                    return rs;
                }

                //费用
                try {
                    if (ValidateUtils.isBlank(row.getCell(7))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行费用未填");
                        return rs;
                    }
                    BigDecimal amount = null;
                    amount = new BigDecimal(row.getCell(7).getNumericCellValue());
                    if (amount.compareTo(new BigDecimal(0))<0){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行费用有误");
                        return rs;
                    }
                    else{
                        umr.setAmount(amount);
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行费用类型有误");
                    return rs;
                }
                //抄表员
                try {
                    value = ValidateUtils.isBlank(row.getCell(8)) ? null : row.getCell(8).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(8).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!ValidateUtils.isBlank(value)){
                    if (tempMap.get("sname").equals(value)){
                        umr.setSid((Long) tempMap.get("sid"));
                    }else{
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"抄表员有误");
                        return rs;
                    }

                }else {
                    umr.setSid((Long) tempMap.get("sid"));
                }



                //抄表时间
                try {
                    Date date2 = row.getCell(9).getDateCellValue();
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = dff.format(date2);
                } catch (Exception e) {
                    value = ValidateUtils.isBlank(row.getCell(2)) ? null : row.getCell(2).getStringCellValue();
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					/*SimpleDateFormat dff1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat dff2 = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
					SimpleDateFormat dff3 = new SimpleDateFormat("yyyy/MM/dd");*/
                    try {
                        if(!ValidateUtils.isBlank(value))dff.parse(value);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第" + (i + 1) + "行的抄表日期格式有误");
                        return rs;
                    }
                }
                if (!ValidateUtils.isBlank(value)) {
                    umr.setRecordTime(DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",value));
                }

                //审核状态
                try {
                    value = ValidateUtils.isBlank(row.getCell(10)) ? null : row.getCell(10).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(10).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行审核状态有误");
                        return rs;
                    }
                }
                if (!ValidateUtils.isBlank(value)){
                    if ("已审核".equals(value)){
                        umr.setState(1);
                    }else if ("未审核".equals(value)){
                        umr.setState(0);
                    }else{
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行审核状态只能是:已审核/未审核");
                        return rs;
                    }

                }

                //操作人
                /*try {
                    value = ValidateUtils.isBlank(row.getCell(11)) ? null : row.getCell(11).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(11).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行审核状态有误");
                        return rs;
                    }
                }*/

                //设置其他属性
                umr.setUpdateBy((Long) param.get("operatorId"));
                umr.setCreateTime(new Date());
                umr.setUpdateTime(new Date());
                umr.setRemark("empty");
                BigDecimal newBalanceAmount =  countPrice((BigDecimal) tempMap.get("balance_amount"),gasCount,price,COUNT_BALANCE_AMOUNT);
                umr.setBalanceAmount(newBalanceAmount);
                preInsertList.add(umr);

                userNumber1.setBalanceAmount(newBalanceAmount);
                userNumber1.setUpdateTime(new Date());
                userNumber1.setUpdateBy((Long) param.get("operatorId"));
                userNumber1.setId((Long) tempMap.get("id"));
                userNumberMapper.updateBlanceAmount(userNumber1);
            }
            mapper.inserts(preInsertList);
            //批量更新账户余额 等待完成

        }else {
            rs.setStatus(Contents.ERROR);
            rs.setMsg("导入文件出错");
        }
        return rs;
    }

    private  Result importUserMeterRecords(Map<String,Object> param,MultipartFile file,Result rs) throws Exception{
        if (null != file){
            //检查文件是否合法
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            Workbook workbook =null;
            Sheet sheet =null;
            Row row =null;
            String value = null;
            //查询所有的燃气用户
            UserNumber  userNumber = new UserNumber();
            userNumber.setCompanyId((Long) param.get("companyId"));
            List<Map<String,Object>> resultMaps = mapper.findMeterRecordsByQueryParam(param);

            Map<String,Map<String,Object>> userNumbersMap = new HashMap<>();
            for (Map<String,Object> map: resultMaps) {
                userNumbersMap.put(""+map.get("id"),map);
            }

            List<UserMeterRecord> preInsertList = new ArrayList<>();
            switch (fileType){
                case "xls":
                    workbook = new HSSFWorkbook(file.getInputStream());
                    sheet = workbook.getSheetAt(0);
                    break;
                case "xlsx":
                    workbook = new XSSFWorkbook(file.getInputStream());
                    sheet = workbook.getSheetAt(0);
                    break;
                default:
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("文件格式不对,只支持.xls或者.xlsx后缀的文件");
                    return rs ;
            }
            UserNumber userNumber1 = new UserNumber();
            //循环验证每行每列数据是否合法
            for (int i = 1;i<=sheet.getLastRowNum();i++){
                row = sheet.getRow(i);
                if (null==row){
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行数据为空");
                    return rs;
                }
                UserMeterRecord umr = null;
                Map<String,Object> tempMap = null;
                // 用户燃气编号
                try {
                    value = ValidateUtils.isBlank(row.getCell(0)) ? null : row.getCell(0).getStringCellValue();
                } catch (Exception e1) {
                    try {
                        value = String.valueOf((long)row.getCell(0).getNumericCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //验证用户燃气编号是否存在
                if (!ValidateUtils.isBlank(value)) {
                    if(ValidateUtils.isBlank(userNumbersMap.get(value))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"行燃气编号有误");
                        return rs;
                    }
                    if (ValidateUtils.isBlank(umr)){
                        //设置用户燃气id
                        umr = new UserMeterRecord();
                        tempMap = userNumbersMap.get(value);
                        umr.setUnid((Long) tempMap.get("id"));
                    }

                }else {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行燃气编号有误");
                    return rs;
                }
                //当期表字
                Integer currentCount =null;
                try {
                    if (ValidateUtils.isBlank(row.getCell(1))){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"当期表字未填");
                        return rs;
                    }
                    currentCount = (int) row.getCell(1).getNumericCellValue();
                    if (currentCount<0||currentCount-(Integer) tempMap.get("current_count")<0){
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第"+(i+1)+"当期表字应为大于上期表字");
                        return rs;
                    }
                    else{
                        umr.setCurrentCount(currentCount);
                    }
                } catch (Exception e1) {
                    rs.setStatus(Contents.ERROR);
                    rs.setMsg("第"+(i+1)+"行当期表字应为大于等于零的纯数字");
                    return rs;
                }

                //抄表时间
                try {
                    Date date2 = row.getCell(2).getDateCellValue();
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = dff.format(date2);
                } catch (Exception e) {
                    value = ValidateUtils.isBlank(row.getCell(2)) ? null : row.getCell(2).getStringCellValue();
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					/*SimpleDateFormat dff1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat dff2 = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
					SimpleDateFormat dff3 = new SimpleDateFormat("yyyy/MM/dd");*/
                    try {
                        if(!ValidateUtils.isBlank(value))dff.parse(value);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        rs.setStatus(Contents.ERROR);
                        rs.setMsg("第" + (i + 1) + "行的抄表日期格式有误");
                        return rs;
                    }
                }
                if (!ValidateUtils.isBlank(value)) {
                    //设置抄表时间
                    umr.setRecordTime(DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",value));
                }
                //设置其他属性
                umr.setSid((Long) tempMap.get("sid"));
                //查询用户uid
                UserNumberMap unm = userNumberMapMapper.findUidByUnid(umr.getUnid());
                umr.setUid(unm.getUid());
                //待增加联表查询uid字段
                //umr.setUid((Long) tempMap.get("uid"));
                umr.setCompanyId((Long) tempMap.get("company_id"));
                umr.setLastCount((Integer) tempMap.get("current_count"));
                umr.setUpdateBy((Long) param.get("operatorId"));
                umr.setCreateTime(new Date());
                umr.setUpdateTime(new Date());
                umr.setRemark("empty");

                Map<String,Object> params= new HashMap<String,Object>();
                params.put("companyId",tempMap.get("company_id"));
                params.put("recordTag",tempMap.get("record_tag"));
                params.put("recordTime",umr.getRecordTime());
                //查询单价
                CompanyPrice price = companyPriceMapper.findLastPrice(params);
                umr.setPrice(price.getPrice());
                //计算费用以及账户余额
                BigDecimal newAmount = countPrice((BigDecimal) tempMap.get("amount"),currentCount-(Integer) tempMap.get("current_count"),price.getPrice(),COUNT_AMOUNT);
                umr.setAmount(newAmount);
                BigDecimal newBalanceAmount =  countPrice((BigDecimal) tempMap.get("balance_amount"),currentCount-(Integer) tempMap.get("current_count"),price.getPrice(),COUNT_BALANCE_AMOUNT);
                umr.setBalanceAmount(newBalanceAmount);
                preInsertList.add(umr);

                //设置并更新燃气账户
                userNumber1.setBalanceAmount(newBalanceAmount);
                userNumber1.setUpdateTime(new Date());
                userNumber1.setUpdateBy((Long) param.get("operatorId"));
                userNumber1.setId((Long) tempMap.get("id"));
                userNumberMapper.updateBlanceAmount(userNumber1);
            }
            mapper.inserts(preInsertList);
            //批量更新账户余额 等待完成

        }else {
            rs.setStatus(Contents.ERROR);
            rs.setMsg("导入文件出错");
        }
        return rs;
    }


}
