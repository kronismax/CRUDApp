<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/pages/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
</head>
<body>
	<h1>Users Data</h1>
	<form:form action="user.do" method="POST" commandName="user">
		<table>
			<tr>
				<td>User ID</td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td>IsAdmin</td>
                <td><form:checkbox path="isAdmin" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="action" value="Add" />
					<input type="submit" name="action" value="Edit" /> <input
					type="submit" name="action" value="Delete" /> <input type="submit"
					name="action" value="Search by id" /></td>
			</tr>
		</table>
	</form:form>
	<br>
	<table border="1">
		<th>ID</th>
		<th>Name</th>
		<th>Age</th>
		<th>IsAdmin</th>
		<th>Date of creation</th>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
				<td>${user.isAdmin}</td>
				<td>${user.createdDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>