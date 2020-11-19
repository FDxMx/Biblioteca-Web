package it.bibliotecaweb.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ConfermaDisattivazioneUtenteServlet
 */
@WebServlet("/ConfermaDisattivazioneUtenteServlet")
public class ConfermaDisattivazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaDisattivazioneUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");

		if (id != null && !id.equals("")) {
				try {
					Utente utente = MyServiceFactory.getUtenteServiceInstance().findById(Integer.parseInt(id));
					MyServiceFactory.getUtenteServiceInstance().passaAInattivo(utente);;
					request.setAttribute("effettuato", "Utente disattivato!");
					request.getRequestDispatcher("ListaUtentiServlet").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		request.setAttribute("errore", "Nessun utente selezionato!");
		request.getRequestDispatcher("ListaUtentiServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
