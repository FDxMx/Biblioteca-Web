<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Autore</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
    <script type="text/javascript">
    
    $(document).ready(function() {

    	$("#form").submit(function( event ) {
    		var messaggioErrore = "";
    		if ( $( "#nome" ).val() == "" || $( "#nome" ).val() == null){
        	  	messaggioErrore = messaggioErrore + "Campo NOME obbligatorio!\n";
            }
        	if ( $( "#cognome" ).val() == "" || $( "#cognome" ).val() == null){
        		  messaggioErrore = messaggioErrore + "Campo COGNOME obbligatorio!\n";
        	}
    	    if ( $( "#data" ).val() == "" || $( "#data" ).val() == null){
    		  messaggioErrore = messaggioErrore + "Campo DATA DI NASCITA obbligatorio!\n";
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
		        
		        <h5>Inserisci autore</h5> 
		        
		    </div>
		    <div class='card-body'>

					<form id="form" method="post" action="InsertAutoreServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value = "${requestScope.nome}" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value = "${requestScope.cognome}" required>
							</div>
						
							<div class="form-group col-md-3">
								<label>Data di nascita</label>
								<input type="date" class="form-control" name="data" id="data" placeholder="Inserire la data di nascita" value = "${requestScope.stringData}" required>
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