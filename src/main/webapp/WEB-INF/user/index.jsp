<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Login and Registration</title>
</head>
<body>
<div  class="text-center">
<h1>Book Club</h1>
<h6>A place for friend to share thoughts on books</h6>
</div>

<div class="container col-6 text-center bg-white shadow p-4 mt-6 rounded">
<h1>Create New User</h1>
<form:form action="/register" method="post" modelAttribute="newUser">
		<div class="form-group">
			<label>User Name:</label>
			<form:input path="userName" class="form-control" />
			<form:errors path="userName" class="text-danger" />
		</div>
		<div class="form-group">
			<label>Email:</label>
			<form:input path="email" class="form-control" />
			<form:errors path="email" class="text-danger" />
		</div>
		<div class="form-group">
			<label>Password:</label>
			<form:password path="password" class="form-control" />
			<form:errors path="password" class="text-danger" />
		</div>
		<div class="form-group">
			<label>Confirm Password:</label>
			<form:password path="confirm" class="form-control" />
			<form:errors path="confirm" class="text-danger" />
		</div>
		<br />
		<input type="submit" value="Register" class="btn btn-outline-info" />
	</form:form>
	</div>
	<br />
<div class="container col-6 text-center bg-white shadow p-4 mb-6 rounded">
<h1>Login</h1>
	<form:form action="/login" method="post" modelAttribute="newLogin">
		<div class="form-group">
		<form:errors path="*" class="text-danger"/>
			<label>Email:</label>
			<form:input path="email" class="form-control" />

		</div>
		<div class="form-group">
			<label>Password:</label>
			<form:password path="password" class="form-control" />

		</div>
		<br />
		<input type="submit" value="Login" class="btn btn-outline-success" />
	</form:form>
</div>
</body>
</html>