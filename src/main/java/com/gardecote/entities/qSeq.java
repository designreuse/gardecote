package com.gardecote.entities;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Table(name="qSeq21", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qSeq.countAll", query="SELECT COUNT(x) FROM qSeq x" )
} )
@IdClass(qSeqPK.class)
public class qSeq implements Serializable {
    @Id
    String debut;
    @Id
    String fin;

    @OneToOne(mappedBy = "qseq")
    private qDoc qdoc;

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public qDoc getQdoc() {
        return qdoc;
    }

    public void setQdoc(qDoc qdoc) {
        this.qdoc = qdoc;
    }

    public qSeq() {
    }

    public qSeq(String debut, String fin, qDoc qdoc) {
        this.debut = debut;
        this.fin = fin;
        this.qdoc = qdoc;
    }
    public qSeqPK getSeqPK(){
        qSeqPK qseqpk=new qSeqPK(this.debut,this.fin);
        return qseqpk;
    }
}
