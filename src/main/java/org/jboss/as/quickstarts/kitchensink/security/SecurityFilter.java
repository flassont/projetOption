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
	
		// L'URI /auth/login permet d'obtenir la page d'identification de l'application, il n'est logiquement pas necessaire d'avoir deja
		// un token pour y acceder. Pour toute autre URI il faut un token.
		if ( !path.startsWith( "/auth/login" ) && !path.startsWith( "/auth/logout" ) && !path.startsWith( "/categoriesIntervenant" ) ) {
			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty() || !authServices.isAuthTokenValid(authorization)) {
				Map<String, String> responseObj = new HashMap<String, String>();
	           responseObj.put("error", "Access denied.");
	           builder = Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity(responseObj);
	           requestContext.abortWith(builder.build());
	           return;
			} else if ( path.startsWith("/responsabilites") ) {
				// Pour acceder aux ressources donnees par l'URI /responsabilites il faut non seulement un token mais
				// aussi etre admin. Ce comportement devrait etre modifie: un enseignent devrait pouvoir acceder aux
				// responsabilites, mais sans pouvoir les modifier.
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
	}

	private boolean isUserAllowed(final String username, final String password,
			final Set<String> rolesSet) {
		boolean isAllowed = false;
		String userRole = "ADMIN";
		if (rolesSet.contains(userRole)) {
			isAllowed = true;
		}
		return isAllowed;
	}

}
