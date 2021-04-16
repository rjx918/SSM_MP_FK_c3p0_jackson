package com.rj.bd.admin.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rj.bd.admin.dao.Classesinfo;
import com.rj.bd.admin.dao.DatasMapper;
import com.rj.bd.admin.dao.MessageMapper;
import com.rj.bd.admin.dao.QueryMapper;
import com.rj.bd.admin.dao.StudentMapper;
import com.rj.bd.admin.entity.Datas;
import com.rj.bd.admin.entity.Message;
import com.rj.bd.admin.entity.Query;
import com.rj.bd.admin.entity.Student;
import com.rj.bd.admin.entity.User;
import com.sun.org.apache.regexp.internal.recompile;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Transactional  //注解式事务
@Service("studentlistService")
public class StudentlistService implements IStudentlistService{

	@Autowired
	public QueryMapper queryMapper;
	
	@Autowired
	public MessageMapper messageMapper;
	
	@Autowired
	public DatasMapper datasMapper;
	
	@Autowired
	public StudentMapper studentMapper;
	
	@Autowired
	public Classesinfo classesinfo;
	
	//查询是所有学生
	public List<Query> query(int page,int size) {
		System.err.println(page+"--"+size);
		return queryMapper.fiandAll(page,size);
	}
	
	
	//条件查询
	public List<Query> studentsearch(String search){
		return queryMapper.studentsearch(search);
	}
	
	//添加学生
	public String studentadd(String name,String snumber,String sclassesid){
		//查询classid 存在否
		
		QueryWrapper<Datas> queryWrappers = Wrappers.query();
		queryWrappers.eq(true, "d_class", sclassesid);
		Integer classesinfo = datasMapper.selectCount(queryWrappers);
		if (classesinfo !=0) {
			//查询学号存在否
			QueryWrapper<Message> snumberWrappers = Wrappers.query();
			snumberWrappers.eq(true, "m_studentnum", snumber);
			Integer studentnumret=messageMapper.selectCount(snumberWrappers);
			if(studentnumret==0){
				
				
				
				
				
				//添加学生信息表数据
				String studentid = "sid"+UUID.randomUUID().toString();  //学生id
				Student student=new Student();
				student.setS_id(studentid);
				student.setS_student(name);
				System.out.println(student);
				 int ret01=studentMapper.insert(student);
				 
				
				
				//添加学号信息
				Message message=new Message();
				message.setM_id("mid"+UUID.randomUUID().toString());
				message.setM_studentnum(snumber);
				message.setM_number("");
				message.setS_id(studentid);
				 int ret02 = messageMapper.insert(message);
				 
				 //添加班级映射
				 
				 Datas datas = new Datas();
//				 datas.setD_id("did"+UUID.randomUUID().toString());
				 datas.setS_id(studentid);
				 datas.setD_class(sclassesid);
				 
				 
				 int ret03 = datasMapper.insert(datas);
				 
				 if(ret01 >0 && ret02 >0 ){
					 return "添加学生成功";
				 }else
					 return "添加学生失败";
			}else
				return "学号已存在";
		}else
			return "班级不存在";
	}
	
	
	
	public List<String> classesinfo(){
		return classesinfo.ClassesSS();
	}
	
	
	
	
	
	
 
	
}
