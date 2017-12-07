package com.kapphk.web.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.kapphk.system.bean.SystemUser;

public class PasswordHelper {
		private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		private String algorithmName = "md5";
		private int hashIterations = 2;

		public void encryptPassword(SystemUser systemUser) {
			String salt=randomNumberGenerator.nextBytes().toHex();
			systemUser.setSalt(salt);
			String newPassword = new SimpleHash(algorithmName, systemUser.getPwd(), ByteSource.Util.bytes(systemUser.getUserName() + salt), hashIterations).toHex();
			systemUser.setPwd(newPassword);
		}
		
		public String encryptPassword(String userName,String pwd,String salt){
			String password = new SimpleHash(algorithmName, pwd, ByteSource.Util.bytes(userName + salt), hashIterations).toHex();
			return password;
		}
		
		public static void main(String[] args) {
			PasswordHelper passwordHelper = new PasswordHelper();
			SystemUser systemUser = new SystemUser();
			systemUser.setPwd("123456");
			systemUser.setUserName("admin");
			passwordHelper.encryptPassword(systemUser);
			System.out.println(systemUser.getPwd());
		}
}
