/**
 * 
 */
package com.rj.bd.admin.utils;

import java.util.HashMap;
import java.util.Map;

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
	public static Map<String, Object> printf(int code,String msg,Object data) {
		HashMap<String, Object> ret = new HashMap<String,Object>();
		ret.put("code", code);
		ret.put("msg", msg);
		ret.put("data", data);
		return ret;
	}
}
