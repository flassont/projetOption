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

@SuppressWarnings("serial")
@Entity
@XmlRootElement
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Intervention extends Responsabilite implements Serializable {
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Module> modules;
	
	@NotNull
	@NotEmpty
	@ManyToOne(fetch = FetchType.EAGER)
	private FormePedago formePedago;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double nbHeuresProf;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double nbHeuresCompl;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	public Collection<Module> getModules() {
		return this.modules;
	}
	
	public void setModules(Collection<Module> mod) {
		this.modules=mod;
	}
	
	public FormePedago getFormePedago() {
		return this.formePedago;
	}
	
	public double getNbHeuresProf() {
		return this.nbHeuresProf;
	}
	
	public void setNbHeuresProf(double nbHP) {
		this.nbHeuresProf=nbHP;
	}
	
	public double getNbHeuresCompl() {
		return this.nbHeuresCompl;
	}
	
	public void setNbHeuresCompl(double nbHC) {
		this.nbHeuresCompl=nbHC;
	}

}
