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

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String stringData = request.getParameter("data");

		try {
			if (nome == null || cognome == null || stringData == null || nome.equals("") || cognome.equals("")
					|| stringData.equals("")) {
				request.setAttribute("nome", nome);
				request.setAttribute("cognome", cognome);
				request.setAttribute("stringData", stringData);
				request.setAttribute("errore", MyServiceFactory.getAutoreServiceInstance().validate(request));
				request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
			} else {
				LocalDate data = LocalDate.parse(stringData);
				Autore autore = new Autore(nome, cognome, data);
				if(MyServiceFactory.getAutoreServiceInstance().list().contains(autore)) {
					request.setAttribute("nome", nome);
					request.setAttribute("cognome", cognome);
					request.setAttribute("stringData", stringData);
					request.setAttribute("errore", "Attenzione autore già esistente!");
					request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
				} else {
					MyServiceFactory.getAutoreServiceInstance().insert(autore);
					request.setAttribute("effettuato", "Operazione effettuata con successo!");
					request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().list());
					request.getRequestDispatcher("listaAutori.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
