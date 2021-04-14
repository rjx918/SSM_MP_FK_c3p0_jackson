package com.rj.bd.admin.service;

import java.util.Map;

import com.rj.bd.admin.entity.Condition;
import com.rj.bd.admin.entity.Message;
import com.rj.bd.admin.entity.Money;
import com.rj.bd.admin.entity.Student;

/**
 * @Desc 学生的 M层接口
 * @author 语录
 *
 */
public interface IStudnetSevice {

	/**
	 * @Desc 查询学生根据id
	 * @param sid 学生的id
	 * @return
	 */
	Map<String, Object> queryStuById(String sid);
	
	/**
	 * @Desc 查询是否已经办理卡 
	 * @param sid 学生id
	 * @return
	 */
	Map<String, Object> queyMsgById(String sid);
	
	/**
	 * @Desc 增加卡
	 * @param message
	 * @return
	 */
	int updateMsg(Message message,Condition condition,Money money);
	
	/**
	 * @Desc 修改学生装太
	 * @param condition
	 * @return
	 */
	 int update(Condition condition);
	 
	 /**
	  * @Desc 查询
	  * @param cardid
	  * @return
	  */
	 Map<String, Object> queryConditionById(String cardid);
	 
	 
	 Map<String, String> getMonById(String m_id);
	 
	 int updateMonById(String m_id,String m_money );
}
