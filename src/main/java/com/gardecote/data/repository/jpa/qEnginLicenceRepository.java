package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qEnginPecheMar;
import com.gardecote.entities.qEnginPechePK;
import com.gardecote.entities.qEnginsLicence;
import com.gardecote.entities.qEnginsLicencePK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Changmentact.
 */
public interface qEnginLicenceRepository extends PagingAndSortingRepository<qEnginsLicence, qEnginsLicencePK> {

}
