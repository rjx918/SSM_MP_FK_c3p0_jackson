/**
 * 
 */
package com.rj.bd.admin.dao;

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
	
	@Select("SELECT COUNT(1) FROM `condition`")
	public int getcardlose();

}
