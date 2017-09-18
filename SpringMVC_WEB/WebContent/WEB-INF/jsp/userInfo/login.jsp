<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
欢迎来到测试登录系统
<form name="mvcForm" action="loginUser.do" method="post">
	<table>
	<tr>
		<td>username: </td>
		<td>
		<input name="userName" type="text" value=""/>
		</td>
	<tr>
	<tr>
		<td>password: </td>
		<td>
		<input name="password" type="text" value=""/>
		</td>
	<tr>
	</table>
	<input type="submit" value="登录">
	<input type="submit" value="注册">
</form>
</body>
</html>