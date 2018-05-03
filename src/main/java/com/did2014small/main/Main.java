package com.did2014small.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.did2014small.collections.*;  

public class Main 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory entityManagerFactory;  
		entityManagerFactory = Persistence.createEntityManagerFactory("did2014smallPu"); 
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		 
		entityManager.getTransaction().begin(); 
	
		List<CorsoStudi> corsiStudi = entityManager
				.createQuery("FROM CorsoStudi WHERE nome = 'Laurea in Informatica'", CorsoStudi.class)          
				.getResultList(); 
		
		int count = 0; 
		for(CorsoStudi cs : corsiStudi) {
			System.out.println(cs.getNome()); 
			int i = 0; 
			for(InsErogato ie : cs.getInserogati()) { 
				if(ie.getInserogatoPadre()!=null) { 
					System.out.println("Insegn: " + ie.getInsegn().getNomeins() + ", anno accademico: " + ie.getAnnoaccademico() + ", inserogato_padre: "
						+ ie.getInserogatoPadre().get_id() + " " + ie.getInserogatoPadre().getInsegn().getNomeins()); 
				} else {
					System.out.println("Insegn: " + ie.getInsegn().getNomeins() + ", anno accademico: " + ie.getAnnoaccademico()); 
				}  
				i++; 
			}  
			System.out.println("Inserogati: " + i);  
			System.out.println("------------------------------------------------------------------------");
			count++; 
		}  
		
		System.out.println("Count: " + count + "\n\n"); 
		
		
		entityManager.getTransaction().commit(); 
		
		entityManager.getTransaction().begin();  
		
		corsiStudi = entityManager
				.createQuery("SELECT cs FROM CorsoStudi cs JOIN cs.inserogati ie "
						+ "WHERE ie.insegn.nomeins = 'Logica'", CorsoStudi.class)          
				.getResultList(); 
		
		for(CorsoStudi cs : corsiStudi) {
			System.out.println(cs.getNome());   
		}   
		
		entityManager.getTransaction().commit(); 
		
		entityManager.getTransaction().begin(); 
		
		corsiStudi = entityManager
				.createNamedQuery("findCorsoStudiByName", CorsoStudi.class)
				.setParameter("nome", "Laurea in Informatica")   
				.getResultList(); 
		
		for(CorsoStudi cs : corsiStudi) {
			System.out.println(cs.get_id() + " " + cs.getNome());   
		}   
		
		entityManager.getTransaction().commit(); 
		
		entityManager.close(); 
    }
}
