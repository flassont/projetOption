package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.AdjointEnseignement;
import org.jboss.as.quickstarts.kitchensink.model.Formation;

@ApplicationScoped
public class AdjointEnseignementRepository {
	
	@Inject
	private EntityManager em;
	
	public List<AdjointEnseignement> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AdjointEnseignement> criteria = cb.createQuery(AdjointEnseignement.class);
        Root<AdjointEnseignement> categ = criteria.from(AdjointEnseignement.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("intitule")));

        return em.createQuery(criteria).getResultList();
	}

}
