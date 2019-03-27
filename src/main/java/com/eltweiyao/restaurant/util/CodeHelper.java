package com.eltweiyao.restaurant.util;

import java.util.UUID;

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
}
