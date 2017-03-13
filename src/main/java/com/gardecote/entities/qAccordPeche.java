package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qAccordPeche", schema="dbo", catalog="GCM8" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qAccordPeche.countAll", query="SELECT COUNT(x) FROM qAccordPeche x" )
} )

public class qAccordPeche implements Serializable {
    @Id
    private Integer identificateurAccord;

    private enumModePeche modePeche;
    private String abbrevAccord;
    private String infos;

    public Integer getIdentificateurAccord() {
        return identificateurAccord;
    }

    public qAccordPeche() {
    }

    public void setIdentificateurAccord(Integer identificateurAccord) {
        this.identificateurAccord = identificateurAccord;
    }

    public enumModePeche getModePeche() {
        return modePeche;
    }

    public void setModePeche(enumModePeche modePeche) {
        this.modePeche = modePeche;
    }

    public String getAbbrevAccord() {
        return abbrevAccord;
    }

    public void setAbbrevAccord(String abbrevAccor) {
        this.abbrevAccord = abbrevAccor;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public qAccordPeche(Integer identificateurAccord, enumModePeche modePeche, String abbrevAccord, String infos) {
        this.identificateurAccord = identificateurAccord;
        this.modePeche = modePeche;
        this.abbrevAccord = abbrevAccord;
        this.infos = infos;

    }


}

