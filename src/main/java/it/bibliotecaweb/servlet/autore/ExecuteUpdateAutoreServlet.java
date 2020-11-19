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
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateAutoreServlet() {
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
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String stringData = request.getParameter("data");
		
		if(id != null && !id.equals("")  && nome != null && !nome.equals("") && cognome != null && !cognome.equals("") && stringData != null && !stringData.equals("")) {
			LocalDate data = LocalDate.parse(stringData);
			Autore autore = new Autore(nome, cognome, data);
			autore.setId(Integer.parseInt(id));
			try {
				if(MyServiceFactory.getAutoreServiceInstance().list().contains(autore)) {
					request.setAttribute("errore", "Attenzione, nessuna modifica effettuata!");
					request.setAttribute("idParametro", id);
					request.getRequestDispatcher("PrepareUpdateAutoreServlet").forward(request, response);
				}
				MyServiceFactory.getAutoreServiceInstance().update(autore);
				request.setAttribute("effettuato", "Operazione effettuata con successo!");
				request.getRequestDispatcher("ListaAutoriServlet").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("errore", "Attenzione, inserire tutti i campi!");
			request.setAttribute("idParametro", id);
			request.getRequestDispatcher("PrepareUpdateAutoreServlet").forward(request, response);
		}
	}

}
