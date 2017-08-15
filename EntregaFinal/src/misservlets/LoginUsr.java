package misservlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import misClases.Usuario;

/**
 * Servlet implementation class LoginUsr
 */
@WebServlet("/LoginUsr")
public class LoginUsr extends HttpServlet {

	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUsr() {
		super();     
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		ServletContext contexto= this.getServletContext();

		HashMap<String,Usuario> usuarios = (HashMap<String,Usuario>)contexto.getAttribute("usuarios");
		Usuario unUsuario = usuarios.get(usr);
		if(unUsuario.getClave().equals(pwd)){
			if(unUsuario.getIsActivo()){
				HttpSession ses = request.getSession();
				ses.setAttribute("usr", usr);
				if(unUsuario.isAdministrador()) {
					ses.setAttribute("tipo", "A");
					//				request.getRequestDispatcher("/WEB-INF/admin.html").include(request, response);
					RequestDispatcher dispatcher = contexto.getRequestDispatcher("/Admin");
					if (dispatcher!=null) 
						dispatcher.forward(request,response);
				}
				else {
					ses.setAttribute("tipo", "U");
					RequestDispatcher dispatcher = contexto.getRequestDispatcher("/User");
					if (dispatcher!=null) 
						dispatcher.forward(request,response);
				}
			}else{
				//Usuario inactivo
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE> Usuario Inactivo </TITLE>");
				out.println("</HEAD>");
				out.println("<BODY>");
				out.println("<CENTER> <H1> Usuario inactivo. Por favor contacte al administrador del sistema.</H1> </CENTER>");
				out.println("<a href='http://localhost:8080/Prototipo/login.html'>Login</a>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.close();
			}
		}else{
			//Usuario y contraseña erroneo
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE> Error </TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("<CENTER> <H1> Usuario o contraseña incorrecta.</H1> </CENTER>");
			out.println("<a href='http://localhost:8080/Prototipo/login.html'>Login</a>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.close();
		}
	}


}