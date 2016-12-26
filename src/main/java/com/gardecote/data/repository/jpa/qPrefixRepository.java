package com.gardecote.data.repository.jpa;

import com.gardecote.entities.enumTypeDoc;
import com.gardecote.entities.qJourDeb;
import com.gardecote.entities.qPrefix;
import com.gardecote.entities.qPrefixPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 02/12/2016.
 */
public interface qPrefixRepository extends PagingAndSortingRepository<qPrefix, qPrefixPK> {

    @Query("select pr from qPrefix pr where pr.typeDoc=:typeDoc")
    public List<qPrefix> prefixesByTypeDoc(@Param("typeDoc") enumTypeDoc typeDoc);

}
