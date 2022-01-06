<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Book Club</title>
</head>
<body>
<div class="container col-10 text-center bg-white shadow p-4 mt-6 rounded">
<h1><c:out value="${oneBook.title}"></c:out></h1>
<h5><c:out value="${oneBook.description}"></c:out></h5>
<c:if test="${oneBook.user.getId() == user_id}">
	<a class="btn btn-outline-info" href="/books/${oneBook.id}/edit">Edit</a>
</c:if>
<a href="/user/dashboard">back to home</a>
</div>
</body>
</html>