package tp.myapp.web.mbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tp.myapp.minibank.core.dto.Client;
import tp.myapp.minibank.core.dto.Compte;
import tp.myapp.minibank.core.service.GestionClients;
import tp.myapp.minibank.core.service.GestionComptes;
import tp.myapp.minibank.core.service.MyServiceException;

@Named
//@ManagedBean
@SessionScoped //javax.context (CDI/JSR299/JEE6) plutot que javax.faces.bean (JSF2 only)
public class ClientComptes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long numClient;
	private String password;
	
	private boolean identifie;
	private Client client;
	private String message;
	
	@Inject
	private GestionClients serviceGestionClients;
	
	@Inject
	private GestionComptes serviceGestionComptes;
	
	private List<Compte> listeComptes;
	
	public void actualiserListeComptes() throws MyServiceException{
		this.listeComptes=getServiceGestionComptes().getComptesOfClient(numClient);
	}
	
	public String identifierClient(){
		String suite=null;message=null;
		try {
			identifie = getServiceGestionClients().isGoodPasswordOfClient(numClient, password);
			if(identifie){
				client=getServiceGestionClients().getClientByNum(numClient);
				actualiserListeComptes();
				//suite="listeComptes"; //.jsp
				suite="listeComptes_href"; //.jsp
			}
		} catch (Exception e) {
			message="echec identification - " + e.getMessage();
		}
		return suite;
	}
	
	public String deconnexion(){
		message=null;
		identifie = false;
		client = null;
		listeComptes=null;
		numClient=null;
		password=null;
	    return "welcome"; //.jsp		
	}
	
	/*
	//@ManagedProperty(value="#{referentiel}")
	@Inject
	private Referentiel referentiel;

	public Referentiel getReferentiel() {
		return referentiel;
	}

	public void setReferentiel(Referentiel referentiel) {
		this.referentiel = referentiel;
	}
	
	private GestionComptes getServiceGestionComptes(){
		return referentiel.getFacadeMiniBank().getServiceGestionComptes();
	}
	
	private GestionClients getServiceGestionClients(){
		return referentiel.getFacadeMiniBank().getServiceGestionClients();
	}*/
	
	private GestionComptes getServiceGestionComptes(){
		return serviceGestionComptes;
	}
	
	private GestionClients getServiceGestionClients(){
		return serviceGestionClients;
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIdentifie() {
		return identifie;
	}

	public void setIdentifie(boolean identifie) {
		this.identifie = identifie;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
