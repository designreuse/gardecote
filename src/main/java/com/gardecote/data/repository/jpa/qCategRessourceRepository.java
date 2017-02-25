package com.gardecote.data.repository.jpa;

import com.gardecote.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dell on 27/10/2016.
 */
public interface qCategRessourceRepository extends PagingAndSortingRepository<qCategRessource,Integer> {

    @Query("select eng.Engins from qCategRessource eng where eng.idtypeConcession=:idTypeConcession ")
    public List<qEnginsLicence> findEngLicence(@Param("idTypeConcession") Integer idTypeConcession);

    @Query("select concess.categoriesRessources from qConcession concess where concess.refConcession=:refConcession")
    public List<qCategRessource> findCategByConcession(@Param("refConcession") String refConcession);



}
