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
@Table(name="qDocuments", schema="dbo", catalog="GCM11")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_DOC", discriminatorType=DiscriminatorType.STRING, length=20)
@IdClass(qDocPK.class)
public class qDoc implements Serializable , Comparable<qDoc> {
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date depart       ;
    @Id
    private String numImm;
    @OneToOne
    private qPrefix qprefix;
    @OneToOne
    private qLic licenceImputation;
    private String segPeche;
    private enumJP typePeche;
    private Integer quota;
    private Integer dureeConcession;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateExpiration;
    private String nomNavire;
    @Column(name="imo", nullable=true, length=10)
    private String imo;
    @Column(name="gt", nullable=true, length=10)
    private String  GT;
    @Column(name="nomcapitaine", nullable=true, length=20)
    private String nomCapitaine;
    @Column(name="debloquerModification", nullable=true, length=10)
    private boolean debloquerModification;
    @Column(name="bloquerDeletion", nullable=true, length=10)
    private boolean bloquerDeletion;
    @Column(name="validation", nullable=true, length=10)
    private boolean validation;
    private enumSupport support;
    @OneToOne( cascade = CascadeType.ALL,targetEntity = qSeq.class)
    private qSeq qseq;

    @OneToOne(targetEntity = qNavireLegale.class)
    private qNavireLegale qnavire;

    @OneToOne( targetEntity = qUsine.class)
    private qUsine qusine;

    @Column(name="Retour", nullable=true, length=10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date     retour       ;

    public qPrefix getQprefix() {
        return qprefix;
    }

    public void setQprefix(qPrefix qprefix) {
        this.qprefix = qprefix;
    }

    @Column(name="doctype", nullable=false, length=10)
    private enumTypeDoc enumtypedoc;

    public boolean isDebloquerModification() {
        return debloquerModification;
    }

    public boolean isValidation() {
        return validation;
    }


    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public void setDebloquerModification(boolean debloquerModification) {
        this.debloquerModification = debloquerModification;
    }

    @OneToOne
    private qConcession qconcession;


    public boolean isBloquerDeletion() {
        return bloquerDeletion;
    }

    public void setBloquerDeletion(boolean bloquerDeletion) {
        this.bloquerDeletion = bloquerDeletion;
    }

    public String getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(String segPeche) {
        this.segPeche = segPeche;
    }

    public enumJP getTypePeche() {
        return typePeche;
    }

    public qLic getLicenceImputation() {
        return licenceImputation;
    }

    public void setLicenceImputation(qLic licenceImputation) {
        this.licenceImputation = licenceImputation;
    }

    public void setTypePeche(enumJP typePeche) {
        this.typePeche = typePeche;
    }

    public enumSupport getSupport() {
        return support;
    }

    public void setSupport(enumSupport support) {
        this.support = support;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getDureeConcession() {
        return dureeConcession;
    }

    public void setDureeConcession(Integer dureeConcession) {
        this.dureeConcession = dureeConcession;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getNomNavire() {
        return nomNavire;
    }

    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getNomCapitaine() {
        return nomCapitaine;
    }

    public void setNomCapitaine(String nomCapitaine) {
        this.nomCapitaine = nomCapitaine;
    }

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public qDoc() {

    }

    public qNavireLegale getQnavire() {
        return qnavire;
    }

    public void setQnavire(qNavireLegale qnavire) {
        this.qnavire = qnavire;
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

    public qUsine getQusine() {
        return qusine;
    }

    public void setQusine(qUsine qusine) {
        this.qusine = qusine;
    }

    public qDoc(enumTypeDoc enumtypedoc, Date depart, Date retour, qSeq qseq, qNavireLegale   qnavire, qUsine   qusine, qConcession qconcess){
        this.qconcession=qconcess;

        this.qnavire=qnavire;
        this.qusine=qusine;
        this.enumtypedoc = enumtypedoc;
        this.depart=depart;
        this.qseq=qseq;
        if(qnavire!=null)  this.numImm=qnavire.getNumimm();
        if(qusine!=null)   this.numImm=qusine.getRefAgrement();
        this.retour = retour;
    }
    public qDoc(qDoc other){
        this.qconcession=other.getQconcession();

        this.qnavire=other.getQnavire();
        this.qusine=other.getQusine();
        this.enumtypedoc = other.getEnumtypedoc();
        this.depart=other.getDepart();
        this.qseq=other.getQseq();
        if(qnavire!=null)  this.numImm=other.getQnavire().getNumimm();
        if(qusine!=null)   this.numImm=other.getQusine().getRefAgrement();
        this.retour = other.getRetour();
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


    @Override
    public int compareTo(qDoc o) {
        //String qespeceIDo=o.getNumOrdre().toString()+o.getQespeceId().toString()+o.getEnumesptype().toString();
        // String qespeceID=this.getNumOrdre().toString()+this.qespeceId.toString()+this.getEnumesptype().toString();
        String qespeceIDo=o.getDepart().toString()+o.getNumImm();
        String qespeceID=this.getDepart().toString()+this.getNumImm();
        return qespeceID.compareTo(qespeceIDo);
    }
}
