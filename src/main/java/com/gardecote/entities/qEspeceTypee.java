package com.gardecote.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
/**
 * Created by Dell on 25/10/2016.
 */

@Entity
@Table(name="qEspeceTypee", schema="dbo", catalog="GCM11" )
@IdClass(qEspeceTypeePK.class)
public class qEspeceTypee implements Serializable, Comparable<qEspeceTypee> {
    @Id
    private String qespeceId;
    @Id
    private enumEspType enumesptype;    
    @Id
    private Integer numOrdre;
    @Id
    private String prefix;
    @Id
    private enumTypeEspTypee typeesptypee;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = qEspece.class)
    @JsonManagedReference
    private qEspece qespece;

    @OneToMany(targetEntity = qEspeceDynamic.class,cascade = CascadeType.ALL)
    private List<qEspeceDynamic> especesDynamic;

    @ManyToMany(mappedBy ="especestypees",cascade = CascadeType.REFRESH)
    private List<qModelJP> modeljp;

    public qEspeceTypeePK getQEspeceTypeePK(){
        qEspeceTypeePK espPK=new qEspeceTypeePK(this.qespeceId,this.enumesptype,this.getNumOrdre(),this.getPrefix(),this.getTypeesptypee());
       return espPK;
    }

    public qEspeceTypee(enumEspType enumesptype, qEspece qespece, List<qModelJP> modeljp,Integer numOrdre,String prefix,enumTypeEspTypee typeesptypee) {
        if(qespece!=null) this.qespeceId = qespece.getCodeEsp();
        else  this.qespeceId = "-1";
        this.enumesptype = enumesptype;
        this.qespece = qespece;
        this.modeljp = modeljp;
        this.prefix=prefix;
        this.typeesptypee=typeesptypee;
        this.numOrdre=numOrdre;
    }

    public List<qEspeceDynamic> getEspecesDynamic() {
        return especesDynamic;
    }

    public void setEspecesDynamic(List<qEspeceDynamic> especesDynamic) {
        this.especesDynamic = especesDynamic;
    }

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public enumTypeEspTypee getTypeesptypee() {
        return typeesptypee;
    }

    public void setTypeesptypee(enumTypeEspTypee typeesptypee) {

        this.typeesptypee = typeesptypee;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
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



    @Override
    public int compareTo(qEspeceTypee o) {
        //String qespeceIDo=o.getNumOrdre().toString()+o.getQespeceId().toString()+o.getEnumesptype().toString();
       // String qespeceID=this.getNumOrdre().toString()+this.qespeceId.toString()+this.getEnumesptype().toString();
        Integer qespeceIDo=o.getNumOrdre();
        Integer qespeceID=this.getNumOrdre();
        return qespeceID.compareTo(qespeceIDo);
        }
}
