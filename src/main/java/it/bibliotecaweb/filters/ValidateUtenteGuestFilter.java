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

import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;

/**
 * Servlet Filter implementation class ValidateUtenteGuestFilter
 */
@WebFilter(urlPatterns = {"/DettagliAutoriServlet", "/ExecuteSearchAutoreServlet", "/ListaAutoriServlet",
		                  "/PrepareSearchAutoriServlet", "/DettagliLibroServlet", "/ExecueteSearchLibroServlet",
		                  "/ListaLibriServlet", "/PrepareSearchLibroServlet", "/dettagliAutore.jsp", "/dettagliLibro.jsp",
		                  "/listaAutori.jsp", "/listaLibri.jsp", "/searchAutore.jsp", "/searchLibro.jsp"})
public class ValidateUtenteGuestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidateUtenteGuestFilter() {
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
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
