package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qDoc;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qDebarquement;
import com.gardecote.entities.qDocPK;
/**
 * Created by Dell on 25/10/2016.
 */
public interface qDebarquementRepository extends PagingAndSortingRepository<qDebarquement,qDocPK> {
}
