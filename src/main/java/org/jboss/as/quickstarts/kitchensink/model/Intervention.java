package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Pour un Module appartenant à un UV, il y a plusieurs formes pedagogiques
 * Chaque forme pedagogique peut faire l'objet de plusieurs interventions
 * (s'il y a plusieurs groupes de TP, par exemple)
 *
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Intervention extends Responsabilite implements Serializable {
	
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
