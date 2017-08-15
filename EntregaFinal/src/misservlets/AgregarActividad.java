package misservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misClases.Actividad;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

/**
 * Servlet implementation class AgregarActividad
 */
@WebServlet("/AgregarActividad")
public class AgregarActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarActividad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		
		Actividad act = new Actividad(request.getParameter("nombre"),0,true);
		jpaFactory.getActividadesDAO().create(act);
		response.sendRedirect("actividades.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
