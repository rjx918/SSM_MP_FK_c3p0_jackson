/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Message;

/**
 * @desc  学生信息  卡号学号这些
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:28:41
 */
public interface MessageMapper extends BaseMapper<Message>{
	@Select("select * from message where s_id=#{s_id}")
	 Map<String, Object> queryMag(String s_id);
}
