package org.jboss.as.quickstarts.kitchensink.util;

import org.jboss.as.quickstarts.kitchensink.model.EtatRelation;

/**
 * Created by Téo on 14/01/2015.
 */
public class RelationPredecessor {
    private int annee;
    private String emailIntervenant;
    private long idResponsabilite;
    private EtatRelation etatInitial;

    public String getEmailIntervenant() {
        return emailIntervenant;
    }

    public long getIdResponsabilite() {
        return idResponsabilite;
    }

    public EtatRelation getEtatInitial() {
        return etatInitial;
    }

    public int getAnnee() {
        return annee;
    }
}
