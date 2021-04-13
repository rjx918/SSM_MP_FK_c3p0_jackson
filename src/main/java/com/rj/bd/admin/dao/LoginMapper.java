/**
 * 
 */
package com.rj.bd.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.admin.entity.User;

/**
 * @desc   登录使用的        
 * 			查询用户名密码
 * 			写入token
 * 			验证token
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午9:45:20
 */


public interface LoginMapper extends BaseMapper<User>{
//	@Select("SELECT * FROM user WHERE name=#{name}  and password = #{password}")
//	public User login(User u);
//	
//	//UPDATE `user` SET `token`='asdasfsaf' WHERE (`id`='01')
//	@Update("UPDATE user SET token=#{token} WHERE (id=#{id})")
//	public int putToken(User u);
}
