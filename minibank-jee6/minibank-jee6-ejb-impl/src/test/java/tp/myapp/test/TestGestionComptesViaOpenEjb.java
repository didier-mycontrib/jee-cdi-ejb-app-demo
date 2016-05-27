package tp.myapp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp.myapp.minibank.core.dto.Compte;
import tp.myapp.minibank.core.dto.Operation;
import tp.myapp.minibank.core.entity._Compte;
import tp.myapp.minibank.core.service.GestionComptes;

public class TestGestionComptesViaOpenEjb extends MyAbstractOpenEjbTest{
	
    //service (ejb) a  tester
	private GestionComptes serviceGestionComptes;
	
	
	
	  @Before
	     public void initService(){
	       if(serviceGestionComptes==null){
	         try{
	           String openEjbJndiName="GestionComptesImpl" + "Local";
	           serviceGestionComptes= (GestionComptes )                
	                context.lookup(openEjbJndiName);
			}catch(Exception ex){ex.printStackTrace();}
		}
	      }

	@Test
	public void testGetCompteByNum(){
		try {
			Compte cpt = serviceGestionComptes.getCompteByNum(1L);
			System.out.println("compte 1 = " + cpt.toString());
			Assert.assertTrue(cpt.getNumero()==1L);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetComptesOfClient(){
		try {
			System.out.println("liste des comptes du client 1 :");
			List<Compte> listeCpt = serviceGestionComptes.getComptesOfClient(1L);
			for(Compte cpt:listeCpt){
				System.out.println("\t"+cpt.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetOperationsOfCompte(){
		try {
			System.out.println("liste des operations du compte 1 :");
			List<Operation> listeOp = serviceGestionComptes.getOperationsOfCompte(1L);
			for(Operation op:listeOp){
				System.out.println("\t"+op.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testBonTransfert(){		
		try {
			System.out.println("test de virement interne");
			double s1_avant = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_avant = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("avant bon virement s1="+s1_avant + " s2=" +s2_avant);
			serviceGestionComptes.transferer(50,1L,2L);
			double s1_apres = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_apres = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("apres bon virement s1="+s1_apres + " s2=" +s2_apres);
			Assert.assertEquals(s1_apres, s1_avant - 50,0.01);
			Assert.assertEquals(s2_apres, s2_avant + 50,0.01);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testMauvaisTransfert(){		
		try {
			System.out.println("test de rollback sur virement interne avec mauvais numCptCred");
			double s1_avant = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_avant = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("avant mauvais virement s1="+s1_avant + " s2=" +s2_avant);
			try {
				serviceGestionComptes.transferer(50,1L,-2L);
			} catch (Exception e) {
				System.out.println("echec normal (virement vers compte -2)");
				//e.printStackTrace();
			}
			double s1_apres = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_apres = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("apres mauvais virement s1="+s1_apres + " s2=" +s2_apres);
			Assert.assertEquals(s1_apres, s1_avant - 0,0.01);
			Assert.assertEquals(s2_apres, s2_avant + 0,0.01);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}

}
