/**
 * 
 */
package com.rj.bd.admin.service;

import com.rj.bd.admin.entity.User;

/**
 * @desc 	
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午10:08:28
 */
public interface ILoginService {
	// 用户登录
	public User Login(User u);
	
	
	// 验证toke
	public User verifyToken(User u);
	
	
	// 写入token
	public boolean putToken(User u);
	
	
	//上传图片
	
	public boolean SetImg(User u);
	
	
}
