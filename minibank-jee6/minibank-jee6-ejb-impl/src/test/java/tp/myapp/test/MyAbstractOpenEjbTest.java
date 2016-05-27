package tp.myapp.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;

public abstract class MyAbstractOpenEjbTest {
	
	protected static Context context; // jndi context for open-ejb

	private static final String TEST_OPENEJB_DS="jdbc/minibankDS";
	
	public static void initializeDerbyProperties(Properties properties) throws Exception {
		 //acces local à base derby , org.apache.derby.jdbc.EmbeddedDriver , jdbc:derby:C:\Prog\glassfish4\javadb\minibank_db_ex1
	    //acces (potentiellement) distant à base derby gérée par un "javadb server" : org.apache.derby.jdbc.ClientDriver , jdbc:derby://localhost:1527/minibank_db_ex1
	    
	    properties.put(TEST_OPENEJB_DS+".JdbcDriver", "org.apache.derby.jdbc.ClientDriver");
	    properties
	        .put(TEST_OPENEJB_DS+".JdbcUrl", "jdbc:derby://localhost:1527/minibank_db_ex2");
	    properties.put(TEST_OPENEJB_DS+".username", "APP");
	    properties.put(TEST_OPENEJB_DS+".password", "APP");
	    
	    properties.put("myPersistenceUnit.hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
	}
	
    public static void initializeMySqlProperties(Properties properties) throws Exception {
    	properties.put(TEST_OPENEJB_DS+".JdbcDriver", "com.mysql.jdbc.Driver");
	    properties
	        .put(TEST_OPENEJB_DS+".JdbcUrl", "jdbc:mysql://localhost/minibank_db_ex2");
	    properties.put(TEST_OPENEJB_DS+".username", "root");
	    properties.put(TEST_OPENEJB_DS+".password", "formation");//"root" or "formation" or ...
	    
	    properties.put("myPersistenceUnit.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	}
	
	@BeforeClass
	public static void initializeEmbeddedContainer() throws Exception {
	    Properties properties = new Properties();
	    properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
	        "org.apache.openejb.client.LocalInitialContextFactory");

	    properties.put(TEST_OPENEJB_DS, "new://Resource?type=DataSource");
	    
	    initializeMySqlProperties(properties);
	    //initializeDerbyProperties(properties);
	   
	    properties.put("javax.persistence.provider","org.hibernate.ejb.HibernatePersistence");
	    properties.put("openejb.embedded.initialcontext.close", "destroy");

	    context = new InitialContext(properties);
	  }
	
	  public abstract void initService();


}
