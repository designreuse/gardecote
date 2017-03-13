package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qNavireHistoriqueChangements;
import com.gardecote.entities.qNavireHistoriqueChangementsPK;
import com.gardecote.entities.qNavireLegale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dell on 06/03/2017.
 */
public interface qHystoriquesRepository extends PagingAndSortingRepository<qNavireHistoriqueChangements, qNavireHistoriqueChangementsPK> {

    @Query("select ch from qNavireHistoriqueChangements  ch where ch.navireConcernee=:navire ")
    List<qNavireHistoriqueChangements> findChangNavById(@Param("navire") qNavireLegale navire);

}
