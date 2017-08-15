package misDaos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import misInterfaces.IActividadesDAO;
import misInterfaces.ICoordenadasDAO;
import misInterfaces.IDificultadesDAO;
import misInterfaces.IFormatosDAO;
import misInterfaces.IImagenesDAO;
import misInterfaces.IRecorridosDAO;
import misInterfaces.IRutasRecorridasDAO;
import misInterfaces.IRutasDAO;
import misInterfaces.IUsuariosDAO;

public class JPADAOFactory extends DAOFactory{
	private static EntityManagerFactory emf = null;

	//TODO: no estoy seguro de ponerlo aca, hay que consultar.
	public static EntityManagerFactory getConexion(){
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("miUP");
		}
		return emf;
	}
	
	@Override
	public IRutasDAO getRutasDAO() {
		return new JPARutasDAO();
	}

	@Override
	public IActividadesDAO getActividadesDAO() {
		return new JPAActividadesDAO();
	}

	@Override
	public IRutasRecorridasDAO getRutasRecorridasDAO() {
		return new JPARutasRecorridasDAO();
	}

	@Override
	public IUsuariosDAO getUsuariosDAO() {
		return new JPAUsuariosDAO();
	}
	
	@Override
	public ICoordenadasDAO getCoordenadasDAO() {
		return new JPACoordenadasDAO();
	}
	
	@Override
	public IRecorridosDAO getRecorridosDAO() {
		return new JPARecorridosDAO();
	}
	
	@Override
	public IDificultadesDAO getDificultadesDAO() {
		return new JPADificultadesDAO();
	}

	@Override
	public IFormatosDAO getFormatosDAO() {
		return new JPAFormatosDAO();
	}
	
	@Override
	public IImagenesDAO getImagenesDAO() {
		return new JPAImagenesDAO();
	}
}
