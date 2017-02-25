package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qLic;

import com.gardecote.entities.qNation;
import com.gardecote.entities.qZone;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Repository : CodeInfractions.
 */
public interface qLicenceRepository extends PagingAndSortingRepository<qLic, String> {

    @Query("select l from qLic  l where TRIM(l.nomnav) like %:searchNomnav% order by l.createdAt DESC")
    Page<qLic>  returnSuggNomNav1(Pageable pageable, @Param("searchNomnav") String searchNomnav);
    //@Query("select l from qLic  l ")
   // public Page<qLic>  findAll(Pageable pageable);
    @Query("select l from qLic  l where l.nation = :nationg order by l.createdAt DESC")
    List<qLic> echec(@Param("nationg") qNation nationg);

    @Query("select l from qLic  l where l.zone = :zone order by l.createdAt DESC")
    List<qLic>  checkZone(@Param("zone") qZone zone);

}
