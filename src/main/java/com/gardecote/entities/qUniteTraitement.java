package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */
public class qUniteTraitement {
    private qEspece esp;
    private Long qte;

    public qUniteTraitement(qEspece esp, Long qte) {
        this.esp = esp;
        this.qte = qte;
    }

    public qEspece getEsp() {
        return esp;
    }

    public void setEsp(qEspece esp) {
        this.esp = esp;
    }

    public Long getQte() {
        return qte;
    }

    public void setQte(Long qte) {
        this.qte = qte;
    }
}
