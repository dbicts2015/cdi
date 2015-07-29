<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Onze cursussen</title>
</head>
<body>
<div class="container">
De startpagina: <c:url value="/StartServlet" var="s"></c:url>
<!-- <a class="btn btn-default" href="${s}">Naar de servlet</a> -->
<form action="StartServlet">
	Filter: <input type="text" name="filter" />
	<input type="submit" value="filteren" />
</form>
<c:if test="${not empty lijst}">
	<table class="table table-striped">
	<thead><tr><th>Cursussen</th></tr></thead>
	<tbody>
		<c:forEach items="${lijst }" var="cursus">
			<tr><td>${cursus}</td></tr>
		</c:forEach>
		</tbody>
	</table>
</c:if>
</div> <!-- .container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</body>
</html>