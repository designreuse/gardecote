package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qTypeNavire", schema="dbo", catalog="GCM11" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qTypeNav.countAll", query="SELECT COUNT(x) FROM qTypeNav x" )
} )
public class qTypeNav implements Serializable {
    @Id
    private String idqTypeNav;
    private String descr;

    public qTypeNav(String idqTypeNav, String descr) {
        this.idqTypeNav = idqTypeNav;
        this.descr = descr;
    }

    public qTypeNav() {
    }

    public String getIdqTypeNav() {
        return idqTypeNav;
    }

    public void setIdqTypeNav(String idqTypeNav) {
        this.idqTypeNav = idqTypeNav;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
