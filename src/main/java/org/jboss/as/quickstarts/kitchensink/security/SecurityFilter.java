package org.jboss.as.quickstarts.kitchensink.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.as.quickstarts.kitchensink.service.AuthenticationServices;

/**
 * This interceptor verify the access permissions for a user based on username
 * and passowrd provided in request
 * */
@PreMatching
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	@Inject
	AuthenticationServices authServices;
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext) {
		
		// On utilise le response builder pour construire la réponse qui va etre envoyé directement avec en arrêtant la requête
		Response.ResponseBuilder builder = null;
		
		String path = requestContext.getUriInfo().getPath();

		// Get request headers
		final MultivaluedMap<String, String> headers = requestContext
			.getHeaders();

		// Fetch authorization header
		String authorization = headers.getFirst(AUTHORIZATION_PROPERTY);
	
		// Pour tous les autres que login il faut un token
		if ( !path.startsWith( "/auth/login" ) ) {
			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty() || !authServices.isAuthTokenValid(authorization)) {
				Map<String, String> responseObj = new HashMap<String, String>();
	           responseObj.put("error", "Access denied.");
	           builder = Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity(responseObj);
	           requestContext.abortWith(builder.build());
	           return;
			} else if ( path.startsWith("/responsabilites") ) {
				// Dans ce cas on peut etre identifié mais on doit aussi etre admin
				if ( !authServices.isAdmin(authorization) ) {
					Map<String, String> responseObj = new HashMap<String, String>();
			        responseObj.put("error", "Access denied.");
			        builder = Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity(responseObj);
			        requestContext.abortWith(builder.build());
				} else {
					return;
				}
			}
		} else {
			// Cas où on est sur la page login
			return;
		}
		
//		// Access allowed for all
//		if (!method.isAnnotationPresent(PermitAll.class)) {
//			// Access denied for all
//			if (method.isAnnotationPresent(DenyAll.class)) {
//				requestContext.abortWith(ACCESS_FORBIDDEN);
//				return;
//			}
//
//			// Get request headers
//			final MultivaluedMap<String, String> headers = requestContext
//					.getHeaders();
//
//			// Fetch authorization header
//			final List<String> authorization = headers
//					.get(AUTHORIZATION_PROPERTY);
//
//			// If no authorization information present; block access
//			if (authorization == null || authorization.isEmpty()) {
//				requestContext.abortWith(ACCESS_DENIED);
//				return;
//			}
//
//			// Get encoded username and password
//			final String encodedUserPassword = authorization.get(0)
//					.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//
//			// Decode username and password
//			String usernameAndPassword = null;
//			try {
//				usernameAndPassword = new String(
//						Base64.decode(encodedUserPassword));
//			} catch (IOException e) {
//				requestContext.abortWith(SERVER_ERROR);
//				return;
//			}
//
//			// Split username and password tokens
//			final StringTokenizer tokenizer = new StringTokenizer(
//					usernameAndPassword, ":");
//			final String username = tokenizer.nextToken();
//			final String password = tokenizer.nextToken();
//
//			// Verifying Username and password
//			System.out.println(username);
//			System.out.println(password);
//
//			// Verify user access
//			if (method.isAnnotationPresent(RolesAllowed.class)) {
//				RolesAllowed rolesAnnotation = method
//						.getAnnotation(RolesAllowed.class);
//				Set<String> rolesSet = new HashSet<String>(
//						Arrays.asList(rolesAnnotation.value()));
//
//				// Is user valid?
//				if (!isUserAllowed(username, password, rolesSet)) {
//					requestContext.abortWith(ACCESS_DENIED);
//					return;
//				}
//			}
//		}
	}

	private boolean isUserAllowed(final String username, final String password,
			final Set<String> rolesSet) {
		boolean isAllowed = false;

		// Step 1. Fetch password from database and match with password in
		// argument
		// If both match then get the defined role for user from database and
		// continue; else return isAllowed [false]
		// Access the database and do this part yourself
		// String userRole = userMgr.getUserRole(username);
		String userRole = "ADMIN";

		// Step 2. Verify user role
		if (rolesSet.contains(userRole)) {
			isAllowed = true;
		}
		return isAllowed;
	}

}