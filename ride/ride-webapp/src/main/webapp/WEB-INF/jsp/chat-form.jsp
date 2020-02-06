<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>msg chat</title>
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
				<form:form action="saveChat" cssClass="form-horizontal" method="post"  enctype="multipart/form-data" modelAttribute="chat">






                    <div class="form-group">
                    						<label for="message" class="col-md-3 control-label">Message</label>
                    						<div class="col-md-9">
                                                <textarea class="form-control" rows="5" id="message" name="message"></textarea>
                    						</div>
                    					</div>

                    <div class="form-group">
						<div class="col-md-offset-2 col-md-9">
							<button id="submit" cssClass="btn btn-primary">Valider</button>
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