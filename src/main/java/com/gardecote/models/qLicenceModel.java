/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.models;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "licences_bat_last"
 *
 * @author Telosys Tools Generator
 *
 */

public class qLicenceModel
{
    private String numlic;
    private String typelicence;
    private Integer ancons;
    private String balise;
    private String calpoids;
    private String count;
    private String createdAt;
    private String debaut;
    private String finauto;
    private String eff;
    private Double gt;
    private Integer imo;
    private Double kw;
    private String larg;
    private String longueur;
    private String nbrhomm;
    private String nomar;
    private String nomnav;
    private String numimm;
    private String port;
    private String puimot;
    private String radio;
    private Double tjb;
    private Integer typb;
    private String updatedAt;
    private Integer typencad;
    private Integer nationIdnation;
    private String qnavireNumimm;
    private Integer qtypnavIdTypelic;
    private Integer zoneIdzone;
    private String qconcessionid;

    //
    // constructors
    //
    public qLicenceModel()
    {
    }

    public String getTypelicence() {
        return typelicence;
    }

    public qLicenceModel(String numlic)
    {
        this.numlic = numlic;
    }

    public qLicenceModel(String numlic, String typelicence, Integer ancons, String balise, String calpoids,
                         String count, String createdAt, String debaut, String finauto, String eff, Double gt, Integer imo,
                         Double kw, String larg, String longueur, String nbrhomm, String nomar, String nomnav, String numimm,
                         String port, String puimot, String radio, Double tjb, Integer typb, String updatedAt, Integer typencad,
                         Integer nationIdnation, String qnavireNumimm, Integer qtypnavIdTypelic, Integer zoneIdzone,
                         String qconcessionid)
    {
        this.numlic = numlic;
        this.typelicence = typelicence;
        this.ancons = ancons;
        this.balise = balise;
        this.calpoids = calpoids;
        this.count = count;
        this.createdAt = createdAt;
        this.debaut = debaut;
        this.finauto = finauto;
        this.eff = eff;
        this.gt = gt;
        this.imo = imo;
        this.kw = kw;
        this.larg = larg;
        this.longueur = longueur;
        this.nbrhomm = nbrhomm;
        this.nomar = nomar;
        this.nomnav = nomnav;
        this.numimm = numimm;
        this.port = port;
        this.puimot = puimot;
        this.radio = radio;
        this.tjb = tjb;
        this.typb = typb;
        this.updatedAt = updatedAt;
        this.typencad = typencad;
        this.nationIdnation = nationIdnation;
        this.qnavireNumimm = qnavireNumimm;
        this.qtypnavIdTypelic = qtypnavIdTypelic;
        this.zoneIdzone = zoneIdzone;
        this.qconcessionid = qconcessionid;
    }

    //
    // getters/setters
    //

    public String getNumlic()
    {
        return this.numlic;
    }

    public void setNumlic(String numlic)
    {
        this.numlic = numlic;
    }


    public void setTypelicence(String typelicence) {
        this.typelicence = typelicence;
    }

    public Integer getAncons()
    {
        return this.ancons;
    }

    public void setAncons(Integer ancons)
    {
        this.ancons = ancons;
    }

    public String getBalise()
    {
        return this.balise;
    }

    public void setBalise(String balise)
    {
        this.balise = balise;
    }

    public String getCalpoids()
    {
        return this.calpoids;
    }

    public void setCalpoids(String calpoids)
    {
        this.calpoids = calpoids;
    }

