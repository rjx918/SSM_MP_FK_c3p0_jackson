/**
 * 
 */
package com.rj.bd.admin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rj.bd.admin.dao.LoginMapper;
import com.rj.bd.admin.entity.User;

/**
 * @desc
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午10:08:43
 */
@Transactional
@Service("loginService")
public class LoginService implements ILoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	// 登录
	@Override
	public User Login(User u) {
		QueryWrapper<User> userQueryWrapper = Wrappers.query();
		userQueryWrapper.eq(true, "name", u.getName()).eq("password", u.getPassword());
		User ret = loginMapper.selectOne(userQueryWrapper);
		return ret;
	}

	// 验证token
	@Override
	public User verifyToken(User u) {
		QueryWrapper<User> queryWrapper = Wrappers.query();
		queryWrapper.eq(true, "token", u.getToken());
		User ret = loginMapper.selectOne(queryWrapper);
		return ret;
	}

	// 写入token
	@Override
	public boolean putToken(User u) {
		UpdateWrapper<User> updateWrapper = Wrappers.update();
		updateWrapper.eq("id", u.getId());
		int ret = loginMapper.update(u, updateWrapper);
		if (ret > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
}
