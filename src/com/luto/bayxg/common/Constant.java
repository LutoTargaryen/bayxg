package com.luto.bayxg.common;

import java.util.Date;

/**
 * ������
 * @author luto
 *
 */
public final class Constant {
	/**
	 * button��ť��
	 */
	public static final String BTNNAME = "��ͼ";
	/**
	 * ��ͼʧ����ʾ��Ϣ
	 */
	public static final String DRAWERR = "��ͼʧ��";
	/**
	 * ͼƬ�����ַ
	 */
	public static final String SAVEIMGADR = "C:\\Users\\dahao\\Desktop\\img";
	/**
	 * ͼƬ����ǰ׺
	 */
	public static final String IMGPREFIX =	"img";
	/**
	 * ͼƬ��ʽ
	 */
	public static final String IMGSUFFIX = "bmp";
	
	/**
	 * �ٶ���Ȩ��APP_ID,�Ӱٶȿ���ƽ̨��ȡ
	 */
	public static final String APP_ID = "";		
	/**
	 * �ٶ���Ȩ��API_KEY,�Ӱٶȿ���ƽ̨��ȡ
	 */
	public static final String API_KEY = "";
	/**
	 * �ٶ���Ȩ��SECRET_KEY,�Ӱٶȿ���ƽ̨��ȡ
	 */
	public static final String SECRET_KEY = "";	
	/**
	 * API����ֵ�е�word����
	 */
	public static final String WORDS_RESULT = "words_result";
	/**
	 * word�����е�item��
	 */
	public static final String WORDS = "words";
	/**
	 * ��ѯ��ַ
	 */
	public static final String SEARCHURL = "https://www.baidu.com/s?wd=";
	
	/**
	 * ����ͼƬ����
	 * ͼƬ���ƹ���:img+ʱ���+ͼƬ��ʽ
	 * @return
	 */
	public static String getImgName(){
		Date date = new Date();
		return IMGPREFIX + date.getTime() +"." + IMGSUFFIX;
	}
}
