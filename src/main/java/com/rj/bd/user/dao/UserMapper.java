package com.rj.bd.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.user.entity.User;


/**
 * 
   @desc    User模块的持久层
 * @author  云帆大师 微信/QQ:909904682
 * @time    2017-8-15
 *
 */
//@Repository("userMapper")//这个单词在此的意思为数据库,该注解标识出当前的类/模块即是持久层(Dao),
                         //userMapper说明当前这个是user模块的Dao,即可以理解为userDao
public interface UserMapper extends BaseMapper<User> {

@Select("select * from user")
public List<User>  fiandAll();

@Insert("insert into user (id,name) values (#{id},#{name})")
public void save(User u);

@Select("select * from user where id=#{id}")
public User queryById(String id);

@Update("update user set name=#{name} where id=#{id}")
public void edit(User user);

@Delete("delete from user where id = #{id}")
public void delete(String id);




	
}
