<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Aggiorna Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		    
		    	${requestScope.errore}
		        <h5>Aggiorna libro</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteUpdateLibroServlet" novalidate="novalidate">
					
						<input type="hidden" name="id" id="id" value="${requestScope.libro.id}">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo</label>
								<input type="text" name="titolo" id="titolo" class="form-control" value="${requestScope.libro.titolo}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere</label>
								<input type="text" name="genere" id="genere" class="form-control" value="${requestScope.libro.genere}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama</label>
								<input type="text" name="trama" id="trama" class="form-control" value="${requestScope.libro.trama}" required>
							</div>
							
							  <div class="form-group col-md-6">
								<label>Autore</label>
								<select id = "autore" name = "autore" class = "form-control" required>
								<option value = "${requestScope.libro.autore.id}">${requestScope.libro.autore.nome} ${requestScope.libro.autore.cognome}</option>
								<c:forEach items = "${requestScope.listaAutori}" var = "autore">
								<option value ="${autore.id}"/>
								<c:out value = "${autore.nome} ${autore.cognome}" />
								</c:forEach>
								</select>
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