package misBeans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import misClases.Actividad;
import misClases.Coordenada;
import misClases.Dificultad;
import misClases.Formato;
import misClases.Imagen;
import misClases.Recorrido;
import misClases.Ruta;
import misClases.RutaRecorrida;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

@ManagedBean
public class RutaBean {
	
	JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
	
	private Ruta rut = new Ruta();
	private List<Coordenada> rutaAux;
	
	private UploadedFile file;
	
	private List<Ruta> rutas;
	private List<Ruta> rutasBusquedaRadial;
	private List<Ruta> rutasVista = jpaFactory.getRutasDAO().getAll();
	
	private String puntoParaBusqueda;
	private double radioEnMetros;
	
	//Agregado para filtrar
	private List<String> nombres;

	private List<Actividad> actividades;
	private List<Dificultad> dificultades;
	private List<Formato> formatos;
	private String distancia;
	private String dificultad;
	private int actividad;
	private String formato;
	private String nombre;
	
	private List<String> actSeleccionadas; 
	private List<Long> distancias;

	
	private Long rutaId;
	
	private RutaRecorrida rr;	
	private Integer puntaje;
	private boolean done;
	private Integer valoracion;
	
	private UploadedFile filekml;
	private boolean isArchivoKML;
	
	//Imagenes
	List<String> images;
	
	private int veces;
	
//	@ManagedProperty(value="#{personaBean}")
//    private PersonaBean personaBean;
//
//    public PersonaBean getPersonaBean()
//    {
//    return personaBean;
//    }
//
//    public void setPersonaBean(PersonaBean personaBean)
//    {
//    this.personaBean = personaBean;
//    }
	
	
	
	public List<String> getNombres() {
		return nombres;
	}


	public String getPuntoParaBusqueda() {
		return puntoParaBusqueda;
	}


	public void setPuntoParaBusqueda(String puntoParaBusqueda) {
		this.puntoParaBusqueda = puntoParaBusqueda;
	}


	public double getRadioEnMetros() {
		return radioEnMetros;
	}


