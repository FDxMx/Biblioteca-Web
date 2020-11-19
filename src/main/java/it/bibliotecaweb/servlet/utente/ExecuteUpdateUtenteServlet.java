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
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateUtenteServlet() {
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

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] idRuolo = request.getParameterValues("idRuolo");

		if (id != null && !id.equals("") && nome != null && !nome.equals("") && cognome != null && !cognome.equals("") && username != null
				&& !username.equals("") && password != null && !password.equals("") && idRuolo != null  && idRuolo.length != 0) {
			Set<Ruolo> ruoli = new HashSet<>();
			try {
				for (String s : idRuolo) {
					Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().findById(Integer.parseInt(s));
					ruoli.add(ruolo);
				}
				Utente utente = new Utente(nome, cognome, username, password, ruoli);
				utente.setId(Integer.parseInt(id));
				for (Utente u : MyServiceFactory.getUtenteServiceInstance().list()) {
					if(u.equals(utente)) {
						request.setAttribute("errore", "Attenzione, nessuna modifica effettuata!");
						request.setAttribute("idParametro", id);
						request.getRequestDispatcher("PrepareUpdateUtenteServlet").forward(request, response);
					}
				}
				MyServiceFactory.getUtenteServiceInstance().update(utente);
				request.setAttribute("effettuato", "Operazione effettuata con successo!");
				request.getRequestDispatcher("ListaUtentiServlet").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("errore");
			}
		}else {
			request.setAttribute("errore", "Attenzione, inserire tutti i campi!");
			request.setAttribute("idParametro", id);
			request.getRequestDispatcher("PrepareUpdateUtenteServlet").forward(request, response);
		}
	}

}
