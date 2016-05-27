package tp.myapp.minibank.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="Client")
@XmlRootElement(name="client")
public class _Client implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="numClient")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	private String nom;
	private String prenom;
	
	@OneToOne
	@JoinColumn(name="ref_adressePrincipale")
	private _Adresse adresse; 
	
	private String telephone;
	private String email;
	//future version: pas de password en clair ici !
	private String password;
	
	
	@ManyToMany(mappedBy="proprietaires")
	private List<_Compte> comptes;
	
	
	public _Client() {
		super();
	}


	public _Client(Long numero, String nom, String prenom, String telephone, String email) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}
	
	public Long getNumero() {
		return numero;
	}
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", telephone=" + telephone + ", email=" + email
				+ "]";
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public _Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(_Adresse adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<_Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<_Compte> comptes) {
		this.comptes = comptes;
	}
	

}
