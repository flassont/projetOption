package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Wishes implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	@Email
	private String memberId;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private WishState etat;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*")
	private long idCours;

}
