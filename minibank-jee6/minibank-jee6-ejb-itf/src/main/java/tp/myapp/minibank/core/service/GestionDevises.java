
package tp.myapp.minibank.core.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import tp.myapp.minibank.core.dto.Devise;

@WebService(targetNamespace="http://tp.myapp.minibank/")
public interface GestionDevises {  
	//CRUD methods for entity "Devise"
	public Devise getDeviseByPk(@WebParam(name="codeDevise") String codeDevise) throws RuntimeException;
	public String createNewDevise(@WebParam(name="devise")Devise devise) throws RuntimeException;
	public void updateDevise(@WebParam(name="devise")Devise devise) throws RuntimeException;
	public void deleteDevise(@WebParam(name="codeDevise") String codeDevise) throws RuntimeException;
	//Other specific methods:
	public Devise getDeviseByName(@WebParam(name="name")String name) throws RuntimeException;
	public double convertir(@WebParam(name="montant")double montant,@WebParam(name="monnaieScr")String monnaieScr,@WebParam(name="monnaieDest")String monnaieDest) throws RuntimeException;
	public List<Devise> getListeDevises() throws RuntimeException;
}
