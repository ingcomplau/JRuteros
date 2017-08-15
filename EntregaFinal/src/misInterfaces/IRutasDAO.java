package misInterfaces;

import java.util.List;

import misClases.Actividad;
import misClases.Ruta;

public interface IRutasDAO {
	public void create(Ruta ruta);
	public void modify(Ruta ruta);
	public void delete(Ruta ruta);
	public Ruta findById(Long id);
	public List<Ruta> getAll();
	public List<Ruta> rutasUsuario(Long id);
	public List<Ruta> buscar(String nombre, List<String> act, String dis, String dif, String form);
}
