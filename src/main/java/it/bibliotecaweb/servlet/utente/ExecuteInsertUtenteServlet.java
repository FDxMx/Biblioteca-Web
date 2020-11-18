package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertUtenteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] stringRuolo = request.getParameterValues("ruolo");

		if (nome == null || cognome == null || username == null || password == null || stringRuolo == null || nome.equals("") || cognome.equals("") || username.equals("") || password.equals("")) {
			request.setAttribute("errore", "Attenzione, inserire tutti i campi");
			request.getRequestDispatcher("PrepareInsertUtenteServlet").forward(request, response);
		}

		try {
			Set<Ruolo> ruoli = new HashSet<>();
			for (String s : stringRuolo) {
				int id = Integer.parseInt(s);
				Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().findById(id);
				ruoli.add(ruolo);
			}
			Utente utente = new Utente(nome, cognome, username, password, ruoli);
			MyServiceFactory.getUtenteServiceInstance().insert(utente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ListaUtentiServlet").forward(request, response);
	}

}