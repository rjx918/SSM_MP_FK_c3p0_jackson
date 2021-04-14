/**
 * 
 */
package com.rj.bd.admin.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.Money;

/**
 * @desc   卡内余额
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:28:41
 */
public interface MoneyMapper extends BaseMapper<Money>{
	
	@Select("SELECT sum(m_money)  FROM money ")
	public float getsum();
	
	@Select("SELECT *  FROM money  where m_id=#{m_id}")
	Map<String, String> getMonById(String m_id);
	
	@Update("UPDATE `money` SET m_money=#{m_money} WHERE m_id=#{m_id}")
	int updateMonById(@Param("m_id")String m_id,@Param("m_money")String m_money );
	

}
