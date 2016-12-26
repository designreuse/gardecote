package com.gardecote.data.repository.jpa;
import com.gardecote.entities.enumPrefix;
import com.gardecote.entities.qModelJP;
import com.gardecote.entities.qPrefixPK;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qTypeConcession;

/**
 * Repository : CodesNotifAm.
 */
public interface qModelJPRepository extends PagingAndSortingRepository<qModelJP, qPrefixPK> {

}
