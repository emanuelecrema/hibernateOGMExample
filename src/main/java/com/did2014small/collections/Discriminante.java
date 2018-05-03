package com.did2014small.collections;

import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;  

@Indexed   
@Embeddable 
public class Discriminante { 
	
	private Integer _id; 
	
	@Field  
	private String nome; 
	
	@Field  
	private String descrizione; 
	
	public Discriminante() {
		
	}

	public Discriminante(String nome, String descrizione) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Integer getId() {  
		return _id; 
	}

	public void setId(Integer _id) {
		this._id = _id;
	}
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Discriminante [_id=" + _id + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}  
	
	
}
