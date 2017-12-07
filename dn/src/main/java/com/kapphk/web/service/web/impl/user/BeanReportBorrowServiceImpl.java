package com.kapphk.web.service.web.impl.user;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.kapphk.web.bean.user.BeanReportBorrow;
import com.kapphk.web.dao.mapper.user.BeanReportBorrowMapper;
import com.kapphk.web.service.web.imethod.user.BeanReportBorrowService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class BeanReportBorrowServiceImpl implements BeanReportBorrowService {

	@Autowired
	private BeanReportBorrowMapper mapper;
	
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
		String[] particular = new String[]{"手机号","真实姓名","借款金额","借款用途","借款日","借款到期日","计划还款期限","年利率",
				"应计年利息","还款及支付利息情况","剩余还款金额","上传时间"};
		String[] property = new String[]{"phone","actualName","borrowAmount","useRemark","startDate","endDate","borrowYear",
				"yearRate","yearInterest","returnRemark","balanceAmount","createTime"};
		System.out.println(ExcelUtils.downExcelList("借款情况报表.xlsx", particular, property, list, response));
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
		Date date = null;
		Date d = new Date();
		List<BeanReportBorrow> list = new ArrayList<BeanReportBorrow>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd") ;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row != null){
				BeanReportBorrow bean = null;
				try {
					value = ValidateUtils.isBlank(row.getCell(0)) ? null : row.getCell(0).getStringCellValue();
				} catch (Exception e) {
					try {
						value = String.valueOf((long)row.getCell(0).getNumericCellValue());
					} catch (Exception e1) {
						e1.printStackTrace();
						title = "第"+(i+1)+"行、第"+"1列格式有误!";
						break;
					}
				}
				if(!ValidateUtils.isBlank(value)){
					bean = new BeanReportBorrow();
					bean.setPhone(value);
				}else{
					title = "第"+(i+1)+"行、第"+"1列必填";
					break;
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(1))){
					try {
						value = row.getCell(1).getStringCellValue();
					} catch (Exception e) {
						try {
							value = String.valueOf(row.getCell(1).getNumericCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"2列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(value)){
						bean.setActualName(value);
					}else{
						title = "第"+(i+1)+"行、第"+"2列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(2))){
					try {
						rate = (float) row.getCell(2).getNumericCellValue();
						
					} catch (Exception e) {
						try {
							rate = Float.valueOf(row.getCell(2).getStringCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"3列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(rate)){
						bean.setBorrowAmount(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"3列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(3))){
					try {
						value = row.getCell(3).getStringCellValue();
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
						bean.setUseRemark(value);
					}else{
						title = "第"+(i+1)+"行、第"+"4列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(4))){
					try {
						date = row.getCell(4).getDateCellValue();
					} catch (Exception e) {
							e.printStackTrace();
							title = "第"+(i+1)+"行、第"+"5列格式有误!";
							break;
					}
					if(!ValidateUtils.isBlank(date)){
						bean.setStartDate(sf.format(date));
					}else{
						title = "第"+(i+1)+"行、第"+"5列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(5))){
					try {
						date = row.getCell(5).getDateCellValue();
					} catch (Exception e) {
							e.printStackTrace();
							title = "第"+(i+1)+"行、第"+"6列格式有误!";
							break;
					}
					if(!ValidateUtils.isBlank(date)){
						bean.setEndDate(sf.format(date));
					}else{
						title = "第"+(i+1)+"行、第"+"5列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(6))){
					try {
						value = row.getCell(6).getStringCellValue();
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
						bean.setBorrowYear(value);
					}else{
						title = "第"+(i+1)+"行、第"+"7列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(7))){
					try {
						rate = (float) row.getCell(7).getNumericCellValue();
						
					} catch (Exception e) {
						try {
							rate = Float.valueOf(row.getCell(7).getStringCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"8列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(rate)){
						bean.setYearRate(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"8列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(8))){
					try {
						rate = (float) row.getCell(8).getNumericCellValue();
						
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
						bean.setYearInterest(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"9列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(9))){
					try {
						value = row.getCell(9).getStringCellValue();
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
						bean.setReturnRemark(value);
					}else{
						title = "第"+(i+1)+"行、第"+"10列必填";
						break;
					}
				}
				if(!ValidateUtils.isBlank(bean) && !ValidateUtils.isBlank(row.getCell(10))){
					try {
						rate = (float) row.getCell(10).getNumericCellValue();
						
					} catch (Exception e) {
						try {
							rate = Float.valueOf(row.getCell(10).getStringCellValue());
						} catch (Exception e1) {
							e1.printStackTrace();
							title = "第"+(i+1)+"行、第"+"11列格式有误!";
							break;
						}
					}
					if(!ValidateUtils.isBlank(rate)){
						bean.setBalanceAmount(new BigDecimal(rate));
					}else{
						title = "第"+(i+1)+"行、第"+"11列必填";
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
