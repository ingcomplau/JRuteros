package misservlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misClases.Actividad;
import misClases.Ruta;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

/**
 * Servlet implementation class ModificarRuta
 */
@WebServlet("/ModificarRuta")
public class ModificarRuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarRuta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		ServletContext sc = this.getServletContext();
		
		Ruta rut = jpaFactory.getRutasDAO().findById(new Long(request.getParameter("id")));
		sc.setAttribute("rutas", rut);
		response.sendRedirect("modificarRut.jsp");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		
		Ruta rut = jpaFactory.getRutasDAO().findById(new Long(request.getParameter("id")));
		rut.setNombre(request.getParameter("nombre"));
		jpaFactory.getRutasDAO().modify(rut);
		response.sendRedirect("rutas.jsp");
	}

}
