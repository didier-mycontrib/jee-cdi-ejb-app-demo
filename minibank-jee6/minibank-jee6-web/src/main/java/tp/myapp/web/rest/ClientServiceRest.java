package tp.myapp.web.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import tp.myapp.minibank.core.dto.Client;
import tp.myapp.minibank.core.service.GestionClients;


@Path("clients")
@Named
public class ClientServiceRest {
	
	@Inject
	private GestionClients gestionClients;
	
	@Path("/xml/{id}")
	@GET
	@Produces("application/xml")
	public Client getClientNum(@PathParam("id")Long num){
		Client client = gestionClients.getClientByNum(num);
		//client.setComptes(null); //to avoid lazy exception (en version sans DTO)
		return client;
	}
	
	
	
	@Path("/json/{id}")
	@GET
	@Produces("application/json")
	public Client getXyByNum2(@PathParam("id")Long num){
		System.out.println("****");
		Client client = gestionClients.getClientByNum(num);
		//client.setComptes(null);
		return client;
	}
	
	
}
