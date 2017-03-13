
package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="qPrefixConcession", schema="dbo", catalog="GCM8" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qPrefix.countAll", query="SELECT COUNT(x) FROM qPrefix  x" )
} )
@IdClass(qPrefixPK.class)
public class qPrefix implements Serializable,Comparable<qPrefix>
{


    @Id
    private String prefix;
    @Id
    private enumTypeDoc typeDoc;

    private int nbrLigneCarnet;
    private String information;

   public qPrefixPK getPrefixPK() {
    qPrefixPK prefpk=new qPrefixPK();
    prefpk.setPrefix(this.getPrefix());
    prefpk.setTypeDoc(this.typeDoc);
    return prefpk;
     }
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

    public int compareTo(qPrefix o) {

        return (this.getPrefix().compareTo(o.getPrefix()));
    }

}
