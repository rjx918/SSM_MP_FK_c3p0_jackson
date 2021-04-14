/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.rj.bd.admin.entity.Datas;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月14日 上午2:10:41
 */
public interface Classesinfo {

	@Select("SELECT DISTINCT `d_class` FROM `data`")
	public List<String> ClassesSS();
	
}

