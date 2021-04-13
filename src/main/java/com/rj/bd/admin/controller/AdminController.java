package com.rj.bd.admin.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rj.bd.admin.entity.User;
import com.rj.bd.admin.service.IIndexService;
import com.rj.bd.admin.service.ILoginService;
import com.rj.bd.admin.utils.Putdata;

import sun.print.resources.serviceui;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private ILoginService loginService;
	
	
	@Autowired 
	private IIndexService iindexService;
	
	
	@ResponseBody
	@RequestMapping("/login")
	public Map<String, Object> loginAdmin(User u,HttpServletRequest request){
		System.out.println("登录页面");
		System.out.println(u);
		if(u!=null){
			if (u.getName()!=null  && u.getPassword() != null) {
				User userinfo = loginService.Login(u);
				if (userinfo != null ) {
					String token = userinfo.getName() +userinfo.getPassword() +request.getSession().getId();
					//吧token 存入数据库中
					userinfo.setToken(token);
					 boolean ret = loginService.putToken(userinfo);
					if(ret){
					    User retuser =	new User();
					    retuser.setName(u.getName());
					    retuser.setToken(token);
						return Putdata.printf(200, "登录成功", retuser);
					}
				}
			}
		}
		return Putdata.printf(-1, "登录失败", null);
	}
	
	//首页数据
	@ResponseBody  //返回文本内容
	@RequestMapping("/index") //api地址
	public Map<String, Object>  indexinfo(User u) {
		//1.判断穿过来的参数是否为空
		if(u!=null){
			//2. 判断有没token字段
			if(u.getToken()!=null){
				//3.验证token
				User tokeninfo = loginService.verifyToken(u);
				//4.判断tokeninfo是否为空
				if (tokeninfo!=null) {
					//这里已经验证成功了
					System.out.println("验证成功");
					 Map<String	,Object> map=new HashMap<String, Object>();
					map.put("cardsum", iindexService.getcardsum() );
					map.put("cardlose", iindexService.getcardlose());
					map.put("grossmoney", iindexService.getgrossmoney());
					map.put("studentsum", iindexService.getstudentsum());
					return Putdata.printf(200, "获取成功", map);
				}else {
					return Putdata.printf(-3, "token失效", null);
				}
			}else{
				return Putdata.printf(-2, "没有token字段", null);
			}
		}else{
			return Putdata.printf(-1, "参数为空", null);
		}
	}
}
