package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qTypeConcession;
import com.gardecote.entities.qCategRessource;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dell on 27/10/2016.
 */
public interface qCategRessourceRepository extends PagingAndSortingRepository<qCategRessource, qTypeConcession> {

}
