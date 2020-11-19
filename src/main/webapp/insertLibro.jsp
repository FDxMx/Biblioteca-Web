<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		    	${requestScope.errore}
		        <h5>Inserisci libro</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteInsertLibroServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo</label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere</label>
								<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama</label>
								<input type="text" name="trama" id="trama" class="form-control" placeholder="Inserire la trama" required>
							</div>
							
							  <div class="form-group col-md-6">
								<label>Autore</label>
								<select id = "autore" name = "autore" class = "form-control" required>
								<option value = ""> Seleziona autore </option>
								<c:forEach items = "${requestScope.listaAutori}" var = "autore">
								<option value ="${autore.id}"/>
								<c:out value = "${autore.nome} ${autore.cognome}" />
								</c:forEach>
								</select>
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