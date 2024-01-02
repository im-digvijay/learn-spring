<!DOCTYPE html>

<html>

<head>

	<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/css/my-test.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/simple-test.js"></script>

</head>

<body>

<h2>Spring MVC Demo - Home Page</h2>

<hr>

<a href="hello/showForm">Hello World Form</a>

<br><br>

<a href="student/showForm">Student Form</a>

<br><br>

<a href="customer/showForm">Customer Form</a>

<br><br>

<img src="${pageContext.request.contextPath}/resources/images/spring-logo.png" 
		width="100px" height="100px" />

<br><br>

<input type="button" onclick="doSomeWork()" value="Click Me" />

</body>

</html>