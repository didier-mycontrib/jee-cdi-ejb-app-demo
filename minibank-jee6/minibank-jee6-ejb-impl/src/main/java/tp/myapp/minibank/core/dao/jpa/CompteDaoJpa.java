package tp.myapp.minibank.core.dao.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.myapp.minibank.core.dao.CompteDao;
import tp.myapp.minibank.core.entity._Client;
//import tp.myapp.minibank.core.entity.Client_;
import tp.myapp.minibank.core.entity._Compte;

@Stateless
@Local
public class CompteDaoJpa extends GenericDaoJpaImpl<_Compte,Long> implements CompteDao {

	
	@Override
	public List<_Compte> findComptesByNumCli(long numCli) {		
		/*
	    return this.getEntityManager().createQuery("SELECT cpt FROM _Client cli  JOIN cli.comptes cpt WHERE cli.numero= :numCli",_Compte.class)
		    .setParameter("numCli", numCli)
	        .getResultList();
	        */
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<_Compte> criteriaQuery = cb.createQuery(_Compte.class);
		
		Root<_Client> clientRoot = criteriaQuery.from(_Client.class);
		
				
		// "Client_" pour "TypeSafe code" avec  pour Devise_.monnaie et Devise_.comptes 
		// Attention ceci nécessite la mise en place d'un générateur de code (ex: hibernate-jpamodelgen) 
		// déclenchable (temporairement ou pas) via maven ou autre :
		//<compilerArguments>
             //<processor>org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor</processor>
        //</compilerArguments>  dans la configuration de maven-compiler-plugin
		// il faut en plus paramétrer le projet eclipse pour qu'il voit les classes générées 
		//(compiler / annotation-processing / enable specific / "target/generated-sources/annotations") , ...
		// ===> GROSSE USINE A GAZ !!!!
		
		Predicate pEqNumCli = cb.equal(clientRoot.get("numero") , numCli);
		//Predicate pEqNumCli = cb.equal(clientRoot.get(Client_.numero) , numCli);
		
		Join<_Client, _Compte> joinComptesOfClient =  clientRoot.join("comptes");
		//Join<Client, Compte> joinComptesOfClient =  clientRoot.join(Client_.comptes);
		
		
		criteriaQuery.select(joinComptesOfClient);		
		criteriaQuery.where(pEqNumCli);
		return this.entityManager.createQuery(criteriaQuery).getResultList();
	}

}
