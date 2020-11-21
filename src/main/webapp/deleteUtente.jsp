<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Elimina Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Eliminare utente?</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="get" novalidate="novalidate">
					
						<input type="hidden" name="id" id="id" value="${requestScope.id}">
					
						<button type="submit" name="submit" value="submit" id="submit" formaction="ExecuteDeleteUtenteServlet" class="btn btn-primary">Conferma</button>
						<button type="submit" name="submit" value="submit" id="submit" formaction="ListaUtentiServlet" class="btn btn-primary">No</button>
							
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>