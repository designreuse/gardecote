package com.gardecote.web;

/**
 * Created by Dell on 22/04/2017.
 */
public class qModelConcessionExport {
    private String refConcession;
    private String nomConcessionnaire;

    public qModelConcessionExport(String refConcession, String nomConcessionnaire) {
        this.refConcession = refConcession;
        this.nomConcessionnaire = nomConcessionnaire;
    }

    public String getRefConcession() {
        return refConcession;
    }

    public void setRefConcession(String refConcession) {
        this.refConcession = refConcession;
    }

    public String getNomConcessionnaire() {
        return nomConcessionnaire;
    }

    public void setNomConcessionnaire(String nomConcessionnaire) {
        this.nomConcessionnaire = nomConcessionnaire;
    }
}
