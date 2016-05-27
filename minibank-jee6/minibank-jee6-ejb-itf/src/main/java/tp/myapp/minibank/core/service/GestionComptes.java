package tp.myapp.minibank.core.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import tp.myapp.minibank.core.dto.Compte;
import tp.myapp.minibank.core.dto.Operation;

@WebService(targetNamespace="http://tp.myapp.minibank/")
public interface GestionComptes {
	
	public Compte getCompteByNum(@WebParam(name="numCpt")long numCpt) throws MyServiceException;
	
	public List<Compte> getComptesOfClient(@WebParam(name="numClient")long numClient) 
			                    throws MyServiceException;
	
	public List<Operation> getOperationsOfCompte(@WebParam(name="numCpt")long numCpt) 
			throws MyServiceException;
	
	public void transferer(@WebParam(name="montant")double montant,
			@WebParam(name="numCptDeb")long numCptDeb,@WebParam(name="numCptCred")long numCptCred) 
			throws MyServiceException;

}
