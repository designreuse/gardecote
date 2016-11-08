package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qEspeceTypee", schema="dbo", catalog="GCM1" )
@IdClass(qEspeceTypeePK.class)
public class qEspeceTypee implements Serializable {
    @Id
    private String qespeceId;
    @Id
    private enumEspType enumesptype;

   // @Id
 //   private String idmodel;



    @OneToOne(cascade = CascadeType.ALL)

    private qEspece qespece;

    @ManyToMany(mappedBy ="especestypees",cascade = CascadeType.REFRESH)

    private List<qModelJP> modeljp;

    public qEspeceTypeePK getQEspeceTypeePK(){
    qEspeceTypeePK espPK=new qEspeceTypeePK(this.qespeceId,this.enumesptype);
    return espPK;
    }

    public qEspeceTypee(enumEspType enumesptype, qEspece qespece, List<qModelJP> modeljp) {
        this.qespeceId = qespece.getCodeEsp();
        this.enumesptype = enumesptype;
        this.qespece = qespece;
        this.modeljp = modeljp;
    }

    public qEspeceTypee() {
    }

    public qEspece getQespece() {
        return qespece;
    }

    public void setQespece(qEspece qespece) {
        this.qespece = qespece;
    }

    public enumEspType getEnumesptype() {
        return enumesptype;
    }

    public void setEnumesptype(enumEspType enumesptype) {
        this.enumesptype = enumesptype;
    }

    public String getQespeceId() {
        return qespeceId;
    }

    public void setQespeceId(String qespeceId) {
        this.qespeceId = qespeceId;
    }



    public List<qModelJP> getModeljp() {
        return modeljp;
    }

    public void setModeljp(List<qModelJP> modeljp) {
        this.modeljp = modeljp;
    }


}
