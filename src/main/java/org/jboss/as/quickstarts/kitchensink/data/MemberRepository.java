package org.jboss.as.quickstarts.kitchensink.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import org.jboss.as.quickstarts.kitchensink.model.Intervenant;
import org.jboss.as.quickstarts.kitchensink.service.AuthenticationServices;

@ApplicationScoped
public class MemberRepository {

    @Inject
    private EntityManager em;
    
    @Inject
	AuthenticationServices authServices;

    public Intervenant findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Intervenant> criteria = cb.createQuery(Intervenant.class);
        Root<Intervenant> member = criteria.from(Intervenant.class);
        criteria.select(member).where(cb.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Intervenant> findAllOrderedByName(String token) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Intervenant> criteria = cb.createQuery(Intervenant.class);
        Root<Intervenant> member = criteria.from(Intervenant.class);
    	if ( authServices.isAdmin(token)) {
    		criteria.select(member).orderBy(cb.asc(member.get("name")));
    	} else {
    		criteria.select(member).where(cb.equal(member.get("email"), authServices.getEmail(token)));
    	}
        return em.createQuery(criteria).getResultList();
    }
}
