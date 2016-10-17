package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qMarree;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qMarreePK;

/**
 * Repository : CodesAmende.
 */
public interface qMareeRepository extends PagingAndSortingRepository<qMarree, qMarreePK> {

}
