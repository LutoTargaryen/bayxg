package com.luto.bayxg.search;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.luto.bayxg.common.Constant;

/**
 * ��Ĭ����������ðٶ�����
 * @author luto
 *
 */
public class Search {
	/**
	 * Ĭ��������򿪰ٶ�����
	 * @param param	��������
	 */
	public static void openDefaultBrowser(String param){  
        //����ϵͳĬ�������������ַ��  
        try {  
            URI uri = new URI(Constant.SEARCHURL+param);  
            Desktop.getDesktop().browse(uri);  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
