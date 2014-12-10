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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Intervenant implements Serializable {

//    @Id
//    @GeneratedValue
//    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;
    
    @NotNull
    @Size(min=1, max=25)
    @Pattern(regexp="[^0-9]*", message="Must not contain numbers")
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    @Id
    private String email;
    
    @NotNull
    @NotEmpty
    private String password;
    
    // Ceci permet de ne pas avoir d'erreur, à comprendre cf : http://stackoverflow.com/questions/22821695/lazyinitializationexception-failed-to-lazily-initialize-a-collection-of-roles
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Relation> relations;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<UV> uvs;

//    @NotNull
//    @Size(min = 10, max = 12)
//    @Digits(fraction = 0, integer = 12)
//    @Column(name = "phone_number")
//    private String phoneNumber;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname(String surname) {
    	return this.surname;
    }
    
    public void setSurname(String surname) {
    	this.surname=surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String pwd) {
    	this.password=pwd;
    }
    
    public Collection<Relation> getRelations() {
    	return relations;
    }
    
    public Collection<UV> getUVs() {
    	return this.uvs;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
