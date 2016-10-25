package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 23/10/2016.
 */
public class qDoc implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="IdDoc", nullable=false)
    private Integer IdDoc;

    enumTypeDoc enumtypedoc;

    qConcession qconcessionconcernee;

    qCategRessource qcategconcernee;

    List<qSeq>  qlistseq;





    public Integer getIdDoc() {
        return IdDoc;
    }

    public void setIdDoc(Integer idDoc) {
        IdDoc = idDoc;
    }

    public enumTypeDoc getEnumtypedoc() {
        return enumtypedoc;
    }

    public void setEnumtypedoc(enumTypeDoc enumtypedoc) {
        this.enumtypedoc = enumtypedoc;
    }



    public List<qSeq> getQlistseq() {
        return qlistseq;
    }

    public void setQlistseq(List<qSeq> qlistseq) {
        this.qlistseq = qlistseq;
    }
}
