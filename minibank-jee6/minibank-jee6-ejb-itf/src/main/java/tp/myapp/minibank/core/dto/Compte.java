package tp.myapp.minibank.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="compte")
public class Compte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long numero;
	private String label;
	private Double solde;
	
	
	//private List<Client> proprietaires;
	
	//private List<Operation> operations;
	
	/*	
	public void addOperation(Operation op){
		if(operations==null)
			operations=new ArrayList<Operation>();
		op.setCompte(this);
		operations.add(op);
	}*/
		
	public Compte() {
		super();
	}
	
		
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde="
				+ solde + "]";
	}
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}

	/*
	public List<Client> getProprietaires() {
		return proprietaires;
	}

	public void setProprietaires(List<Client> proprietaires) {
		this.proprietaires = proprietaires;
	}*/
    /*
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}*/
	
	

}
