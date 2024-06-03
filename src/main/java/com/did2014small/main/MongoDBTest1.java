package com.did2014small.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.Search; 
import org.hibernate.search.jpa.FullTextEntityManager; 
import org.hibernate.search.query.dsl.QueryBuilder;

import com.did2014small.collections.*;  

import org.hibernate.search.jpa.FullTextQuery; 

public class MongoDBTest1 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("did2014smallPu"); 
		EntityManager entityManager = entityManagerFactory.createEntityManager();  
		 
		// ... code ... 
		
		// Query 4 
		String q4 = "SELECT ie.insegn FROM CorsoStudi cs \n"
                + "\t JOIN cs.inserogati ie \n"      
           	+ "\t WHERE cs.nome = 'Laurea in Informatica' \n"; 
	    	
		List<Insegn> insegnamenti = entityManager
				.createQuery(q4, Insegn.class)
				.getResultList(); 
		
		for(Insegn i : insegnamenti) {
			System.out.println(i.getNomeins()); 
		}  
		
		// ... code ... 
		
		// close connection
		entityManager.close();

		// EDIT TO PROD!
		
		System.out.println("\nEntityManager closed");  
		
	}  

}   
