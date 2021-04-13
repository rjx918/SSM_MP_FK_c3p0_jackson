package com.rj.bd.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.user.dao.UserMapper;
import com.rj.bd.user.entity.User;

@Transactional
@Service("userService")//该注解的意思为标识出当前的类/模块是一个M层，且当前这个Service的值为userService
public class UserServiceImpl   implements   IUserService{

	@Autowired
	private UserMapper userMapper;
	
	
	
	public List<User> findAll(Page<User> page,User user) {
		
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		
		if (user.getName()==null)//如果传递过来的对象为null那么就是查询全部的意思，即刚进入该模块
		{
			queryWrapper.like(true, "name", "");//相当于 where name like '%%'
		}
		else
		{
			queryWrapper.like(true, "name", user.getName());//相当于 where name like '%美%'
		}
		List<User> list = userMapper.selectPage(page, queryWrapper).getRecords();
		System.out.println("userServiceImpl()--->findAll--->list--->"+list);
		return   list;
	
	}

	
	public void save(User u) {
		//for(int i =1;i<=10;i++)
		//{
			 u.setId(UUID.randomUUID().toString());
			 userMapper.save(u);
			//if (i==5) 
			//{
			 // System.out.println(2/0);	
			//}
		//}
         		
	}



	public User queryById(String id) {
		return userMapper.queryById(id);
	}



	public void eidt(User user) {
		userMapper.edit(user);
	}



	public void delete(String id) {
		userMapper.delete(id);		
	}

}
