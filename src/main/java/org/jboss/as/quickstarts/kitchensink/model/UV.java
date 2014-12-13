package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UV extends Responsabilite {
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Module> modules;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Option> options;
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<Module> getModules() {
		return this.modules;
	}
	
	public Collection<Option> getOptions() {
		return this.options;
	}
	
}
