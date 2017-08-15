package misServices;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.PathParam;

import misClases.Coordenada;
import misClases.Recorrido;
import misClases.Ruta;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

public class RutasService {
	
	JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA);
	Recorrido rec = new Recorrido();
	
	public RutasService(){}
	
	public void createCoordenada(Coordenada cor, Long idRuta){
		Ruta r = jpaFactory.getRutasDAO().findById(idRuta);
		r.getRecorrido().getCoordenadas().add(cor);
		jpaFactory.getRutasDAO().modify(r);
	}
	
	public void removeCoordenada(long id,Long idRuta){
		//El id representa la posicion relativa en la lista, NO el id de coordenada.
		//rec.getCoordenadas().remove((int) id); 
		Ruta r = jpaFactory.getRutasDAO().findById(idRuta);
		r.getRecorrido().getCoordenadas().remove((int) id);
		jpaFactory.getRutasDAO().modify(r);
	}	
	
	public void removeAll(){
		//rec.getCoordenadas().clear();
	}	
	
	public List<Coordenada> getCoordenadas(Long idRuta){
		//return rec.getCoordenadas();
		Ruta r = jpaFactory.getRutasDAO().findById(idRuta);
		return r.getRecorrido().getCoordenadas();
	}
	
}
/*public class RutasService {
	
	JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA
	Recorrido rec;
	
	public RutasService(){}
	
	public void loadRecorrido(Long id){
		A fines practicos de la entrega, se efectuaran todas las modificaciones 
		sobre el recorrido de id 1.
		rec = jpaFactory.getRecorridosDAO().findById(id);
	}
	
	public void createCoordenada(Coordenada cor){
		cor.setId((long)rec.getCoordenadas().size());
		rec.getCoordenadas().add(cor);
	}
	
	public void removeCoordenada(long id){
		//El id representa la posicion relativa en la lista, NO el id de coordenada.
		rec.getCoordenadas().remove((int) id); 
	}	
	
	public void removeAll(){
		rec.getCoordenadas().clear();
	}	
	
	public List<Coordenada> getCoordenadas(){
		return rec.getCoordenadas();
	}
	
	//Aca intento guardar el recorrido en la db pero no puedo
	public void saveRecorrido(){
		jpaFactory.getRecorridosDAO().modify(rec);;   
	}
}*/

