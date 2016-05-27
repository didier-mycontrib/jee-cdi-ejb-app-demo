
package tp.myapp.minibank.core.service.impl.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.jws.WebService;

import tp.myapp.minibank.core.dao.DeviseDao;
import tp.myapp.minibank.core.dto.Devise;
import tp.myapp.minibank.core.entity._Devise;
import tp.myapp.minibank.core.impl.ejb.itf.GestionDevisesLocal;
import tp.myapp.minibank.core.impl.ejb.itf.GestionDevisesRemote;
import tp.myapp.minibank.core.service.GestionDevises;

//@Stateless
@Singleton //(comme @Stateless mais toujours un seul , ce qui est pratique pour placer des données dedans)
@Named
@WebService(targetNamespace="http://tp.myapp.minibank/",
            endpointInterface="tp.myapp.minibank.core.service.GestionDevises")
@PermitAll
public  class GestionDevisesImpl implements GestionDevises , GestionDevisesLocal , GestionDevisesRemote {
	
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GestionDevisesImpl.class);

	private Devise entityToDto(_Devise d){
		return new Devise(d.getCodeDevise() , d.getMonnaie() , d.getDChange());
	}
	
	private List<Devise> entityListToDtoList(List<_Devise> listeEntity){
		List<Devise> liste = new ArrayList<Devise>();
		for(_Devise d:listeEntity){
			liste.add(entityToDto(d));
		}
		return liste; 
	}
	
	private _Devise dtoToEntity(Devise d){
		return new _Devise(d.getCodeDevise() , d.getMonnaie() , d.getDChange());
	}
	
	//@Inject
	@EJB
	private DeviseDao daoDevise;
	
	@Resource
	   private SessionContext context;
	   
	/*
	@PostConstruct
	public void init(){
		long duration=10;//la premiere fois apres 10 ms
		long interval=1000*60*5;
		context.getTimerService().createTimer(duration,interval, "sur GestionDevisesImpl toutes_les_5minutes_tant_que_pas_cancel");
	}

	@Timeout
	public void timeOutHandler(Timer timer){
	   System.out.println("timeoutHandler (GestionDevisesImpl) : " + timer.getInfo());        
	   timer.cancel();//pour arrêter (si periodique avec interval)
	}
	
	==> voir exemple @Schedule (automatic timer) sur GestionClientsImpl
	*/
	
   @Override
	public Devise getDeviseByPk(String codeDevise) throws RuntimeException {
		try{
			 _Devise entity =  daoDevise.getEntityById(codeDevise);
			 return entityToDto(entity);
		 }catch(Exception ex){
		    logger.error("echec getDeviseByPk",ex);
		    throw new RuntimeException("echec getDeviseByPk",ex);
		}
	}

   @Override
	public String createNewDevise(Devise devise) throws RuntimeException {
		try{
			daoDevise.persistNewEntity(dtoToEntity(devise));
			return devise.getCodeDevise();
		}catch(Exception ex){
		    logger.error("echec createNewDevise",ex);
		    throw new RuntimeException("echec createNewDevise",ex);
		}
	}
	
   @Override
	public void updateDevise(Devise devise) throws RuntimeException {
		try{
			daoDevise.updateEntity(dtoToEntity(devise));
		}catch(Exception ex){
		    logger.error("echec updateDevise",ex);
		    throw new RuntimeException("echec updateDevise",ex);
		}
	}
   @Override
	public void deleteDevise(String codeDevise) throws RuntimeException {
		try{
			daoDevise.deleteEntity(codeDevise);
		}catch(Exception ex){
		    logger.error("echec deleteDevise",ex);
		    throw new RuntimeException("echec deleteDevise",ex);
		}
	}

       
   @Override
	public Devise getDeviseByName(String name) throws RuntimeException {
		System.out.println("in GestionDeviseImpl.getDeviseByName , username = "+context.getCallerPrincipal());
		if(context.isCallerInRole("friends"))
			System.out.println("caller of GestionDeviseImpl.getDeviseByName as role=friends");
		try{
			return entityToDto(daoDevise.getDeviseByName(name));			
		}catch(Exception ex){
		    logger.error("echec getDeviseByName",ex);
		    throw new RuntimeException("echec getDeviseByName",ex);
		}	              
	}
   @Override
	public double convertir(double montant,String monnaieScr,String monnaieDest) throws RuntimeException {
		double res=0.0;
		try{
			_Devise devSrc = daoDevise.getDeviseByName(monnaieScr);
			_Devise devDest = daoDevise.getDeviseByName(monnaieDest);
			res=montant *  devSrc.getDChange() / devDest.getDChange()  ;
			return res;			
		}catch(Exception ex){
		    logger.error("echec convertir",ex);
		    throw new RuntimeException("echec convertir",ex);
		}	                
	}

	//@RolesAllowed("friends")
   @Override
	public List<Devise> getListeDevises() throws RuntimeException {
		System.out.println("in GestionDeviseImpl.getListeDevises , username = "+context.getCallerPrincipal());
		if(context.isCallerInRole("friends"))
			System.out.println("caller of getListeDevises as role=friends");
		try{
			return entityListToDtoList(daoDevise.getAllDevise());	
		}catch(Exception ex){
		    logger.error("echec getListeDevises",ex);
		    throw new RuntimeException("echec getListeDevises",ex);
		}	                 
	}
}
