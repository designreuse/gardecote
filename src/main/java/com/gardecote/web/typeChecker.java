package com.gardecote.web;

import com.gardecote.entities.qTypeConcessionArtisanal;
import com.gardecote.entities.qTypeConcessionCotiere;
import com.gardecote.entities.qTypeConcessionHautiriere;

/**
 * Created by Dell on 22/11/2016.
 */
public class typeChecker {


    public boolean isArtisanal(Object object) {
        return object instanceof qTypeConcessionArtisanal;
    }
    public boolean isCotier(Object object) {
        return object  instanceof qTypeConcessionCotiere;
    }
    public boolean isHautiriere(Object object) {
        return object instanceof qTypeConcessionHautiriere;
    }
}
