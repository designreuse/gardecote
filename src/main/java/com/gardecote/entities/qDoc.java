package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)

public class qDoc implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="IdDoc", nullable=false)
    private Integer IdDoc;

    enumTypeDoc enumtypedoc;

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
