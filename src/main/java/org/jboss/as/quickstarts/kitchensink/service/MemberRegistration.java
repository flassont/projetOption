package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.model.Intervenant;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Intervenant> memberEventSrc;

    public void update(Intervenant member) throws Exception {
        log.info("Updating " + member.getName());
        em.merge(member);
    }
    
    public void register(Intervenant member) throws Exception {
        log.info("Registering " + member.getName());
        em.persist(member);
    }
    
    public void delete(String email) throws Exception {
        Intervenant member = em.find(Intervenant.class,email);
        log.info("Deleting " + member.getName());
        em.remove(member);
    }
}
