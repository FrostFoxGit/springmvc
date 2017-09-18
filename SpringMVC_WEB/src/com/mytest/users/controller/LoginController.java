package com.mytest.users.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mytest.users.entity.T_User;
import com.mytest.users.service.UserService;

@Controller
public class LoginController {
	
	@Resource
	private UserService userService;

	/**
	 * Model本身不能设置页面跳转的url地址别名或者物理跳转地址，那么我们可以通过控制器方法的返回值来设置跳转url
     * 地址别名或者物理跳转地址
	 * @param user		用户信息
	 * @param model		一个接口， 其实现类为ExtendedModelMap，继承了ModelMap类
	 * @return			跳转url地址别名或者物理跳转地址
	 */
	@RequestMapping(value="/loginUser.do")
	public String getLoginUser(T_User user ,Model model ,HttpServletRequest request ){
		System.out.println("--------------Controller--------------");
		System.out.println("进入Action登录获取用户信息方法");
		System.out.println("登录用户名："+user.getUserName());
		try {
			HttpSession session = request.getSession();
			T_User tUser = this.userService.getLoginUser(user);
			//获取用户登录信息
			model.addAttribute("user", tUser);
			//用户信息放入session中
			session.setAttribute(("userName:"+tUser.getUserName()), tUser);
		} catch (Exception e) {
			System.out.println("异常："+e);
			return "common/error";
		}
		System.out.println("-----------结束----------");
		return "userInfo/loginIndex";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="cancellationUser.do")
	public String cancellationUser(T_User tUser,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("userName:"+tUser.getUserName());
		System.out.println("已从session删除用户："+tUser.getUserName());
		return "userInfo/login";
	}

}
