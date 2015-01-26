package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.model.Relation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by Téo on 14/01/2015.
 */
@Stateless
public class RelationRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public void register(Relation relation) throws Exception {
        log.info("Registering " + relation.getId());
        em.persist(relation);
    }

    public void update(Relation relation) throws Exception {
        log.info("Updating " + relation.getId());
        em.merge(relation);
    }
}
