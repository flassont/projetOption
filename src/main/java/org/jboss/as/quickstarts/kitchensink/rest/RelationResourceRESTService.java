package org.jboss.as.quickstarts.kitchensink.rest;

import org.jboss.as.quickstarts.kitchensink.model.UV;
import org.jboss.as.quickstarts.kitchensink.util.RelationPredecessor;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Téo on 14/01/2015.
 */
@Path("/relations")
@RequestScoped
public class RelationResourceRESTService {

    @POST
    @Path("/uvs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRelation (RelationPredecessor relationPredecessor) {
        return null;
    }
}
