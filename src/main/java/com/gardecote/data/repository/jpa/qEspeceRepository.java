package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qEspece;
import com.gardecote.entities.qLic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : Classinfractions.
 */
public interface qEspeceRepository extends PagingAndSortingRepository<qEspece, String> {

    @Query("select esp from qEspece  esp where esp.nomFr like %:esp% order by esp.nomFr DESC")
    Page<qEspece> returnSuggPage(Pageable pageable, @Param("esp") String esp);
}
