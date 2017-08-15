package rest;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


import misClases.Coordenada;
import misClases.Recorrido;
import misServices.RutasService;



@Path("/rutas/{idrut}")
public class RutasResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	RutasService rs;
	
	public RutasResource(){
		rs = new RutasService();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createCoordenada(@FormParam("lat") double latitud,@FormParam("lon") double longitud,@PathParam("idrut") Long idRuta){
		Coordenada cor = new Coordenada(latitud,longitud);
		rs.createCoordenada(cor,idRuta);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Coordenada> getRecorrido(@PathParam("idrut")Long idRuta){
		return rs.getCoordenadas(idRuta);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //ó como el de arriba con el form param
	public void deleteCoordenada(@FormParam("id") long id,@PathParam("idrut")Long idRuta){
		rs.removeCoordenada(id,idRuta);
	}
	
	@DELETE
	@Path("clear")
	public void deleteCoordenada(){
		//rs.removeAll();
	}

}
/*public class RutasResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Long id;
	
	RutasService rs = new RutasService();
	
	public RutasResource(){
		//rs.loadRecorrido(new Long(1));
	}
	
	public RutasResource(UriInfo uriInfo, Request request, long id){
		rs.loadRecorrido(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void getId(@FormParam("id") long id) {
		rs.loadRecorrido(new Long(id));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createCoordenada(@FormParam("lat") double latitud,@FormParam("lon") double longitud){
		Coordenada cor = new Coordenada(latitud,longitud);
		rs.createCoordenada(cor);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Coordenada> getRecorrido(){
		return rs.getCoordenadas();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //ó como el de arriba con el form param
	public void deleteCoordenada(@FormParam("id") long id){
		rs.removeCoordenada(id);
	}
	
	@DELETE
	@Path("clear")
	public void deleteCoordenada(){
		rs.removeAll();
	}
	
	@PUT
	@Path("save")
	public void saveRecorrido(){
		rs.saveRecorrido();
	}
}*/
