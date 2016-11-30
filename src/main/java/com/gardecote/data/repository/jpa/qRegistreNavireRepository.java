package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qLicenceNational;
import com.gardecote.entities.qNavire;

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
public interface qRegistreNavireRepository extends PagingAndSortingRepository<qNavire, String> {


    @Query("select l from qLic  l where l.qnavire=:navire and l.dateDebutAuth<=:currentDate and l.dateFinAuth>=:currentDate order by l.dateDebutAuth")
    List<qLic> retActLicences(@Param("navire") qNavire navire,@Param("currentDate") Date currentDate);

    @Query("select l from qNavire  l where TRIM(l.nomnav) like %:searchNomnav% order by l.nomnav")
    Page<qNavire>  returnSuggNomNav1(Pageable pageable, @Param("searchNomnav") String searchNomnav);
    @Query("select l from qNavire  l where TRIM(l.nomnav) like %:terme% order by l.nomnav")
    Page<qNavire>  findAll(Pageable pageable,@Param("terme") String terme);
}
