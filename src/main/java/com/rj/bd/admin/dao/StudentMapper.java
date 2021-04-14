/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Student;

/**
 * @desc    学生姓名 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:28:41
 */
public interface StudentMapper extends BaseMapper<Student>{
	@Select("select * from student where s_id=#{s_id}")
	public Map<String, Object> queryStuById(String s_id);
}
