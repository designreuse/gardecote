package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qDebarquement;
/**
 * Created by Dell on 25/10/2016.
 */
public interface qDebquementRepository extends PagingAndSortingRepository<qDebarquement,qDocPK> {
}
