package misservlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misClases.Coordenada;
import misClases.Recorrido;
import misClases.Ruta;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

/**
 * Servlet implementation class AgregarActividad
 */
@WebServlet("/AgregarRuta")
public class AgregarRuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarRuta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		
		List<Coordenada> waypoints = new ArrayList<Coordenada>();
		waypoints.add(new Coordenada(24,24));
		waypoints.add(new Coordenada(28,28));
		waypoints.add(new Coordenada(48,48));
		waypoints.add(new Coordenada(68,68));
		Recorrido rec = new Recorrido(waypoints);
		
		//Ruta rut = new Ruta(request.getParameter("nombre"),jpaFactory.getUsuariosDAO().findById(new Long(1)),"descripcion","privada",rec,"formato",2,"dificultad",jpaFactory.getActividadesDAO().findById(new Long(1)),2,"00-00-0000","foto");
	//	jpaFactory.getRutasDAO().create(rut);
		response.sendRedirect("rutas.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
