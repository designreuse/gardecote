package com.gardecote.data.repository.jpa;
import com.gardecote.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 03/03/2017.
 */
public interface qEnginsAuthoriseeRepository extends PagingAndSortingRepository<qEnginAuthorisee, qEnginAuthoriseePK> {

    @Query("select eng from qEnginAuthorisee  eng where eng.navire=:navire  order by eng.maillageAuthorise")
    List<qEnginAuthorisee> getEnginsAuthorisees(@Param("navire") qNavireLegale navire);
    @Query("select eng from qCategRessource  eng where eng.qNavires=:navire ")
    List<qCategRessource> getCategoriesRattachees(@Param("navire") qNavireLegale navire);
}
