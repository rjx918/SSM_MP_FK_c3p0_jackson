/**
 * 
 */
package com.rj.bd.admin.dao;

import org.apache.ibatis.annotations.Select;

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
	

}
