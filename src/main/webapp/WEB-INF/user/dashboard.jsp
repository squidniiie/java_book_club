<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Dashboard</title>
</head>
<body>
<div class="container col-10 text-center bg-white shadow p-4 mt-6 rounded">
	<h1>Hello, ${name}</h1>
	<h6>Books from everyone's shelves:</h6>
	<a href="/logout">logout</a>
	<a href="/new">Create New Book</a>
</div>
<br />
<div class="container col-10 text-center bg-white shadow p-4 mt-6 rounded">
	<h3>Available books to borrow:</h3>
	<div class="d-flex justify-content-center">
	<table>
		<thead>
		<tr>
			<th>ID:</th>
			<th>Title:</th>
			<th>Author:</th>
			<th>User:</th>
			<th>Actions:</th>
		</tr>
		<tr><br /></tr>			
		</thead>
		<tbody>
		<c:forEach items="${books}" var="book">
			<c:if test="${user_id != book.borrowUser.id}">
				<tr>
					<td><c:out value="${book.id}"></c:out></td>
					<td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
					<td><c:out value="${book.author}"></c:out></td>
					<td><c:out value="${book.getUser().getUserName()}"></c:out></td>
					
						<c:if test="${book.user.getId() == user_id}">
							<td><a class="btn btn-outline-warning" href="/books/${book.id}/edit">Edit</a></td>
  							<td>
  								<form action="/books/${book.id}" method="post">
    							<input type="hidden" name="_method" value="delete">
    							<input class="btn btn-outline-danger" type="submit" value="Delete"></form>
    						</td>
    					</c:if>
    					<c:if test="${book.user.id != user_id && book.borrowUser == null}">
							<td><a class="btn btn-outline-primary" href="/books/borrow/${book.id}">Borrow</a></td>
						</c:if>
				</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
	<br />
		<div class="container col-10 text-center bg-white shadow p-4 mt-6 rounded">
			<h3>${name}'s Borrowed Books:</h3>
			<div class="d-flex justify-content-center">
			<br />
			<table>
				<thead>
					<th>ID:</th>
					<th>Title:</th>
					<th>Author:</th>
					<th>User:</th>
					<th>Actions:</th>
				</thead>
				<tbody>
				<c:forEach items="${borrowBooks}" var="borBook">
					<tr>
						<td><c:out value="${borBook.id}"></c:out></td>
						<td><a href="/books/${borBook.id}"><c:out value="${borBook.title}"></c:out></a></td>
						<td><c:out value="${borBook.author}"></c:out></td>
						<td><c:out value="${borBook.getUser().getUserName()}"></c:out></td>
						<td><a class="btn btn-outline-warning" href="/books/return/${borBook.id}">Return Book</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div> 
</body>
</html>