<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<em>Welcome! <c:out value="${greeting}"></c:out> <c:out value="${special}"></c:out></em>
<hr />
	<h1>Hello World!</h1>

<hr />

<h2><a href="./AnimalList">Animal List</a></h2>

<hr/>

	<form action="hi">
		Name: <input type="text" name="name"> <input type="submit" value="Submit">
	</form>

</body>
</html>