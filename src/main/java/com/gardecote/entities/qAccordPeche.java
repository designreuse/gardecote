package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qAccordPeche", schema="dbo", catalog="GCM5" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qAccordPeche.countAll", query="SELECT COUNT(x) FROM qAccordPeche x" )
} )

public class qAccordPeche implements Serializable {
    @Id
    private Integer identificateurAccord;

    private enumModePeche modePeche;
    private String abbrevAccor;
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

    public String getAbbrevAccor() {
        return abbrevAccor;
    }

    public void setAbbrevAccor(String abbrevAccor) {
        this.abbrevAccor = abbrevAccor;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public qAccordPeche(Integer identificateurAccord, enumModePeche modePeche, String abbrevAccor, String infos) {
        this.identificateurAccord = identificateurAccord;
        this.modePeche = modePeche;
        this.abbrevAccor = abbrevAccor;
        this.infos = infos;

    }


}

