package com.gardecote.data.repository.jpa;

import com.gardecote.entities.*;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */

public interface qRegistreNavireRepository extends PagingAndSortingRepository<qBateau, String> {
    @Query("select l from qLic  l where l.qnavire=:navire and l.dateDebutAuth<=:currentDate and l.dateFinAuth>=:currentDate order by l.dateDebutAuth")
    List<qLic> retActLicences(@Param("navire") qBateau navire,@Param("currentDate") Date currentDate);
    @Query("select l from qBateau  l where TRIM(l.nomnav) like %:searchNomnav% order by l.nomnav")
    Page<qBateau>  returnSuggNomNav1(Pageable pageable, @Param("searchNomnav") String searchNomnav);
    @Query("select l from qBateau  l where TRIM(l.nomnav) like %:terme% order by l.nomnav")
    Page<qBateau>  findAll(Pageable pageable,@Param("terme") String terme);

    @Query("select l from qBateau  l where TRIM(l.nomnav) = :searchBat")
    qBateau  findByName(@Param("searchBat") String searchBat);

    @Query("select l from qNavireLegale  l where TRIM(l.nomnav) = :searchBat")
    qNavireLegale findLegalByName(@Param("searchBat") String searchBat);


}
