package com.gardecote.data.repository.jpa;

import com.gardecote.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
public interface qDocRepository extends PagingAndSortingRepository<qDoc,qDocPK> {
    public Page<qDoc>  findAll(Pageable pageable);

    @Query("select jr from qJourDeb jr where jr.numImm=:numimm and jr.dateJour >= :datedep and  jr.dateJour <= :dateret")
    public List<qJourDeb> checkUsedJoursDeb(@Param("numimm") String numimm,@Param("datedep") Date datedep, @Param("dateret") Date dateret);

    @Query("select jr from qJourMere jr where jr.numImm=:numimm and jr.dateJour>= :datedep and  jr.dateJour <= :dateret")
    public List<qJourMere> checkUsedJoursMere(@Param("numimm") String numimm,@Param("datedep") Date datedep, @Param("dateret") Date dateret);

    @Query("select lic from qLic lic where lic.qnavire=:qnav")
    public List<qLic> retLicences(@Param("qnav") qNavire qnav);

    @Query("select pcrn from qPageCarnet pcrn where pcrn.typeDoc=:prefix and pcrn.qcarnet.qprefix.typeDoc=:typeDoc")
    public List<qPageCarnet> checkPrefix(@Param("prefix") String prefix,@Param("typeDoc") String typeDoc);

    @Query("select doc from qDoc doc where ((doc.depart=:searchDateCapture) or (:searchDateCapture is null) ) and ((doc.numImm=:searchBat) or (:searchBat is null) )")
    public Page<qDoc> findAllMatchedDocs(Pageable pageable,@Param("searchDateCapture") Date searchDateCapture,@Param("searchBat") String searchBat);



}
