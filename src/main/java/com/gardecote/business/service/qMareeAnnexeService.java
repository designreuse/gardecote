package com.gardecote.business.service;

import com.gardecote.data.repository.jpa.qJourMereAnnexeRepository;
import com.gardecote.data.repository.jpa.qMarreeAnnexeRepository;
import com.gardecote.data.repository.jpa.qPageCarnetAnnexRepository;
import com.gardecote.entities.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dell on 25/12/2016.
 */
public interface qMareeAnnexeService {
    qMarreeAnnexe findById(qDocPK idcarnet);
   List<qMarreeAnnexe>  findAll();

    qMarreeAnnexe save(qMarreeAnnexe authprov);

    qMarreeAnnexe create(qMarreeAnnexe authprov) ;

    qMarreeAnnexe update(qMarreeAnnexe authprov);


    void delete(qDocPK  idauthpr) ;


}
