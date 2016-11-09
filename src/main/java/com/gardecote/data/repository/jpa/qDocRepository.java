package com.gardecote.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;
/**
 * Created by Dell on 25/10/2016.
 */
public interface qDocRepository extends PagingAndSortingRepository<qDoc,qDocPK> {
    public Page<qDoc>  findAll(Pageable pageable);
}
