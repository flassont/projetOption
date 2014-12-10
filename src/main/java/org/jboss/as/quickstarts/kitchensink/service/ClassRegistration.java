package org.jboss.as.quickstarts.kitchensink.service;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.model.Intervention;

public class ClassRegistration {
	
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Intervention> classesEventSrc;

    public void save(Intervention classes) throws Exception {
        log.info("Saving " + classes.getId() + " " + classes.getFormePedago());
        em.persist(classes);
        classesEventSrc.fire(classes);
    }
    
    public void delete(long id) throws Exception {
        Intervention classes = em.find(Intervention.class,id);
        log.info("Deleting " + classes.getId() + " " + classes.getFormePedago());
        em.remove(classes);
        classesEventSrc.fire(classes);
    }

}
