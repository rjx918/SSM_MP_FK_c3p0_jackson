package com.rj.bd.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes(value={"queryByNameStr"})
public class AdminController {
	
	
	@RequestMapping("/login")
	public String loginAdmin(){
		
		
		return "";
	}

}
