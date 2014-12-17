package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Une option est le regroupement d'eleves ayant choisi la meme specialite a l'ecole
 * Le departement informatique gere actuellement 8 options :
 * La promotion tronc commun de 1ere annee FING
 * La formation ingenierie logicielle 1ere annee
 * La promotion GSI de 2eme annee FING
 * La promotion GIPAD de 2eme annee FING
 * La formation ingenierie logicielle 2eme annee
 * La promotion GSI de 3eme annee FING
 * La promotion GIPAD de 3eme annee FING
 * La formation ingenierie logicielle 3eme annee
 *
 */
@Entity
public class Option extends Responsabilite {
	
	/**
	 * Le nombre d'eleves de l'option
	 */
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*")
	private int nbEleves;
	
	/**
	 * Annee pour laquelle l'option est valable
	 */
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	/**
	 * Liste des UVs de l'option
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<UV> uvs;
	
	public int getNbEleves() {
		return this.nbEleves;
	}
	
	public void setNbEleves(int nbEleves) {
		this.nbEleves=nbEleves;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}
	
}
