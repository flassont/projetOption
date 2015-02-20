package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Nicolas, Téo, Amandine
 * Chaque UV est constituée de différents modules regroupant chacun des interventions
 * A la fin de l'annee, les eleves doivent valider individuellement chaque module
 * Pour valider un module, il faut que la moyenne des notes obtenues dans le module en question soit superieure a 8
 * Si un module n'est pas valide, il fait l'objet d'un rattrapage
 *
 */
@Entity
@Table(name="module")
public class Module extends Responsabilite {
	
	public Module() {}
	
	/**
	 * Interventions regroupees sous le module donne
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="module_intervention")
	private Collection<Intervention> interventions;
	
	/**
	 * UVs dans lesquelles le module donne est inclus
	 */
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="modules")
	private Collection<UV> uvs;
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}

}
