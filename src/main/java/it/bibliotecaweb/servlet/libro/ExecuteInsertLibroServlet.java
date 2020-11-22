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
 * Servlet implementation class ExecuteInsertLibroServlet
 */
@WebServlet("/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertLibroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String trama = request.getParameter("trama");
		String autore = request.getParameter("autore");

		try {
			if (titolo == null || genere == null || trama == null || autore == null || titolo.equals("") || genere.equals("") || trama.equals("") || autore.equals("")) {
				request.setAttribute("titolo", titolo);
				request.setAttribute("genere", genere);
				request.setAttribute("trama", trama);
				Autore a = autore != null && !autore.equals("")?  MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(autore)) : null;
				request.setAttribute("autore", a);
				request.setAttribute("errore", MyServiceFactory.getLibroServiceInstance().validate(request));
				request.getRequestDispatcher("PrepareInsertLibroServlet").forward(request, response);
			} else {
				Libro libro = new Libro(titolo, genere, trama, MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(autore)));
				if (MyServiceFactory.getLibroServiceInstance().list().contains(libro)) {
					request.setAttribute("titolo", titolo);
					request.setAttribute("genere", genere);
					request.setAttribute("trama", trama);
					Autore a = autore != null && !autore.equals("")?  MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(autore)) : null;
					request.setAttribute("autore", a);
					request.setAttribute("errore", "Attenzione libro già esistente!");
					request.getRequestDispatcher("PrepareInsertLibroServlet").forward(request, response);
					return;
				} else {
					MyServiceFactory.getLibroServiceInstance().insert(libro);
					request.setAttribute("effettuato", "Operazione effettuata con successo!");
					request.setAttribute("listaLibri", MyServiceFactory.getLibroServiceInstance().list());
					request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
