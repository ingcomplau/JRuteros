package misInterfaces;

import java.util.List;

import misClases.*;

public interface IRecorridosDAO {
	public void create(Recorrido recorrido);
	public void modify(Recorrido recorrido);
	public void delete(Recorrido recorrido);
	public Recorrido findById(Long id);
	public List<Recorrido> getAll();
}
