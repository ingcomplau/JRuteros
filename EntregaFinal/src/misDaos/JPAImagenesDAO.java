package misDaos;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import misClases.Actividad;
import misClases.Imagen;
import misClases.Ruta;
import misInterfaces.IActividadesDAO;
import misInterfaces.IImagenesDAO;

public class JPAImagenesDAO implements IImagenesDAO{

	@Override
	public List<Imagen> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Imagen> imagenes=(List<Imagen>)(em.createQuery("from misClases.Imagen i")).getResultList();
		
		etx.commit();
		em.close();
	
		return imagenes;
	}
	
	public void modify(Imagen imagen){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(imagen);
		
		etx.commit();
		em.close();
	}
	
	public void create(Imagen imagen){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(imagen);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Imagen imagen) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Find, remove
		Imagen act = em.getReference(Imagen.class,imagen.getId());
		em.remove(act);
		
		etx.commit();
		em.close();
	}

	@Override
	public Imagen findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Imagen imagen = em.find(Imagen.class,id);
		
		etx.commit();
		em.close();
		
		return imagen;
	}
	
/*
	public List<Imagen> buscar(String nombre){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		/*String query = "from misClases.Imagen i where 1 = 1";
		
		if (nombre != "") {
			query += " and nombre LIKE '" + nombre + "%'";
		}
		if (cantidad != "")
			query += " and cantidad <= " + cantidad;
			 		
		etx.begin();
		
		List<Actividad> act = (List<Actividad>)(em.createQuery(query)).getResultList();
					
		etx.commit();
		em.close();
	
		return act;
	}*/
}
