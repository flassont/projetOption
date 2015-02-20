package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.UV;

@ApplicationScoped
public class UVRepository {

	@Inject
	private EntityManager em;
	
	public List<UV> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UV> criteria = cb.createQuery(UV.class);
        Root<UV> categ = criteria.from(UV.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("intitule")));

        return em.createQuery(criteria).getResultList();
	}
	
}
