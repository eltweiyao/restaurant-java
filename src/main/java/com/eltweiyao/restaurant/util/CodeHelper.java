package com.eltweiyao.restaurant.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.UUID;

@Slf4j
public class CodeHelper {
	
	/**
	 * 部门编号的每一分段长度，如为4则code将类似1234-5678
	 */
	public static int CODE_LENGTH = 4;
	
	public static String QrcodePic=getProjectPath();
	
	public static String createUUID(){
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	}

	public static String getProjectPath(){
	    String nowpath;             //当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\bin   
		String tempdir;   
		nowpath=System.getProperty("user.dir");   
		tempdir=nowpath.replace("bin", "webapps");  //把bin 文件夹变到 webapps文件里面    
		tempdir+="\\"+"CTF\\qrcode\\";  //拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro    
	    return tempdir;
	}

	public static String getMD5Value(String s){
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e){
			log.error("获取MD5值出错， errMsg = {}, stack info=", e.getMessage(), e);
			throw new RuntimeException("获取MD5值出错");
		}
	}
}