	public void setRadioEnMetros(double radioEnMetros) {
		this.radioEnMetros = radioEnMetros;
	}


	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}
	
	public Integer getValoracion() {
		return valoracion;
	}


	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}


	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public RutaRecorrida getRr() {
		return rr;
	}


	public void setRr(RutaRecorrida rr) {
		this.rr = rr;
	}

	@PostConstruct
	public void init(){
		actividades = jpaFactory.getActividadesDAO().getAll();
		actSeleccionadas = new LinkedList<String>();
		dificultades = jpaFactory.getDificultadesDAO().getAll();
		formatos = jpaFactory.getFormatosDAO().getAll();
		rutas = rutasVista;
		nombres = new LinkedList<String>();
		this.distancia = "";

		this.isArchivoKML = false;
		this.filekml = null;

		if(rutas != null){
			for(Ruta r : rutasVista){
				nombres.add(r.getNombre());
			}
		}
		rut = new Ruta();
	}

	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	
	public Long getRutaId() {
		return rutaId;
	}

	public void setRutaId(Long rutaId) {
		this.rutaId = rutaId;
	}
    
	public Ruta getRut() {
		return rut;
	}

	public void setRut(Ruta rut) {
		this.rut = rut;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	
	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<String> getActSeleccionadas() {
		return actSeleccionadas;
	}

	public void setActSeleccionadas(List<String> actSeleccionadas) {
		this.actSeleccionadas = actSeleccionadas;
	}
	
	public List<Long> getDistancias() {
		return distancias;
	}

	public void setDistancias(List<Long> distancias) {
		this.distancias = distancias;
	}

	public List<Dificultad> getDificultades() {
		return dificultades;
	}

	public void setDificultades(List<Dificultad> dificultades) {
		this.dificultades = dificultades;
	}

	public List<Formato> getFormatos() {
		return formatos;
	}

	public void setFormatos(List<Formato> formatos) {
		this.formatos = formatos;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	
	public int getActividad() {
		return actividad;
	}


	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}	
	
	public int getVeces() {
		return veces;
	}

	public void setVeces(int veces) {
		this.veces = veces;
	}	

	


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public String guardarRuta(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		PersonaBean personaBean = (PersonaBean) elContext.getELResolver().getValue(elContext, null, "PersonaBean");
		rut.setUsuario(personaBean.getUsr());
		rut.setActividad(jpaFactory.getActividadesDAO().findById(new Long(actividad)));
		rut.setDificultad(jpaFactory.getDificultadesDAO().findById(new Long(dificultad)));
		rut.setFormato(jpaFactory.getFormatosDAO().findById(new Long(formato)));
		rut.setDistancia(new Long(distancia));
		
		//Setea un recorrido vacio
		List<Coordenada> waypoints = new LinkedList<Coordenada>();
	    //waypoints.add(new Coordenada(-34.921439, -57.954848));
	    Recorrido rec = new Recorrido(waypoints);
		rut.setRecorrido(rec);
		
	  	if(isArchivoKML)	// Si se cargo un archivo KML cargo los puntos
    	{
    		try {
				parseKml();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		isArchivoKML = false;
    	}
    	this.filekml = null;
		
		jpaFactory.getRutasDAO().create(rut);
		
		rutaId=rut.getId();
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = "/resources/images/" + rutaId;
        String filePath = ec.getRealPath(path);
        
        boolean carpeta = new File(filePath).mkdirs();
        
		return "agregarRutaExito";
	}
	
	public String guardarRutaModificada(){
		
		rut.setActividad(jpaFactory.getActividadesDAO().findById(new Long(actividad)));
		rut.setDificultad(jpaFactory.getDificultadesDAO().findById(new Long(dificultad)));
		rut.setFormato(jpaFactory.getFormatosDAO().findById(new Long(formato)));
		rut.setDistancia(new Long(distancia));
		
	  	if(isArchivoKML)	// Si se cargo un archivo KML cargo los puntos
    	{
    		try {
    			//Setea un recorrido vacio
    			List<Coordenada> waypoints = new LinkedList<Coordenada>();
    		    //waypoints.add(new Coordenada(-34.921439, -57.954848));
    		    Recorrido rec = new Recorrido(waypoints);
    			rut.setRecorrido(rec);
				parseKml();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		isArchivoKML = false;
    	}
    	this.filekml = null;
		
		jpaFactory.getRutasDAO().modify(rut);
		
		rutaId=rut.getId();
		
		return "agregarRutaExito";
	}
	
	public String agregarRuta() throws IOException{
		return todasRutas();
	}
	
	public String cancelarRuta(){
		Ruta rut2 = jpaFactory.getRutasDAO().findById(this.rutaId);
		jpaFactory.getRutasDAO().delete(rut2);
		return "agregarRutaFracaso";
	}
	
	public String cancelarRutaModificada(){
		Ruta rut2 = jpaFactory.getRutasDAO().findById(this.rutaId);
		rut2.getRecorrido().setCoordenadas(rutaAux);
		jpaFactory.getRutasDAO().modify(rut2);
		return "agregarRutaExito";
	}
	
	public String atrasRuta(){
		return "agregarRutaAtras";
	}
	
	public void uploadFoto(FileUploadEvent event) {  
		UploadedFile file = event.getFile();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = "/resources/images/" + rutaId + "/%s";
        String filePath = ec.getRealPath(String.format(path,file.getFileName()));
        File f = new File(path);
        
        System.out.println(path);
        //String carpeta = "C:\\Fotos\\" + rut.getId().toString();
        
        //String foto = carpeta + "\\" + file.getFileName();
        
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
     //       String filename = File(i)
         //   File.separator
            fos.write(file.getContents());
            fos.flush();
            fos.close();
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
	 }
	
	public String agregarImagenes() {
		return "agregarImagenesExito";
	}
	
	public String detalle() throws IOException{
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		PersonaBean personaBean = (PersonaBean) elContext.getELResolver().getValue(elContext, null, "PersonaBean");
		
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		
		if(id != null){
			rut = jpaFactory.getRutasDAO().findById(new Long(id));
			rutaId=rut.getId();
		}
		
		rr = jpaFactory.getRutasRecorridasDAO().buscar(personaBean.getUsr().getId(), rut.getId());
		
		if (rr != null) {
			puntaje = rr.getPuntaje();	
			done = rr.isRecorrida();
		}
		else {
			puntaje = 0;
			done = false;
		}
		
		valoracion = jpaFactory.getRutasRecorridasDAO().promedio(rut.getId());
		veces = jpaFactory.getRutasRecorridasDAO().veces(rut.getId());
						
		images = new LinkedList<String>();
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = "/resources/images/" + rutaId;
        String filePath = ec.getRealPath(path);
        
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null){
            for (int i = 0; i < listOfFiles.length; i++) {
	          if (listOfFiles[i].isFile()) {
	            //images.add(filePath + "\\" + listOfFiles[i].getName());
	        	images.add(listOfFiles[i].getName());
	          }
	        }
        }
		return "detalleRutas";
	}
	
	public String modificarRuta(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		rut = jpaFactory.getRutasDAO().findById(new Long(id));
		rutaId=rut.getId();
		rutaAux= new ArrayList<Coordenada>();
		
		for(Coordenada cor : rut.getRecorrido().getCoordenadas()){
			rutaAux.add(new Coordenada(cor.getCoordenadaX(),cor.getCoordenadaY()));
		}

		actividades = jpaFactory.getActividadesDAO().getAll();
		actSeleccionadas = new LinkedList<String>();
		dificultades = jpaFactory.getDificultadesDAO().getAll();
		formatos = jpaFactory.getFormatosDAO().getAll();
		rutas = rutasVista;
		nombres = new LinkedList<String>();
		
		this.dificultad = rut.getDificultad().getNombre();
		this.formato = rut.getFormato().getNombre();
		this.distancia = rut.getDistancia().toString();
		

		this.isArchivoKML = false;
		this.filekml = null;

		if(rutas != null){
			for(Ruta r : rutasVista){
				nombres.add(r.getNombre());
			}
		}

		return "modificarRuta";
	}
	
	public String buscar(){
		
		rutasVista = jpaFactory.getRutasDAO().buscar(nombre, actSeleccionadas, distancia, dificultad, formato);

		return "/rutas.xhtml?faces-redirect=true";
	}
	
	public String loginUsr() throws IOException{
		rutasVista = jpaFactory.getRutasDAO().getAll();
		
		//Codigo para refrescar pagina
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		
		return "/loginUsr.xhtml?faces-redirect=true";
	}
	
	public String todasRutas() throws IOException{
		rutasVista = jpaFactory.getRutasDAO().getAll();
		
		//Codigo para refrescar pagina
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		
		return "/rutas.xhtml?faces-redirect=true";
	}
	
	public String misRutas() throws IOException{
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		PersonaBean personaBean = (PersonaBean) elContext.getELResolver().getValue(elContext, null, "PersonaBean");
		
		rutasVista = jpaFactory.getRutasDAO().rutasUsuario(new Long(personaBean.getUsr().getId()));
		
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	
		return "/rutasUsuario.xhtml?faces-redirect=true";
	}

	public String puntuar() throws IOException {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		PersonaBean personaBean = (PersonaBean) elContext.getELResolver().getValue(elContext, null, "PersonaBean");
		
		if (rr != null) {
			rr.setPuntaje(puntaje);
			rr.setRecorrida(done);
			jpaFactory.getRutasRecorridasDAO().modify(rr);
		}
		else {
			rr = new RutaRecorrida();
			
			rr.setPuntaje(puntaje);
			rr.setUsuario(personaBean.getUsr());
			rr.setRuta(rut);
			rr.setRecorrida(done);
			jpaFactory.getRutasRecorridasDAO().create(rr);
			
		}
		
		return detalle();
	}
	
	public String eliminarRuta() throws IOException {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		Ruta r = jpaFactory.getRutasDAO().findById(new Long(id));
		
		jpaFactory.getRutasDAO().delete(r);
		return misRutas();
	}
	
	public UploadedFile getFilekml() {
		return filekml;
	}


	public void setFilekml(UploadedFile filekml) {
		this.filekml = filekml;
		//No encontre otra manera de preguntar si no se cargo el archivo.
		if(this.filekml.getSize() != 0){
			isArchivoKML = true;
		}
	}


	public void parseKml() throws IOException {
		InputStream is = filekml.getInputstream();
		Kml kml = Kml.unmarshal(is);
		Feature feature = kml.getFeature();
		parseFeature(feature);
	}

	private void parseFeature(Feature feature) {
		if(feature != null) {
			if(feature instanceof Document) 
			{
				Document document = (Document) feature;
				List<Feature> featureList = document.getFeature();
				for(Feature documentFeature : featureList) {
					if(documentFeature instanceof Placemark)
					{
						Placemark placemark = (Placemark) documentFeature;
						Geometry geometry = placemark.getGeometry();
						parseGeometry(geometry);
					}
				}
			}
		}
	}

	private void parseGeometry(Geometry geometry) 
	{
		if(geometry != null) 
		{
			if(geometry instanceof LineString) 
			{
				LineString polygon = (LineString) geometry;
				List<Coordinate> coordinates =polygon.getCoordinates();
				if(coordinates != null) 
				{
					for(Coordinate coordinate : coordinates) 
					{
						parseCoordinate(coordinate);
					}
				}
			}
		}
	}

	private void parseCoordinate(Coordinate coordinate) {
		if(coordinate != null)
		{
			rut.getRecorrido().getCoordenadas().add(new Coordenada(coordinate.getLatitude(), coordinate.getLongitude()));
		}
	}

	//Revisar para leer imagenes
/*
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String studentId = context.getExternalContext().getRequestParameterMap().get("studentId");
            Student student = studentService.find(Long.valueOf(studentId));
            return new DefaultStreamedContent(new ByteArrayInputStream(student.getImage()));
        }
    }*/
	
	public String busquedaRadial(){
		System.out.println(this.puntoParaBusqueda);
		String[] coord = this.puntoParaBusqueda.split(",");		
		Coordenada cor = new Coordenada(Double.parseDouble(coord[0]),Double.parseDouble(coord[1]));
		this.calcularBusqueda(cor,this.radioEnMetros);
		
		return "/loginUsr.xhtml?faces-redirect=true";
	}
	
	private void calcularBusqueda(Coordenada cor,double radio){
		List<Ruta> allRutas = jpaFactory.getRutasDAO().getAll();
		List<Ruta> rutasDentroDelRadio = new ArrayList<>();

		for(Ruta rut:allRutas){
			for(Coordenada unaCoordenada:rut.getRecorrido().getCoordenadas()){
				long R = 6378137; 
				double dLat = (unaCoordenada.getCoordenadaX() - cor.getCoordenadaX())*Math.PI/180;
				double dLong = (unaCoordenada.getCoordenadaY() - cor.getCoordenadaY())*Math.PI/180;
				double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
						Math.cos(cor.getCoordenadaX()*Math.PI/180) * Math.cos(unaCoordenada.getCoordenadaX()*Math.PI/180) *
						Math.sin(dLong / 2) * Math.sin(dLong / 2);
				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				double d = R * c; // the distance in meter

				if (d < (radio))	// Si la distancia entre puntos es menor a la buscada
				{
					if(!rutasDentroDelRadio.contains(rut))
					{
						rutasDentroDelRadio.add(rut);
					}
				}
			}
		}

		this.rutasVista = rutasDentroDelRadio;
	}

	public List<Ruta> getRutasBusquedaRadial() {
		return rutasBusquedaRadial;
	}


	public void setRutasBusquedaRadial(List<Ruta> rutasBusquedaRadial) {
		this.rutasBusquedaRadial = rutasBusquedaRadial;
	}
	
	
}
