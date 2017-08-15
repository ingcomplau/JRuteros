package misClases;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ACTIVIDAD")
public class Actividad {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="actividad",fetch=FetchType.EAGER)
	private List<Ruta> rutas; //TODO se deberia hacer una consulta para ver las consultas. 
	private String nombre;
	private Integer cantidad;
	Boolean isActivo;

	public Actividad(){
		
	}
	
	public Actividad(String nombre, Integer cantidad, Boolean isActivo) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.isActivo = isActivo;
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

	public Integer getCantidad() {
		return rutas.size();
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}


}
