package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qBateau;
import com.gardecote.entities.qNavireLegale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Dell on 04/03/2017.
 */
public interface qRegistreNavireLegalRepository extends PagingAndSortingRepository<qNavireLegale, String> {
    @Query("select l from qNavireLegale  l where TRIM(l.nomnav) like %:terme% order by l.nomnav")
Page<qNavireLegale> findAll(Pageable pageable, @Param("terme") String terme);

}

