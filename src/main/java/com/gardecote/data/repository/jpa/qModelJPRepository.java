package com.gardecote.data.repository.jpa;
import com.gardecote.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : CodesNotifAm.
 */
public interface qModelJPRepository extends PagingAndSortingRepository<qModelJP, qPrefixPK> {

    @Query("select espt from qEspeceTypee  espt where  espt.prefix=:pr")
    List<qEspeceTypee> findEspTypees(@Param("pr") String pr);


}
