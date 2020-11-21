package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
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
 * Servlet implementation class ExecuteSearchAutoreServlet
 */
@WebServlet("/ExecuteSearchAutoreServlet")
public class ExecuteSearchAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchAutoreServlet() {
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
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("data");
		String idLibro = request.getParameter("libro");
		
		try {
			Libro libro = idLibro != null && !idLibro.equals("") ? MyServiceFactory.getLibroServiceInstance().findById(Integer.parseInt(idLibro)) : null;
			Set<Libro> libri = new HashSet<>();
			if (libro!=null) {
				libri.add(libro);				
			}
			LocalDate data = dataNascita != null && !dataNascita.equals("") ? LocalDate.parse(dataNascita) : null;
			Autore autore = new Autore(nome, cognome, data, libri);
			Set<Autore> autori = new HashSet<>();
			autori = MyServiceFactory.getAutoreServiceInstance().findByExample(autore);
			request.setAttribute("listaAutori", autori);
			request.getRequestDispatcher("listaAutori.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
