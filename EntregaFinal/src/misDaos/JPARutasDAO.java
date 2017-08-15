package misDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import misClases.*;
import misInterfaces.IRutasDAO;

public class JPARutasDAO implements IRutasDAO{

	@Override
	public List<Ruta> getAll(){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Ruta> rutas=(List<Ruta>)(em.createQuery("from misClases.Ruta i")).getResultList();
		
		etx.commit();
		em.close();
	
		return rutas;
	}
	
	public void modify(Ruta ruta){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		//Merge
		em.merge(ruta);
		
		etx.commit();
		em.close();
	}
	
	public void create(Ruta ruta){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		em.persist(ruta);
		
		etx.commit();
		em.close();
	}

	@Override
	public void delete(Ruta ruta) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		//TODO Nose si es asi, no estoy seguro.
		//Find, remove
		Ruta rut = em.getReference(Ruta.class,ruta.getId());
		em.remove(rut);
		
		etx.commit();
		em.close();
	}

	@Override
	public Ruta findById(Long id) {
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		Ruta ruta = em.find(Ruta.class,id);
		
		etx.commit();
		em.close();
		
		//No creo que se necesite la transaccion porque es un buscar.
		
		return ruta;
	}

	public List<Ruta> rutasUsuario(Long id){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
		
		List<Ruta> rutas = (List<Ruta>)(em.createQuery("from misClases.Ruta i where usuario_id = " + id)).getResultList();
		List<Ruta> rutascompletas = (List<Ruta>)(em.createQuery("from misClases.Ruta i")).getResultList();
		etx.commit();
		em.close();
	
		return rutas;
	}
	
	public List<Ruta> buscar(String nombre, List<String> act, String dis, String dif, String form){
		EntityManagerFactory emf = JPADAOFactory.getConexion();
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		String query = "from misClases.Ruta i where 1 = 1";
		
		if (nombre != "") {
			query += " and nombre LIKE '" + nombre + "%'";
		}
		if (act.size() > 0) {
			query += " and actividad_id IN (";
			query += act.get(0);
			if (act.size() > 1) 
				for(int i=1; i<act.size(); i++) {
					query += ",";
					query += act.get(i);
				}
			query += ")";
		}
		if (dis != "")
			query += " and distancia <= " + dis;
		if (dif != "")
			query += " and dificultad_id = " + dif;
		if (form != "")
			query += " and formato_id = " + form;
		
		etx.begin();
		
		List<Ruta> rutas = (List<Ruta>)(em.createQuery(query)).getResultList();
		
		
		//List<Ruta> rutascompletas = (List<Ruta>)(em.createQuery("from misClases.Ruta i")).getResultList();
		etx.commit();
		em.close();
	
		return rutas;
	}
	
	
}