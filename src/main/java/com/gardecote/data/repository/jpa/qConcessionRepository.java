package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qConcession;
import com.gardecote.entities.qPageCarnet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Batobserv.
 */
public interface qConcessionRepository extends PagingAndSortingRepository<qConcession, String> {
    @Query("select conc  from qConcession conc where (conc.refConcession like %:searchconcession%) or (conc.qconsignataire.nomconsignataire like %:searchconcession%) order by conc.refConcession")
    Page<qConcession> returnSuggConcession(Pageable pageable, @Param("searchconcession") String searchconcession);

}
