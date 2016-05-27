package org.mycontrib.generic.persistence;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



//@Stateless et @Local dans une sous classe prise en charge par le conteneur EJB3
public class GenericDaoJpaImpl<T,ID extends Serializable> extends AbstractGenericDaoJpaImpl<T,ID> {
	
		
	public GenericDaoJpaImpl() {
		super();		
	}

	@PersistenceContext() //with default (unique) persitent Unit.
	//@PersistenceContext(unitName="myPersistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
