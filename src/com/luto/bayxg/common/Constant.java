package com.luto.bayxg.common;

import java.util.Date;

/**
 * 常量类
 * @author luto
 *
 */
public final class Constant {
	/**
	 * button按钮名
	 */
	public static final String BTNNAME = "截图";
	/**
	 * 截图失败提示信息
	 */
	public static final String DRAWERR = "截图失败";
	/**
	 * 图片保存地址
	 */
	public static final String SAVEIMGADR = "C:\\Users\\dahao\\Desktop\\img";
	/**
	 * 图片命名前缀
	 */
	public static final String IMGPREFIX =	"img";
	/**
	 * 图片格式
	 */
	public static final String IMGSUFFIX = "bmp";
	
	/**
	 * 百度授权的APP_ID,从百度开发平台获取
	 */
	public static final String APP_ID = "";		
	/**
	 * 百度授权的API_KEY,从百度开发平台获取
	 */
	public static final String API_KEY = "";
	/**
	 * 百度授权的SECRET_KEY,从百度开发平台获取
	 */
	public static final String SECRET_KEY = "";	
	/**
	 * API返回值中的word数组
	 */
	public static final String WORDS_RESULT = "words_result";
	/**
	 * word数组中的item项
	 */
	public static final String WORDS = "words";
	/**
	 * 查询网址
	 */
	public static final String SEARCHURL = "https://www.baidu.com/s?wd=";
	
	/**
	 * 返回图片名称
	 * 图片名称规则:img+时间戳+图片格式
	 * @return
	 */
	public static String getImgName(){
		Date date = new Date();
		return IMGPREFIX + date.getTime() +"." + IMGSUFFIX;
	}
}
