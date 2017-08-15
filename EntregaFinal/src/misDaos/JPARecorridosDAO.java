package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.*;
import misInterfaces.IRecorridosDAO;

public class JPARecorridosDAO implements IRecorridosDAO{

	@Override
	public List<Recorrido> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Recorrido> recorridos=(List<Recorrido>)(em.createQuery("from misClases.Recorrido i")).getResultList();
		
		etx.commit();
		em.close();
	
		return recorridos;
	}
	
	public void modify(Recorrido recorrido){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(recorrido);
		
		etx.commit();
		em.close();
	}
	
	public void create(Recorrido recorrido){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(recorrido);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Recorrido recorrido) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		//TODO Nose si es asi, no estoy seguro.
		//Find, remove
		Recorrido rec = em.getReference(Recorrido.class,recorrido.getId());
		em.remove(rec);
		
		etx.commit();
		em.close();
	}

	@Override
	public Recorrido findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Recorrido recorrido = em.find(Recorrido.class,id);
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return recorrido;
	}


}
