/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Condition;

/**
 * @desc  状态
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:28:41
 */
public interface ConditionMapper extends BaseMapper<Condition>{
	
	
	
	//挂失数量
	@Select("SELECT COUNT(1) FROM `condition` WHERE c_condition =1")
	public int getcardlose();
	
	
	@Select("SELECT * FROM `condition` WHERE `m_id` =(SELECT `m_id` FROM message WHERE `m_number`=#{m_number})")
	public Map<String, Object> getConByCarid(String m_number);

}
