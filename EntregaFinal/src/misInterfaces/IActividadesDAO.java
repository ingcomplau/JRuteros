package misInterfaces;

import java.util.List;

import misClases.Actividad;

public interface IActividadesDAO {
	public void create(Actividad actividad);
	public void modify(Actividad actividad);
	public void delete(Actividad actividad);
	public Actividad findById(Long id);
	public List<Actividad> getAll();
	public List<Actividad> buscar(String nombre, String cantidad);
}
