package org.mycontrib.generic.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;

//@Stateless et @Local dans une sous classe prise en charge par le conteneur EJB3
public abstract class  AbstractGenericDaoJpaImpl<T,ID extends Serializable> implements GenericDao<T,ID> {
	
	private Class<T> persistentClass;
	protected EntityManager entityManager;
	
	private void initPersistentClass(){
		try {
	    	   //System.out.println(getClass().getSimpleName());
	    	   ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
	    	   //System.out.println("parameterizedType="+parameterizedType.toString());
	    	   Type typeT = parameterizedType.getActualTypeArguments()[0];
	    	   //System.out.println(typeT.toString());
	    	   if(!typeT.toString().equals("T")){
	    		   this.persistentClass = (Class<T>) typeT;
	    		   //System.out.println("persistentClass="+persistentClass.getSimpleName());
	    	   }
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	public AbstractGenericDaoJpaImpl() {
		initPersistentClass();
     }
	

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	//m�thode � red�finir via
	//@PersistenceContext() ou @PersistenceContext(unitName="myPersistenceUnit")
	//public setEntityManager(EntityManager entityManager){
		//this.entityManager=entityManager;
	//}
	public abstract void setEntityManager(EntityManager entityManager);
	
	// getter for "entityManager" to use in dao subclass
	public EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public  void deleteEntity(ID pk) {
		Object e= getEntityById(pk);
		if(e!=null)
			removeEntity(e);
	}


	@Override
	public void removeEntity(Object e) {
		entityManager.remove(e);
	}

	@Override
	public  T updateEntity(T e) {
		return entityManager.merge(e);
	}

	@Override
	public  T getEntityById(ID pk) {
		return entityManager.find(persistentClass, pk);
	}

	@Override
	public  T persistNewEntity(T e) {
		entityManager.persist(e);
		return e;
	}

}
