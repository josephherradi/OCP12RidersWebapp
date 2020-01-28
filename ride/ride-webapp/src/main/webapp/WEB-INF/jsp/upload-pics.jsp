<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>upload pictures</title>
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
				<form:form action="${pageContext.request.contextPath}/sorties/savepictures" cssClass="form-horizontal" method="post"  enctype="multipart/form-data" modelAttribute="picNamesSortieId">

					<form:hidden path="sortieId" />



                                    <div class="form-group">
                   						<label for="file" class="col-md-3 control-label">Photos</label>
                   						<div class="col-md-9">
            <p>
                <input id="fileInput" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
                selected files: <span id="fileNum">0</span>;
                total size: <span id="fileSize">0</span>
            </p>
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
	<script>
                function updateSize() {
                    var nBytes = 0,
                            oFiles = document.getElementById("fileInput").files,
                            nFiles = oFiles.length;
                    for (var nFileId = 0; nFileId < nFiles; nFileId++) {
                        nBytes += oFiles[nFileId].size;
                    }

                    var sOutput = nBytes + " bytes";
                    // optional code for multiples approximation
                    for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
                        sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
                    }
                    // end of optional code

                    document.getElementById("fileNum").innerHTML = nFiles;
                    document.getElementById("fileSize").innerHTML = sOutput;
                }
            </script>
</body>
</html>