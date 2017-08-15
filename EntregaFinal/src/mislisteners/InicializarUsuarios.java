package mislisteners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import misClases.Usuario;

/**
 * Application Lifecycle Listener implementation class InicializarUsuarios
 *
 */
@WebListener
public class InicializarUsuarios implements ServletContextListener {

	/**
	 * Default constructor. 
	 */
	public InicializarUsuarios() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  { 
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 * 
	 * el listener contextInitialized lee los usuarios del documento /Recursos/usuarios.txt y carga
	 * en el diccionario usuarios el nombre de usuario y su contraseña, y en el diccionario
	 * tipoUsr el nombre de usuario y sus permisos en la aplicacion.
	 */
	public void contextInitialized(ServletContextEvent sce)  { 
		// TODO Auto-generated method stub
		ServletContext contexto= sce.getServletContext();
		String archUsuarios = contexto.getInitParameter("archivoUsuarios");
		HashMap<String,Usuario> usuarios = new HashMap<String,Usuario>();

		BufferedReader r = null;
		try{
			r = new BufferedReader(new InputStreamReader(contexto.getResourceAsStream(archUsuarios)));

			String linea = r.readLine();
			//Usuario,Password,Tipo,Activo,DNI,apellido,nombre,domicilio,fecha,sexo,email@email
			while (linea != null) {
				String[] partes = linea.split(",");
				String usr = partes[0];
				String pwd = partes[1];
				Boolean tipo;
				if(partes[2].equals("A")){
					tipo = true;
				}else{
					tipo = false;
				}
				Boolean activo;
				if(partes[3].equals("Y")){
					activo = true;
				}else{
					activo = false;
				}
				String dni = partes[4];
				String apellido = partes[5];
				String nombre = partes[6];
				String domicilio = partes[7];
				String fecha = partes[8];
				String sexo = partes[9];
				String email = partes[10];
				//Usuario unUsuario = new Usuario(usr,pwd,nombre,apellido,Integer.parseUnsignedInt(dni),domicilio,fecha,email,sexo,tipo,activo);
				//usuarios.put(usr,unUsuario);
				linea = r.readLine();
			}
			contexto.setAttribute("usuarios",usuarios);
		}
		catch(Exception e) {
		}
		finally{
			if (r!=null) {
				try{r.close();} 
				catch(Exception e) {}
			}
		}
	}
}
