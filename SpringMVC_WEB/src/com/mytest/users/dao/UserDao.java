package com.mytest.users.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mytest.common.dao.BaseDao;
import com.mytest.users.entity.T_User;

@Repository
public class UserDao extends BaseDao<T_User>{
	
	public T_User getLoginUser(T_User user){
		T_User tUser = super.get("TUserMapper.getLogin", user);
		System.out.println("在Dao层用户邮箱结果："+tUser.getEmail());
		return tUser;
	}
	
	public List<T_User> getUserList(T_User user){
		List<T_User> tUserList = super.getList("TUserMapper.getUserList", user);
		return tUserList;
	}

}
