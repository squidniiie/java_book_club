<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="/static/style.css" />
<title>Book Club</title>
</head>
<body>
<div class="container col-6 text-center bg-white shadow p-4 mt-6 rounded">
	<h1>Change your Entry</h1>
	<a href="/user/dashboard">Dashboard</a>
<form:form action="/books/${book.id}/edit" method="post" modelAttribute="book">
		<input type="hidden" name="_method" value="put" />
		<form:input type="hidden" path="user" value="${book.getUser().getId()}"></form:input>
		<div class="form-group">
				<p>
					<form:label class="h6" path="title" for="title">Title:</form:label>
					<br />
					 <form:errors path="title" class="text-danger" />
					
					<form:input type="text" path="title"/>
				</p>
					<form:label class="h6" path="author" for="author">Author:</form:label>
					<br />
					<form:errors path="author" class="text-danger" />
					
					<form:input type="text" path="author" />
				<p>
				</p>
				<p>
					<form:label class="h6" path="description" for="description">My Thoughts:</form:label>
					<br />
					<form:errors path="description" class="text-danger" />
					
					<form:textarea path="description"/>
				</p>
	
		<input type="submit" value="Add Updated Book" class="btn btn-outline-info" />
		
		</div>
	</form:form>
	<br />
	<form action="/books/${book.id}" method="post">
    		<input type="hidden" name="_method" value="delete">
    		<input class="btn btn-outline-danger" type="submit" value="Delete">
    	</form>
	</div>
</body>
</html>