package misClases;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="RECORRIDO")
public class Recorrido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//@OneToOne(optional=true,mappedBy="recorrido")
	//private Ruta ruta;
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	/*@JoinTable(name="RECORRIDO_COORDENADA",
			joinColumns=@JoinColumn(name="id",
			referencedColumnName="RECORRIDO_ID"),
			inverseJoinColumns=@JoinColumn(name="id",
			referencedColumnName="COORDENADA_ID")
	)*/
	private List<Coordenada> coordenadas=new ArrayList<Coordenada>();
	
	public Recorrido(){
		
	}
	
	public Recorrido(List<Coordenada> coordenadas) {
		super();
		this.coordenadas = coordenadas;
	}

	public List<Coordenada> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<Coordenada> coordenadas) {
		this.coordenadas = coordenadas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
