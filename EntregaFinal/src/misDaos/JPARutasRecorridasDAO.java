package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.*;
import misInterfaces.IRutasRecorridasDAO;

public class JPARutasRecorridasDAO implements IRutasRecorridasDAO{

	@Override
	public List<RutaRecorrida> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<RutaRecorrida> rutaRecorrida=(List<RutaRecorrida>)(em.createQuery("from misClases.RutaRecorrida i")).getResultList();
		
		etx.commit();
		em.close();
	
		return rutaRecorrida;
	}
	
	public void modify(RutaRecorrida rutaRecorrida){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(rutaRecorrida);
		
		etx.commit();
		em.close();
	}
	
	public void create(RutaRecorrida rutaRecorrida){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(rutaRecorrida);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(RutaRecorrida rutaRecorrida) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		//TODO Nose si es asi, no estoy seguro.
		//Find, remove
		RutaRecorrida rut = em.getReference(RutaRecorrida.class,rutaRecorrida.getId());
		em.remove(rut);
		
		etx.commit();
		em.close();
	}

	@Override
	public RutaRecorrida findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		RutaRecorrida rutaRecorrida = em.find(RutaRecorrida.class,id);
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return rutaRecorrida;
	}

	@Override
	public RutaRecorrida buscar(Long persona, Long ruta) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<RutaRecorrida> listaRR = (List<RutaRecorrida>) (em.createQuery("from misClases.RutaRecorrida i where ruta_id = " + ruta + "and usuario_id = " + persona)).getResultList();
		
		RutaRecorrida rr; 
		
		if (listaRR.size() != 0)
			rr = listaRR.get(0);
		else
			rr = null;
	
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return rr;
	}
	
	@Override
	public Integer promedio(Long ruta) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		Integer suma = 0;
		Integer promedio = 0;
		int i;
		
		etx.begin();
		
		List<RutaRecorrida> listaRR = (List<RutaRecorrida>) (em.createQuery("from misClases.RutaRecorrida i where ruta_id = " + ruta)).getResultList();
		if (listaRR.size() > 0){
			for(i=0; i<listaRR.size();i++){
				suma += listaRR.get(i).getPuntaje();
			}
			promedio = suma/i;
		}
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return promedio;
	}
	
	public Integer veces(Long ruta){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		Integer cant = 0;
		
		etx.begin();
		
		List<RutaRecorrida> listaRR = (List<RutaRecorrida>) (em.createQuery("from misClases.RutaRecorrida i where ruta_id = " + ruta)).getResultList();
		
		for(int i=0;i<listaRR.size();i++)
			cant++;
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return cant;
	}
}
