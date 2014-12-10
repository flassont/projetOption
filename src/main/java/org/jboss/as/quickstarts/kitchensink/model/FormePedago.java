package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class FormePedago {
	
	@NotNull
	@NotEmpty
	@Id
	private String formePedago;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@NotNull
	@NotEmpty
	private double coef;
	
	@OneToMany
	private Collection<Intervention> interventions;
	
	public String getFormePedago() {
		return this.formePedago;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public double getCoef() {
		return this.coef;
	}
	
	public void setCoef(double coef) {
		this.coef=coef;
	}
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}

}
