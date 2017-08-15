package misClases;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RUTA")
public class Ruta {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	private String descripcion;
	private String privacidad;
	@OneToOne(optional=false,cascade ={CascadeType.ALL})
	private Recorrido recorrido;
	@ManyToOne(optional=false)
	@JoinColumn(name="formato_id")
	private Formato formato;
		
	private Long distancia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="dificultad_id")
	private Dificultad dificultad;
	@ManyToOne(optional=false)
	@JoinColumn(name="actividad_id")
	private Actividad actividad;
	private double tiempo;
	private String fecha;
	private Byte[] foto;
	@OneToMany(mappedBy="ruta",cascade={CascadeType.ALL})
	private List<RutaRecorrida> misPuntajes;
	@OneToMany(mappedBy="ruta",cascade=(CascadeType.ALL))
	private List<Imagen> imagenes;

	public Ruta(){
		
	}
	
	public Ruta(String nombre, Usuario usuario, String descripcion, String privacidad, Recorrido recorrido,
			Formato formato, Long distancia, Dificultad dificultad, Actividad actividad, double tiempo, String fecha,
			Byte[] foto) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.privacidad = privacidad;
		this.recorrido = recorrido;
		this.formato = formato;
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.actividad = actividad;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrivacidad() {
		return privacidad;
	}
	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}
	public Recorrido getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(Recorrido recorrido) {
		this.recorrido = recorrido;
	}
	public Formato getFormato() {
		return formato;
	}
	public void setFormato(Formato formato) {
		this.formato = formato;
	}
	public Long getDistancia() {
		return distancia;
	}
	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}
	public Dificultad getDificultad() {
		return dificultad;
	}
	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Byte[] getFoto() {
		return foto;
	}
	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}
	
	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

}
