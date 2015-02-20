package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.CategIntervenant;
import org.jboss.as.quickstarts.kitchensink.model.Formation;

@ApplicationScoped
public class FormationRepository {

	@Inject
	private EntityManager em;
	
	public List<Formation> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Formation> criteria = cb.createQuery(Formation.class);
        Root<Formation> categ = criteria.from(Formation.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("intitule")));

        return em.createQuery(criteria).getResultList();
	}
	
}
