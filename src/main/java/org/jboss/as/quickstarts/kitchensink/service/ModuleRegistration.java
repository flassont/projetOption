package org.jboss.as.quickstarts.kitchensink.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.model.Module;

@Stateless
public class ModuleRegistration {
	
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    public void register(Module module) throws Exception {
        log.info("Registering " + module.getIntitule());
        em.persist(module);
    }
}
