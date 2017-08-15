package misClases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PUNTAJE")
public class RutaRecorrida {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional=false)
	private Ruta ruta;
	@ManyToOne(optional=false)
	private Usuario usuario;
	private Integer puntaje;
	private boolean recorrida;

	public RutaRecorrida(){
		
	}

	public RutaRecorrida(Ruta ruta, Usuario usuario, Integer puntaje, boolean recorrida) {
		super();
		this.ruta = ruta;
		this.usuario = usuario;
		this.puntaje = puntaje;
		this.recorrida = recorrida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public boolean isRecorrida() {
		return recorrida;
	}

	public void setRecorrida(boolean recorrida) {
		this.recorrida = recorrida;
	}

	
}
