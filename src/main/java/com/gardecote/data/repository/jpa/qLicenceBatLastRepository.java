package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qLicenceBatLast;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
/**
 * Repository : CodeInfractions.
 */
public interface qLicenceBatLastRepository extends PagingAndSortingRepository<qLicenceBatLast, Long> {

    @Query("select l from qLicenceBatLast  l where TRIM(l.nomnav) like %:searchNomnav% order by l.idLic")
    Page<qLicenceBatLast>  returnSuggNomNav1(Pageable pageable,@Param("searchNomnav") String searchNomnav);

}
