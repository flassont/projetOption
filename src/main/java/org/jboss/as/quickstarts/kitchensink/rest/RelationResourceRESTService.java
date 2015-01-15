package org.jboss.as.quickstarts.kitchensink.rest;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.data.RelationRepository;
import org.jboss.as.quickstarts.kitchensink.data.ResponsabiliteRepository;
import org.jboss.as.quickstarts.kitchensink.model.*;
import org.jboss.as.quickstarts.kitchensink.service.RelationRegistration;
import org.jboss.as.quickstarts.kitchensink.service.ResponsabiliteRegistration;
import org.jboss.as.quickstarts.kitchensink.util.RelationPredecessor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Téo on 14/01/2015.
 */
@Path("/relations")
@RequestScoped
public class RelationResourceRESTService {

    @Inject
    private MemberRepository memberRepository;

    @Inject
    private ResponsabiliteRepository responsabiliteRepository;

    @Inject
    private RelationRepository relationRepository;

    @Inject
    RelationRegistration registration;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRelation (RelationPredecessor relationPredecessor) {
        Intervenant intervenant = memberRepository.findByEmail(relationPredecessor.getEmailIntervenant());
        Responsabilite responsabilite = responsabiliteRepository.findById(relationPredecessor.getIdResponsabilite());
        Relation relation = new Relation(relationPredecessor.getAnnee(), intervenant, responsabilite, relationPredecessor.getEtatInitial());
        Response.ResponseBuilder builder = null;
        try {
            registration.register(relation);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //A voir si @PathParam et @Consumes marchent bien ensemble.
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRelation (@PathParam("id") long id, EtatRelation etat) {
        Relation relation = relationRepository.findById(id);
        relation.addEtatRelation(etat);
        Response.ResponseBuilder builder = null;
        try {
            registration.update(relation);
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
