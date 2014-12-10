package org.jboss.as.quickstarts.kitchensink.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Module {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@NotNull
	@NotEmpty
	private String intitule;
	
	@NotNull
	@NotEmpty
	private Intervenant responsable;
	
	@NotNull
	@NotEmpty
	@ManyToOne(fetch=FetchType.EAGER)
	private UV uv;
	
	public long getId() {
		return this.id;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public String getIntitule() {
		return this.intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule=intitule;
	}
	
	public Intervenant getResponsable() {
		return this.responsable;
	}
	
	public void setResponsable(Intervenant respo) {
		this.responsable=respo;
	}
	
	public UV getUV() {
		return this.uv;
	}

}
