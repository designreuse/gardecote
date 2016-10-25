package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qZone;
import org.springframework.data.repository.PagingAndSortingRepository;
import  com.gardecote.entities.qQuotasAuthPK;
import com.gardecote.entities.qQuotasAuthorisee;
/**
 * Created by Dell on 25/10/2016.
 */
public interface qQuotasAuthoriseeRepository extends PagingAndSortingRepository<qQuotasAuthorisee, qQuotasAuthPK> {
}
