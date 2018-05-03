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
		 
		// Query 1 
		// In questo caso non ci sarebbe bisogno di DISTINCT 
		String q1 = "SELECT DISTINCT cs FROM CorsoStudi cs JOIN cs.inserogati ie \n"
				+ "\t WHERE ie.insegn.nomeins = 'Logica'"; 
		System.out.println("Query 1: " + q1 + "\n"); 
		
		List<CorsoStudi> corsiStudi = entityManager
				.createQuery(q1, CorsoStudi.class)
				.getResultList(); 
		
		for(CorsoStudi cs : corsiStudi) {
			System.out.println(cs.getNome()); 
		}  
		
		// Query 2 
		String q2 = "FROM CorsoStudi cs \n"
				+ "\t WHERE cs.nome = 'Laurea in Informatica'"; 
		
		
		
		System.out.println("\nQuery 2: " + q2 + "\n"); 
		
		corsiStudi = entityManager
				.createNamedQuery("findCorsoStudiByName", CorsoStudi.class)
				.setParameter("nome", "Laurea in Informatica").getResultList(); 
		
		Set<Insegn> insegnamenti = new HashSet<Insegn>();   
		for(CorsoStudi cs : corsiStudi) { 
			for(InsErogato ie : cs.getInserogati()) {
				insegnamenti.add(ie.getInsegn()); 
			}  
		} 
		
		for(Insegn i : insegnamenti) {
			System.out.println(i); 
		} 
		 
		// Query 3 
		String q3 = "FROM CorsoStudi cs \n" 
				+ "\t WHERE cs.nome = 'Laurea in Informatica'"; 
			
		System.out.println("\nQuery 3: " + q3 + "\n"); 
		
		// begin transaction 
		entityManager.getTransaction().begin();  
		
		corsiStudi = entityManager
				.createQuery(q3, CorsoStudi.class)
				.getResultList(); 
		
		for(CorsoStudi cs : corsiStudi) {
			for(InsErogato ie : cs.getInserogati())   
				System.out.println("_id: " + ie.get_id() + ", nomeins: "+ ie.getInsegn().getNomeins() + ", annoaccademico: " + ie.getAnnoaccademico()); 
		}  
	 
		// Query 4 
		String q4 = "db.corsostudi.find({ 'nome': 'Laurea in Informatica' })"; 
			
		System.out.println("\nQuery 4: " + q4 + "\n"); 
		
		corsiStudi = entityManager
				.createNativeQuery(q4, CorsoStudi.class) 
				.getResultList(); 
		
		for(CorsoStudi cs : corsiStudi) {  
			insegnamenti.clear(); 
			System.out.println("corsoStudi: " + cs.getNome());  
			for(InsErogato ie : cs.getInserogati()) {
				insegnamenti.add(ie.getInsegn()); 
			} 
			for(Insegn i : insegnamenti) { 
				System.out.println("\t Nomeins: " + i.getNomeins()); 
			} 
		}  
	
		// entityManager.getTransaction().begin(); 
		
		//Add full-text superpowers to any EntityManager: 
	    //FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);   
	    /*    
	    MongoClient mongoClient = new MongoClient("localhost", 27017);  
		MongoDatabase mongoDatabase = mongoClient.getDatabase("did2014small");  
	   
	    MongoCollection<Document> corsostudiCollection = mongoDatabase.getCollection("corsostudi"); 
	    
	    List<Integer> ids = new ArrayList<Integer>();
	   
	    FindIterable<Document> corsiStudiDocuments = corsostudiCollection.find(); 
	    
	    for(Document corsoStudiDocument : corsiStudiDocuments) {
	    	Integer id = corsoStudiDocument.getInteger("_id"); 
	    	System.out.println(id);  
	    	ids.add(id); 
	    }   

	    System.out.println(">"+ids.size());

	    for(Integer c : ids){
	        CorsoStudi cs = new CorsoStudi(); 
	        cs.set_id(c); 
	        
	        // find cs 
	        String query = "FROM CorsoStudi cs \n" 
					+ "\t WHERE cs._id = " + cs.get_id();   
				
			System.out.println("\nQuery : " + query + "\n"); 
			
			// begin transaction 
			entityManager.getTransaction().begin();  
			
			cs = entityManager
					.createQuery(query, CorsoStudi.class).getSingleResult(); 
			
			// commit transaction 
			entityManager.getTransaction().commit();  
	        
	        System.out.println("indexing: " + cs.get_id());
	        ftem.index(cs);//index each element
	        ftem.flushToIndexes();//apply changes to indexes
	        ftem.clear();//free memory since the queue is processed
	    } 
	    
	    mongoClient.close(); */ 
		
		//Add full-text superpowers to any EntityManager:
		FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);
		/* try {
			ftem.createIndexer(CorsoStudi.class) 
			 .startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */       
		
		//Optionally use the QueryBuilder to simplify Query definition:
		QueryBuilder b = ftem.getSearchFactory()
		   .buildQueryBuilder()
		   .forEntity(CorsoStudi.class)
		   .get();

		//Create a Lucene Query:
		Query lq = b.keyword().onField("inserogati.insegn.nomeins").matching("operativi").createQuery();

		//Transform the Lucene Query in a JPA Query:
		FullTextQuery ftQuery = ftem.createFullTextQuery(lq, CorsoStudi.class);

		//List all matching Hypothesis:  
		List<CorsoStudi> corsiStudi_hs = ftQuery.getResultList(); 
		
	    System.out.println("\nHibernate Search "); 
	    for(CorsoStudi cs : corsiStudi_hs) {  
	    	System.out.print("Nome: " + cs.getNome()); 
	    	System.out.println(", inserogati count: " + cs.getInserogati().size());  
	    }  
	    /*
	    for(InsErogato ie : corsiStudi_hs.get(3).getInserogati()) {
	    	System.out.println("inserogato_padre: " + ie.getInserogatoPadre());     
	    } */
	    
		// close connection 
		entityManager.close();  
		
		System.out.println("\nEntityManager closed");  
		
	}  

}   
