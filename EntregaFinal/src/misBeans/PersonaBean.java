package misBeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

import misClases.Emailer;
import misClases.Ruta;
import misClases.RutaRecorrida;
import misClases.Usuario;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

@ManagedBean
public class PersonaBean {

	private Usuario usr = new Usuario();
	private String fechaActual;
	private String clave;
	private String usuario;
	private Long idRutaVotar;
	private int puntajeRuta;
	private JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
	private List<String> images;
	
	@PostConstruct
	public void init() {
		  images = new ArrayList<String>();
          for (int i = 1; i <= 3; i++) {
              images.add("MapM" + i + ".jpg");
          }
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}
	
	public String getFechaActual() {
		DateFormat formFecha = new SimpleDateFormat("dd/MM/yy");
		fechaActual = formFecha.format(new Date()); 
	    return fechaActual;
	}
	 
	 public String login(){
		 Usuario unUsuario = jpaFactory.getUsuariosDAO().recuperarUsuario(this.usuario);
		 usr = unUsuario;
		 if(unUsuario != null){
			 if(unUsuario.getClave().equals(this.clave)){
				 if(unUsuario.isAdministrador()){
					 return "loginExitoAdministrador";
				 }else{
					 if(unUsuario.getIsActivo()){
						 return "loginExitoUsuario";
					 }else{
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Ingreso", "Usuario inactivo"));	
						 usr = new Usuario();
						 return "loginFracaso";
					 }
				 }
			 }else{
				 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Ingreso", "Usuario/Contraseña invalidos"));	
				 	usr = new Usuario();
				 	return "loginFracaso";
			 }
		 }else{
			 	//Puede que sea un usuario erroneo
			 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Ingreso", "Usuario/Contraseña invalidos"));	
			 	usr = new Usuario();
			 	return "loginFracaso";
		 }	 
	}

	 public String logout(){
		 this.usr = new Usuario();
		 this.clave = "";
		 this.usuario = "";
		 return "/index.xhtml?faces-redirect=true";	
	 }
	 
	 public String register(){
		 	Usuario unUsuario = jpaFactory.getUsuariosDAO().recuperarUsuario(usr.getUsuario());
			String claveUsuario = UUID.randomUUID().toString().substring(0, 8);
			
			if (unUsuario == null){
				usr.setClave(claveUsuario);
				usr.setAdministrador(false);
				usr.setIsActivo(true);
				jpaFactory.getUsuariosDAO().create(usr);
				mandarMail();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Su contraseña fue enviada a su mail:"+usr.getEmail());
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
		    	this.usr = new Usuario();
				return "registroExito";
			}
			else{		
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro erroneo", "Usuario ya existente");
		        RequestContext.getCurrentInstance().showMessageInDialog(message);	
				this.usr = new Usuario();
				return "registroFracaso";
			}				
		}
	
	 private void mandarMail(){
		 String[] emailList = {usr.getEmail()};
		 Emailer smtpMailSender = new Emailer();
		 try {
			 smtpMailSender.postMail( emailList,usr.getClave(),usr);
		 } catch (AuthenticationFailedException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 } catch (MessagingException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
	 
	 public String cancelarRegister(){
		 this.usr = new Usuario();
		 return "registroCancelar";
	 }
	 
	 public String loadUser(){
		 usr = jpaFactory.getUsuariosDAO().recuperarUsuario(this.usuario);
		 return "perfilUsuario";
	 }
	
	 public String modify(){
		 jpaFactory.getUsuariosDAO().modify(usr);
		 return "value";
	 }
	 
	 public String cambiarContraseña(){
		 jpaFactory.getUsuariosDAO().modify(usr);
		 return "value";
	 }

	public Long getIdRutaVotar() {
		return idRutaVotar;
	}

	public void setIdRutaVotar(Long idRutaVotar) {
		this.idRutaVotar = idRutaVotar;
	}
	
	public int getPuntajeRuta() {
		return puntajeRuta;
	}

	public void setPuntajeRuta(int puntajeRuta) {
		this.puntajeRuta = puntajeRuta;
	}

	public void votarRuta(){
		Ruta rut = jpaFactory.getRutasDAO().findById(this.idRutaVotar);
		RutaRecorrida rutRecorrida = new RutaRecorrida(rut,this.usr,this.puntajeRuta,true);
		jpaFactory.getRutasRecorridasDAO().create(rutRecorrida);
	}
	 
	 public List<String> getImages() {
	        return images;
	 }
	 
	 public String irRegistrarse(){
		 this.usr = new Usuario();
		 return "registrarseExito";
	 }
}
