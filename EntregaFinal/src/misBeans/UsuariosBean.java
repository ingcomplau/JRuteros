package misBeans;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import misClases.*;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

public class UsuariosBean {
	
	private String username;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosVista;
	private JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
	private String busqueda;
	
	@PostConstruct
	public void init() {
		usuarios = usuariosVista;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;		
	}
	
	public void setUsuarios(List<Usuario> value){
		this.usuarios = value;
	}
	
	public List<Usuario> getUsuariosVista() {
		return usuariosVista;
	}

	public void setUsuariosVista(List<Usuario> usuariosVista) {
		this.usuariosVista = usuariosVista;
	}
	
	public void check(Usuario usr) {
		jpaFactory.getUsuariosDAO().modify(usr);
	}
	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	public String verUsr() {
		usuariosVista = jpaFactory.getUsuariosDAO().getAll();		
		
		return "/usuarios.xhtml?faces-redirect=true";

	}
	
	public String buscar(){
		usuariosVista = jpaFactory.getUsuariosDAO().buscar(busqueda);

		return "/usuarios.xhtml?faces-redirect=true";
	}
}
