package misInterfaces;

import java.util.List;

import misClases.Dificultad;
import misClases.Formato;

public interface IFormatosDAO {
	public void create(Formato formato);
	public void modify(Formato formato);
	public void delete(Formato formato);
	public Formato findById(Long id);
	public List<Formato> getAll();
}
