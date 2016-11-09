package com.gardecote.data.repository.jpa;
import com.gardecote.entities.enumEngin;
import com.gardecote.entities.enumEnginDeb;

import com.gardecote.entities.qEnginPecheDeb;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Changmentact.
 */
public interface qEnginPecheDebRepository extends PagingAndSortingRepository<qEnginPecheDeb, enumEnginDeb> {

}
