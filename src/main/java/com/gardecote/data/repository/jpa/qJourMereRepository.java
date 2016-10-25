package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qJourDeb;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qJourMere;
/**
 * Created by Dell on 25/10/2016.
 */
public interface  qJourMereRepository extends PagingAndSortingRepository<qJourMere, Long> {
}
