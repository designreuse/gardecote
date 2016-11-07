package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Table(name="qDoc", schema="dbo", catalog="GCM1" )
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_DOC", discriminatorType=DiscriminatorType.STRING, length=20)
@IdClass(qDocPK.class)
public class qDoc implements Serializable {

    @Id
   private Date depart       ;

    @Id
    private String numImm;

    @OneToOne( cascade = CascadeType.ALL,targetEntity = qSeq.class)
    private qSeq qseq;



    @Column(name="Retour", nullable=false, length=10)
    private Date     retour       ;



    @Column(name="doctype", nullable=false, length=10)
    private enumTypeDoc enumtypedoc;


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

    public qSeq getQseq() {
        return qseq;
    }

    public void setQseq(qSeq qsec) {
        this.qseq = qsec;
    }

    public qDoc(enumTypeDoc enumtypedoc, Date depart, Date retour,qSeq qseq, qRegistreNavire   qnavire) {
        this.enumtypedoc = enumtypedoc;
        this.depart=depart;
        this.qseq=qseq;
        this.numImm=qnavire.getNumimm();
        this.retour = retour;

    }

    public enumTypeDoc getEnumtypedoc() {
        return enumtypedoc;
    }

    public void setEnumtypedoc(enumTypeDoc enumtypedoc) {
        this.enumtypedoc = enumtypedoc;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }



    public Date getRetour() {
        return retour;
    }

    public void setRetour(Date retour) {
        this.retour = retour;
    }
}
