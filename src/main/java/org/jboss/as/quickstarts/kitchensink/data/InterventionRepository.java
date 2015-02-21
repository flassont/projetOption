package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.Intervention;

@ApplicationScoped
public class InterventionRepository {
	
	@Inject
	private EntityManager em;
	
	public List<Intervention> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Intervention> criteria = cb.createQuery(Intervention.class);
        Root<Intervention> categ = criteria.from(Intervention.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("intitule")));

        return em.createQuery(criteria).getResultList();
	}

}
