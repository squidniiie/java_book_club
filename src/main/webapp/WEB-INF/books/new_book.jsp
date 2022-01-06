<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Book Club</title>
</head>
<body>
<div class="container col-6 text-center bg-white shadow p-4 mt-6 rounded">
	<h1>Add a Book to Your Shelf</h1>
	<a href="/dashboard">Dashboard</a>
	<form:form action="/new" method="post" modelAttribute="newBook">
		<form:input type="hidden" path="user" value="${user.id }" />
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
	
		<input type="submit" value="Add Book" class="btn btn-outline-info" />
		</div>
	</form:form>
	</div>
</body>
</html>