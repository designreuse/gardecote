package com.gardecote.data.repository.jpa;
import com.gardecote.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;
/**
 * Repository : Authprov.
 */
    /**
     * Repository : Bateaucomplet.
     */
  public  interface qCarnetRepository extends PagingAndSortingRepository<qCarnet, qCarnetPK> {
        @Query("select qp.qcarnet from qPageCarnet qp where qp.numeroPage=:numeroDebut and qp.typeDoc= :typeDoc")
        public qCarnet retCarnet(@Param("numeroDebut") String  numeroDebut,@Param("typeDoc") enumTypeDoc typeDoc);
        @Query("select cp from qPageCarnet cp where cp.qcarnet = :carnetDebut and cp.numeroOrdrePage>=:debutNumberL and cp.numeroOrdrePage<=:finNumberL")
        public List<qPageCarnet> retPages(@Param("carnetDebut") qCarnet  carnetDebut,@Param("debutNumberL") long  debutNumberL ,@Param("finNumberL") long  finNumberL);
        @Query("select cp.numeroOrdrePage from qPageCarnet cp where cp.numeroPage = :nump and cp.typeDoc=:typeDoc")
        public Long retNumOrdre(@Param("nump") String  nump,@Param("typeDoc") enumTypeDoc  typeDoc);
        @Query("select cp.numeroOrdrePage from qPageCarnet cp where cp.numeroPage = :str ")
        public String check(@Param("str") String  str);

    }
