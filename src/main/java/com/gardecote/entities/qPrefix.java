
package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="qPrefix20", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qPrefix.countAll", query="SELECT COUNT(x) FROM qPrefix  x" )
} )
@IdClass(qPrefixPK.class)
public class qPrefix implements Serializable
{
    @Id
    private String prefix;
    @Id
    private enumTypeDoc typeDoc;

    private int nbrLigneCarnet;
    private String information;


    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getNbrLigneCarnet() {
        return nbrLigneCarnet;
    }

    public void setNbrLigneCarnet(int nbrLigneCarnet) {
        this.nbrLigneCarnet = nbrLigneCarnet;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public qPrefix(String prefix,enumTypeDoc typDoc, int nbrLigneCarnet, String information) {
        this.prefix = prefix;
        this.nbrLigneCarnet = nbrLigneCarnet;
        this.information = information;
        this.typeDoc = typDoc;
    }

    public qPrefix() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qPrefix)) return false;

        qPrefix qPrefix = (qPrefix) o;

        if (getNbrLigneCarnet() != qPrefix.getNbrLigneCarnet()) return false;
        if (!getPrefix().equals(qPrefix.getPrefix())) return false;
        if (getTypeDoc() != qPrefix.getTypeDoc()) return false;
        return getInformation().equals(qPrefix.getInformation());

    }

    @Override
    public int hashCode() {
        int result = getPrefix().hashCode();
        result = 31 * result + getTypeDoc().hashCode();
        result = 31 * result + getNbrLigneCarnet();
        result = 31 * result + getInformation().hashCode();
        return result;
    }
}
