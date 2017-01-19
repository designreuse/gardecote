package com.gardecote.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qTypeConcession;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Repository : Authprov.
 */

/**
 * Repository : Bateaucomplet.
 */

public  interface qTypeConcessionRepository extends PagingAndSortingRepository<qTypeConcession, Integer> {
    @Query("select ctg from qCategRessource ctg where ctg.typeconcessionConcernee=:typeconcession")
    public List<qCategRessource> checkTypeconcession(@Param("typeconcession") qTypeConcession typeconcession);

}

