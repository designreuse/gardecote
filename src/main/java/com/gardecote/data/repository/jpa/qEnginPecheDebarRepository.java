package com.gardecote.data.repository.jpa;
import com.gardecote.entities.enumEngin;
import com.gardecote.entities.enumEnginDeb;


import com.gardecote.entities.qEnginPecheDebar;
import com.gardecote.entities.qEnginPechePK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Changmentact.
 */
public interface qEnginPecheDebarRepository extends PagingAndSortingRepository<qEnginPecheDebar, qEnginPechePK> {

}
