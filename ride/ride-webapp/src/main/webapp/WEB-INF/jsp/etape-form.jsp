<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Etape</title>
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
		 <h2>Sortie</h2>

            <div class="form-group">
			<div class="col-md-offset-2 col-md-9">
				<form:form action="saveEtape?sortieId=${letape.sortie.sortieId}" cssClass="form-horizontal" method="post"  enctype="multipart/form-data" modelAttribute="letape">

					<form:hidden path="etapeId" />
					<form:hidden path="nom"/>
					<form:hidden path="distance"/>
					<form:hidden path="latitude"/>
					<form:hidden path="longitude"/>



                    <div class="form-group">
                    						<label for="description" class="col-md-3 control-label">description</label>
                    						<div class="col-md-9">
                                                <textarea class="form-control" rows="5" id="description" name="description"></textarea>
                    						</div>
                    					</div>
                    <div class="form-group">
						<div class="col-md-offset-2 col-md-9">
							<button id="submit" cssClass="btn btn-primary">Submit</button>
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