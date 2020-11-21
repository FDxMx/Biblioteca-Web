package it.bibliotecaweb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet Filter implementation class ValidateUtenteAdminFilter
 */
@WebFilter(urlPatterns = {"/DettagliUtenteServlet", "/ExecuteDeleteUtenteServlet", "/ExecuteInsertUtenteServlet", "/ExecuteSearchUtenteServlet",
						  "/ExecuteUpdateUtenteServlet", "/ListaUtentiServlet", "/PrepareDeleteUtenteServlet", "/PrepareInsertUtenteServlet" ,
						  "/PrepareSearchUtenteServlet", "/PrepareUpdateUtenteServlet", "/deleteUtente.jsp", "/dettagliUtente.jsp", "/insertUtente.jsp",
						  "/listaUtenti.jsp", "/searchUtente.jsp", "/updateUtente.jsp"})
public class ValidateUtenteAdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidateUtenteAdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest myRequest = (HttpServletRequest) request;
		HttpServletResponse myResponse = (HttpServletResponse)response;
		
		HttpSession session = myRequest.getSession();

		Utente utente = (Utente)session.getAttribute("utenteSession");

		if (utente == null || utente.getStato().equals(StatoUtente.INATTIVO)) {
			myResponse.sendRedirect(myRequest.getContextPath());
			return;
		} else {
			try {
				Ruolo admin = MyServiceFactory.getRuoloServiceInstance().findById(1);
				if (!utente.getRuoli().contains(admin)) {
					myRequest.setAttribute("errore", "Non hai i permessi per questa operazione!!!");
					session.invalidate();
					myRequest.getRequestDispatcher("index.jsp").forward(myRequest, myResponse);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
