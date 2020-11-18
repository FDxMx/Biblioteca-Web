package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class InsertAutoreServlet
 */
@WebServlet("/InsertAutoreServlet")
public class InsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAutoreServlet() {
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
		String stringData = request.getParameter("data");
		
		if(nome == null || cognome == null || stringData == null || nome.equals("") || cognome.equals("") || stringData.equals("")) {
			request.setAttribute("errore", "Attenzione, inserire tutti i campi");
			request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
		}
		LocalDate data = LocalDate.parse(stringData);
		Autore autore = new Autore(nome, cognome, data);
		try {
			MyServiceFactory.getAutoreServiceInstance().insert(autore);
			request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaAutori.jsp").forward(request, response);
	}

}
