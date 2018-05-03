package com.did2014small.collections;

import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;    

@Indexed 
@Embeddable
public class Insegn { 
	
	private Integer _id; 
	
	@Field  
	private String nomeins;  
	
	@Field  
	private String codiceins; 
	
	public Insegn() {
		
	} 
	
	public Insegn(String nomeins, String codiceins) {
		super();
		this.nomeins = nomeins;
		this.codiceins = codiceins;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	} 

	public String getNomeins() {
		return nomeins;
	}

	public void setNomeins(String nomeins) {
		this.nomeins = nomeins;
	} 
	
	public String getCodiceins() {
		return codiceins;
	}

	public void setCodiceins(String codiceins) {
		this.codiceins = codiceins;
	} 
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insegn other = (Insegn) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Insegn [_id=" + _id + ", nomeins=" + nomeins + ", codiceins=" + codiceins + "]";
	}
}  
