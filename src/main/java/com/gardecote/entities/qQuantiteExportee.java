package com.gardecote.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 25/10/2016.
 */

@Entity
@Table(name="qQuantiteExportee33", schema="dbo", catalog="GCM4" )
@IdClass(qQuantiteExporteePK.class)
public class qQuantiteExportee implements Serializable {
    @Id
    String refAgrement;
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateTraitement;
    @Id
    private enumZonOrientation enumZonOrientationPeche;



    private Integer qte;


    public qQuantiteExportee(String refAg,Date dep,enumZonOrientation enumZonOrientationPeche, Integer qte) {
        this.refAgrement = refAg;
        this.dateTraitement = dep;
        this.enumZonOrientationPeche = enumZonOrientationPeche;
        this.qte = qte;

    }





    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public enumZonOrientation getEnumZonOrientationPeche() {
        return enumZonOrientationPeche;
    }

    public void setEnumZonOrientationPeche(enumZonOrientation enumZonOrientationPeche) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public qQuantiteExportee() {
    }
}
