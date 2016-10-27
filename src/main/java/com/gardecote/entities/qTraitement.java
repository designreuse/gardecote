package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
public class qTraitement implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idTraitement", nullable=false)
    private Long    idTraitement      ;
    @Column
    @Enumerated
    @ElementCollection(targetClass = enumSegPeche.class)
    private  List<enumSegPeche> segs;
    private qUsine qusine;
    @OneToMany(mappedBy = "qtraitement",targetEntity = qQuantiteExportee.class)
    private List<qQuantiteExportee> qQteExp;
    private Long qteDechu;
    private Date dateTraitement;
    //@OneToMany
    @OneToMany(mappedBy="qtraitement", targetEntity=qPageTraitement.class)
    private List<qPageTraitement> pagesTraitement;

    public qTraitement(enumTypeDoc enumtypedoc, List<qSeq> qlistseq, List<enumSegPeche> segs, qUsine qusine, List<qQuantiteExportee> qQteExp, Long qteDechu, Date dateTraitement, List<qPageTraitement> pagesTraitement) {
      //  super(enumtypedoc, qlistseq);
        this.segs = segs;
        this.qusine = qusine;
        this.qQteExp = qQteExp;
        this.qteDechu = qteDechu;
        this.dateTraitement = dateTraitement;
        this.pagesTraitement = pagesTraitement;
    }

    public qTraitement() {
    }

    public List<enumSegPeche> getSegs() {
        return segs;
    }

    public void setSegs(List<enumSegPeche> segs) {
        this.segs = segs;
    }

    public qUsine getQusine() {
        return qusine;
    }

    public void setQusine(qUsine qusine) {
        this.qusine = qusine;
    }

    public List<qQuantiteExportee> getqQteExp() {
        return qQteExp;
    }

    public void setqQteExp(List<qQuantiteExportee> qQteExp) {
        this.qQteExp = qQteExp;
    }

    public Long getQteDechu() {
        return qteDechu;
    }

    public void setQteDechu(Long qteDechu) {
        this.qteDechu = qteDechu;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public List<qPageTraitement> getPagesTraitement() {
        return pagesTraitement;
    }

    public void setPagesTraitement(List<qPageTraitement> pagesTraitement) {
        this.pagesTraitement = pagesTraitement;
    }
}
