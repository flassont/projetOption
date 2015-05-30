package org.mines.nantes.model;

import javax.persistence.*;

/**
 * Wish emited by a User for a Module
 * Created by Florian on 29/05/2015.
 */
@Entity
public class Wish {

	@Id
	@GeneratedValue
	private int id;

	/** Year for which this Wish has been made */
	private int year;

	@ManyToOne
	private Intervenant intervenant;

	@ManyToOne
	private Module module;
}
