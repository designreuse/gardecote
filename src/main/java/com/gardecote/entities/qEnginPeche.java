package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPeche", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPeche.countAll", query="SELECT COUNT(x) FROM qEnginPeche x" )
} )
public class qEnginPeche implements Serializable {
    @Id
   private Integer IdEngin;

    private String Engin;
@ManyToOne
    private qCategRessource categressource;

    public Integer getIdEngin() {
        return IdEngin;
    }

    public void setIdEngin(Integer idEngin) {
        IdEngin = idEngin;
    }

    public String getEngin() {
        return Engin;
    }

    public void setEngin(String engin) {
        Engin = engin;
    }

    public qCategRessource getCategressource() {
        return categressource;
    }

    public void setCategressource(qCategRessource categressource) {
        this.categressource = categressource;
    }
}
