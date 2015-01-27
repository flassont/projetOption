package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.Intervenant;
import org.jboss.as.quickstarts.kitchensink.model.Responsabilite;

public class ResponsabiliteRepository {
	
	@Inject
    private EntityManager em;
	
	public Responsabilite findById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Responsabilite> criteria = cb.createQuery(Responsabilite.class);
        Root<Responsabilite> responsabilite = criteria.from(Responsabilite.class);
        criteria.select(responsabilite).where(cb.equal(responsabilite.get("id"), id));
        
        return em.createQuery(criteria).getSingleResult();
    }
	
	public <T extends Responsabilite> List<T> findAllOrderedByIntitule(Class<T> responsabilite) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(responsabilite);
        Root<T> respTable = criteria.from(responsabilite);
        criteria.select(respTable).orderBy(cb.asc(respTable.get("intitule")));

        return em.createQuery(criteria).getResultList();
    }
}
