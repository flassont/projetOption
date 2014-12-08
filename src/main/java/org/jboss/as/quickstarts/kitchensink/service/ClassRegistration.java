package org.jboss.as.quickstarts.kitchensink.service;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.model.Classes;

public class ClassRegistration {
	
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Classes> classesEventSrc;

    public void save(Classes classes) throws Exception {
        log.info("Saving " + classes.getEntitlement() + " " + classes.getClassType());
        em.persist(classes);
        classesEventSrc.fire(classes);
    }
    
    public void delete(long id) throws Exception {
        Classes classes = em.find(Classes.class,id);
        log.info("Deleting " + classes.getEntitlement() + " " + classes.getClassType());
        em.remove(classes);
        classesEventSrc.fire(classes);
    }

}
