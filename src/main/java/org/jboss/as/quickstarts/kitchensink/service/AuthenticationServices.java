package org.jboss.as.quickstarts.kitchensink.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Intervenant;

// The @Singleton annotation permet d'avoir un EJB unique par application et qui peut être partagé entre les classes
// alors que statful créer un EJB/ client
@Singleton
public class AuthenticationServices {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Intervenant> memberEventSrc;

	@Inject
	private MemberRepository memberRepository;

	private final Map<String, String> authorizationTokensStorage = new HashMap();

	public String login(String email, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Intervenant> criteria = cb.createQuery(Intervenant.class);
		Root<Intervenant> member = criteria.from(Intervenant.class);
		criteria.select(member).where(cb.equal(member.get("email"), email));
		Intervenant inter = em.createQuery(criteria).getSingleResult();
		if (inter != null && inter.getPassword().equals(password)) {
			// On génère un token
			String authToken = UUID.randomUUID().toString();
			authorizationTokensStorage.put(authToken, email);
			return authToken;
		} else
			return null;
	}

	public boolean isAuthTokenValid(String authToken) {
		return authorizationTokensStorage.containsKey(authToken);
	}

	public void logout(String email, String authToken) {
		if (authorizationTokensStorage.containsKey(authToken)) {
			String email2 = authorizationTokensStorage.get(authToken);
			if (email.equals(email2)) {
				authorizationTokensStorage.remove( authToken );
			}
		}
		return;
	}
	
	public boolean isAdmin(String authToken) {
		boolean toReturn = false;
		if (authorizationTokensStorage.containsKey(authToken)) {
			String email = authorizationTokensStorage.get(authToken);
			Intervenant inter = memberRepository.findByEmail(email);
			if ( inter.isAdmin() )
				toReturn = true;
		}
		return toReturn;
	}
	
	public String getEmail(String token) {
		return authorizationTokensStorage.get(token);
	}
}
