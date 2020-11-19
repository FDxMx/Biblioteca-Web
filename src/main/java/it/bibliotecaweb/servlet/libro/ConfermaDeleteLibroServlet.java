package it.bibliotecaweb.servlet.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ConfermaDeleteLibroServlet
 */
@WebServlet("/ConfermaDeleteLibroServlet")
public class ConfermaDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaDeleteLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");

		if (id != null && !id.equals("")) {
			Libro libro = new Libro();
			libro.setId(Integer.parseInt(id));
				try {
					MyServiceFactory.getLibroServiceInstance().delete(libro);
					request.setAttribute("effettuato", "Eliminazione effettuata con successo!");
					request.getRequestDispatcher("ListaLibriServlet").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		request.setAttribute("errore", "Nessun libro selezionato!");
		request.getRequestDispatcher("ListaLibriServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
