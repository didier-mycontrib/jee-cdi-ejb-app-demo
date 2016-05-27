package tp.myapp.web.mbean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import tp.myapp.minibank.core.facade.FacadeMiniBank;
import tp.myapp.minibank.core.facade.FacadeMiniBankFactory;

//@ManagedBean
@Named
@ApplicationScoped //javax.context (CDI/JSR299/JEE6) plutot que javax.faces.bean (JSF2 only)
public class Referentiel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Referentiel (cote web) et FacadeMiniBank (cote EJB)
	// PLUS absolument NECESSAIRE si injections directes des EJBs via CDI/@Inject de JEE6
	
	/*
	private FacadeMiniBank facadeMiniBank;
	
	public Referentiel(){
		
		
		facadeMiniBank = FacadeMiniBankFactory.getInstance();
	}

	public FacadeMiniBank getFacadeMiniBank() {
		return facadeMiniBank;
	}
	*/
	
	

}
