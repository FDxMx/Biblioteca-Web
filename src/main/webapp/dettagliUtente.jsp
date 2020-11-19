<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Dettagli Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Dettagli utente</h5> 
		        
		    </div>
		    
		    <div class='card-body'>

				<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${requestScope.utente.id}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${requestScope.utente.nome}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${requestScope.utente.cognome}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username:</dt>
				  <dd class="col-sm-9">${requestScope.utente.username}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${requestScope.utente.stato}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruoli:</dt>
				  <c:forEach var="ruolo" items="${requestScope.utente.ruoli}">
				  <c:out value = "${ruolo.codice}" /> <br>
				  </c:forEach>
		    	</dl>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>