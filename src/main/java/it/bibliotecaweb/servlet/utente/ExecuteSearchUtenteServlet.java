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
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchUtenteServlet() {
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
		String username = request.getParameter("username");
		String stato = request.getParameter("stato");
		String idRuolo = request.getParameter("ruolo");
		
		try {
			StatoUtente statoEnum = stato != null && !stato.equals("") ? StatoUtente.valueOf(stato) : null;
			Ruolo ruolo = idRuolo != null && !idRuolo.equals("") ? MyServiceFactory.getRuoloServiceInstance().findById(Integer.parseInt(idRuolo)) : null;
			Set<Ruolo> ruoli = new HashSet<>();
			if (ruolo != null) {
				ruoli.add(ruolo);
			}
			Utente utente = new Utente(nome, cognome, username, statoEnum, ruoli);
			Set<Utente> utenti = new HashSet<>();
			utenti = MyServiceFactory.getUtenteServiceInstance().findByExample(utente);
			request.setAttribute("listaUtenti", utenti);
			request.getRequestDispatcher("listaUtenti.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
