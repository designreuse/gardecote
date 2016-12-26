package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qJourMereAnnexe;
import com.gardecote.entities.qJourPK;
import com.gardecote.entities.qMarreeAnnexe;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 25/12/2016.
 */
public interface qJourMereAnnexeRepository extends PagingAndSortingRepository<qJourMereAnnexe, qJourPK> {
}
