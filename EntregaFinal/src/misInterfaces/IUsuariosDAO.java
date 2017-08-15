package misInterfaces;

import java.util.List;

import misClases.Usuario;

public interface IUsuariosDAO {
	public void create(Usuario usuario);
	public void modify(Usuario usuario);
	public void delete(Usuario usuario);
	public Usuario findById(long id);
	public List<Usuario> getAll();
	public Usuario recuperarUsuario(String usuario);
	public List<Usuario> buscar(String busqueda);
	public List<Usuario> getByYear(int año);
}