    public String getCount()
    {
        return this.count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getDebaut()
    {
        return this.debaut;
    }

    public void setDebaut(String debaut)
    {
        this.debaut = debaut;
    }

    public String getFinauto()
    {
        return this.finauto;
    }

    public void setFinauto(String finauto)
    {
        this.finauto = finauto;
    }

    public String getEff()
    {
        return this.eff;
    }

    public void setEff(String eff)
    {
        this.eff = eff;
    }

    public Double getGt()
    {
        return this.gt;
    }

    public void setGt(Double gt)
    {
        this.gt = gt;
    }

    public Integer getImo()
    {
        return this.imo;
    }

    public void setImo(Integer imo)
    {
        this.imo = imo;
    }

    public Double getKw()
    {
        return this.kw;
    }

    public void setKw(Double kw)
    {
        this.kw = kw;
    }

    public String getLarg()
    {
        return this.larg;
    }

    public void setLarg(String larg)
    {
        this.larg = larg;
    }

    public String getLongueur()
    {
        return this.longueur;
    }

    public void setLongueur(String longueur)
    {
        this.longueur = longueur;
    }

    public String getNbrhomm()
    {
        return this.nbrhomm;
    }

    public void setNbrhomm(String nbrhomm)
    {
        this.nbrhomm = nbrhomm;
    }

    public String getNomar()
    {
        return this.nomar;
    }

    public void setNomar(String nomar)
    {
        this.nomar = nomar;
    }

    public String getNomnav()
    {
        return this.nomnav;
    }

    public void setNomnav(String nomnav)
    {
        this.nomnav = nomnav;
    }

    public String getNumimm()
    {
        return this.numimm;
    }

    public void setNumimm(String numimm)
    {
        this.numimm = numimm;
    }

    public String getPort()
    {
        return this.port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getPuimot()
    {
        return this.puimot;
    }

    public void setPuimot(String puimot)
    {
        this.puimot = puimot;
    }

    public String getRadio()
    {
        return this.radio;
    }

    public void setRadio(String radio)
    {
        this.radio = radio;
    }

    public Double getTjb()
    {
        return this.tjb;
    }

    public void setTjb(Double tjb)
    {
        this.tjb = tjb;
    }

    public Integer getTypb()
    {
        return this.typb;
    }

    public void setTypb(Integer typb)
    {
        this.typb = typb;
    }

    public String getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getTypencad()
    {
        return this.typencad;
    }

    public void setTypencad(Integer typencad)
    {
        this.typencad = typencad;
    }

    public Integer getNationIdnation()
    {
        return this.nationIdnation;
    }

    public void setNationIdnation(Integer nationIdnation)
    {
        this.nationIdnation = nationIdnation;
    }

    public String getQnavireNumimm()
    {
        return this.qnavireNumimm;
    }

    public void setQnavireNumimm(String qnavireNumimm)
    {
        this.qnavireNumimm = qnavireNumimm;
    }

    public Integer getQtypnavIdTypelic()
    {
        return this.qtypnavIdTypelic;
    }

    public void setQtypnavIdTypelic(Integer qtypnavIdTypelic)
    {
        this.qtypnavIdTypelic = qtypnavIdTypelic;
    }

    public Integer getZoneIdzone()
    {
        return this.zoneIdzone;
    }

    public void setZoneIdzone(Integer zoneIdzone)
    {
        this.zoneIdzone = zoneIdzone;
    }

    public String getQconcessionid()
    {
        return this.qconcessionid;
    }

    public void setQconcessionid(String qconcessionid)
    {
        this.qconcessionid = qconcessionid;
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer("com.mycompany.licencemodel").append(".").append("Qlic30")
                .append("(");

        buffer.append("[").append("numlic").append("=").append(numlic).append("]");
        buffer.append("[").append("typelicence").append("=").append(typelicence).append("]");
        buffer.append("[").append("ancons").append("=").append(ancons).append("]");
        buffer.append("[").append("balise").append("=").append(balise).append("]");
        buffer.append("[").append("calpoids").append("=").append(calpoids).append("]");
        buffer.append("[").append("count").append("=").append(count).append("]");
        buffer.append("[").append("createdAt").append("=").append(createdAt).append("]");
        buffer.append("[").append("debaut").append("=").append(debaut).append("]");
        buffer.append("[").append("finauto").append("=").append(finauto).append("]");
        buffer.append("[").append("eff").append("=").append(eff).append("]");
        buffer.append("[").append("gt").append("=").append(gt).append("]");
        buffer.append("[").append("imo").append("=").append(imo).append("]");
        buffer.append("[").append("kw").append("=").append(kw).append("]");
        buffer.append("[").append("larg").append("=").append(larg).append("]");
        buffer.append("[").append("longueur").append("=").append(longueur).append("]");
        buffer.append("[").append("nbrhomm").append("=").append(nbrhomm).append("]");
        buffer.append("[").append("nomar").append("=").append(nomar).append("]");
        buffer.append("[").append("nomnav").append("=").append(nomnav).append("]");
        buffer.append("[").append("numimm").append("=").append(numimm).append("]");
        buffer.append("[").append("port").append("=").append(port).append("]");
        buffer.append("[").append("puimot").append("=").append(puimot).append("]");
        buffer.append("[").append("radio").append("=").append(radio).append("]");
        buffer.append("[").append("tjb").append("=").append(tjb).append("]");
        buffer.append("[").append("typb").append("=").append(typb).append("]");
        buffer.append("[").append("updatedAt").append("=").append(updatedAt).append("]");
        buffer.append("[").append("typencad").append("=").append(typencad).append("]");
        buffer.append("[").append("nationIdnation").append("=").append(nationIdnation).append("]");
        buffer.append("[").append("qnavireNumimm").append("=").append(qnavireNumimm).append("]");
        buffer.append("[").append("qtypnavIdTypelic").append("=").append(qtypnavIdTypelic).append("]");
        buffer.append("[").append("zoneIdzone").append("=").append(zoneIdzone).append("]");
        buffer.append("[").append("qconcessionid").append("=").append(qconcessionid).append("]");

        return buffer.append(")").toString();
    }
}