package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qPageCarnet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Consignatires.
 */
public interface qPageCarnetRepository extends PagingAndSortingRepository<qPageCarnet, String> {

}
