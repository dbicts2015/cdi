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
<title>Cursusbeheer</title>
</head>
<body>
<div class="container">
	<h2 class="text-center">Cursusbeheer</h2>
	<form action="CursusServlet" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="code">Code: </label>
			<div class="col-sm-10">
			   <input type="text" name="code" id="code" class="form-control" value="${cursusInputBean.code}" placeholder="code"/>
			</div>
		</div>
	    <c:if test="${not empty codefout }">
		   <div class="col-sm-offset-2 alert alert-danger">
		      <a href="#" class="close" data-dismiss="alert">&times;</a>
			  ${codefout}
		   </div>
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="code">Naam: </label>
			<div class="col-sm-10">
			    <input type="text" name="naam" id="naam" class="form-control" value="${cursusInputBean.naam}" placeholder="naam"/>
			</div>
		</div>
		<c:if test="${not empty naamfout }">
		  <div class="col-sm-offset-2 alert alert-danger">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			${naamfout}
		  </div>
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="code">Duur: </label>
			<div class="col-sm-10">
			    <input type="text" name="duur" id="duur" class="form-control" value="${cursusInputBean.duur}" placeholder="duur"/>
			</div>
		</div>
		<c:if test="${not empty duurfout }">
		  <div class="col-sm-offset-2 alert alert-danger">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			${duurfout}
		  </div>
		</c:if>
		<div class="form-group">
		   <div class="col-sm-offset-2 col-sm-10">
		      <input type="submit" value="Voeg cursus toe" class="btn btn-default"/>
		   </div>
		</div>
	</form>
	<table class="table table-bordered table-striped">
		<thead>
			<tr><th>Code</th><th>Naam</th>
			<th>
			   <c:if test="${not empty codenietgevondenfout }">
			      <div class="alert alert-danger">
			          ${codenietgevondenfout }
			      </div>
			   </c:if>
			</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${cursussen}" var="cursus">
				<tr>
					<td>${cursus.code}</td>
					<td>${cursus.naam}</td>
					<td>${cursus.duur}</td>
					<td><a class="btn btn-default" href="CursusServlet?delete=${cursus.code }">Verwijderen</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</body>
</html>