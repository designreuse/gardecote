package com.gardecote.entities;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DynamicUpdate
@DiscriminatorValue("TRAITEMENT")
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qTraitement.countAll", query="SELECT COUNT(x) FROM qTraitement x")
})

public class qTraitement extends  qDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToMany(targetEntity = qSegUsines.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocTraitementSegmentsBIS")
    private  List<qSegUsines> segs;
    @OneToMany(targetEntity=qQuantiteExportee.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocTraitementQteExpBIS")
    private List<qQuantiteExportee> qQteExp;
    @OneToOne(targetEntity=qQuantitesTraites.class,cascade = CascadeType.ALL)
    private qQuantitesTraites qQteTraitees;
    private Long qteDechu;
    //@OneToMany
    @OneToMany(targetEntity=qPageTraitement.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocTraitPagesBIS")
    private List<qPageTraitement> pagesTraitement;


    public qTraitement(enumTypeDoc enumtypedoc, Date depart, Date retour, qSeq qseq, qNavire qnavire, qUsine qusine, qConcession qconcess, String refAgrement, Date dateTraitement, List<qSegUsines> segs, List<qQuantiteExportee> qQteExp,qQuantitesTraites qQteTraitees, Long qteDechu, List<qPageTraitement> pagesTraitement) {
        super(enumtypedoc, depart, retour, qseq, qnavire, qusine, qconcess);
        this.segs = segs;
        this.qQteExp = qQteExp;
        this.qQteTraitees = qQteTraitees;
        this.qteDechu = qteDechu;
        this.pagesTraitement = pagesTraitement;
    }

    public qTraitement() {
    }

    public qQuantitesTraites getqQteTraitees() {
        return qQteTraitees;
    }

    public List<qSegUsines> getSegs() {
        return segs;
    }

    public void setSegs(List<qSegUsines> segs) {
        this.segs = segs;
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


    public void setqQteTraitees(qQuantitesTraites qQteTraitees) {
        this.qQteTraitees = qQteTraitees;
    }

    public List<qPageTraitement> getPagesTraitement() {
        return pagesTraitement;
    }

    public void setPagesTraitement(List<qPageTraitement> pagesTraitement) {
        this.pagesTraitement = pagesTraitement;
    }

    @Override
    public String toString() {
        return "qTraitement{" +
                "segs=" + segs +
                ", qQteExp=" + qQteExp +
                ", qQteTraitees=" + qQteTraitees +
                ", qteDechu=" + qteDechu +
                ", pagesTraitement=" + pagesTraitement +
                '}';
    }
}
