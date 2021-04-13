package com.rj.bd.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.user.entity.User;


public interface IUserService {

	public List<User>  findAll(Page<User> page, User user);

	public void save(User u);

	public User queryById(String id);

	public void eidt(User user);

	public void delete(String id);

}
