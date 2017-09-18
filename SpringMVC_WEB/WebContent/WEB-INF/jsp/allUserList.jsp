<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
登录用户信息：
<table>
<tr>
<td width="140px">用户姓名：</td>
<td width="140px">${user.userName }</td>
</tr>
<tr>
<td width="140px">用户邮箱：</td>
<td width="140px">${user.email }</td>
</tr>
<tr>
<td width="140px">用户角色：</td>
<td width="140px">Root</td>
</tr>
<tr>
<td width="140px"><a href="#">修改用户信息</a></td>
<td width="140px"><a href="#">注销用户信息</a></td>
</tr>
</table>
<br/>
所有用户信息列表：
<table>
<tr>
<td width="140px">用 户  ID</td>
<td width="140px">用户姓名</td>
<td width="140px">用户邮箱</td>
<td width="140px">操       作</td>
</tr>
<c:forEach items="${userList }" var="tUser">
<tr>
<td>${tUser.userID }</td>
<td>${tUser.userName }</td>
<td>${tUser.email }</td>
<td>
<a href="#">修改</a>
<a href="#">查看</a>
<a href="#">通讯</a>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>