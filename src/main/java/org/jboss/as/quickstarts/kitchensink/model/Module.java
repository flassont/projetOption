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
 * Chaque UV est constituée de différents modules regroupant chacun des interventions
 * A la fin de l'annee, les eleves doivent valider individuellement chaque module
 * Pour valider un module, il faut que la moyenne des notes obtenues dans le module en question soit superieure a 8
 * Si un module n'est pas valide, il fait l'objet d'un rattrapage
 *
 */
@Entity
public class Module extends Responsabilite {
	
	/**
	 * Annee pour laquelle le module est valable
	 */
	@NotNull
	private int annee;
	
	/**
	 * Interventions regroupees sous le module donne
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Intervention> interventions;
	
	/**
	 * UVs dans lesquelles le module donne est inclus
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<UV> uvs;
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}

}
