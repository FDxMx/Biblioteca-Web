<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Search Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca utente</h5> 
		        
		        <a class="btn btn-primary " href="PrepareInsertUtenteServlet">Add New Utente</a>
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteSearchUtenteServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome">
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome">
							</div>
						
							<div class="form-group col-md-3">
								<label>Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Inserire l'username">
							</div>
							
							  <div class="form-group col-md-6">
								<label>Ruolo</label>
								<select id = "ruolo" name = "ruolo" class = "form-control" >
								<option value = ""> Seleziona ruolo </option>
								<c:forEach items = "${requestScope.listaRuoli}" var = "ruolo">
								<option value ="${ruolo.id}"/>
								<c:out value = "${ruolo.codice}" />
								</c:forEach>
								</select>
							</div> 
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>