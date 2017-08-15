package misInterfaces;

import java.util.List;

import misClases.Dificultad;

public interface IDificultadesDAO {
	public void create(Dificultad dificultad);
	public void modify(Dificultad dificultad);
	public void delete(Dificultad dificultad);
	public Dificultad findById(Long id);
	public List<Dificultad> getAll();
}
