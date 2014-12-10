package org.jboss.as.quickstarts.kitchensink.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class FormePedago {
	
	@NotNull
	@NotEmpty
	@Id
	private String formePedago;
	
	

}
