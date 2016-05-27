package tp.myapp.minibank.core.facade.impl;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import tp.myapp.minibank.core.facade.FacadeMiniBank;
import tp.myapp.minibank.core.service.GestionClients;
import tp.myapp.minibank.core.service.GestionComptes;

public class FacadeMiniBankImpl implements FacadeMiniBank {
	
	private GestionComptes serviceGestionComptes;	
	private GestionClients serviceGestionClients;	
	
	private InitialContext ic;
	
	private void initServiceFromApplicationContext(){		
		try {
			//jndi names for Jboss 7
			//java:global[/application name]/module name/enterprise bean name[/interface name]
			     //ou bien (si access depuis la meme appli)
			//java:app[/module name]/enterprise bean name[/interface name]
			serviceGestionComptes=(GestionComptes)ic.lookup("java:app/minibank-jee6EJB/GestionComptesImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionComptesLocal");
			serviceGestionClients= (GestionClients)ic.lookup("java:app/minibank-jee6EJB/GestionClientsImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionClientsLocal");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public FacadeMiniBankImpl(){
		try {
			ic = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		initServiceFromApplicationContext();
	}
	
	public FacadeMiniBankImpl(Properties props){
		try {
			ic = new InitialContext(props);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		initServiceFromApplicationContext();
	}
	

	@Override
	public GestionComptes getServiceGestionComptes() {
		return serviceGestionComptes;
	}

	@Override
	public GestionClients getServiceGestionClients() {
		return serviceGestionClients;
	}
	
	

}
