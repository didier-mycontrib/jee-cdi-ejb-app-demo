package tp.myapp.minibank.core.facade.impl;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import tp.myapp.minibank.core.facade.FacadeMiniBank;
import tp.myapp.minibank.core.service.GestionClients;
import tp.myapp.minibank.core.service.GestionComptes;

public class FacadeMiniBankDelegate implements FacadeMiniBank {
	
	private GestionComptes serviceGestionComptes;	
	private GestionClients serviceGestionClients;	
	
	private InitialContext ic;
	
	private void initServiceFromApplicationContext(){		
		try {
			//jndi internal names for Jboss 7
			//java:global[/application name]/module name/enterprise bean name[/interface name]
			     //ou bien (si access depuis la meme appli)
			//java:app[/module name]/enterprise bean name[/interface name]
			
			// jndi external names (from external EJB client app):
			//ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
			
			
			serviceGestionComptes=(GestionComptes)ic.lookup("ejb:minibank-jee6/minibank-jee6EJB//GestionComptesImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionComptesRemote");
			serviceGestionClients=(GestionClients)ic.lookup("ejb:minibank-jee6/minibank-jee6EJB//GestionClientsImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionClientsRemote");
			
			
			//déclenché depuis un lookup externe à JBoss , un chemin en "java:global/...." mène à l'exception suivante:
			//"javax.naming.NoInitialContextException: Need to specify class name in environment or system property"
			
			/*
			serviceGestionComptes=(GestionComptes)ic.lookup("java:global/minibank-jee6/minibank-jee6EJB/GestionComptesImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionComptesRemote");
			serviceGestionClients=(GestionClients)ic.lookup("java:global/minibank-jee6/minibank-jee6EJB/GestionClientsImpl!tp.myapp.minibank.impl.domain.ejb.itf.GestionClientsRemote");
			*/
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public FacadeMiniBankDelegate(){
		try {
			ic = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		initServiceFromApplicationContext();
	}
	
	public FacadeMiniBankDelegate(Properties props){
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
