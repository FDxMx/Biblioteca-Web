package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteSearchLibroServlet
 */
@WebServlet("/ExecuteSearchLibroServlet")
public class ExecuteSearchLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchLibroServlet() {
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
		String idAutore = request.getParameter("autore");

			try {
				Autore autore = idAutore != null && !idAutore.equals("") ? MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(idAutore)) : null;
				Libro libro = new Libro(titolo, genere, autore);
				Set<Libro> libri = new HashSet<>();
				libri = MyServiceFactory.getLibroServiceInstance().findByExample(libro);
				request.setAttribute("listaLibri", libri);
				request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
