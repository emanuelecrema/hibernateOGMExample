package com.did2014small.collections;

import java.util.List;

import javax.persistence.ElementCollection;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;   
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field; 
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded; 

@NamedQueries(
		{  
			@NamedQuery(
					name = "findCorsoStudiByName", 
					query = "FROM CorsoStudi cs WHERE cs.nome = :nome"  
			)  
		}  
) 

@Indexed 
@Entity 
@Table(name="corsostudi")  
public class CorsoStudi { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private Integer _id; 
	
	@Field 
	private String nome; 
	
	@Field 
	private String codice; 
	
	@Field 
	private String abbreviazione; 
	
	@Field 
	private Integer durataAnni; 
	
	@Field 
	private String sede;   
	
	@Field 
	private String informativa;  
	
	@Field 
	private Integer id_segreteria;  
	
	@IndexedEmbedded 
	@ElementCollection 
	private List<InsErogato> inserogati; 
	
	public CorsoStudi() {
		
	}

	public CorsoStudi(String nome, String codice, String abbreviazione, Integer durataAnni, String sede,
			String informativa , Integer id_segreteria, List<InsErogato> inserogati) {
		super();
		this.nome = nome;
		this.codice = codice;
		this.abbreviazione = abbreviazione;
		this.durataAnni = durataAnni;
		this.sede = sede;
		this.informativa = informativa;
		this.id_segreteria = id_segreteria; 
		this.inserogati = inserogati; 
	}
	
	public Integer get_id() {
		return _id; 
	} 
	
	public void set_id(Integer _id) {
		this._id = _id; 
	}       

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public Integer getDurataAnni() {
		return durataAnni;
	}

	public void setDurataAnni(Integer durataAnni) {
		this.durataAnni = durataAnni;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getInformativa() {
		return informativa;
	}

	public void setInformativa(String informativa) {
		this.informativa = informativa;
	}

	public Integer getId_segreteria() {
		return id_segreteria;
	}  

	public void setId_segreteria(Integer id_segreteria) {
		this.id_segreteria = id_segreteria;
	} 

	public List<InsErogato> getInserogati() {
		return inserogati; 
	}   
	
	public void setInserogati(List<InsErogato> inserogati) {
		this.inserogati = inserogati; 
	}

	@Override
	public String toString() {
		return "CorsoStudi [_id=" + _id + ", nome=" + nome + ", codice=" + codice + ", abbreviazione=" + abbreviazione
				+ ", durataAnni=" + durataAnni + ", sede=" + sede + ", informativa=" + informativa + ", id_segreteria="
				+ id_segreteria + ", inserogati=" + inserogati + "]";
	}    
	
}
