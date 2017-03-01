package com.gardecote.entities;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qZone33", schema="dbo", catalog="GCM4" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qZone.countAll", query="SELECT COUNT(x) FROM qZone x" )
} )
public class qZone implements Serializable {
    @Id
    private Integer IdZone;
    private String nom;

    public qZone() {
    }

    public qZone(Integer idZone, String nom) {
        IdZone = idZone;
        this.nom = nom;
    }

    public Integer getIdZone() {
        return IdZone;
    }

    public void setIdZone(Integer idZone) {
        IdZone = idZone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
