package misDaos;

import misInterfaces.IActividadesDAO;
import misInterfaces.ICoordenadasDAO;
import misInterfaces.IDificultadesDAO;
import misInterfaces.IFormatosDAO;
import misInterfaces.IImagenesDAO;
import misInterfaces.IRecorridosDAO;
import misInterfaces.IRutasRecorridasDAO;
import misInterfaces.IRutasDAO;
import misInterfaces.IUsuariosDAO;

public abstract class DAOFactory {
	
	//Lista de tipos de DAOs soportados por le Factory 
	public static final int JPA = 1;
	
	/* Un metodo por cada DAO que va a ser creado.
	 * Estos metodos lo van a implementar los factories concretos.
	 * Podemos tener varios factories: JPADAOFactory, DATASOURCEDAOFactory, etc.*/
	public abstract IActividadesDAO getActividadesDAO();
	public abstract IRutasRecorridasDAO getRutasRecorridasDAO();
	public abstract IRutasDAO getRutasDAO();
	public abstract IUsuariosDAO getUsuariosDAO();
	public abstract ICoordenadasDAO getCoordenadasDAO();
	public abstract IRecorridosDAO getRecorridosDAO();
	public abstract IDificultadesDAO getDificultadesDAO();
	public abstract IFormatosDAO getFormatosDAO();
	public abstract IImagenesDAO getImagenesDAO();
	
	public static DAOFactory getDAOFactory(int factory){
		switch(factory){
			case 1: {
				return new JPADAOFactory();
			}
			default:{
				return null;
			}
		}
	}
}
