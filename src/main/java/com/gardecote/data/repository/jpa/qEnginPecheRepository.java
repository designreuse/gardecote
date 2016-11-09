package com.gardecote.data.repository.jpa;
import com.gardecote.entities.enumEngin;
import com.gardecote.entities.qEnginPeche;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Changmentact.
 */
public interface qEnginPecheRepository extends PagingAndSortingRepository<qEnginPeche, enumEngin> {

}
