package com.mytest.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 中转类
 * @author Administrator
 *
 */
@Controller
public class transferController {
	
	@RequestMapping("tSocketServiceIM")
	public String tSocketServiceIM(){
		return "";
	}
	
	@RequestMapping("tSocketClientIM")
	public String tSocketClientIM(){
		return "";
	}

}
