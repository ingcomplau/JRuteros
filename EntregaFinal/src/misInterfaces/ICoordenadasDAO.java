package misInterfaces;

import java.util.List;

import misClases.Coordenada;

public interface ICoordenadasDAO {
	public void create(Coordenada coordenada);
	public void modify(Coordenada coordenada);
	public void delete(Coordenada coordenada);
	public Coordenada findById(Long id);
	public List<Coordenada> getAll();
}
