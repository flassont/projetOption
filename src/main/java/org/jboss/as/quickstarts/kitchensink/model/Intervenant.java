package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Une personne appartenant au departement informatique de l'ecole
 * On pourra envisager de mettre a disposition l'application a d'autres departements de l'ecole
 *
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Intervenant implements Serializable {
	
	public Intervenant() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	protected int version;


    /**
     * Nom de l'intervenant
     */
    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    /**
     * Prenom de l'intervenant
     */
    @NotNull
    @Size(min=1, max=25)
    @Pattern(regexp="[^0-9]*", message="Must not contain numbers")
    @XmlElement(name="surname") // Obligé de rajouter ca sinon le champ n'est pas transmis en REST je ne sais pas pourquoi
    private String surname;

    /**
     * Adresse email de l'intervenant
     * Cle primaire : l'adresse email est unique au sein de l'ecole
     */
    @NotNull
    @NotEmpty
    @Email
    @Id
    private String email;

    /**
     * Mot de passe de l'intervenant
     */
    @NotNull
    @NotEmpty
    // TODO voir l'encodage du mot de passe
    private String password;

    /**
     * Liste des relations entre l'intervenant et des responsabilites
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="intervenant")
    @JsonIgnoreProperties("intervenant")
    private Collection<Relation> relations;
    
    /**
     * Catégorie de l'intervenant
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private CategIntervenant categ;
    
    /**
     * Droit d'administrateur 
     */
    private boolean admin = false;

    public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getName() {
        return name;
    }
    
    public String getSurname() {
    	return this.surname;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public CategIntervenant getCateg() {
    	return this.categ;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
    	this.surname=surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String pwd) {
    	this.password=pwd;
    }

	public Collection<Relation> getRelations() {
		return this.relations;
	}

}
