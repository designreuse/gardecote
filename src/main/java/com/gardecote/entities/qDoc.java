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
   private Date depart       ;

    @Id
    private String numImm;

    @Column(name="nbrPages", nullable=false)
    private Integer nbrPages;

    @Column(name="Retour", nullable=false, length=10)
    private Date     retour       ;

    @OneToOne
    private qRegistreNavire    qnavire;

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

    public qRegistreNavire getQnavire() {
        return qnavire;
    }

    public void setQnavire(qRegistreNavire qnavire) {
        this.qnavire = qnavire;
    }

    public qDoc(enumTypeDoc enumtypedoc,Date depart,Date     retour,Integer nbrPages,qRegistreNavire    qnavire) {
        this.enumtypedoc = enumtypedoc;
        this.depart=depart;
        this.qnavire=qnavire;
        this.numImm=this.qnavire.getNumimm();
        this.retour = retour;
        this.nbrPages=nbrPages;
    }



    public enumTypeDoc getEnumtypedoc() {
        return enumtypedoc;
    }

    public void setEnumtypedoc(enumTypeDoc enumtypedoc) {
        this.enumtypedoc = enumtypedoc;
    }






}
