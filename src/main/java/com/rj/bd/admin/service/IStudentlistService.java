package com.rj.bd.admin.service;

import java.util.List;

import com.rj.bd.admin.entity.Datas;
import com.rj.bd.admin.entity.Query;


public interface IStudentlistService {
	public List<Query> query();
	
	public List<Query> studentsearch(String search);
	
	//添加学生
	public String studentadd(String name,String snumber,String sclassesid);
	
	//查询班级
	public List<String> classesinfo();
	
}
