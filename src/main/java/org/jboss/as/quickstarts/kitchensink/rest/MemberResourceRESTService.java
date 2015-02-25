package org.jboss.as.quickstarts.kitchensink.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Intervenant;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/members")
@RequestScoped
public class MemberResourceRESTService {
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";

    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private MemberRepository repository;

    @Inject
    MemberRegistration registration;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Intervenant> listAllMembers(@Context HttpHeaders httpHeaders) {

    	// Fetch authorization header
    	String token = httpHeaders.getHeaderString(AUTHORIZATION_PROPERTY);
        return repository.findAllOrderedByName(token);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Intervenant lookupMemberByEmail(@PathParam("email") String email) {
        Intervenant member = repository.findByEmail(email);
        if (member == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return member;
    }
    
    @DELETE
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIntervenant(final @PathParam("email") String email) {
    	
    	Response.ResponseBuilder builder = null;
        
        Intervenant intervenant = null;
        try {
        	intervenant = repository.findByEmail(email);
        } catch (NoResultException e) {
            // ignore
        }

         try {
             // Validates member using bean validation
             //validateMember(member);


             registration.delete(intervenant.getEmail());

             // Create an "ok" response
             builder = Response.ok();
         } catch (ConstraintViolationException ce) {
             // Handle bean validation issues
             builder = RESTServicesResources.createViolationResponse(ce.getConstraintViolations(), log);
         } catch (ValidationException e) {
             // Handle the unique constrain violation
             Map<String, String> responseObj = new HashMap<String, String>();
             responseObj.put("email", "Email taken");
             builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
         } catch (Exception e) {
             // Handle generic exceptions
             Map<String, String> responseObj = new HashMap<String, String>();
             responseObj.put("error", e.getMessage());
             builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
         }
         
         return builder.build();
    }

    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(Intervenant member) {
    	

        Response.ResponseBuilder builder = null;

        try {          

            if ( emailAlreadyExists(member.getEmail()) )
            	registration.update(member);
            else {
            	// Validates member using bean validation
                validateMember(member);
            	registration.register(member);
            }

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = RESTServicesResources.createViolationResponse(ce.getConstraintViolations(), log);
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("email", "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    /**
     * <p>
     * Validates the given Member variable and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
     * </p>
     * <p>
     * If the error is caused because an existing member with the same email is registered it throws a regular validation
     * exception so that it can be interpreted separately.
     * </p>
     * 
     * @param member Member to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException If member with the same email already exists
     */
    private void validateMember(Intervenant member) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Intervenant>> violations = validator.validate(member);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        // Check the uniqueness of the email address
        if (emailAlreadyExists(member.getEmail())) {
            throw new ValidationException("Unique Email Violation");
        }
    }

    /**
     * Checks if a member with the same email address is already registered. This is the only way to easily capture the
     * "@UniqueConstraint(columnNames = "email")" constraint from the Member class.
     * 
     * @param email The email to check
     * @return True if the email already exists, and false otherwise
     */
    public boolean emailAlreadyExists(String email) {
        Intervenant member = null;
        try {
            member = repository.findByEmail(email);
        } catch (NoResultException e) {
            // ignore
        }
        return member != null;
    }
}
