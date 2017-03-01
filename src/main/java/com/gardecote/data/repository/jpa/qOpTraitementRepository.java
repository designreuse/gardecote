package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qOpTrPK;
import com.gardecote.entities.qOpTraitement;
import com.gardecote.entities.qPageCarnetPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 25/02/2017.
 */
public interface qOpTraitementRepository extends PagingAndSortingRepository<qOpTraitement, qOpTrPK> {
}
