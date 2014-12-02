package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Classes implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	@Size(min=1, max=50)
	private String entitlement;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private ClassType classType;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double duration;
	
	@ManyToMany
	private Collection<Member> members;
	
	@OneToMany
	private Collection<Wishes> wishes;

}
