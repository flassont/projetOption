package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class UPValue implements Serializable {
	
	@Id
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private ClassType type;
	
	@NotEmpty
	@NotNull
	@Pattern(regexp="[0-9]*[.,][0-9]*")
	private double coeff;

}
