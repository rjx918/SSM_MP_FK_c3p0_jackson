package com.rj.bd.admin.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rj.bd.admin.dao.ConditionMapper;
import com.rj.bd.admin.dao.MessageMapper;
import com.rj.bd.admin.dao.MoneyMapper;
import com.rj.bd.admin.dao.StudentMapper;
import com.rj.bd.admin.entity.Condition;
import com.rj.bd.admin.entity.Message;
import com.rj.bd.admin.entity.Money;
import com.rj.bd.admin.entity.Student;

/**
 * @Desc 学生接口的实现类
 * @author 语录
 *
 */
@Service
public class StudnetService implements IStudnetSevice {

	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private ConditionMapper conditionMapper;
	
	@Autowired
	private MoneyMapper moneyMapper;
	/**
	 * 1. 根据id查询学生
	 */
	@Override
	public Map<String, Object> queryStuById(String sid) {
		// TODO Auto-generated method stub
		System.out.println(studentMapper.queryStuById(sid));
		return studentMapper.queryStuById(sid); //返回结果
	}

	/**
	 * 1. 查询学生是否办理卡
	 */
	@Override
	public Map<String, Object> queyMsgById(String sid) {
		// TODO Auto-generated method stub
		System.out.println(messageMapper.queryMag(sid));
		return messageMapper.queryMag(sid);//返回结果
	}

	/**
	 * 1. 增加卡
	 */
	@Override
	public int updateMsg(Message message,Condition condition,Money money) {
		// TODO Auto-generated method stub
		messageMapper.updateById(message);
		conditionMapper.insert(condition);
		moneyMapper.insert(money);
		return 1;
	}
	
	
	/**
	 * @Desc 学生卡状态
	 */
	public int update(Condition condition){
		return conditionMapper.updateById(condition);
	}

	@Override
	public Map<String, Object> queryConditionById(String cardid) {
		// TODO Auto-generated method stub
		return conditionMapper.getConByCarid(cardid);
	}
	
	/**
	 * @Desc 查询mon 
	 * @param m_id
	 * @return
	 */
	public Map<String, String> getMonById(String m_id){
		return moneyMapper.getMonById(m_id);
	}

	@Override
	public int updateMonById(String m_id, String m_money) {
		// TODO Auto-generated method stub
		return moneyMapper.updateMonById(m_id, m_money);
	}
	
}
