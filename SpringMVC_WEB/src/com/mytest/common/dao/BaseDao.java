package com.mytest.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository
public class BaseDao<T> extends SqlSessionDaoSupport{
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	public T get(String statementName,T parameterObject){
		System.out.println("获取对象");
		System.out.println("调用的配置："+statementName+"  参数内存地址："+parameterObject);
		T t = sqlSessionFactory.openSession().selectOne(statementName,parameterObject);
		return t;
	}
	
	public List<T> getList(String statementName,T parameterObject){
		System.out.println("获取对象List集合");
		System.out.println("调用的配置："+statementName+"  参数内存地址："+parameterObject);
		List<T> list = sqlSessionFactory.openSession().selectList(statementName, parameterObject);
		System.out.println("对象数组长度："+list.size());
		return list;
	}

}
