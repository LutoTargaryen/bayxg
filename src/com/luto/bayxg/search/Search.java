package com.luto.bayxg.search;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.luto.bayxg.common.Constant;

/**
 * 打开默认浏览器调用百度搜索
 * @author luto
 *
 */
public class Search {
	/**
	 * 默认浏览器打开百度搜索
	 * @param param	搜索内容
	 */
	public static void openDefaultBrowser(String param){  
        //启用系统默认浏览器来打开网址。  
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
