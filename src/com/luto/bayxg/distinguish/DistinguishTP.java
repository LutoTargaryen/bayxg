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
 * 识别图片
 * @author luto
 *
 */
public class DistinguishTP {
	

	/**
	 * 识别图片中的文字
	 * @param path	图片路径
	 * @return
	 * @throws IOException 
	 */
	public static String getCode(BufferedImage img) throws IOException {
		//关于百度API的更多信息请查看官方文档:https://cloud.baidu.com/doc/OCR/OCR-Java-SDK.html#.E7.AE.80.E4.BB.8B
		
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(Constant.APP_ID, Constant.API_KEY, Constant.SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		//	将传入的BufferedImage转为二进制数组
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img,Constant.IMGSUFFIX,out);
		byte[] imgByte = out.toByteArray();
		//	调用接口
		JSONObject res = client.basicGeneral(imgByte, new HashMap<String, String>());
		//	取出返回值里面的words_result数组,并将其解析为字符串
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
