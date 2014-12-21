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

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Une UV est un regroupement de modules de cours
 * A la fin de l'annee, les eleves doivent valider individuellement chaque UV de leur option
 * Une UV est validee si la moyenne des notes des modules est superieure a 8
 *
 */
@Entity
public class UV extends Responsabilite {
	
	/**
	 * Liste des modules inclus dans l'UV
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Module> modules;
	
	/**
	 * Liste des options dans lesquelles l'UV est enseignee
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Option> options;
	
	public Collection<Module> getModules() {
		return this.modules;
	}
	
	public Collection<Option> getOptions() {
		return this.options;
	}
	
}
