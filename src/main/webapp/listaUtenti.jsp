<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="it.bibliotecaweb.model.Autore"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Lista Utenti</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">
		<div class='card'>
			<div class='card-header'>
				<p style = color:red >${requestScope.errore}</p>
				<p style = color:green >${requestScope.effettuato}</p>
				<h5>Lista utenti</h5>
			</div>
			<div class='card-body'>
			
				<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          		<c:if test="${ruolo.codice == 'ADMIN'}">
				<a class="btn btn-primary " href="PrepareInsertUtenteServlet">Add New Utente</a>
				</c:if>
       	 		</c:forEach>
				
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Stato</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="utente" items="${requestScope.listaUtenti}">
								<tr>
									<td><c:out value="${utente.id}" /></td>
									<td><c:out value="${utente.nome}" /></td>
									<td><c:out value="${utente.cognome}" /></td>
									<td><c:out value="${utente.username}" /></td>
									<td><c:out value="${utente.stato}" /></td>
									<td>
										<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN'}">
										<a class="btn  btn-sm btn-outline-secondary"
										href="DettagliUtenteServlet?idParametro=${utente.id}">Dettagli</a>
										</c:if>
       	 								</c:forEach>
       	 								
										<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN'}">
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateUtenteServlet?idParametro=${utente.id}">Edit</a>
										</c:if>
       	 								</c:forEach>
										
										<c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          								<c:if test="${ruolo.codice == 'ADMIN'}">
										<a class="btn btn-outline-danger btn-sm"
										href="PrepareDeleteUtenteServlet?idParametro=${utente.id}">Elimina</a>
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