package misClases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="COORDENADA")
@XmlRootElement
public class Coordenada {

	@Id
	@Column(name="COORDENADA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@XmlElement(name="lat")
	private double coordenadaX;
	@XmlElement(name="lon")
	private double coordenadaY;
	
	public Coordenada(){
		
	}
	
	public Coordenada(double coordenadaX, double coordenadaY) {
		super();
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public double getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	
	
}
