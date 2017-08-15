package misInterfaces;

import java.util.List;

import misClases.*;

public interface IRutasRecorridasDAO {
	public void create(RutaRecorrida puntajes);
	public void modify(RutaRecorrida puntajes);
	public void delete(RutaRecorrida puntajes);
	public RutaRecorrida findById(Long id);
	public List<RutaRecorrida> getAll();
	public RutaRecorrida buscar(Long persona, Long ruta);
	public Integer promedio(Long ruta);
	public Integer veces(Long ruta);
}
