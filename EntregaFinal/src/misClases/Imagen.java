package misClases;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="IMAGEN")
public class Imagen {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional=false)
	private Ruta ruta;
	@Column(name = "imagen", updatable = false, columnDefinition = "longblob")
	private byte[] imagen;
	@Column(name = "date", insertable=false, updatable = false, nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date date;
	
	public Imagen(){}
	
	public Imagen(Ruta ruta, byte[] imagen){
		this.ruta = ruta;
		this.imagen = imagen;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
		
	
}
