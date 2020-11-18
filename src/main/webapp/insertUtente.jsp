<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        "${requestScope.errore}"
		        <h5>Inserisci utente</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteInsertUtenteServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" required>
							</div>
						
							<div class="form-group col-md-3">
								<label>Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Inserire l'username" required>
							</div>
							
							<div class="form-group col-md-3">
								<label>Password</label>
								<input type="text" class="form-control" name="password" id="password" placeholder="Inserire la password" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Ruoli</label>
								<c:forEach items = "${requestScope.listaRuoli}" var = "ruolo"><br>
									<input type="checkbox" class="form-check form-check-inline" id="ruolo" name="ruolo" value="${ruolo.id}" required>
									<c:out value = "${ruolo.codice}" />
								</c:forEach>
							</div> 
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Inserisci</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>