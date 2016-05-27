package tp.myapp.minibank.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Operation")
@XmlRootElement(name="operation")
public class _Operation implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="numOp")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	private String label;
	private Double montant; //positif ou negatif
	private Date dateOp;
	
	
	@ManyToOne
	@JoinColumn(name="ref_compte")
	private _Compte compte;
	
		
	@Override
	public String toString() {
		return "Operation [numero=" + numero + ", label=" + label
				+ ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

	public _Operation() {
		super();
	}
	

	public _Operation(String label, Double montant) {
		super();
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
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
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Date getDateOp() {
		return dateOp;
	}
	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

	public _Compte getCompte() {
		return compte;
	}

	public void setCompte(_Compte compte) {
		this.compte = compte;
	}
	
	
	
}
