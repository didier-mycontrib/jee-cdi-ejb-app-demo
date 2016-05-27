package tp.myapp.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp.myapp.minibank.core.dto.Client;
import tp.myapp.minibank.core.service.GestionClients;
	
public class TestGestionClientsViaOpenEjb extends MyAbstractOpenEjbTest{
	
    //service (ejb) a  tester
	@Inject
	private GestionClients service;
	
		
	 @Before
	   public void initService(){
	      if(service==null){
	         try{
	           String openEjbJndiName="GestionClientsImpl" + "Local";
	           service= (GestionClients )                
	                context.lookup(openEjbJndiName);
			}catch(Exception ex){ex.printStackTrace();}
	  }
	 }
	
	
	@Test
	public void testGetClientByNum(){
		try {
			Client cli = service.getClientByNum(1L);
			System.out.println("client 1 = " + cli.toString());
			Assert.assertTrue(cli.getNumero()==1L);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testPasswordOfClient(){
		try {
		
			System.out.println("affectation  et verification du mot de passe new-pwd1 au client 1  :");
			service.changePasswordOfClient(1L, "new-pwd1");
			Assert.assertTrue(service.isGoodPasswordOfClient(1L, "new-pwd1"));
			System.out.println("affectation et verification du mot de passe pwd1 au client 1  :");
			service.changePasswordOfClient(1L, "pwd1");
			Assert.assertTrue(service.isGoodPasswordOfClient(1L, "pwd1"));			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	
}
