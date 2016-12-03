package com.gardecote.entities;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name="qNavire5", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qNavire.countAll", query="SELECT COUNT(x) FROM qNavire x" )
} )
public class qNavire implements Serializable {

    @Id
    @Column(name="numimm", nullable=false)
    @NotEmpty
    private String     numimm;

    @OneToMany(mappedBy = "qnavire")
    private List<qLic> licences;


    @Column(name="typb", length=100)
    private enumTypeBat    typb         ;

    @Column(name="nomnav", length=255)
    @NotEmpty
    private String     nomnav;

    @Column(name="nomar", length=255)
    @NotEmpty
    private String     nomar;

    @Column(name="longueur", length=255)
    @NotEmpty
    private String     longg        ;

    @Column(name="puimot", length=255)
    @NotEmpty
    private String     puimot  ;

    @OneToOne
    @NotNull
    private qNation    nation     ;

    public String getBalise() {
        return balise;
    }

    public void setBalise(String balise) {
        this.balise = balise;
    }

    @Column(name="larg", length=255)
    @NotEmpty
    private String     larg         ;


    @Column(name="count", length=255)
    @NotEmpty
    private String     count        ;

    @Column(name="nbrhomm", length=255)
    @NotEmpty
    private String     nbrhomm      ;

    @Column(name="eff", length=255)
    @NotEmpty
    private String     eff ;

    @Column(name="ancons", length=255)
    @NotNull
    private Integer     anneeconstr       ;

    @Column(name="calpoids", length=255)
    @NotEmpty
    private String     calpoids     ;


    @Column(name="gt")
    @NotNull
    private float    gt           ;


    @Column(name="kw" )
    @NotNull
    private float    kw           ;


    @Column(name="tjb")
    @NotNull
    private float     tjb          ;
    @Column(name="imo")
    @NotNull
    private Integer    imo          ;

    @Column(name="port", length=255)
    @NotEmpty
    private String     port         ;

    @Column(name="radio", length=255)
    @NotEmpty
    private String     radio        ;

    @Column(name="balise")
    @NotEmpty
    private String   balise           ;


    public enumTypeBat getTypb() {
        return typb;
    }

    public void setTypb(enumTypeBat typb) {
        this.typb = typb;
    }

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public String getNomnav() {
        return nomnav;
    }

    public void setNomnav(String nomnav) {
        this.nomnav = nomnav;
    }

    public List<qLic> getLicences() {
        return licences;
    }

    public void setLicences(List<qLic> licences) {
        this.licences = licences;
    }

    public String getNomar() {
        return nomar;
    }

    public void setNomar(String nomar) {
        this.nomar = nomar;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getPuimot() {
        return puimot;
    }

    public void setPuimot(String puimot) {
        this.puimot = puimot;
    }

    public qNation getNation() {
        return nation;
    }

    public void setNation(qNation nation) {
        this.nation = nation;
    }

    public String getLarg() {
        return larg;
    }

    public void setLarg(String larg) {
        this.larg = larg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNbrhomm() {
        return nbrhomm;
    }

    public void setNbrhomm(String nbrhomm) {
        this.nbrhomm = nbrhomm;
    }

    public String getEff() {
        return eff;
    }

    public void setEff(String eff) {
        this.eff = eff;
    }

    public Integer getAnneeconstr() {
        return anneeconstr;
    }

    public void setAnneeconstr(Integer anneeconstr) {
        this.anneeconstr = anneeconstr;
    }

    public String getCalpoids() {
        return calpoids;
    }

    public void setCalpoids(String calpoids) {
        this.calpoids = calpoids;
    }

    public float getGt() {
        return gt;
    }

    public void setGt(float gt) {
        this.gt = gt;
    }

    public float getKw() {
        return kw;
    }

    public void setKw(float kw) {
        this.kw = kw;
    }

    public float getTjb() {
        return tjb;
    }

    public void setTjb(float tjb) {
        this.tjb = tjb;
    }

    public Integer getImo() {
        return imo;
    }

    public void setImo(Integer imo) {
        this.imo = imo;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public qNavire() {
    }

    public qNavire(String numimm, String nomnav, String nomar, String longg, String puimot, qNation nation, String larg, String count, String nbrhomm, String eff, Integer anneeconstr, String calpoids, float gt, float kw, float tjb, Integer imo, String port, String radio,String balise) {
        this.numimm = numimm;
        this.nomnav = nomnav;
        this.nomar = nomar;
        this.longg = longg;
        this.puimot = puimot;
        this.nation = nation;
        this.larg = larg;
        this.count = count;
        this.nbrhomm = nbrhomm;
        this.eff = eff;
        this.anneeconstr = anneeconstr;
        this.calpoids = calpoids;
        this.gt = gt;
        this.kw = kw;
        this.tjb = tjb;
        this.imo = imo;
        this.port = port;
        this.radio = radio;
        this.balise = balise;
    }
}
