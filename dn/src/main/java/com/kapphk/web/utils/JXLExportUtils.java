package com.kapphk.web.utils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用 导出
 * @author zoneyu 15-12-11
 */
public class JXLExportUtils {

	private final String NUMBER = "number" ;
	private final String STRING = "string" ;
	private String fileName = "" ;
	private List<String> titleList = new ArrayList<String>() ;
	private List<String> columnList = new ArrayList<String>() ;
	
	private List<String> typeList = new ArrayList<String>() ;
	private WritableWorkbook workbook = null ;
	private WritableSheet sheet = null ;
	private OutputStream os = null ;
	@SuppressWarnings("rawtypes")
	private Class clazz = null ;
	
	/**
	 * 第1步，创建导出对象
	 * @param fileName  导出文件名
	 */
	public JXLExportUtils(String fileName){
		this.fileName = fileName ;
	}
	
	/**
	 * 第2步、添加导出数据集中的类型
	 * @param clazz   集合中的类型，目前支持实体跟Map
	 */
	@SuppressWarnings("rawtypes")
	public void addExportType(Class clazz){
		this.clazz = clazz ;
	}
	
	/**
	 * 第3步、添加导出列
	 * @param name     列的中文名称
	 * @param column   列对应的实体字段
	 * @throws Exception 
	 */
	public void addExportProperty(String name,String column,Object... args) throws Exception{
		if(null != name && !"".equals(name) && null != column && !"".equals(column)){
			titleList.add(name) ;
			columnList.add(column) ;
			if(args != null && args.length > 0){
				typeList.add(String.valueOf(args[0])) ;
			}else{
				typeList.add(this.STRING) ;
			}
		}else{
			throw new Exception("列中文名称跟字段不对应") ;
		}
	}
	
	/**
	 * 第4步、写头部 信息
	 * @param response
	 * @throws Exception
	 */
	public void writerHead(HttpServletResponse response) throws Exception{
        response.reset();// 清空输出流   
        String fname = new String(fileName.getBytes(),"iso8859-1") ;
        response.setHeader("Content-disposition", "attachment; filename="+fname+".xls");// 设定输出文件头   
        response.setContentType("application/msexcel");// 定义输出类型 
	}
	
	/**
	 * 第5步、写表头
	 * @param response
	 * @throws Exception
	 */
	public void writerTitle(HttpServletResponse response) throws Exception{
		os = response.getOutputStream();// 取得输出流   
		workbook = Workbook.createWorkbook(os); // 建立excel文件   
        sheet = workbook.createSheet(fileName, 0); // sheet名称  
        WritableCellFormat style = getStyle() ;
        sheet.getSettings().setDefaultColumnWidth(20);
        
        if(titleList.size() > 0){ 
        	for(int i = 0 ; i < titleList.size() ; i++){
    			Label label = new Label(i, 0, titleList.get(i),style) ;
    			sheet.addCell(label) ;
    		}
        }else{
        	throw new Exception("表头为空") ;
        }
	}
	
	/**
	 * 第6步、写数据
	 * @param response
	 * @throws Exception
	 */
	public void writerData(List<? extends Object> list) throws Exception{
		if(null != clazz){
			if(!ValidateUtils.isEmptyForCollection(list)){
				if(list.size() < 65535){
					Object object = list.get(0) ;
					WritableCellFormat style = getStyle() ;
					if(object instanceof Map || clazz.getName().equals(object.getClass().getName())){
						for(int i = 0 ; i < list.size() ; i++){
							if(clazz.getName().equals(Map.class.getName())){//map集合
								buildData(list.get(i),i+1,style) ;
							}else if(clazz.getName().equals(object.getClass().getName())){//实体
								buildEntityData(list.get(i),i+1,style) ;
							}else{
								throw new Exception("暂不支持此类型的数据导出") ;
							}
						}
						try {
							workbook.write();
							workbook.close();
							os.close(); // 关闭流
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							workbook = null ;
							os = null ;
						}
					}else{
						throw new Exception("导出数据集设置的数据类型跟数据集中的类型不匹配，请修改第二步") ;
					}
				}else{
					throw new Exception("导出数据超过了65535条，请通过筛选条件筛选后再导出") ;
				}
			}
		}else{
			throw new Exception("导出数据集的类型为空，请添加第二步") ;
		}
	}
	
	private void buildEntityData(Object object, Integer row, WritableCellFormat style) throws Exception {
		for(int i = 0 ; i < columnList.size() ; i++){
			Field field = object.getClass().getDeclaredField(columnList.get(i)) ;
			field.setAccessible(true) ;
			String content = field.get(object) == null ? "" : String.valueOf(field.get(object)) ;
			Label label = new Label(i, row, content, style) ;
			sheet.addCell(label) ;
		}
	}

	@SuppressWarnings("unchecked")
	protected void buildData(Object object,Integer row,WritableCellFormat style) throws Exception{
		Map<String,Object> map = (Map<String,Object>)object ;
		for(int i = 0 ; i < columnList.size() ; i++){
			String content = map.get(columnList.get(i)) == null ? "" : String.valueOf(map.get(columnList.get(i))) ;
			Label label = new Label(i, row, content, style) ;
			sheet.addCell(label) ;
		}
	}
	
	/**
	 * 单元格样式
	 */
	public WritableCellFormat getStyle() throws Exception{
		WritableFont wfcontent = new WritableFont(WritableFont.ARIAL,10, WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
        WritableCellFormat wcfcontent = new WritableCellFormat(wfcontent);
        wcfcontent.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        wcfcontent.setAlignment(Alignment.CENTRE);
        return wcfcontent ;
	}
	
}
