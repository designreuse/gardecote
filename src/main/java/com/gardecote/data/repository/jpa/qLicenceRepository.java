package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qLicence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
/**
 * Repository : CodeInfractions.
 */
public interface qLicenceRepository extends PagingAndSortingRepository<qLicence, String> {

    @Query("select l from qLicence  l where TRIM(l.nomnav) like %:searchNomnav% order by l.idLic")
    Page<qLicence>  returnSuggNomNav1(Pageable pageable,@Param("searchNomnav") String searchNomnav);

}
