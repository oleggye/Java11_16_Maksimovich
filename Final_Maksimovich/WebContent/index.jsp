<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Index</title>
</head>
<body>

	<c:redirect url="/controller">
		<c:param name="command" value="home" />
	</c:redirect>
</body>
</html>