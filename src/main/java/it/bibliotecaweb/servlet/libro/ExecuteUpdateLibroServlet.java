package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateLibroServlet
 */
@WebServlet("/ExecuteUpdateLibroServlet")
public class ExecuteUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String trama = request.getParameter("trama");
		String idAutore = request.getParameter("autore");
		
		if(id != null && !id.equals("") && titolo != null && !titolo.equals("") && genere != null && !genere.equals("") && trama != null && !trama.equals("") && idAutore != null && !idAutore.equals("")) {
			try {
				Autore autore = MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(idAutore));
				Libro libro = new Libro(titolo, genere, trama, autore);
				libro.setId(Integer.parseInt(id));
				for (Libro l : MyServiceFactory.getLibroServiceInstance().list()) {
					if(l.equals(libro)) {
						request.setAttribute("errore", "Attenzione, nessuna modifica effettuata!");
						request.setAttribute("idParametro", id);
						request.getRequestDispatcher("PrepareUpdateLibroServlet").forward(request, response);
					}
				}
				MyServiceFactory.getLibroServiceInstance().update(libro);
				request.setAttribute("effettuato", "Operazione effettuata con successo!");
				request.getRequestDispatcher("ListaLibriServlet").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("errore", "Attenzione, inserire tutti i campi!");
			request.setAttribute("idParametro", id);
			request.getRequestDispatcher("PrepareUpdateLibroServlet").forward(request, response);
		}
	}

}
