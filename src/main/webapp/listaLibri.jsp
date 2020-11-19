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
<title>Lista Libri</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">
		<div class='card'>
			<div class='card-header'>
				${requestScope.effettuato}
				${requestScope.errore}
				<h5>Lista libri</h5>
			</div>
			<div class='card-body'>
			
				<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          		<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
				<a class="btn btn-primary " href="PrepareInsertLibroServlet">Add New Libro</a>
				</c:if>
       	 		</c:forEach>

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Titolo</th>
								<th>Genere</th>
								<th>Trama</th>
								<th>Autore</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="libro" items="${requestScope.listaLibri}">
								<tr>
									<td><c:out value="${libro.id}" /></td>
									<td><c:out value="${libro.titolo}" /></td>
									<td><c:out value="${libro.genere}" /></td>
									<td><c:out value="${libro.trama}" /></td>
									<td><c:out value="${libro.autore.nome} ${libro.autore.cognome}" /></td>
									<td>
										<a class="btn  btn-sm btn-outline-secondary"
										href="DettagliLibroServlet?idParametro=${libro.id}">Dettagli</a>
										
										<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateLibroServlet?idParametro=${libro.id}">Edit</a>
										</c:if>
       	 								</c:forEach>
       	 								
       	 								<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
										<a class="btn btn-outline-danger btn-sm"
										href="DeleteLibroServlet?idParametro=${libro.id}">Delete</a>
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