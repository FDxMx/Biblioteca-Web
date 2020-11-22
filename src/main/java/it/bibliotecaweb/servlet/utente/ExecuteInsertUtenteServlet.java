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
import it.bibliotecaweb.model.StatoUtente;
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
		String[] stringRuolo = request.getParameterValues("idRuolo");

		try {
			if (nome == null || cognome == null || username == null || password == null || stringRuolo == null
					|| nome.equals("") || cognome.equals("") || username.equals("") || password.equals("")
					|| stringRuolo.length == 0) {
				request.setAttribute("nome", nome);
				request.setAttribute("cognome", cognome);
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				Set<Ruolo> ruoli = new HashSet<>();
				if (stringRuolo != null && stringRuolo.length > 0) {
					for (String s : stringRuolo) {
						int id = Integer.parseInt(s);
						Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().findById(id);
						ruoli.add(ruolo);
					}
					request.setAttribute("ruoli", ruoli);
				}
				request.setAttribute("errore", MyServiceFactory.getUtenteServiceInstance().validate(request));
				request.getRequestDispatcher("PrepareInsertUtenteServlet").forward(request, response);
				return;
			} else {
				for (Utente u : MyServiceFactory.getUtenteServiceInstance().list()) {
					if (u.getUsername().equals(username)) {
						request.setAttribute("nome", nome);
						request.setAttribute("cognome", cognome);
						request.setAttribute("username", username);
						request.setAttribute("password", password);
						Set<Ruolo> ruoli = new HashSet<>();
						if (stringRuolo != null && stringRuolo.length > 0) {
							for (String s : stringRuolo) {
								int id = Integer.parseInt(s);
								Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().findById(id);
								ruoli.add(ruolo);
							}
							request.setAttribute("ruoli", ruoli);
						}
						request.setAttribute("errore", "Questo username è gia stato utilizzato!");
						request.getRequestDispatcher("PrepareInsertUtenteServlet").forward(request, response);
						return;
					}
				}
				Set<Ruolo> ruoli = new HashSet<>();
				for (String s : stringRuolo) {
					int id = Integer.parseInt(s);
					Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().findById(id);
					ruoli.add(ruolo);
				}
				Utente utente = new Utente(nome, cognome, username, password, ruoli);
				utente.setStato(StatoUtente.ATTIVO);
				MyServiceFactory.getUtenteServiceInstance().insert(utente);
				request.setAttribute("effettuato", "Operazione effettuata con successo!");
				request.getRequestDispatcher("ListaUtentiServlet").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
