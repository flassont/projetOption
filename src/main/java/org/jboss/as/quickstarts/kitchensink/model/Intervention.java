package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Intervention implements Serializable {
	
	@Id
	@GeneratedValue
	private long interventionId;
	
	@NotNull
	@NotEmpty
	private long moduleId;
	
//	@NotNull
//	@NotEmpty
//	@Size(min=1, max=50)
//	private String entitlement;
	
	@NotNull
	@NotEmpty
	@ManyToOne
	private FormePedago formePedago;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double nbHeuresProf;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double nbHeuresCompl;
	
	@OneToOne
	private Relation relations;
	
	public long getId() {
		return this.interventionId;
	}
	
//	public String getEntitlement() {
//		return this.entitlement;
//	}
	
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
	
	public Relation getRelations() {
		return this.relations;
	}

}
