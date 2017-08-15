package misBeans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

import misClases.Actividad;
import misClases.Ruta;
import misClases.RutaRecorrida;
import misClases.Usuario;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

@ManagedBean
public class ActividadBean {

	private Actividad act = new Actividad();
	private String nombre;
	private List<Actividad> actividades;
	private List<Actividad> actividadesVista;
	
	private String cantidad;

	private JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA

	@PostConstruct
	public void init() {
		 actividades = actividadesVista;
	}

	public Actividad getAct() {
		return act;
	}

	public void setAct(Actividad act) {
		this.act = act;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public List<Actividad> getActividadesVista() {
		return actividadesVista;
	}

	public void setActividadesVista(List<Actividad> actividadesVista) {
		this.actividadesVista = actividadesVista;
	}

	public String verAct() throws IOException{
		actividadesVista = jpaFactory.getActividadesDAO().getAll();
		
		//Codigo para refrescar pagina
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		
		return "/actividades.xhtml?faces-redirect=true";
	}
	
	public void check(Actividad act) {
		if(act.getCantidad() == 0)
			jpaFactory.getActividadesDAO().modify(act);
		if( act.getCantidad() != 0 ) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("No se puede desactivar " + act.getNombre() + 
					" porque tiene una o mas rutas asociadas"));
		}
//		else{
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error de Ingreso", "Usuario inactivo");
//			//FacesContext.getCurrentInstance().addMessage(null, message);
//			RequestContext.getCurrentInstance().showMessageInDialog(message);
//		}
	}
	
	public void validateCheck(FacesContext context, UIComponent component, Object o) {
		HtmlSelectBooleanCheckbox checkBox = (HtmlSelectBooleanCheckbox) component;
		if( act.getCantidad() != 0 ) {			
			FacesMessage message = new FacesMessage("You need to select the mandatory field");
			context.addMessage("LoginForm:staff",message);
		//context.getApplication().getNavigationHandler().handleNavigation(context, "/Welcome.jsp", "error");
		}
	}
	
	
	public String buscar(){
		actividadesVista = jpaFactory.getActividadesDAO().buscar(nombre, cantidad);

		return "/actividades.xhtml?faces-redirect=true";
	}

}
