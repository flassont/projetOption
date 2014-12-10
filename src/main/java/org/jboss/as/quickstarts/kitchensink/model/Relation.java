package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Relation implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	@ManyToOne(fetch = FetchType.EAGER)
	private Intervenant member;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private EtatRelation relationState;
	
	@NotNull
	@NotEmpty
	@ManyToOne(fetch = FetchType.EAGER)
	private Intervention classes;
	
	public long getId() {
		return this.id;
	}
	
	public Intervenant getMember() {
		return this.member;
	}
	
	public EtatRelation getRS() {
		return this.relationState;
	}
	
	public void setRS(EtatRelation rs) {
		this.relationState=rs;
	}
	
	public Intervention getClasses() {
		return this.classes;
	}

}
