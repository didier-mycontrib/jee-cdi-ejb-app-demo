package tp.myapp.minibank.core.service.impl.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.myapp.minibank.core.dao.ClientDao;
import tp.myapp.minibank.core.dto.Adresse;
import tp.myapp.minibank.core.dto.Client;
import tp.myapp.minibank.core.entity._Adresse;
import tp.myapp.minibank.core.entity._Client;
import tp.myapp.minibank.core.impl.ejb.itf.GestionClientsLocal;
import tp.myapp.minibank.core.impl.ejb.itf.GestionClientsRemote;
import tp.myapp.minibank.core.service.GestionClients;
import tp.myapp.minibank.core.service.MyServiceException;

@Stateless
@Named
@WebService(targetNamespace="http://tp.myapp.minibank/",
            endpointInterface="tp.myapp.minibank.core.service.GestionClients")
@PermitAll
public class GestionClientsImpl implements GestionClients , GestionClientsLocal , GestionClientsRemote {
	
	
private static Logger logger = LoggerFactory.getLogger(GestionClientsImpl.class);
	
    //@EJB (de JEE5/JEE6)utilise sans argument
    // injecte le seul composant EJB existant compatible
    // avec l'interface
	@EJB
	private ClientDao clientDao;
	
	private Client entityToDto(_Client c){
		Client cli = new Client(c.getNumero(), c.getNom(), c.getPrenom(), c.getTelephone(), c.getEmail());
		cli.setPassword(c.getPassword());
		cli.setAdresse(new Adresse(c.getAdresse().getIdAdr(), 
				                   c.getAdresse().getRue(),
				                   c.getAdresse().getCodePostal(),
				                   c.getAdresse().getVille()));
		return cli;
	}
	
	private List<Client> entityListToDtoList(List<_Client> listeEntity){
		List<Client> liste = new ArrayList<Client>();
		for(_Client c:listeEntity){
			liste.add(entityToDto(c));
		}
		return liste; 
	}
	
	private _Client dtoToEntity(Client c){
		_Client cli = new _Client(c.getNumero(), c.getNom(), c.getPrenom(), c.getTelephone(), c.getEmail());
		cli.setPassword(c.getPassword());
		cli.setAdresse(new _Adresse(c.getAdresse().getIdAdr(), 
				                   c.getAdresse().getRue(),
				                   c.getAdresse().getCodePostal(),
				                   c.getAdresse().getVille()));
		return cli;
	}
	
	
	/*
	@Schedule(second="0,30", minute="*", hour="*")
	public void automaticPeriodicExecution(Timer automaticTimer) {
	System.out.println("Executing (toutes les 30s) ...");
		System.out.println("Execution Time : " + new Date());
		System.out.println("____________________________________________");  
	}*/

	
	 @Override
	public Client getClientByNum(long numCli) throws MyServiceException {
		Client cli=null;
		try {
			cli= entityToDto(clientDao.getEntityById(numCli));
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("echec GestionClientsImpl.getClientByNum",e);
			throw new MyServiceException("echec GestionClientsImpl.getClientByNum",e);
		}
		return cli;
	}

	@Override
	public boolean isGoodPasswordOfClient(long numClient, String password)
			throws MyServiceException {
		boolean res=false;
		try {
			_Client cli= clientDao.getEntityById(numClient);
			if(cli.getPassword()!=null && cli.getPassword().equals(password))
				res=true;
		} catch (Exception e) {
			logger.error("echec GestionClientsImpl.isGoodPasswordOfClient",e);
			throw new MyServiceException("echec GestionClientsImpl.isGoodPasswordOfClient",e);
		}
		return res;
	}

	@Override
	public void changePasswordOfClient(long numClient, String password)
			throws MyServiceException {
		try {
			_Client cli= clientDao.getEntityById(numClient);
			cli.setPassword(password);
			//clientDao.updateEntity(cli); pas utile cat cli persistant du fait du comportement Transactionnnel
		} catch (Exception e) {		
			logger.error("echec GestionClientsImpl.changePasswordOfClient",e);
			throw new MyServiceException("echec GestionClientsImpl.changePasswordOfClient",e);
		}
	}

}
