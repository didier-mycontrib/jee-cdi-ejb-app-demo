
package tp.myapp.minibank.core.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="devise")
public  class Devise  implements java.io.Serializable {
 private static final long serialVersionUID = 1L;
 
	private Double dChange;

	private String monnaie;
	

	private String codeDevise;

	public Devise(){
		super(); 
	}     
	
	
	public Devise(String codeDevise , String monnaie, Double dChange) {
		super();
		this.dChange = dChange;
		this.monnaie = monnaie;
		this.codeDevise = codeDevise;
	}


	public String toString(){
		return "Devise("+ "dChange=" + dChange+","+ "monnaie=" + monnaie+","+ "codeDevise=" + codeDevise + ")";
	}
 
	public Double getDChange() {
		return this.dChange;
	}
	public void setDChange(Double dChange){
		this.dChange=dChange;
	}
	public String getMonnaie() {
		return this.monnaie;
	}
	public void setMonnaie(String monnaie){
		this.monnaie=monnaie;
	}
	public String getCodeDevise() {
		return this.codeDevise;
	}
	public void setCodeDevise(String codeDevise){
		this.codeDevise=codeDevise;
	}

        
}   
