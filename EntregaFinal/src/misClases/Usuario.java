package misClases;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="usuario", cascade = CascadeType.REMOVE)
	private List<Ruta> misRutas;
	private String usuario;
	private String clave;
	private String nombre;
	private String apellido;
	private String dni;
	private String domicilio;
	private String fechaNacimiento;
	private String email;
	private String sexo;
	private boolean administrador;
	private boolean isActivo;
	@Column(name = "date", insertable=false, updatable = false, nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date date;
	@OneToMany(mappedBy="usuario", cascade = CascadeType.REMOVE)
	private List<RutaRecorrida> misRecorridos;
	
	public Usuario(){
		
	}

	public Usuario(String usuario, String clave, String nombre, String apellido, String dni,
			String domicilio, String fechaNacimiento, String email, String sexo, boolean administrador,
			boolean isActivo) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.sexo = sexo;
		this.administrador = administrador;
		this.isActivo = isActivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
