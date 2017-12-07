package com.kapphk.web.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
/**
 * 导出
 * @author dengwen 
 * 2016-9-21下午5:57:59
 */
@SuppressWarnings("unused")
public class ExcelUtils {
	
	/*列宽*/
	private static int columnWidth = 5000;
	/*列高*/
	private static short rowHeight = 400;
	
	/**
	 * @param List<T> 导出数据
	 * @param excelName 保存到硬盘的文件名
	 * @param particular 工作表的信息详细 :[姓名 , 年龄 , 爱好 ] 注意: particular与property长度要一致
	 * @param property 实体类的属性 : [name , age , hobby , yyy , zzz]
	 * @return 返回结果,如果参数格式无误则返回true,否则返回false
	 * @author dengwen 
	 * 2016-7-27下午2:25:08
	 */
	@SuppressWarnings("rawtypes")
	public static <T> boolean downExcelObject(String excelName, String[] particular,
			String[] property, List<T> list, HttpServletResponse response) {
		// 先判断是否符合,不符合要求直接 返回 false;
		if(isEmpty(particular) || isEmpty(property) || list == null || list.size() == 0 ||
		   !excelName.matches("^.+\\.(?i)(xls||xlsx)$") || particular.length != property.length){ return false;}
		
		Workbook workbook = excelName.matches("^.+\\.(?i)(xls)$") ? new HSSFWorkbook() : new XSSFWorkbook();
		//数据样式对象
		CellStyle rowStyle = createCellStyle(workbook, 0);
		// 工作表名
		String tableName = excelName.substring(0, excelName.lastIndexOf("."));
		Sheet sheet = getSheet(workbook, tableName, particular);
		Row row = null;
		Cell cell = null;
		Class clazz = null;
		String versions = null;
		Field field = null;
		Class<?> type = null;
		Object value = null;

		ServletOutputStream os = null;
		try {
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(excelName.getBytes(), "ISO-8859-1"));
			os = response.getOutputStream();
			//操作单元格；将用户列表写入excel
			int k = 1;
			for (int j = 0; j < list.size(); j++) {
				if(j/65534>0 && j%65534==0){
					sheet =getSheet(workbook, tableName+j/65534, particular);
					k=1;
				}
				/**
				 * getDeclaredField：得到任意修饰的属性（public/private） getField():
				 * 得到公开的属性（public） 获取集合对象
				 */
				clazz = list.get(j).getClass();
				row = sheet.createRow(k);
				for (int i = 0; i < property.length; i++) {
					try {
						// 遍历对象属性获取值
						field = clazz.getDeclaredField(property[i]);
						type = field.getType();
						field.setAccessible(true);
						value = field.get(list.get(j));
						
						// 创建单元格对象,加载数据样式
						cell = row.createCell(i);
						cell.setCellStyle(rowStyle);
						if (String.class.isAssignableFrom(type)) {
							cell.setCellValue((String) value);
						} else if (Boolean.class.isAssignableFrom(type)) {
							if ((Boolean) value) {
								cell.setCellValue("男");
							} else {
								cell.setCellValue("女");
							}
						} else if (Number.class.isAssignableFrom(type)) {
							cell.setCellValue(Double.parseDouble(value.toString()));
						} else if (Date.class.isAssignableFrom(type)) {
							cell.setCellValue((Date) value);
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
				k++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.write(os);
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 根据 List<Map>集合导出数据
	 * @param excelName 保存到硬盘的文件名
	 * @param particular 工作表的信息详细 ：[姓名 , 年龄 , 爱好 , xxx , yyy] 注意: particular与property长度要一致
	 * @param property key : [name , age , hobby , yyy , zzz]
	 * @param return true 或 false
	 * @author dengwen 
	 * 2016-7-27下午2:25:08
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean downExcelList(String excelName, String[] particular,
			String[] property, List<T> list, HttpServletResponse response) {
		// 先判断是否符合,不符合要求直接 返回 false;
		if(isEmpty(particular) || isEmpty(property) ||
		   !excelName.matches("^.+\\.(?i)(xls||xlsx)$") || particular.length != property.length){ return false;}
		
		Workbook workbook = excelName.matches("^.xls$") ? new HSSFWorkbook() : new XSSFWorkbook();
		//数据样式对象
		CellStyle rowStyle = createCellStyle(workbook, 0);
		// 工作表名
		String tableName = excelName.substring(0, excelName.lastIndexOf("."));
		Sheet sheet = getSheet(workbook, tableName, particular);
		Row row = null;
		Cell cell = null;
		Map<String,Object> map = null;
		ServletOutputStream os = null;
		try {
			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(excelName.getBytes(), "ISO-8859-1"));
			os = response.getOutputStream();
			//操作单元格；将用户列表写入excel
			int k = 1;
			for (int j = 0; j < list.size(); j++) {
				if(j/65534>0 && j%65534==0){
					sheet =getSheet(workbook, tableName+j/65534, particular);
					k=1;
				}
				
				map = (Map<String, Object>) list.get(j);
				row = sheet.createRow(k);
				for (int i = 0; i < property.length; i++) {
					try {
						// 创建单元格对象,加载数据样式
						cell = row.createCell(i);
						cell.setCellStyle(rowStyle);
						Object value =  map.get(property[i]);
						cell.setCellValue(ValidateUtils.isBlank(value) ? "" : String.valueOf(value));
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
				k++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.write(os);
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 样式
	 * @param workbook  
	 * @param fontSize 文字大小
	 * @author dengwen 
	 * 2016-7-27下午2:25:08
	 */
	private static CellStyle createCellStyle(Workbook workbook,int state) {
		 CellStyle bodyStyle = workbook.createCellStyle();
         bodyStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边边框
         bodyStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边边框颜色
         bodyStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边边框
         bodyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边边框颜色
         bodyStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
         bodyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框颜色
         bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);// 设置单元格水平方向对其方式
         bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 设置单元格垂直方向对其方式
		 if(state > 0){
			 //XLS标题头样式 header style
	         Font headFont = workbook.createFont();
	         headFont.setFontHeightInPoints((short) 13);// 大小
	         headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
	         headFont.setFontName("黑体");
	         bodyStyle.setFont(headFont);
	         bodyStyle.setBorderTop(CellStyle.BORDER_THIN);
	         bodyStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	         bodyStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	         bodyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); // 前景色
		 }else{
			 bodyStyle.setWrapText(true);// 自动换行
		 }
         return bodyStyle;
	}
	
	/**
	 * 分页创建工作表
	 * @author dengwen 
	 * 2016-7-27下午2:27:45
	 */
	private static Sheet getSheet(Workbook workbook, String tableName, String[] particular){
		//创建工作表
		Sheet sheet = workbook.createSheet(tableName);
		//列标样式对象
		CellStyle columnStyle = createCellStyle(workbook, 1);
		//创建列标题行；并且设置列标题
		Row row = sheet.createRow(0);
		row.setHeight(rowHeight);// 高度
		for (int i = 0; i < particular.length; i++) {
		Cell cell = row.createCell(i);
			//加载列标样式
			cell.setCellStyle(columnStyle);
			cell.setCellValue(particular[i]);
			sheet.setColumnWidth(i, columnWidth);
		}
		return sheet;
	}
	
	public static List<List<String>> importExcel(MultipartFile file) throws Exception{
		String fileName = file.getOriginalFilename();
		if(fileName.lastIndexOf(".xls") == -1 && fileName.lastIndexOf(".xlsx") == -1){
			throw new Exception("文件有误, 以.xls或者.xlsx后缀名文件");
		}
		Workbook workbook = null;
		// 检查版本
		fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		boolean is03Excel = fileName.matches("^.xls$");
		workbook = is03Excel ? new HSSFWorkbook(file.getInputStream())
				: new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		String value = null;
		Row row = null;
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row != null){
				row.getCell(0).getStringCellValue();
			}
		}
		return list;
	}
	
	private static boolean isEmpty(Object... args){
		if(args != null && args.length > 0){
			return false ;
		}
		return true ;
	}
}
