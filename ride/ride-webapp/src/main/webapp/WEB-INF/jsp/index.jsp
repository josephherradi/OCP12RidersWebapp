<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container" >
		<div class="col-md-8 col-md-offset-3">

			<div class="panel-body">

				<h3>Connexion utilisateur</h3>
				${error }
				<form method="post"
					action="${pageContext.request.contextPath }/account/login">
					<div class="form-group" >
						<div class="col-md-10">

							<label for="Username">Identifiant:</label> <input type="text"
								name="userId" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10">

							<label for="password">mot de passe</label> <input type="password"
								name="password" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10">
							<br> <input type="submit" name="Login">
						</div>
					</div>



				</form>
				<div style="text-align: left">
				<a href="${pageContext.request.contextPath}/account/registrationForm"
					class="button medium hpbottom">Nouvel utilisateur?</a>
			</div>
			</div>
		</div>
	</div>

</body>
</html>