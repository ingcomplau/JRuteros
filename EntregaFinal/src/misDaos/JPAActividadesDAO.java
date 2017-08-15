package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.Actividad;
import misClases.Ruta;
import misInterfaces.IActividadesDAO;

public class JPAActividadesDAO implements IActividadesDAO{

	@Override
	public List<Actividad> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Actividad> actividades=(List<Actividad>)(em.createQuery("from misClases.Actividad i")).getResultList();
		
		etx.commit();
		em.close();
	
		return actividades;
	}
	
	public void modify(Actividad actividad){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(actividad);
		
		etx.commit();
		em.close();
	}
	
	public void create(Actividad actividad){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(actividad);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Actividad actividad) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Find, remove
		Actividad act = em.getReference(Actividad.class,actividad.getId());
		em.remove(act);
		
		etx.commit();
		em.close();
	}

	@Override
	public Actividad findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Actividad actividad = em.find(Actividad.class,id);
		
		etx.commit();
		em.close();
		
		return actividad;
	}

	public List<Actividad> buscar(String nombre, String cantidad){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		String query = "from misClases.Actividad i where 1 = 1";
		
		if (nombre != "") {
			query += " and nombre LIKE '%" + nombre + "%'";
		}
		//if (cantidad != "")
		//	query += " and cantidad <= " + cantidad;
			 		
		etx.begin();
		
		List<Actividad> act = (List<Actividad>)(em.createQuery(query)).getResultList();
					
		etx.commit();
		em.close();
	
		return act;
	}
}
