package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.Actividad;
import misClases.Dificultad;
import misClases.Formato;
import misClases.Ruta;
import misInterfaces.IActividadesDAO;
import misInterfaces.IDificultadesDAO;
import misInterfaces.IFormatosDAO;

public class JPAFormatosDAO implements IFormatosDAO{

	@Override
	public List<Formato> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Formato> formatos=(List<Formato>)(em.createQuery("from misClases.Formato i")).getResultList();
		
		etx.commit();
		em.close();
	
		return formatos;
	}
	
	public void modify(Formato formato){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(formato);
		
		etx.commit();
		em.close();
	}
	
	public void create(Formato formato){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(formato);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Formato formato) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Find, remove
		Formato form = em.getReference(Formato.class,formato.getId());
		em.remove(form);
		
		etx.commit();
		em.close();
	}

	@Override
	public Formato findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Formato formato = em.find(Formato.class,id);
		
		etx.commit();
		em.close();
		
		return formato;
	}

}
