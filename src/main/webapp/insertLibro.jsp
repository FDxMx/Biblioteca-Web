<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    
    <script type="text/javascript">
    
    $(document).ready(function() {

    	$("#form").submit(function( event ) {
    		var messaggioErrore = "";
    	  if ( $( "#titolo" ).val() == "" || $( "#titolo" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo TITOLO obbligatorio!\n";
    	  }
    	  if ( $( "#genere" ).val() == "" || $( "#genere" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo GENERE obbligatorio!\n";
    	  }
    	  if ( $( "#trama" ).val() == "" || $( "#trama" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo TRAMA obbligatorio!\n";
      	  }
    	  if ( $( "#autore" ).val() == "" || $( "#autore" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo AUTORE obbligatorio!\n";
    	  }
    	  if(messaggioErrore != ""){
      		  alert(messaggioErrore);
      		  event.preventDefault();
        	  return;
      	  }
    	});
    });
    </script>
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		    
		    	 <c:forEach items = "${requestScope.errore}" var = "errore">
		        	<p style = color:red ><c:out value = "${errore}"></c:out> <br></p>
		        </c:forEach>
		        
		        <h5>Inserisci libro</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form id="form" method="post" action="ExecuteInsertLibroServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo</label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" value = "${requestScope.titolo}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere</label>
								<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere" value = "${requestScope.genere}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama</label>
								<input type="text" name="trama" id="trama" class="form-control" placeholder="Inserire la trama" value = "${requestScope.trama}" required>
							</div>
							
							  <div class="form-group col-md-6">
								<label>Autore</label>
								<select id = "autore" name = "autore" class = "form-control" required>
								<option value = "${requestScope.autore.id}"> ${requestScope.autore.nome} ${requestScope.autore.cognome}</option>
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