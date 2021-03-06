/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.rj.bd.admin.entity.Query;

import tk.mybatis.mapper.entity.Example;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午11:52:41
 */
public interface QueryMapper{
	//查询所有
	public List<Query>  fiandAll(@Param("page")int page,@Param("size")int size);
	
	
	
	//查询符合条件的
	
	public List<Query> studentsearch(@Param("search")String search);






	
}
