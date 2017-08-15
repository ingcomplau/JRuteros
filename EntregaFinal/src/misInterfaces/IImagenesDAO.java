package misInterfaces;

import java.util.List;

import misClases.Imagen;

public interface IImagenesDAO {
	public void create(Imagen imagen);
	public void modify(Imagen imagen);
	public void delete(Imagen imagen);
	public Imagen findById(Long id);
	public List<Imagen> getAll();
}
