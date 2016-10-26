package com.gardecote.entities;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Date;
/**
 * Created by Dell on 25/10/2016.
 */
public class qTraitement extends qDoc {



    private  List<enumSegPeche> segs;
    private qUsine qusine;
    private List<qQuantiteExportee> qQteExp;
    private Long qteDechu;
    private Date dateTraitement;
    @OneToMany
    private List<qPageTraitement> pagesTraitement;

    public qTraitement(enumTypeDoc enumtypedoc, List<qSeq> qlistseq, List<enumSegPeche> segs, qUsine qusine, List<qQuantiteExportee> qQteExp, Long qteDechu, Date dateTraitement, List<qPageTraitement> pagesTraitement) {
        super(enumtypedoc, qlistseq);
        this.segs = segs;
        this.qusine = qusine;
        this.qQteExp = qQteExp;
        this.qteDechu = qteDechu;
        this.dateTraitement = dateTraitement;
        this.pagesTraitement = pagesTraitement;
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
