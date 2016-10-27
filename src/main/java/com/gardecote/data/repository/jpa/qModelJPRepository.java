package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qModelJP;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qTypeConcession;

/**
 * Repository : CodesNotifAm.
 */
public interface qModelJPRepository extends PagingAndSortingRepository<qModelJP, qTypeConcession> {

}
