<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test= "${sessionScope.utenteSession == null || sessionScope.utenteSession.stato == 'INATTIVO'}"  >
		<c:redirect url = "index.jsp"/>
</c:if>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Search Autore</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca autore</h5> 
		        
		        <a class="btn btn-primary " href="insertAutore.jsp">Add New Autore</a>
		    
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteSearchAutoreServlet" novalidate="novalidate">
					
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
								<label>Data di nascita</label>
								<input type="date" class="form-control" name="data" id="data" placeholder="Inserire la data di nascita">
							</div>
							
							  <div class="form-group col-md-6">
								<label>Libro</label>
								<select id = "libro" name = "libro" class = "form-control" >
								<option value = ""> Seleziona libro </option>
								<c:forEach items = "${requestScope.listaLibri}" var = "libro">
								<option value ="${libro.id}"/>
								<c:out value = "${libro.titolo}" />
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