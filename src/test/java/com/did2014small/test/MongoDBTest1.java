package com.did2014small.test;

import com.did2014small.collections.*; 

import java.util.List;  

import org.hibernate.ogm.utils.OgmAssertions; 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.junit.After;  
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException; 

public class MongoDBTest1 {
	
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("did2014smallPu"); 
	
	private static EntityManager entityManager; 
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	@Before
	public void createSession() {
		closeSession();
		entityManager = entityManagerFactory.createEntityManager(); 
		entityManager.getTransaction().begin();  
	} 
	
	@After
	public void closeSession() {
		if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().commit();  
		}   
		if (entityManager != null ) {
			entityManager.close();
			entityManager = null;  
		}
	} 
	
	@Test
	public void testEqualOperatorWithEmbeddedCollection() throws Exception {
		List<?> result = entityManager.createQuery("FROM CorsoStudi cs WHERE cs.nome = 'Laurea in Informatica'").getResultList(); 
		OgmAssertions.assertThat(result).onProperty("nome").containsOnly("Laurea in Informatica");
	}
	
}
