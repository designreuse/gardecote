package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 13/04/2017.
 */
@Entity
@Table(name="qTaskProgressBar", schema="dbo", catalog="GCM11" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="taskProgressBar.countAll", query="SELECT COUNT(x) FROM qTaskProgressBar x" )
} )
public class qTaskProgressBar implements Serializable {
    @Id
    @Column(name="idProgressBar", nullable=false)
    private String idProgressBar;

    private int total;

    private int nbrTaitee;
    private int progress;
    private String status;

    public void setIdProgressBar(String idProgressBar) {
        this.idProgressBar = idProgressBar;
    }
    public void increaseProgress(int pr) {progress=progress+pr;};
    public String getIdProgressBar() {
        return idProgressBar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNbrTaitee() {
        return nbrTaitee;
    }

    public void setNbrTaitee(int nbrTaitee) {
        this.nbrTaitee = nbrTaitee;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public qTaskProgressBar() {
    }

    public qTaskProgressBar(int total, int nbrTaitee, int progress, String status) {
        this.total = total;
        this.nbrTaitee = nbrTaitee;
        this.progress = progress;
        this.status = status;
    }

}
