package tp.client.app.test.jee;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import tp.myapp.minibank.core.dto.Devise;
import tp.myapp.minibank.core.impl.ejb.itf.GestionDevisesRemote;
import tp.myapp.minibank.core.service.GestionDevises;

public class MyJeeClientApp {
	
	private static final String JBOSS_WILDFLY="JbossWildFly";
	private static final String GLASSFISH="Glassfish";
	private static String serverType= JBOSS_WILDFLY;
	//private static String serverType= GLASSFISH;

	
	private static ConnectionFactory connectionFactory=null;

	private static Queue queue;
	
	private static Properties props = new Properties();
	
	public static void initJndiClientProperties() {
		if(serverType.equals(GLASSFISH)){
		//pour glassfish (v3,v4) sur localhost :
			props.put("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
			props.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
			props.put("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
			props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
			props.put("org.omg.CORBA.ORBInitialPort", "3700");
		}
		else if(serverType.equals(JBOSS_WILDFLY)){
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); //remote://localhost:4447 for Jboss7.1 , http-remoting://localhost:8080 for wildfly 8,9
			props.put(Context.SECURITY_PRINCIPAL, "guest");//"admin" , "guest" , "..."
			props.put(Context.SECURITY_CREDENTIALS, "guest007"); //"pwd", "guest007"
			//avec admin = utilisateur ajouté via la commande JBOSS7_HOME/bin/add-user 
			//mot de passe=pwd et rôles associés admin,guest
			//et avec "guest" = rôle configuré sur la partie "messaging" de standalone(-full).xml 
			props.put("jboss.naming.client.ejb.context", true);
		}
	}

	public static void main(String[] args) {
		initJndiClientProperties();
		try {
			Context jndiContext = new InitialContext(props);
			testJms(jndiContext);
			testUpdateDeviseViaJms(jndiContext);
			Thread.sleep(2000);
			testConversionSurEjbRemote(jndiContext);
			testDynCallOfWsSoap();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public static void testConversionSurEjbRemote(Context jndiContext) {
		try {
			GestionDevisesRemote ejbGestionDevises = null;
			if(serverType.equals(GLASSFISH)){
		         ejbGestionDevises = (GestionDevisesRemote) jndiContext.lookup("tp.myapp.minibank.core.impl.ejb.itf.GestionDevisesRemote");
		         //NB: if not set , username/password will be prompted by AAC / appclient in order to access secure remote EJB
			}else if(serverType.equals(JBOSS_WILDFLY)){
				  ejbGestionDevises = (GestionDevisesRemote) jndiContext.lookup("minibank-jee6/minibank-jee6-ejb-impl/GestionDevisesImpl!tp.myapp.minibank.core.impl.ejb.itf.GestionDevisesRemote");
				//app/minibank-jee6-ejb-1.0-SNAPSHOT/GestionComptesImpl!tp.myapp.minibank.core.impl.ejb.itf.GestionComptesRemote
			}
			
			System.out.println("test_convertir sur ejb remote:");
	        System.out.println("40 euros= "  + ejbGestionDevises.convertir(40, "euro", "yen") + " yens");
	        System.out.println("30 euros= "  + ejbGestionDevises.convertir(30, "euro", "dollar") + " dollars");
	        System.out.println("liste des devises via ejb remote:");
	        List<Devise> listeDevises = ejbGestionDevises.getListeDevises();
	        for(Devise devise : listeDevises){
	        	System.out.println("\t" + devise.toString());
	        }
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static void testDynCallOfWsSoap() {
	    QName SERVICE_NAME = new QName("http://tp.myapp.minibank/", "GestionDevisesImplService");
	    QName PORT_NAME = new QName("http://tp.myapp.minibank/", "GestionDevisesImplPort");

	    String wdlUrl =	null;
	    
	    if(serverType.equals(GLASSFISH)){
	    	wdlUrl = "http://localhost:8080/GestionDevisesImplService/GestionDevisesImpl?wsdl";
	    }else if(serverType.equals(JBOSS_WILDFLY)){
	    	 wdlUrl = "http://localhost:8080/minibank-jee6-ejb-impl/GestionDevisesImpl?wsdl";
	    }

	    URL wsdlDocumentLocation=null;
		try {wsdlDocumentLocation = new URL(wdlUrl);
		 } catch (MalformedURLException e) {	e.printStackTrace();}

		//avec import javax.xml.ws.Service;    
		Service service = Service.create(wsdlDocumentLocation, SERVICE_NAME);
		
		/*interface*/ GestionDevises  gestionDevisesWSProxy = (GestionDevises)  
		              service.getPort(PORT_NAME, GestionDevises.class);
		
		if(serverType.equals(JBOSS_WILDFLY)){
			BindingProvider bp = (BindingProvider) gestionDevisesWSProxy;
			bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "guest");
			bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "guest007");
		}
		/*
		System.out.println("liste des devises via SOAP web service:");
		  List<Devise> listeDevises =
					gestionDevisesWSProxy.getListeDevises();
	        for(Devise devise : listeDevises){
	        	System.out.println("\t" + devise.toString());
	        }
	        */
		System.out.println("Resultat web service soap , devise euro = " + gestionDevisesWSProxy.getDeviseByName("euro"));
		}
	
	private static Connection initJmsConnection(Context jndiContext){
		Connection connection = null;
		try {
			if(serverType.equals(GLASSFISH)){
			    connectionFactory = (ConnectionFactory)jndiContext.lookup("jms/__defaultConnectionFactory");
			    //predefini dans glassfish
			    connection = connectionFactory.createConnection();
			}
			else if(serverType.equals(JBOSS_WILDFLY)){
				 connectionFactory = (ConnectionFactory)jndiContext.lookup("jms/RemoteConnectionFactory");
				//avec <entry name="java:jboss/exported/jms/RemoteConnectionFactory"/>           
				//dans standalone(-full).xml 
				 connection = connectionFactory.createConnection( props.getProperty(Context.SECURITY_PRINCIPAL), props.getProperty(Context.SECURITY_CREDENTIALS)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public static void testJms(Context jndiContext) {
		try {
			Connection connection = initJmsConnection(jndiContext);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//queue = (Queue) jndiContext.lookup("myQueue"); //or jms/myQueue ?
			//Queue queue = (Queue) ic.lookup("jms/queue/myQueue"); 
			// avec queue/myQueue qui doit etre exporté dans standalone(-full).xml 
			//<entry name="java:jboss/exported/jms/queue/test"/> 
			
			queue=session.createQueue("myQueue"); //NB: createQueue() create a new queue or open an existing one
			
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message = session.createTextMessage();
			
			final int NUM_MSGS=3;
			for (int i = 0; i < NUM_MSGS; i++) {
			    message.setText("This is message " + (i + 1));
			    System.out.println("Sending message: " + message.getText());
			    messageProducer.send(message);
			}
			messageProducer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public static void testUpdateDeviseViaJms(Context jndiContext) {
		try {
			Connection connection = initJmsConnection(jndiContext);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			queue=session.createQueue("deviseQueue"); //NB: createQueue() create a new queue or open an existing one
			
			MessageProducer messageProducer = session.createProducer(queue);
			MapMessage message = session.createMapMessage();
			
			message.setString("code", "E"); // E,Y,D,L
			message.setDouble("change", 1.74);
			System.out.println("Sending message to update devise with code: " + message.getString("code") + " et change=" + message.getString("change"));
			messageProducer.send(message);
			messageProducer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

}
