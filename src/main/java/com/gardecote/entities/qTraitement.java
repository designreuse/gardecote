package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qTraitement20", schema="dbo", catalog="GCM1" )
// Define named queries here
@DiscriminatorValue("TRAITEMENT")
@NamedQueries ( {
        @NamedQuery ( name="qTraitement.countAll", query="SELECT COUNT(x) FROM qTraitement x" )
} )

public class qTraitement extends  qDoc implements Serializable {
    @Column
    @Enumerated
    @ElementCollection(targetClass = enumSegPeche.class)
    private  List<qSegUsines> segs;

    @OneToMany(mappedBy = "qtraitement",targetEntity = qQuantiteExportee.class)
    private List<qQuantiteExportee> qQteExp;
    @OneToMany(mappedBy = "traitement",targetEntity = qQuantitesTraites.class)
    private qQuantitesTraites qQteTraitees;
    private Long qteDechu;

    //@OneToMany
    @OneToMany(mappedBy="qtraitement", targetEntity=qPageTraitement.class)
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
}
