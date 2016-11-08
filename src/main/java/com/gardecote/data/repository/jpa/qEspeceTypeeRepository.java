package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qEspece;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qEspeceTypeePK;
import com.gardecote.entities.qEspeceTypee;
/**
 * Repository : Classinfractions.
 */
public interface qEspeceTypeeRepository extends PagingAndSortingRepository<qEspeceTypee, qEspeceTypeePK> {

}
