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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.service.AuthenticationServices;

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
