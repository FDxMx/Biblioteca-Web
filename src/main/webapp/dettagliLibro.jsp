<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Dettagli Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Dettagli libro</h5> 
		        
		    </div>
		    
		    <div class='card-body'>

				<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${requestScope.libro.id}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Titolo:</dt>
				  <dd class="col-sm-9">${requestScope.libro.titolo}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Genere:</dt>
				  <dd class="col-sm-9">${requestScope.libro.genere}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Trama:</dt>
				  <dd class="col-sm-9">${requestScope.libro.trama}</dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Autore:</dt>
				  <dd class="col-sm-9">${requestScope.libro.autore.nome} ${requestScope.libro.autore.cognome}</dd>
		    	</dl>
			<!-- end card-body -->			   
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>