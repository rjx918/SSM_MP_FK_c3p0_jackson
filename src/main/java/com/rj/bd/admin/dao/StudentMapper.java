/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Query;
import com.rj.bd.admin.entity.Student;

/**
 * @desc    学生姓名 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:28:41
 */
public interface StudentMapper extends BaseMapper<Student>{

	public List<Query>  fiandAll();
	

	
	
}
