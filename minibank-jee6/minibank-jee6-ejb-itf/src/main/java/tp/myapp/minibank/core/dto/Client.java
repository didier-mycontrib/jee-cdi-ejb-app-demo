package tp.myapp.minibank.core.dto;

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

@XmlRootElement(name="client")
public class Client implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	
	private Long numero;
	private String nom;
	private String prenom;
	

	private Adresse adresse; 
	
	private String telephone;
	private String email;
	//future version: pas de password en clair ici !
	private String password;
	
	

	//private List<Compte> comptes;
	
	
	
	public Long getNumero() {
		return numero;
	}
	
	
	public Client() {
		super();
	}


	public Client(Long numero, String nom, String prenom, String telephone, String email) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
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
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
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
	/*
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	*/
	

}
