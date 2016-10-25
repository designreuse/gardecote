package com.gardecote.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
public class qPageTraitement extends qPageCarnet {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------

   private  Integer  nbrLigne=10;
    // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link


    public  Integer getNbrLigne() {
        return nbrLigne;
    }

    @OneToOne
    private qTraitement     qtraitement;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="IdCarnet", referencedColumnName="IdCarnet")
    private qCarnet carnet;

    @OneToMany(mappedBy = "pages",targetEntity = qJourMere.class)
    private List<qUniteTraitement> opTraitements;
    //----------------------------------------------------------------------




}
