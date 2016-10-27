package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_DOC", discriminatorType=DiscriminatorType.STRING, length=20)
@IdClass(qDocPK.class)
public class qDoc implements Serializable {

    @Id
    @Column(name="Depart", nullable=false, length=10)
    private Date depart       ;
    @Id
    @OneToOne
    private qRegistreNavire    qnavire;

    private enumTypeDoc enumtypedoc;

    @OneToMany(mappedBy="qdoc", targetEntity=qSeq.class)
    private List<qSeq>  qlistseq;

    public qDoc() {

    }

    public Date getDepart() {
        return depart;
    }
    public qDocPK getqDocPK(){
        qDocPK qMarree=new qDocPK();


        return qMarree;
    }
    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public qRegistreNavire getQnavire() {
        return qnavire;
    }

    public void setQnavire(qRegistreNavire qnavire) {
        this.qnavire = qnavire;
    }

    public qDoc(enumTypeDoc enumtypedoc, List<qSeq> qlistseq) {
        this.enumtypedoc = enumtypedoc;
        this.qlistseq = qlistseq;
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
