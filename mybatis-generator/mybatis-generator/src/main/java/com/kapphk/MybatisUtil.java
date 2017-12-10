package com.kapphk;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.haogrgr.mybatis.generator.plugin.MyPlugin;

/**
 * Mybatis生成工具类
 * @author zoneyu 2014年10月20日
 */
public class MybatisUtil {
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(MyPlugin.class.getResource("/genrator.xml").toURI());
		System.out.println(configFile.getPath());
		init();

		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator generator = new MyBatisGenerator(config, callback,warnings);
		generator.generate(null);

		System.out.println(warnings);
	}
	/*false
	[org.mybatis.generator.api.dom.java.Field@5e0a24a9]
			[org.mybatis.generator.api.dom.java.Field@5e0a24a9]
			[Existing file 
			E:/works/stsworkspace2/kapphk-model/com/kapphk/model/model/CashLogModel.java 
			was overwritten, 
			Existing file 
			E:/works/stsworkspace2/kapphk-model/com/kapphk/model/dao/CashLogMapper.java 
			was overwritten]
			
			*/


	public static void init() {
		//File file = new File("E:/works/stsworkspace2/kapphk-model/src/main/java");
		//if (file.exists()) {
		//	System.out.println(file.delete());
		//}
		//file.mkdirs();
	}
}
