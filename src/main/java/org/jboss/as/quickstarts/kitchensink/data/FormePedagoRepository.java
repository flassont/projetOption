package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.FormePedago;

@ApplicationScoped
public class FormePedagoRepository {
	
	@Inject
	private EntityManager em;
	
	public List<FormePedago> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FormePedago> criteria = cb.createQuery(FormePedago.class);
        Root<FormePedago> categ = criteria.from(FormePedago.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("formePedago")));
        return em.createQuery(criteria).getResultList();
	}

}
