package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qAccordPeche;
import com.gardecote.entities.qEspeceDynamic;
import com.gardecote.entities.qEspeceDynamicPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 04/03/2017.
 */
public interface qEspeceDynamicRepository extends PagingAndSortingRepository<qEspeceDynamic, qEspeceDynamicPK> {
}
