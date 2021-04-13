/**
 * 
 */
package com.rj.bd.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rj.bd.admin.dao.ConditionMapper;
import com.rj.bd.admin.dao.MoneyMapper;
import com.rj.bd.admin.dao.StudentMapper;
import com.rj.bd.admin.entity.Condition;
import com.rj.bd.admin.entity.Money;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:24:32
 */

@Transactional  //注解式事务
@Service("iindexService")
public class IndexService implements IIndexService{
	
	@Autowired
	private ConditionMapper conditionMapper;
	
	@Autowired
	private MoneyMapper moneyMapper;
	
	
	@Autowired
	private StudentMapper studentMapper;
	

	@Override
	public int getcardsum() {
		return conditionMapper.selectCount(null);
	}


	@Override
	public int getcardlose() {
		return conditionMapper.getcardlose();
		
		
	}


	@Override
	public Float getgrossmoney() {
		return moneyMapper.getsum();
	}


	@Override
	public int getstudentsum() {
		return studentMapper.selectCount(null);
	}

}
