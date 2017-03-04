package com.gardecote.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 04/03/2017.
 */
@Entity
@DynamicUpdate
@DiscriminatorValue("NAVIREILLICITE")
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qNavireIllicite.countAll", query="SELECT COUNT(x) FROM qNavireIllicite x")
})
public class qNavireIllicite  extends qBateau implements Serializable {

    private Date dateActivite;
    @OneToOne
    private qZone zoneActivite;

    private String informations;

    public Date getDateActivite() {
        return dateActivite;
    }

    public void setDateActivite(Date dateActivite) {
        this.dateActivite = dateActivite;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public qZone getZoneActivite() {
        return zoneActivite;
    }

    public void setZoneActivite(qZone zoneActivite) {
        this.zoneActivite = zoneActivite;
    }

    public qNavireIllicite(Date dateActivite, qZone zoneActivite, String informations) {
        this.dateActivite = dateActivite;
        this.zoneActivite = zoneActivite;
        this.informations = informations;
    }

    public qNavireIllicite() {
    }
}
