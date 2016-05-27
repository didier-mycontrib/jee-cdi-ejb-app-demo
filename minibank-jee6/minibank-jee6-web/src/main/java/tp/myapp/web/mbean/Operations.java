package tp.myapp.web.mbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tp.myapp.minibank.core.dto.Operation;
import tp.myapp.minibank.core.service.GestionComptes;

//@ManagedBean
@Named
@RequestScoped //javax.context (CDI/JSR299/JEE6) plutot que javax.faces.bean (JSF2 only)
public class Operations  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//private UIParameter paramNumCptSel;

	
	private Long numCpt;
	private List<Operation> listeOperations;
	
	@Inject
	private GestionComptes serviceGestionComptes;
	
	private GestionComptes getServiceGestionComptes(){
		//return clientComptes.getReferentiel().getFacadeMiniBank().getServiceGestionComptes();
		return serviceGestionComptes;
	}
	
	/*public String listerDernieresOperations(){
		String suite="operations";//.jsp
		try {
			this.numCpt=(Long) paramNumCptSel.getValue();
			listeOperations=getServiceGestionComptes().getOperationsOfCompte(numCpt);
		} catch (Exception e) {
			e.printStackTrace();
			suite=null;
		}
		return suite;
	}*/
	
	/*version mode GET (JSF2 , facelet) */
	public void initOperations(){
		try {
			System.out.println("initOperations (mode GET / JSF2 / facelet) , numCpt="+numCpt);
			//numCpt initialized by <f:viewParam name="numCptSel" value="#{operations.numCpt}"/>
			listeOperations=getServiceGestionComptes().getOperationsOfCompte(numCpt);
		} catch (Exception e) {
			e.printStackTrace();		
		}		
	}
	
	
	//@ManagedProperty(value="#{clientComptes}")
	@Inject
	private ClientComptes clientComptes;

	public ClientComptes getClientComptes() {
		return clientComptes;
	}

	public void setClientComptes(ClientComptes clientComptes) {
		this.clientComptes = clientComptes;
	}

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	public List<Operation> getListeOperations() {
		return listeOperations;
	}

	public void setListeOperations(List<Operation> listeOperations) {
		this.listeOperations = listeOperations;
	}

	/*
	public UIParameter getParamNumCptSel() {
		return paramNumCptSel;
	}

	public void setParamNumCptSel(UIParameter paramNumCptSel) {
		this.paramNumCptSel = paramNumCptSel;
	}
    */
	
	
	

}
