package com.gardecote.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gardecote.entities.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@IdClass(qCategPK.class)
public class qCategRessourceModelInput {


    private Integer idcateg;

    private Integer typeSupport;



    private  Integer typeconcessionConcernee_qtypeconcessionpk;

    public qCategRessourceModelInput() {
    }

    public Integer getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(Integer idcateg) {
        this.idcateg = idcateg;
    }

    public Integer getTypeSupport() {
        return typeSupport;
    }

    public void setTypeSupport(Integer typeSupport) {
        this.typeSupport = typeSupport;
    }

    public Integer getTypeconcessionConcernee_qtypeconcessionpk() {
        return typeconcessionConcernee_qtypeconcessionpk;
    }

    public void setTypeconcessionConcernee_qtypeconcessionpk(Integer typeconcessionConcernee_qtypeconcessionpk) {
        this.typeconcessionConcernee_qtypeconcessionpk = typeconcessionConcernee_qtypeconcessionpk;
    }
}
