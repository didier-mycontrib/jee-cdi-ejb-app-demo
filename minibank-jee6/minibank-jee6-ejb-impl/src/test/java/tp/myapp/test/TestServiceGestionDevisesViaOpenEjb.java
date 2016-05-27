
package tp.myapp.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp.myapp.minibank.core.dto.Devise;
import tp.myapp.minibank.core.service.GestionDevises;


public class TestServiceGestionDevisesViaOpenEjb extends MyAbstractOpenEjbTest{ 

    @Inject
	private GestionDevises service = null; // service metier a tester
	

	  @Before
	     public void initService(){
	       if(service==null){
	         try{
	           String openEjbJndiName="GestionDevisesImpl" + "Local";
	           service= (GestionDevises )                
	                context.lookup(openEjbJndiName);
			}catch(Exception ex){ex.printStackTrace();}
		}
	      }
	        

	@Test
   public void test_getDeviseByName() {
     try{
        System.out.println("test_getDeviseByName");
        Devise d = service.getDeviseByName("euro");
        System.out.println("monnaie euro (getDeviseByName) : " + d);
        Assert.assertTrue(d.getMonnaie().equals("euro"));
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
	@Test
   public void test_convertir() {
     try{
        System.out.println("test_convertir");
        System.out.println("50 euros= "  + service.convertir(50, "euro", "yen") + " yens");
        System.out.println("20 euros= "  + service.convertir(20, "euro", "dollar") + " dollars");
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
	@Test
   public void test_getListeDevises() {
     try{
        System.out.println("test_getListeDevises");
       for(Devise d : service.getListeDevises()){
    	   System.out.println("\t"+d);
       }
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
      
}
