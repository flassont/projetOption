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
package org.jboss.as.quickstarts.kitchensink.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.service.AuthenticationServices;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/auth")
@RequestScoped
public class AuthenticationRESTService {
    
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private MemberRepository repository;

    @Inject
    AuthenticationServices authServices;

    @POST
    @Path( "/login" )
    @Consumes( "application/x-www-form-urlencoded" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response login(
            @FormParam( "email" ) String email,
            @FormParam( "password" ) String password ) {

       Response.ResponseBuilder builder = null;
            	
       String token = authServices.login(email, password);
       if ( token != null ) {
    	   Map<String, String> responseObj = new HashMap<String, String>();
    	   responseObj.put("token", token);
    	   builder = Response.status(Response.Status.ACCEPTED).entity(responseObj);
    	   return builder.build();
       } else {
    	   Map<String, String> responseObj = new HashMap<String, String>();
    	   responseObj.put("error", "Mauvais identifiants.");
    	   builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    	   return builder.build();
       }
    }
    
    @POST
    @Path( "/logout" )
    @Consumes( "application/x-www-form-urlencoded" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response logout(
            @FormParam( "email" ) String email,
            @FormParam( "token" ) String token ) {

       Response.ResponseBuilder builder = null;
            	
       authServices.logout(email, token);
       builder = Response.status(Response.Status.ACCEPTED);
       return builder.build();
       
    }
      
}
