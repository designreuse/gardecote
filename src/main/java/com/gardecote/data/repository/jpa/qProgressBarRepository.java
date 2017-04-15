package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qPrefix;
import com.gardecote.entities.qPrefixPK;
import com.gardecote.entities.qTaskProgressBar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dell on 13/04/2017.
 */
public interface qProgressBarRepository extends PagingAndSortingRepository<qTaskProgressBar, String> {

    @Query("select pr from qTaskProgressBar pr where pr.idProgressBar=:idPogressBar")
    public qTaskProgressBar findProgressBar(@Param("idPogressBar") String idPogressBar);

}
