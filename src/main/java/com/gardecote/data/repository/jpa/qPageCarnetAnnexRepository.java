package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qMarreeAnnexe;
import com.gardecote.entities.qPageAnnexe;
import com.gardecote.entities.qPageCarnetPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 25/12/2016.
 */
public interface qPageCarnetAnnexRepository extends PagingAndSortingRepository<qPageAnnexe, qPageCarnetPK> {
}
