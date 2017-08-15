package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import misClases.Actividad;
import misClases.Usuario;
import misInterfaces.IUsuariosDAO;

public class JPAUsuariosDAO implements IUsuariosDAO{

	@Override
	public List<Usuario> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Usuario> usuarios=(List<Usuario>)(em.createQuery("from misClases.Usuario i")).getResultList();
		
		etx.commit();
		em.close();
	
		return usuarios;
	}
	
	public void create(Usuario usuario){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(usuario);
		
		etx.commit();
		em.close();
	}
	
	public void modify(Usuario usuario){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.merge(usuario);
		
		etx.commit();
		em.close();
	}
	
	public void delete(Usuario usuario){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		//em.find(Class<Usuario> usuario, usuario.getId());
		Usuario usr = em.getReference(Usuario.class,usuario.getId());
		em.remove(usr);
		
		etx.commit();
		em.close();
	}
	
	public Usuario findById(long id){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Usuario usuario = em.find(Usuario.class,id);
		
		etx.commit();
		em.close();
		
		return usuario;
	}
	
	public Usuario recuperarUsuario(String usuario) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		
		Usuario usr = null;
		Query q ;
		try {
			q = em.createQuery("FROM Usuario u WHERE u.usuario ='"+usuario+"'");
			usr = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			usr = null;
			//e.printStackTrace();
		}
		em.close();
		return usr;
	}

	public List<Usuario> buscar(String var){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		String query = "from misClases.Usuario i where 1 = 1";
		
		if (var != "") {
			query += " AND (nombre LIKE '" + var + "%'";
			query += " OR apellido LIKE '" + var + "%'";
			query += " OR usuario LIKE '" + var + "%'";
			query += ")";
		}
		
		etx.begin();
		
		List<Usuario> users = (List<Usuario>)(em.createQuery(query)).getResultList();
					
		etx.commit();
		em.close();
	
		return users;
	}
	
	@Override
	public List<Usuario> getByYear(int año){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		String query = "from misClases.Usuario i " 
		+ "where date LIKE '"
		+ año 
		+ "%'";
		
		
		List<Usuario> usuarios=(List<Usuario>)(em.createQuery(query)).getResultList();
		
		etx.commit();
		em.close();
	
		return usuarios;
	}
}
