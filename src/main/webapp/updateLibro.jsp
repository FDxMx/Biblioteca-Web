<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Aggiorna Libro</title>
	
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
		    
		    	<p style = color:red >${requestScope.errore}</p>
		        <h5>Aggiorna libro</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form id="form" method="post" action="ExecuteUpdateLibroServlet" novalidate="novalidate">
					
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