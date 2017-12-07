package com.kapphk.web.service.web.impl.user;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.user.BeanReportTrans;
import com.kapphk.web.dao.mapper.user.BeanReportTransMapper;
import com.kapphk.web.service.web.imethod.user.BeanReportTransService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class BeanReportTransServiceImpl implements BeanReportTransService {

	@Autowired
	private BeanReportTransMapper mapper;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String realName, String startTime,
			String endTime, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(realName,startTime,endTime,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(realName,startTime,endTime);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 导出
	 */
	@Override
	public void exportExcel(HttpServletResponse response) {
		List<Map<String,Object>> list = mapper.findList(null,null,null,0,9999999);
		String[] particular = new String[]{"月份","转出手机号","转出真实姓名","转出课程名称","转出金额","转入手机号","转入真实姓名","转入课程名称",
				"转入金额","转出部门经办人","转入部门经办人","财务审核人","上传时间"};
		String[] property = new String[]{"ym","fromPhone","fromName","fromCourse","fromAmount","toPhone","toName",
				"toCourse","toAmount","fromOperator","toOperator","approveWho","createTime"};
		System.out.println(ExcelUtils.downExcelList("转账单.xlsx", particular, property, list, response));
	}

	/**
	 * 导入
	 */
	@Override
	public Result importExcel(Result rs, MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		Workbook workbook = null;
		// 检查版本
		fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		boolean is03Excel = fileName.matches("^.xls$");
		workbook = is03Excel ? new HSSFWorkbook(file.getInputStream())
				: new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		String value = null;
		Float rate = null;
		Row row = null;
		String title = null;
		Date d = new Date();
		List<BeanReportTrans> list = new ArrayList<BeanReportTrans>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row != null){
				BeanReportTrans bean = null;
				try {
					value = ValidateUtils.isBlank(row.getCell(0)) ? null : row.getCell(0).getStringCellValue();
				} catch (Exception e) {
					try {
						value = String.valueOf((int)row.getCell(0).getNumericCellValue());
					} catch (Exception e1) {
						e1.printStackTrace();
						title = "第"+(i+1)+"行、第"+"1列格式有误!";
						break;
					}
				}
				if(!ValidateUtils.isBlank(value)){
					bean = new BeanReportTrans();
					bean.setYm(value);
				}else{
					title = "第"+(i+1)+"行、第"+"1列必填";
					break;
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(1)) ? null : row.getCell(1).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf((long)row.getCell(1).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"2列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setFromPhone(value);
					}else{
						title = "第"+(i+1)+"行、第"+"2列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(1)) ? null : row.getCell(2).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(2).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"3列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setFromName(value);
					}else{
						title = "第"+(i+1)+"行、第"+"3列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(3)) ? null : row.getCell(3).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(3).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"4列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setFromCourse(value);
					}else{
						title = "第"+(i+1)+"行、第"+"4列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						rate = ValidateUtils.isBlank(row.getCell(4)) ? null : (float) row.getCell(4).getNumericCellValue();
						
					} catch (Exception e) {
						try {
							rate = Float.valueOf(row.getCell(4).getStringCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"5列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(rate)){
						bean.setFromAmount(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"5列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(5)) ? null : row.getCell(5).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf((long)row.getCell(5).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"6列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setToPhone(value);
					}else{
						title = "第"+(i+1)+"行、第"+"6列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(6)) ? null : row.getCell(6).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf((float)row.getCell(6).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"7列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setToName(value);
					}else{
						title = "第"+(i+1)+"行、第"+"7列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(7)) ? null : row.getCell(3).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(3).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"8列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setToCourse(value);
					}else{
						title = "第"+(i+1)+"行、第"+"8列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						rate = ValidateUtils.isBlank(row.getCell(8)) ? null : (float) row.getCell(8).getNumericCellValue();
						
					} catch (Exception e) {
						try {
							rate = Float.valueOf(row.getCell(8).getStringCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"9列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(rate)){
						bean.setToAmount(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"9列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(9)) ? null : row.getCell(9).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(9).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"10列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setFromOperator(value);
					}else{
						title = "第"+(i+1)+"行、第"+"10列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(10)) ? null : row.getCell(10).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(10).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"11列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setToOperator(value);
					}else{
						title = "第"+(i+1)+"行、第"+"11列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean)){
					try {
						value = ValidateUtils.isBlank(row.getCell(11)) ? null : row.getCell(11).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(11).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"12列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setApproveWho(value);
					}else{
						title = "第"+(i+1)+"行、第"+"12列必填";
						break;
					}
				}
				bean.setCreateTime(d);
				list.add(bean);
			}
		}
		if(!ValidateUtils.isBlank(title)){
			rs.setStatus(Contents.ERROR);
			rs.setMsg(title);
			return rs;
		}
		if(!ValidateUtils.isEmptyForCollection(list))mapper.inserts(list);
		return rs;
	}

}
