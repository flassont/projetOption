package org.jboss.as.quickstarts.kitchensink.rest;

import org.jboss.as.quickstarts.kitchensink.data.CategIntervenantRepository;
import org.jboss.as.quickstarts.kitchensink.model.CategIntervenant;
import org.jboss.as.quickstarts.kitchensink.service.RelationRegistration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Téo on 17/02/2015.
 */
@Path("/categoriesIntervenant")
@RequestScoped
public class CategIntervenantRESTService {

    @Inject
    private CategIntervenantRepository categIntervenantRepository;


    @Inject
    RelationRegistration registration;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategIntervenant> listAllMembers() {
        return categIntervenantRepository.findAllOrderedByName();
    }

}
