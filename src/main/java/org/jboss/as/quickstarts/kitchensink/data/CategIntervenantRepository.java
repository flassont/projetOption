package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.CategIntervenant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Téo on 17/02/2015.
 */
@ApplicationScoped
public class CategIntervenantRepository {

    @Inject
    private EntityManager em;

    public List<CategIntervenant> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategIntervenant> criteria = cb.createQuery(CategIntervenant.class);
        Root<CategIntervenant> categ = criteria.from(CategIntervenant.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("nom")));

        return em.createQuery(criteria).getResultList();
    }
}
