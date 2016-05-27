package tp.myapp.minibank.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="Adresse")
@XmlRootElement(name="adresse")
public class _Adresse implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idAdr;
	private String rue;
	private String codePostal;
	private String ville;
		
	
	public _Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public _Adresse(Long idAdr, String rue, String codePostal, String ville) {
		super();
		this.idAdr = idAdr;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}




	@Override
	public String toString() {
		return "Adresse [idAdr=" + idAdr + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + "]";
	}
	
	public Long getIdAdr() {
		return idAdr;
	}
	public void setIdAdr(Long idAdr) {
		this.idAdr = idAdr;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	

}
