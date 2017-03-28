package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qEspece;
import com.gardecote.entities.qModelJP;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qEspeceTypeePK;
import com.gardecote.entities.qEspeceTypee;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : Classinfractions.
 */
public interface qEspeceTypeeRepository extends PagingAndSortingRepository<qEspeceTypee, qEspeceTypeePK> {

    @Query("select md from qModelJP md")
    List<qModelJP> findModel();
}
