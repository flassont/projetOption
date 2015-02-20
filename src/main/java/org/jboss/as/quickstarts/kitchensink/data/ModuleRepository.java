package org.jboss.as.quickstarts.kitchensink.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.model.Module;

@ApplicationScoped
public class ModuleRepository {
	
	@Inject
	private EntityManager em;

	public List<Module> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Module> criteria = cb.createQuery(Module.class);
        Root<Module> categ = criteria.from(Module.class);
        criteria.select(categ).orderBy(cb.asc(categ.get("intitule")));

        return em.createQuery(criteria).getResultList();
	}
	
}
