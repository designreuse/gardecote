package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qLic;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
/**
 * Repository : CodeInfractions.
 */
public interface qLicenceRepository extends PagingAndSortingRepository<qLic, String> {

    @Query("select l from qLic  l where TRIM(l.nomnav) like %:searchNomnav% order by l.numlic")
    Page<qLic>  returnSuggNomNav1(Pageable pageable, @Param("searchNomnav") String searchNomnav);
    //@Query("select l from qLic  l ")
   // public Page<qLic>  findAll(Pageable pageable);
}
