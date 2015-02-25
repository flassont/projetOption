package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

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
	
	public UV() {}
	
	/**
	 * Liste des modules inclus dans l'UV
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Module> modules;
	
	/**
	 * Liste des options dans lesquelles l'UV est enseignee
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Formation> options;
	
	public Collection<Module> getModules() {
		return this.modules;
	}
	
	public Collection<Formation> getOptions() {
		return this.options;
	}
	
}
