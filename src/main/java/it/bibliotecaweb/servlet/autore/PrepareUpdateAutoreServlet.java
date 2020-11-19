package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateAutoreServlet
 */
@WebServlet("/PrepareUpdateAutoreServlet")
public class PrepareUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateAutoreServlet() {
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
				Autore autore = MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt(id));
				request.setAttribute("autore", autore);
				request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Autore autore = MyServiceFactory.getAutoreServiceInstance().findById(Integer.parseInt((String)request.getAttribute("idParametro")));
				request.setAttribute("autore", autore);
				request.setAttribute("errore", request.getAttribute("errore"));
				request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
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
