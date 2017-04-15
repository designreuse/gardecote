package com.gardecote.data.repository.jpa;

import com.gardecote.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.Expression;
import java.util.List;

/**
 * Created by Dell on 27/10/2016.
 */
public interface qCategRessourceRepository extends PagingAndSortingRepository<qCategRessource,Integer> {

    @Query("select eng.Engins from qCategRessource eng where eng.idtypeConcession=:idTypeConcession ")
    public List<qEnginsLicence> findEngLicence(@Param("idTypeConcession") Integer idTypeConcession);

    @Query("select concess.categoriesRessources from qConcession concess where concess.refConcession=:refConcession")
    public List<qCategRessource> findCategByConcession(@Param("refConcession") String refConcession);

    @Query("select c from qCategRessource c join c.typeconcessionConcernee typeC where type(typeC) =  qTypeConcessionHautiriere and c.typeconcessionConcernee.prefix=:pref ")
    public qCategRessource  findCategH(@Param("pref") qPrefix pref);
    @Query("select c from qCategRessource c join c.typeconcessionConcernee typeC where type(typeC) =  qTypeConcessionCotiere and c.typeconcessionConcernee.prefix=:pref ")
    public qCategRessource  findCategC(@Param("pref") qPrefix pref);

}
