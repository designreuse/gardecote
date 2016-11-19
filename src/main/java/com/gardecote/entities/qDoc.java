package com.gardecote.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@DynamicUpdate
@Table(name="qDoc4", schema="dbo", catalog="GCM1" )
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_DOC", discriminatorType=DiscriminatorType.STRING, length=20)
@IdClass(qDocPK.class)
public class qDoc implements Serializable {

    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date depart       ;

    @Id
    private String numImm;

    @OneToOne( cascade = CascadeType.ALL,targetEntity = qSeq.class)
    private qSeq qseq;



    @Column(name="Retour", nullable=true, length=10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date     retour       ;



    @Column(name="doctype", nullable=false, length=10)
    private enumTypeDoc enumtypedoc;

    @OneToOne
    private qConcession qconcession;

    public qDoc() {

    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
    }

    public Date getDepart() {
        return depart;
    }
    public qDocPK getqDocPK(){
        qDocPK qdk=new qDocPK(this.getNumImm(),this.depart);


        return qdk;
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

    public qDoc(enumTypeDoc enumtypedoc, Date depart, Date retour,qSeq qseq, qRegistreNavire   qnavire,qConcession qconcess){
        this.qconcession=qconcess;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qDoc)) return false;

        qDoc qDoc = (qDoc) o;

        if (!getDepart().equals(qDoc.getDepart())) return false;
        return getNumImm().equals(qDoc.getNumImm());

    }

    @Override
    public int hashCode() {
        int result = getDepart().hashCode();
        result = 31 * result + getNumImm().hashCode();
        return result;
    }
}
