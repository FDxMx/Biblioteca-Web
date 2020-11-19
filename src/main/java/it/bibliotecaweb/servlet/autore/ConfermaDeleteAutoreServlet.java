package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ConfermaDeleteAutoreServlet
 */
@WebServlet("/ConfermaDeleteAutoreServlet")
public class ConfermaDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfermaDeleteAutoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		if (id != null && !id.equals("")) {
			Autore autore = new Autore();
			autore.setId(Integer.parseInt(id));
			if(autore.getLibri().size() < 1) {
				try {
					MyServiceFactory.getAutoreServiceInstance().delete(autore);
					request.setAttribute("effettuato", "Eliminazione effettuata con successo!");
					request.getRequestDispatcher("ListaAutoriServlet").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("errore", "Non puoi eliminare un autore che ha scritto libri!");
			request.getRequestDispatcher("ListaAutoriServlet").forward(request, response);
		}
		request.setAttribute("errore", "Nessun autore selezionato!");
		request.getRequestDispatcher("ListaAutoriServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
