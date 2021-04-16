/**
 * 
 */
package com.rj.bd.admin.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import freemarker.template.Configuration;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午1:38:56
 */
public class Putdata {

	/**
	 * @desc  格式化输出  json格式的数据
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static String printf(int code,String msg,Object data,Model model) {
		HashMap<String, Object> ret = new HashMap<String,Object>();
		Gson gson = new Gson();  
		ret.put("code", code);
		ret.put("msg", msg);
		ret.put("data", gson.toJson(data));
		model.addAttribute("data", ret);
		return "data";
	}
	
	
	public static Map<String, Object> printf2(int code,String msg,Object data) throws IOException {
		
		
		
		//1.创建freemarker实例
		Configuration  cfg=new Configuration();
		
		//2.设定模板文件所在的路径
		cfg.setDirectoryForTemplateLoading(new File("templates"));
		cfg.setTemplateUpdateDelay(1);
		//创建根容器
		Map<Object,Object> root = new HashMap();
		
		//普通数据类型
		root.put("name", "秋枫");
		return null;
	}
	
	
	
}
