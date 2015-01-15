package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Relation;
import org.jboss.as.quickstarts.kitchensink.model.Responsabilite;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Teo on 15/01/2015.
 */
public class RelationRepository {

    @Inject
    private EntityManager em;

    public Relation findById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relation> criteria = cb.createQuery(Relation.class);
        Root<Relation> relation = criteria.from(Relation.class);
        criteria.select(relation).where(cb.equal(relation.get("id"), id));

        return em.createQuery(criteria).getSingleResult();
    }
}
