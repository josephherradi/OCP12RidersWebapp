<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sortie</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">

			<div class="panel-body">

				<br>
				<button type="button" name="back" onclick="history.back()">back</button>

				<br>
		 <h2>Participer à la sortie</h2>

            <div class="form-group">
			<div class="col-md-offset-2 col-md-9">
				<form:form action="/sorties/saveParticipant" cssClass="form-horizontal" method="post"  modelAttribute="laSortie">

					<form:hidden path="sortieId" />
					<form:hidden path="organisateur"/>
					<form:hidden path="nom"/>
					<form:hidden path="description"/>
					<form:hidden path="nbrParticipants"/>
					<form:hidden path="niveau"/>
					<form:hidden path="horspiste"/>
					<form:hidden path="duree"/>
					<form:hidden path="distance"/>
					<form:hidden path="nbrEtapes"/>
					<form:hidden path="filename"/>
					<form:hidden path="statut"/>


                    <div class="form-group">
						<div class="col-md-offset-2 col-md-9">
							<button id="submit" cssClass="btn btn-primary">OK</button>
						</div>
					</div>

				   </form:form>
                    </div>
                  </div>






			</div>
		</div>
	</div>
</body>
</html>