<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Aggiorna Autore</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		    	
		    	<p style = color:red >${requestScope.errore}</p>
		        <h5>Aggiorna autore</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteUpdateAutoreServlet" novalidate="novalidate">
					
					<input type="hidden" name="id" id="id" value="${requestScope.autore.id}">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" value= "${requestScope.autore.nome}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" value="${requestScope.autore.cognome}" required>
							</div>
						
							<div class="form-group col-md-3">
								<label>Data di nascita</label>
								<input type="date" class="form-control" name="data" id="data" value = "${requestScope.autore.dataNascita}" required>
							</div>
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Aggiorna</button>
					</form>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>