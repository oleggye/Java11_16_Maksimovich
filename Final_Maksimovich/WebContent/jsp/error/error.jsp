<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Error Page</title>
</head>
<body>
	<h1 align="center">ERROR PAGE!</h1>
	<c:out value="${errorMessage}"></c:out>
</body>
</html>