package com.rj.bd.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.user.entity.User;
import com.rj.bd.user.service.IUserService;

import sun.print.resources.serviceui;

/**
 * 
   @desc    User模块的控制器
 * @author  云帆大师 微信/QQ:909904682
 * @time    2017-8-15
 *
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value={"queryByNameStr"})//将条件查询的值保持在session中
public class UserController {

	@Autowired//自动装配：一个名字叫userService的bean,从Springmvc框架扫描到的@Service注解中去寻找
	public IUserService  userService;
	
	HttpSession session;//因为一会要清空session，所以做成全局的
	
	
	
	@RequestMapping("/query")
	public String queryUser(HttpServletRequest request,User user,Page<User> page,String name){
		System.out.println("UserController:queryUser()");
		System.out.println("name---------->"+name);
		            
		 session=request.getSession();
		 String sesseionName=(String) session.getAttribute("queryByNameStr");
		if(sesseionName==null)
		{
			session.setAttribute("queryByNameStr", name);
		}
		  String  getName=(String) session.getAttribute("queryByNameStr");
		  user.setName(getName);
		  page.setSize(2);
		  List<User> list = userService.findAll(page,user);
		//System.out.println("--------------->"+list.size());
		
		
		request.setAttribute("list", list);//将带有数据的list传递给前台的查询展示页面
		/*在把查询条件保持到session中的时候，不提倡使用user对象，因为容易出错，
		原因是因为在修改，添加等操作的时候会影响到user对象,所以还是建议将一个一个
		的添加查询对象单独的起一个新的名字存入，且@SessionAttributes的value属性的值
		是数组类型的，所以可以直接加即可，如果非要用对象，建议将这些条件查询的属性归为一个
		条件查询对象，例如ConditionQueryObject,然后就可以存对象了
		*
		*
		*/
		request.setAttribute("page", page);
		for (User user2 : list) {
			System.out.println(user2.getId()+"\t"+user2.getName());
		}
		
		return "user/user_list";
	}
	
	
@ResponseBody	
@RequestMapping("/query02")	
public List<User> query02(HttpServletRequest request,User user,Page<User> page,String name)
{
	System.out.println("UserController:queryUser()");
	System.out.println("name---------->"+name);
	            
	 session=request.getSession();
	 String sesseionName=(String) session.getAttribute("queryByNameStr");
	if(sesseionName==null)
	{
		session.setAttribute("queryByNameStr", name);
	}
	  String  getName=(String) session.getAttribute("queryByNameStr");
	  user.setName(getName);
	  page.setSize(2);
	  List<User> list = userService.findAll(page,user);
	request.setAttribute("page", page);
	 return list;
}
	
@RequestMapping("/cleasSession")
public void clearSession(SessionStatus  status)
{
	  System.out.println("clearSession()");
	  status.setComplete();//清除当前处理器通过@SessionAttribute注册的session属性
}
	
	
	
	
	
	
	
@RequestMapping("/addPage")	
public String addPage()
{
	System.out.println("-------进入User模块的添加页面------》");
    return "user/user_add";
}	
	
	

@RequestMapping("/add")	
public String add(User u,HttpServletRequest  request)
{
System.out.println("-------add()------》");


userService.save(u);
return "redirect:/user/query";
}	

@RequestMapping("/editPage")	
public ModelAndView editpage(String id)
{
	ModelAndView modelAndView = new ModelAndView();
	User user = userService.queryById(id);
	modelAndView.addObject("user",user);
	modelAndView.setViewName("user/user_edit");
	return modelAndView;
}	
	

@RequestMapping("/edit")	
public String edit(User user)
{
	userService.eidt(user);
	return "redirect:/user/query.action";
}	

@RequestMapping("delete")
public String delete(String id) {
	userService.delete(id);
	return"redirect:/user/query.action";
}

}
