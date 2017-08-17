package com.gardecote.data.repository.jpa;
import com.gardecote.entities.*;
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
public interface qPageCarnetRepository extends PagingAndSortingRepository<qPageCarnet,qPageCarnetPK> {
    @Query("select p  from qPageCarnet p where (p.numeroPage  like %:searchpage%) and  p.typeDoc=:typeDoc order by p.numeroOrdrePage")
    Page<qPageCarnet>  returnSuggPage(Pageable pageable, @Param("searchpage") String searchpage,@Param("typeDoc") enumTypeDoc typeDoc);
    @Query("select p  from qPageAnnexe p where (p.numeroPage  like %:searchpage%) and  p.typeDoc=:typeDoc order by p.numeroOrdrePage")
    Page<qPageCarnet>  returnSuggPageAnnexe(Pageable pageable, @Param("searchpage") String searchpage,@Param("typeDoc") enumTypeDoc typeDoc);

    @Query("select p  from qPageCarnet p where p.qcarnet=:carnet AND p.numeroOrdrePage>=:debut order by p.numeroOrdrePage")
    ArrayList<qPageCarnet> returnFinList(@Param("carnet") qCarnet carnet, @Param("debut") Long debut);

    @Query("select p  from qPageCarnet p where type(p)= qPageAnnexe AND (p.numeroPage  like %:numeroP%)  order by p.numeroOrdrePage")
    Page<qPageCarnet>  returnSuggPageP(Pageable pageable, @Param("numeroP") String numeroP);
    @Query("select p  from qPageCarnet p where (p.numeroPage  =:numeroP)")
    Page<qPageCarnet>  returnSearchedPage(Pageable pageable, @Param("numeroP") String numeroP);
    @Query("select count(p)  from qPageMarree p where (p.qmarree is not null and p.numeroPage  =:numeroP)")
    Integer retDoc(@Param("numeroP") String numeroP);

    @Query("select espd.especesDyn  from qPageMarree espd where espd=:pm ")
    ArrayList<qEspeceDynamic> findEspDynForPage(@Param("pm") qPageMarree pm);



}
