package com.gardecote.data.repository.jpa;

import com.gardecote.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Query("select doc from qDoc doc where doc.validation=:validationValue")
    public Page<qDoc>  findAll(Pageable pageable,@Param("validationValue") boolean validationValue);

    @Query("select jr from qJourDeb jr where jr.numImm=:numimm and jr.dateJour >= :datedep and  jr.dateJour <= :dateret")
    public List<qJourDeb> checkUsedJoursDeb(@Param("numimm") String numimm,@Param("datedep") Date datedep, @Param("dateret") Date dateret);

    @Query("select jr from qJourMere jr where jr.numImm=:numimm and jr.dateJour>= :datedep and  jr.dateJour <= :dateret")
    public List<qJourMere> checkUsedJoursMere(@Param("numimm") String numimm,@Param("datedep") Date datedep, @Param("dateret") Date dateret);

    @Query("select lic from qLic lic where lic.qnavire=:qnav")
    public List<qLic> retLicences(@Param("qnav") qNavireLegale qnav);

    @Query("select pcrn from qPageCarnet pcrn where pcrn.typeDoc=:prefix and pcrn.qcarnet.qprefix.typeDoc=:typeDoc")
    public List<qPageCarnet> checkPrefix(@Param("prefix") String prefix,@Param("typeDoc") String typeDoc);

    @Query("select doc from qDoc doc where ((doc.depart<=:searchDateCapture AND doc.retour>=:searchDateCapture)  ) and ((doc.numImm=:searchBat) or (:searchBat is null) or :searchDateCapture is null )")
    public Page<qDoc> findAllMatchedDocs(Pageable pageable,@Param("searchDateCapture") Date searchDateCapture,@Param("searchBat") String searchBat);

    @Query("select doc from qDoc doc where ((:searchDateCapture1 between  doc.depart and doc.retour) or (:searchDateCapture2 between doc.depart and doc.retour) or (doc.depart between :searchDateCapture1 and :searchDateCapture2 ) or (doc.retour between :searchDateCapture1 and :searchDateCapture2 ) ) and ((doc.numImm=:searchBat) or (:searchBat is null) ) and (doc.enumtypedoc<>2)")
    public Page<qDoc> findAllMatchedDocsBetweenDates(Pageable pageable,@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("searchBat") String searchBat);

    @Query("select doc from qDoc doc where   (doc.depart between :searchDateCapture1 and :searchDateCapture2 )   and ((doc.numImm=:searchBat) or (:searchBat is null) ) and (doc.enumtypedoc=2)")
    public Page<qDoc> findAllMatchedDocsBetweenDatesTr(Pageable pageable,@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("searchBat") String searchBat);

 //   @Query("select doc from qDoc doc where   (doc.depart between :searchDateCapture1 and :searchDateCapture2 )   and ((doc.numImm=:searchBat) or (:searchBat is null) ) and (doc.enumtypedoc=2)")
  //  public Page<qDoc> findAllMatchedDocsBetweenDatesByConcession(Pageable pageable,@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("types") List<qTypeConcession> types);

    @Query("select d from qDebarquement d where (:typeCD member of d.typesConcession) and ((:searchDateCapture1 between  d.depart and d.retour) or (:searchDateCapture2 between d.depart and d.retour) or (d.depart between :searchDateCapture1 and :searchDateCapture2 ) or (d.retour between :searchDateCapture1 and :searchDateCapture2 ) ) and (d.qnavire.nomnav like %:nomNavire%  ) ")
    public  List<qDebarquement> findAllMatchedDocsBetweenDatesByConcessionD(@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("nomNavire") String nomNavire, @Param("typeCD") qTypeConcession typeCD);

    @Query("select m from qMarree m where ( m.typeConcessionLiee= :typeCD ) and ((:searchDateCapture1 between  m.depart and m.retour) or (:searchDateCapture2 between m.depart and m.retour) or (m.depart between :searchDateCapture1 and :searchDateCapture2 ) or (m.retour between :searchDateCapture1 and :searchDateCapture2 ) ) and (m.qnavire.nomnav like %:nomNavire% ) ")
    public  List<qMarree> findAllMatchedDocsBetweenDatesByConcessionM(@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("nomNavire") String nomNavire, @Param("typeCD") qTypeConcession typeCD);

    @Query("select jm from qJourMere jm where ( jm.pageMarree.qmarree.typeConcessionLiee= :tcon ) and ((jm.dateJour between :searchDateCapture1 and:searchDateCapture2)  ) and (jm.navire.nomnav like %:nomNavire%)  ")
    public List<qJourMere> findAllMatchedJoursM(@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("nomNavire") String nomNavire, @Param("tcon") qTypeConcession tcon);

    @Query("select jd from qJourDeb jd where (:tcon member of jd.pagesDeb.qdebarquement.typesConcession ) OR (jd.dateJour between :searchDateCapture1 and :searchDateCapture2)  and (jd.navire.nomnav like %:nomNavire%) ")
    public List<qJourDeb> findAllMatchedJoursD(@Param("searchDateCapture1") Date searchDateCapture1,@Param("searchDateCapture2") Date searchDateCapture2,@Param("nomNavire") String nomNavire,@Param("tcon") qTypeConcession tcon);

}
