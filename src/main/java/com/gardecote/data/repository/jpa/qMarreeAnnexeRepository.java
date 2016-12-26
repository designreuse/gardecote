package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qDocPK;
import com.gardecote.entities.qMarreeAnnexe;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 25/12/2016.
 */
public interface qMarreeAnnexeRepository extends PagingAndSortingRepository<qMarreeAnnexe, qDocPK> {
}
