package com.gardecote.data.repository.jpa;

/**
 * Created by Dell on 23/02/2017.
 */

import com.gardecote.entities.qDocPK;
import com.gardecote.entities.qTraitement;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface qTraitementRepository extends PagingAndSortingRepository<qTraitement,qDocPK>{

        }
