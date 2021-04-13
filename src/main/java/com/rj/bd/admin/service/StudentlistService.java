package com.rj.bd.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rj.bd.admin.dao.StudentMapper;
import com.rj.bd.admin.entity.Student;

@Transactional  //注解式事务
@Service("studentlistService")
public class StudentlistService implements IStudentlistService{

	@Autowired
	public StudentMapper studentMapper;
	
	public List<Student> query() {
		return studentMapper.fiandAll();
	}

	
}
