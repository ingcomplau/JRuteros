package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.*;
import misInterfaces.ICoordenadasDAO;

public class JPACoordenadasDAO implements ICoordenadasDAO{

	@Override
	public List<Coordenada> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Coordenada> coordenadas=(List<Coordenada>)(em.createQuery("from misClases.Coordenada i")).getResultList();
		
		etx.commit();
		em.close();
	
		return coordenadas;
	}
	
	public void modify(Coordenada coordenada){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(coordenada);
		
		etx.commit();
		em.close();
	}
	
	public void create(Coordenada coordenada){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(coordenada);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Coordenada coordenada) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		//TODO Nose si es asi, no estoy seguro.
		//Find, remove
		Coordenada coor = em.getReference(Coordenada.class,coordenada.getId());
		em.remove(coor);
		
		etx.commit();
		em.close();
	}

	@Override
	public Coordenada findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Coordenada coordenada = em.find(Coordenada.class,id);
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return coordenada;
	}

	
}
