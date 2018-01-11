package com.luto.bayxg.distinguish;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import com.luto.bayxg.common.Constant;

/**
 * ʶ��ͼƬ
 * @author luto
 *
 */
public class DistinguishTP {
	

	/**
	 * ʶ��ͼƬ�е�����
	 * @param path	ͼƬ·��
	 * @return
	 * @throws IOException 
	 */
	public static String getCode(BufferedImage img) throws IOException {
		//���ڰٶ�API�ĸ�����Ϣ��鿴�ٷ��ĵ�:https://cloud.baidu.com/doc/OCR/OCR-Java-SDK.html#.E7.AE.80.E4.BB.8B
		
		// ��ʼ��һ��AipOcr
		AipOcr client = new AipOcr(Constant.APP_ID, Constant.API_KEY, Constant.SECRET_KEY);

		// ��ѡ�������������Ӳ���
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		//	�������BufferedImageתΪ����������
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img,Constant.IMGSUFFIX,out);
		byte[] imgByte = out.toByteArray();
		//	���ýӿ�
		JSONObject res = client.basicGeneral(imgByte, new HashMap<String, String>());
		//	ȡ������ֵ�����words_result����,���������Ϊ�ַ���
		Object object = res.get(Constant.WORDS_RESULT);
		String valueToString = JSONObject.valueToString(object);
		JSONArray parseArray = JSONArray.parseArray(valueToString);
		int size = parseArray.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			com.alibaba.fastjson.JSONObject jsonObject = parseArray.getJSONObject(i);
			sb.append(jsonObject.get(Constant.WORDS));
		}

		return sb.toString();
	}

}
