<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}">
	<c:redirect url="index.jsp" />
</c:if>

<%@page import="it.bibliotecaweb.model.Autore"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Lista Autori</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">
		<div class='card'>
			<div class='card-header'>
				<h5>Lista autori</h5>
			</div>
			<div class='card-body'>
			
				<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          		<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
				<a class="btn btn-primary " href="insertAutore.jsp">Add New Autore</a>
				</c:if>
       	 		</c:forEach>

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Data di nascita</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="autore" items="${requestScope.listaAutori}">
								<tr>
									<td><c:out value="${autore.id}" /></td>
									<td><c:out value="${autore.nome}" /></td>
									<td><c:out value="${autore.cognome}" /></td>
									<td><c:out value="${autore.dataNascita}" /></td>
									<td>
										<a class="btn  btn-sm btn-outline-secondary"
										href="ShowArticoloServlet?idParametro=${autore.id}">Visualizza</a>
										
										<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateArticoloServlet?idParametro=${autore.id}">Edit</a>
										</c:if>
       	 								</c:forEach>
       	 								
       	 								<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
										<a class="btn btn-outline-danger btn-sm"
										href="DeleteArticoliServlet?idParametro=${autore.id}">Delete</a>
										</c:if>
       	 								</c:forEach>
       	 								
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<!-- end card-body -->
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />
</body>
</html>