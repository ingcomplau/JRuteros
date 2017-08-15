package misservlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misClases.Actividad;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

/**
 * Servlet implementation class ModificarActividad
 */
@WebServlet("/ModificarActividad")
public class ModificarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarActividad() {
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
		
		Actividad act = jpaFactory.getActividadesDAO().findById(new Long(request.getParameter("id")));
		sc.setAttribute("actividad", act);
		response.sendRedirect("modificarAct.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		
		Actividad act = jpaFactory.getActividadesDAO().findById(new Long(request.getParameter("id")));
		act.setNombre(request.getParameter("nombre"));
		jpaFactory.getActividadesDAO().modify(act);
		response.sendRedirect("actividades.jsp");
	}

}
