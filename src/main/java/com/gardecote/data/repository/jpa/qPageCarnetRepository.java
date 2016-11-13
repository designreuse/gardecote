package com.gardecote.data.repository.jpa;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qPageCarnet;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository : Consignatires.
 */
public interface qPageCarnetRepository extends PagingAndSortingRepository<qPageCarnet, String> {
    @Query("select p  from qPageCarnet p where p.numeroPage like %:searchpage% order by p.numeroOrdrePage")
    Page<qPageCarnet>  returnSuggPage(Pageable pageable, @Param("searchpage") String searchpage);

    @Query("select p  from qPageCarnet p where p.qcarnet=:carnet AND p.numeroOrdrePage>=:debut order by p.numeroOrdrePage")
    ArrayList<qPageCarnet> returnFinList(@Param("carnet") qCarnet carnet, @Param("debut") Long debut);

}
