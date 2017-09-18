package com.mytest.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/helloWorld")
	public String helloWerld(Model model){
		model.addAttribute("message", "StringMvc第一个");
		return null;
	}
	
	@RequestMapping(value="/login.do")
	public String login(){
		return "login";
	}

}
