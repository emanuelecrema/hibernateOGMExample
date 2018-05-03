package com.did2014small.collections;

import javax.persistence.Embeddable;   
import javax.persistence.Embedded;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded; 

@Indexed  
@Embeddable 
public class InsErogatoPadre { 
	
	private Integer _id; 
	
	@Field  
	private String annoaccademico; 
	
	@IndexedEmbedded   
	@Embedded 
	private Insegn insegn; 
	
	@IndexedEmbedded   
	@Embedded
	private Discriminante discriminante; 

	@Field  
	private Integer modulo;  
	
	@Field  
	private String discriminantemodulo; 
	
	@Field  
	private String nomemodulo; 
	
	@Field  
	private Double crediti; 
	
	@Field  
	private String programma; 
	
	@Field  
	private Integer id_facolta; 
	
	@Field  
	private String hamoduli;  
	
	@Field  
	private String nomeunita; 
	
	@Field  
	private Integer annierogazione; 
	
	public InsErogatoPadre() {
		
	}

	public InsErogatoPadre(String annoaccademico, Insegn insegn, Discriminante discriminante, Integer modulo,
			String discriminantemodulo, String nomemodulo, Double crediti, String programma, Integer id_facolta,
			String hamoduli, String nomeunita, Integer annierogazione) {
		super();
		this.annoaccademico = annoaccademico;
		this.insegn = insegn;
		this.discriminante = discriminante;
		this.modulo = modulo;
		this.discriminantemodulo = discriminantemodulo;
		this.nomemodulo = nomemodulo;
		this.crediti = crediti;
		this.programma = programma;
		this.id_facolta = id_facolta;
		this.hamoduli = hamoduli;
		this.nomeunita = nomeunita;
		this.annierogazione = annierogazione;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getAnnoaccademico() {
		return annoaccademico;
	}

	public void setAnnoaccademico(String annoaccademico) {
		this.annoaccademico = annoaccademico;
	}

	public Insegn getInsegn() {
		return insegn;
	}

	public void setInsegn(Insegn insegn) {
		this.insegn = insegn;
	}

	public Discriminante getDiscriminante() {
		return discriminante;
	}

	public void setDiscriminante(Discriminante discriminante) {
		this.discriminante = discriminante;
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}

	public String getDiscriminantemodulo() {
		return discriminantemodulo;
	}

	public void setDiscriminantemodulo(String discriminantemodulo) {
		this.discriminantemodulo = discriminantemodulo;
	}

	public String getNomemodulo() {
		return nomemodulo;
	}

	public void setNomemodulo(String nomemodulo) {
		this.nomemodulo = nomemodulo;
	}

	public Double getCrediti() {
		return crediti;
	}

	public void setCrediti(Double crediti) {
		this.crediti = crediti;
	}

	public String getProgramma() {
		return programma;
	}

	public void setProgramma(String programma) {
		this.programma = programma;
	}

	public Integer getId_facolta() {
		return id_facolta;
	}

	public void setId_facolta(Integer id_facolta) {
		this.id_facolta = id_facolta;
	}

	public String getHamoduli() {
		return hamoduli;
	}

	public void setHamoduli(String hamoduli) {
		this.hamoduli = hamoduli;
	}

	public String getNomeunita() {
		return nomeunita;
	}

	public void setNomeunita(String nomeunita) {
		this.nomeunita = nomeunita;
	}

	public Integer getAnnierogazione() {
		return annierogazione;
	}

	public void setAnnierogazione(Integer annierogazione) {
		this.annierogazione = annierogazione;
	}

	@Override
	public String toString() {
		return "InsErogatoPadre [_id=" + _id + ", annoaccademico=" + annoaccademico + ", insegn=" + insegn
				+ ", discriminante=" + discriminante + ", modulo=" + modulo + ", discriminantemodulo="
				+ discriminantemodulo + ", nomemodulo=" + nomemodulo + ", crediti=" + crediti + ", programma="
				+ programma + ", id_facolta=" + id_facolta + ", hamoduli=" + hamoduli + ", nomeunita=" + nomeunita
				+ ", annierogazione=" + annierogazione + "]";
	} 
} 
