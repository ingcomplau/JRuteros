package misservlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misClases.*;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/Testing")
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost/phpmyadmin/index.php
		
		JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
		
		Actividad act = null;
		Dificultad dif = null;
		Formato form = null;
		Usuario usr = null;
		Ruta r = null;
		
		for(int i=0;i<4;i++){
			usr = new Usuario("usuario"+i,"clave","nombre","apellido","00000000","domicilio","00-00-0000","test@test"+i+".com","M",false,true);
			jpaFactory.getUsuariosDAO().create(usr);
		}

		Usuario admin = new Usuario("admin","clave","nombreAdmin","apellidoAdmin","00000000","domicilio","00-00-0000","test@testadmin.com","M",true,true);
	    jpaFactory.getUsuariosDAO().create(admin);
	    
		for(int i=0;i<4;i++){
			act = new Actividad("test"+i,0,true);
			dif = new Dificultad("dif"+i);
			form = new Formato("form"+i);

			jpaFactory.getActividadesDAO().create(act);
			jpaFactory.getDificultadesDAO().create(dif);
			jpaFactory.getFormatosDAO().create(form);
			
			List<Coordenada> waypoints = new LinkedList<Coordenada>();
		    waypoints.add(new Coordenada(-34.921439, -57.954848));
		    Recorrido rec = new Recorrido(waypoints);
		    
			r = new Ruta("nombre"+i,jpaFactory.getUsuariosDAO().findById(new Long(1)),"descripcion","privada",rec,form,new Long(100),dif,jpaFactory.getActividadesDAO().findById(new Long(1)),2,"00-00-0000",null);
			

			jpaFactory.getRutasDAO().create(r);
		}
			
		RutaRecorrida rr = new RutaRecorrida(r,usr,5,true);
		jpaFactory.getRutasRecorridasDAO().create(rr);;
		
		response.sendRedirect("index.xhtml");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
