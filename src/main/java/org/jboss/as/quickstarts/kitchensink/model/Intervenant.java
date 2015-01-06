/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Intervenant implements Serializable {

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
    // TODO spécifier que l'adresse doit être celle de l'école ? Comme ca, on serait bien certains qu'elle est unique
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
    @OneToMany(fetch = FetchType.EAGER) // Ceci permet de ne pas avoir d'erreur, à comprendre cf
    // http://stackoverflow.com/questions/22821695/lazyinitializationexception-failed-to-lazily-initialize-a-collection-of-roles
    private Collection<Relation> relations;
    
    /**
     * Catégorie de l'intervenant
     */
    //@NotNull
    @ManyToOne
    //TODO lorsque l'on ajoute un intervenant dans la liste, on doit choisir quel categorie d'intervenant il est
    //on doit choisir parmi les valeurs de la table CategIntervenant
    private CategIntervenant categ;

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
    
    public Collection<Relation> getRelations() {
    	return relations;
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

}
