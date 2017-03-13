package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qEspeceTypee", schema="dbo", catalog="GCM8" )
@IdClass(qEspeceTypeePK.class)
public class qEspeceTypee implements Serializable, Comparable<qEspeceTypee> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceTypee)) return false;

        qEspeceTypee that = (qEspeceTypee) o;

        if (getQespeceId() != null ? !getQespeceId().equals(that.getQespeceId()) : that.getQespeceId() != null)
            return false;
        if (getEnumesptype() != that.getEnumesptype()) return false;
        return !(getModeljp() != null ? !getModeljp().equals(that.getModeljp()) : that.getModeljp() != null);

    }

    @Override
    public int hashCode() {
        int result = getQespeceId() != null ? getQespeceId().hashCode() : 0;
        result = 31 * result + (getEnumesptype() != null ? getEnumesptype().hashCode() : 0);
        result = 31 * result + (getModeljp() != null ? getModeljp().hashCode() : 0);
        return result;
    }



    @Override
    public int compareTo(qEspeceTypee o) {
        String qespeceIDo=o.getQespeceId().toString()+o.getEnumesptype().toString();
        String qespeceID=this.qespeceId.toString()+o.getEnumesptype().toString();
        return qespeceIDo.compareTo(qespeceID);
        }
}
