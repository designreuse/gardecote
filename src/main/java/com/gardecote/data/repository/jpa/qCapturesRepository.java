package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qCapture;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qCapturePK;
/**
 * Repository : Activitebat.
 */
public interface qCapturesRepository extends PagingAndSortingRepository<qCapture, qCapturePK> {

}
