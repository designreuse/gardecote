package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qCapture;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qSeq;
import com.gardecote.entities.qSeqPK;
/**
 * Repository : Activitebat.
 */
public interface qSeqRepository extends PagingAndSortingRepository<qSeq, qSeqPK> {

}
