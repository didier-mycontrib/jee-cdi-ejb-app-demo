
package tp.myapp.minibank.core.dao.jpa;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.myapp.minibank.core.dao.DeviseDao;
import tp.myapp.minibank.core.entity._Devise;

@Named
@Stateless
@Local
public class DeviseDaoJpa extends GenericDaoJpaImpl<_Devise,String> implements DeviseDao {
	
	@Override
	public _Devise getDeviseByName(final String deviseName) {
		/* return  this.entityManager
				.createQuery("select d from _Devise d where d.monnaie = :deviseName", _Devise.class)
				.setParameter("deviseName",deviseName)
				.getSingleResult();*/
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<_Devise> criteriaQuery = cb.createQuery(_Devise.class);
		
		Root<_Devise> deviseRoot = criteriaQuery.from(_Devise.class);
		Predicate pEqMonnaie = cb.equal(deviseRoot.get("monnaie") , deviseName);
		criteriaQuery.select(deviseRoot);
		criteriaQuery.where(pEqMonnaie);
		return this.entityManager.createQuery(criteriaQuery).getSingleResult();
	}
		
	@Override
	public List<_Devise> getAllDevise() {
		/*return  this.entityManager
				.createQuery("select d from _Devise d",_Devise.class).getResultList();*/
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<_Devise> criteriaQuery = cb.createQuery(_Devise.class);
		
		Root<_Devise> deviseRoot = criteriaQuery.from(_Devise.class);
		criteriaQuery.select(deviseRoot);		
		
		return this.entityManager.createQuery(criteriaQuery).getResultList();
	}
}
