package org.jboss.as.quickstarts.kitchensink.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.model.Responsabilite;

@Stateless
public class ResponsabiliteRegistration {
	
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    public void register(Responsabilite responsabilite) throws Exception {
        log.info("Registering " + responsabilite.getIntitule());
        em.persist(responsabilite);
    }
}
