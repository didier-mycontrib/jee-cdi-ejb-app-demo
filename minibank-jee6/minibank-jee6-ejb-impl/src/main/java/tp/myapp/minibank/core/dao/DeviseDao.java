
package tp.myapp.minibank.core.dao;

import java.util.List;

import org.mycontrib.generic.persistence.GenericDao;

import tp.myapp.minibank.core.entity._Devise;

public interface DeviseDao extends GenericDao<_Devise,String> {	
	  public _Devise getDeviseByName(String deviseName);
	  public List<_Devise> getAllDevise(); 
}
