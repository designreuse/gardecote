package com.gardecote.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="qNavireLegale", schema="dbo", catalog="GCM5" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qNavireLegale.countAll", query="SELECT COUNT(x) FROM qNavireLegale x" )
} )
public class qNavireLegale extends qBateau implements Serializable {

    @Column(name="numlic", length=255)
    private String     numlic       ;

    private enumModePeche   modePeche ;

    @Column(name="nomar", length=255)
    //  @NotEmpty
    private String     nomar;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="debaut")
    private Date       dateDebutAuth      ;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="finauto")
    private Date       dateFinAuth       ;
    // fin de remplissage de champs bateau

    // pour la compatibilté avec les anciens données

    @OneToMany(mappedBy = "qnavire")
    private List<qLic> licences;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocNaviresCategRessources")
    @JsonBackReference
    //   @NotNull
    private List<qCategRessource>   qcatressources;

    @OneToMany(targetEntity=qEnginAuthorisee.class,cascade = CascadeType.ALL)
    private List<qEnginAuthorisee> enginsAuthorisees;

    @OneToMany(targetEntity=qNavireHistoriqueChangements.class,cascade = CascadeType.ALL)
    private List<qNavireHistoriqueChangements> historiqueChangements;


    @OneToMany(mappedBy = "qnavire")
    private List<qDoc> documents;

    public List<qLic> getLicences() {
        return licences;
    }

    public List<qCategRessource> getQcatressources() {
        return qcatressources;
    }

    public void setQcatressources(List<qCategRessource> qcatressources) {
        this.qcatressources = qcatressources;
    }

    public List<qEnginAuthorisee> getEnginsAuthorisees() {
        return enginsAuthorisees;
    }

    public void setEnginsAuthorisees(List<qEnginAuthorisee> enginsAuthorisees) {
        this.enginsAuthorisees = enginsAuthorisees;
    }

    public String getNomar() {
        return nomar;
    }

    public void setNomar(String nomar) {
        this.nomar = nomar;
    }

    public List<qDoc> getDocuments() {
        return documents;
    }

    public void setDocuments(List<qDoc> documents) {
        this.documents = documents;
    }

    public void setLicences(List<qLic> licences) {
        this.licences = licences;
    }


    public qNavireLegale() {
    }

    public String getNumlic() {
        return numlic;
    }

    public void setNumlic(String numlic) {
        this.numlic = numlic;
    }

    public enumModePeche getModePeche() {
        return modePeche;
    }

    public void setModePeche(enumModePeche modePeche) {
        this.modePeche = modePeche;
    }

    public Date getDateDebutAuth() {
        return dateDebutAuth;
    }

    public void setDateDebutAuth(Date dateDebutAuth) {
        this.dateDebutAuth = dateDebutAuth;
    }

    public Date getDateFinAuth() {
        return dateFinAuth;
    }

    public void setDateFinAuth(Date dateFinAuth) {
        this.dateFinAuth = dateFinAuth;
    }

    public List<qNavireHistoriqueChangements> getHistoriqueChangements() {
        return historiqueChangements;
    }

    public void setHistoriqueChangements(List<qNavireHistoriqueChangements> historiqueChangements) {
        this.historiqueChangements = historiqueChangements;
    }

    public qNavireLegale(String numimm, String nomnav,  String longg, String puimot, qNation nation, String larg, String count, String nbrhomm, String eff, Integer anneeconstr, String calpoids, float gt, float kw, float tjb, Integer imo, String port, String radio, String balise, Date updatedOn, String numlic, enumModePeche modePeche, Date dateDebutAuth, Date dateFinAuth, List<qCategRessource> qcatressources, List<qEnginAuthorisee> enginsAuthorisees,String nomar) {
        super(numimm, nomnav,longg, puimot, nation, larg, count, nbrhomm, eff, anneeconstr, calpoids, gt, kw, tjb, imo, port, radio, balise, updatedOn);
        this.numlic = numlic;
        this.modePeche = modePeche;
        this.dateDebutAuth = dateDebutAuth;
        this.dateFinAuth = dateFinAuth;
        this.qcatressources = qcatressources;
        this.enginsAuthorisees = enginsAuthorisees;
        this.nomar=nomar;
    }

}
