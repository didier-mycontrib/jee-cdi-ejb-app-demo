package tp.myapp.minibank.core.service.impl.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.myapp.minibank.core.dao.CompteDao;
import tp.myapp.minibank.core.dto.Compte;
import tp.myapp.minibank.core.dto.Operation;
import tp.myapp.minibank.core.entity._Compte;
import tp.myapp.minibank.core.entity._Operation;
import tp.myapp.minibank.core.impl.ejb.itf.GestionComptesLocal;
import tp.myapp.minibank.core.impl.ejb.itf.GestionComptesRemote;
import tp.myapp.minibank.core.interceptors.ExecutionTimeInterceptor;
import tp.myapp.minibank.core.service.GestionComptes;
import tp.myapp.minibank.core.service.MyServiceException;

@Stateless
@Named
@WebService(targetNamespace="http://tp.myapp.minibank/",
endpointInterface="tp.myapp.minibank.core.service.GestionComptes")
@Interceptors({ExecutionTimeInterceptor.class})
@PermitAll
//@TransactionManagement(TransactionManagementType.CONTAINER) by default
public class GestionComptesImpl implements GestionComptes,GestionComptesLocal,GestionComptesRemote {
	
	private static Logger logger = LoggerFactory.getLogger(GestionComptesImpl.class);
	
	@EJB
	private CompteDao compteDao;
	
	private Compte entityToDto(_Compte c){
		Compte cpt = new Compte(c.getNumero(), c.getLabel(), c.getSolde());
		return cpt;
	}
	
	private Operation entityOpToDtoOp(_Operation o){
		Operation op = new Operation(o.getLabel(), o.getMontant());
		op.setNumero(o.getNumero());
		op.setDateOp(o.getDateOp());
		return op;
	}
	
	private List<Compte> entityListToDtoList(List<_Compte> listeEntity){
		List<Compte> liste = new ArrayList<Compte>();
		for(_Compte c:listeEntity){
			liste.add(entityToDto(c));
		}
		return liste; 
	}
	
	private _Compte dtoToEntity(Compte c){
		_Compte cpt = new _Compte(c.getNumero(), c.getLabel(), c.getSolde());
		return cpt;
	}
	
	public GestionComptesImpl(){
		System.out.println("conctructeur de GestionComptesImpl avec compteDao="+compteDao+" et ejb/this="+this);
	}
	
	@PostConstruct
	protected void init(){
		System.out.println("GestionComptesImpl.init() / @PostConstruct avec compteDao="+compteDao+" et ejb/this="+this);
	}
	@PreDestroy
	protected void fin(){
		System.out.println("GestionComptesImpl.fin() / 	@PreDestroy sur ejb/this="+this);
	}
	
	@Override
	public Compte getCompteByNum(long numCpt) throws MyServiceException {
		Compte cpt=null;
		try {
			cpt= entityToDto(compteDao.getEntityById(numCpt));
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("echec GestionComptesImpl.getCompteByNum",e);
			throw new MyServiceException("echec GestionComptesImpl.getCompteByNum",e);
		}
		return cpt;
	}

	@Override
	public List<Compte> getComptesOfClient(long numClient)
			throws MyServiceException {		
		List<Compte> listeCpt = null;
		try {
			listeCpt=entityListToDtoList(compteDao.findComptesByNumCli(numClient));
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.getComptesOfClient",e);
			throw new MyServiceException("echec GestionComptesImpl.getComptesOfClient",e);
		}
		return listeCpt;
	}
	
	

	@Override
	//@TransactionAttribute(TransactionAttributeType.REQUIRED) by default
	public void transferer(double montant, long numCptDeb, long numCptCred)
			throws MyServiceException {
		try {
			_Compte cptDeb = compteDao.getEntityById(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			cptDeb.addOperation(new _Operation("debit virement",-montant));
			//compteDao.updateEntity(cptDeb);//inutile si cptDeb est persistant (du fait de @Transaction...)
			_Compte cptCred = compteDao.getEntityById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			cptCred.addOperation(new _Operation("credit virement",+montant));
			//compteDao.updateEntity(cptCred);//inutile si cptCred est persistant (du fait de @Transaction...)
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.transferer",e);
			throw new MyServiceException("echec GestionComptesImpl.transferer",e);
		}

	}

	public List<Operation> getOperationsOfCompte(long numCpt)
			throws MyServiceException {
		List<Operation> listeOp = new ArrayList<Operation>();
		try {
			_Compte cpt= compteDao.getEntityById(numCpt);
			for(_Operation o : cpt.getOperations()){
			   listeOp.add(entityOpToDtoOp(o));
			}
			//listeOp.size(); //En version sans DTO : pour éviter future lazy exeception coté web ou test unitaire 
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.getOperationsOfCompte",e);
			throw new MyServiceException("echec GestionComptesImpl.getgetOperationsOfCompte",e);
		}
		return listeOp;
	}

}
