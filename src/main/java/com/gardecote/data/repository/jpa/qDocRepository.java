package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qConsignataire;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qDebarquement;
import com.gardecote.entities.qDoc;
/**
 * Created by Dell on 25/10/2016.
 */
public interface qDocRepository extends PagingAndSortingRepository<qDoc,Long> {
}
