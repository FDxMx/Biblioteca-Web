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
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idParametro");
		
		if(id != null && !id.equals("")) {
			try {
				Utente utente = MyServiceFactory.getUtenteServiceInstance().findById(Integer.parseInt(id));
				request.setAttribute("utente", utente);
				request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().list());
				request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Utente utente = MyServiceFactory.getUtenteServiceInstance().findById(Integer.parseInt((String)request.getAttribute("idParametro")));
				request.setAttribute("utente", utente);
				request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().list());
				request.setAttribute("errore", request.getAttribute("errore"));
				request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
