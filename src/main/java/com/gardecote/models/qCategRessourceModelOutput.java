package com.gardecote.models;

//@IdClass(qCategPK.class)
public class qCategRessourceModelOutput {

    private Integer idtypeConcession;

    private Integer typeSupport;

    private String Engins;

    private  Integer typeconcessionConcernee;

    public qCategRessourceModelOutput() {
    }

    public Integer getIdtypeConcession() {
        return idtypeConcession;
    }

    public void setIdtypeConcession(Integer idtypeConcession) {
        this.idtypeConcession = idtypeConcession;
    }

    public Integer getTypeSupport() {
        return typeSupport;
    }

    public void setTypeSupport(Integer typeSupport) {
        this.typeSupport = typeSupport;
    }

    public String getEngins() {
        return Engins;
    }

    public void setEngins(String engins) {
        Engins = engins;
    }

    public Integer getTypeconcessionConcernee() {
        return typeconcessionConcernee;
    }

    public void setTypeconcessionConcernee(Integer typeconcessionConcernee) {
        this.typeconcessionConcernee = typeconcessionConcernee;
    }
}
