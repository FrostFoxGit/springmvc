package com.mytest.users.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mytest.users.dao.UserDao;
import com.mytest.users.entity.T_User;


@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public T_User getLoginUser(T_User user){
		return this.userDao.getLoginUser(user);
	}
	
	public List<T_User> getUserList(T_User user){
		return this.userDao.getUserList(user);
	}

}
