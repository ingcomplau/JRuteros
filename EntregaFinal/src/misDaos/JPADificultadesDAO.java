package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.Actividad;
import misClases.Dificultad;
import misClases.Ruta;
import misInterfaces.IActividadesDAO;
import misInterfaces.IDificultadesDAO;

public class JPADificultadesDAO implements IDificultadesDAO{

	@Override
	public List<Dificultad> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Dificultad> dificultades=(List<Dificultad>)(em.createQuery("from misClases.Dificultad i")).getResultList();
		
		etx.commit();
		em.close();
	
		return dificultades;
	}
	
	public void modify(Dificultad dificultad){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(dificultad);
		
		etx.commit();
		em.close();
	}
	
	public void create(Dificultad dificultad){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(dificultad);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Dificultad dificultad) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Find, remove
		Dificultad dif = em.getReference(Dificultad.class,dificultad.getId());
		em.remove(dif);
		
		etx.commit();
		em.close();
	}

	@Override
	public Dificultad findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Dificultad dificultad = em.find(Dificultad.class,id);
		
		etx.commit();
		em.close();
		
		return dificultad;
	}

}
