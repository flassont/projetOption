package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Nicolas, Téo, Amandine
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Intervention extends Responsabilite {
	
	public Intervention() {}
	
	/**
	 * Liste des modules incluant l'Intervention concernee
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Module> modules;
	
	/**
	 * Forme pedagogique de l'intervention
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private FormePedago formePedago;
	
	/**
	 * Nombre d'heures assurees par les intervenants dans le cadre de l'intervention
	 */
	@NotNull
	private double nbHeuresProf;
	
	/**
	 * Nombre d'heures complementaires
	 * Par exemple, les MOOC en sus des interventions en classe
	 */
	@NotNull
	private double nbHeuresCompl;
	
	public Collection<Module> getModules() {
		return this.modules;
	}
	
	
	
	public FormePedago getFormePedago() {
		return this.formePedago;
	}
	
	public double getNbHeuresProf() {
		return this.nbHeuresProf;
	}
	
	public double getNbHeuresCompl() {
		return this.nbHeuresCompl;
	}
	
	public void setModules(Collection<Module> mod) {
		this.modules=mod;
	}
	
	public void setNbHeuresProf(double nbHP) {
		this.nbHeuresProf=nbHP;
	}
	
	public void setNbHeuresCompl(double nbHC) {
		this.nbHeuresCompl=nbHC;
	}

}
