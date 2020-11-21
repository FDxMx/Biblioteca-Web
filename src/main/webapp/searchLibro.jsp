<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Search Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		    
		        <h5>Ricerca libro</h5> 
		        
		        <c:forEach var="ruolo" items="${sessionScope.utenteSession.ruoli}">
          		<c:if test="${ruolo.codice == 'ADMIN' or ruolo.codice == 'CLASSIC'}">
		        <a class="btn btn-primary " href="PrepareInsertLibroServlet">Add New Libro</a>
		        </c:if>
       	 		</c:forEach>
		        
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteSearchLibroServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo</label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo">
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere</label>
								<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere">
							</div>
							
							  <div class="form-group col-md-6">
								<label>Autore</label>
								<select id = "autore" name = "autore" class = "form-control" >
								<option value = ""> Seleziona autore </option>
								<c:forEach items = "${requestScope.listaAutori}" var = "autore">
								<option value ="${autore.id}"/>
								<c:out value = "${autore.nome} ${autore.cognome}" />
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