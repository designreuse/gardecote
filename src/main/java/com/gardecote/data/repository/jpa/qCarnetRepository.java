package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qCarnet;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qCarnetPK;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import com.gardecote.entities.qPageCarnet;
import java.util.List;
/**
 * Repository : Authprov.
 */


    /**
     * Repository : Bateaucomplet.
     */

  public  interface qCarnetRepository extends PagingAndSortingRepository<qCarnet, qCarnetPK> {

        @Query("select qp.qcarnet from qPageCarnet qp where qp.numeroPage=:numeroDebut")
        public qCarnet retCarnet(@Param("numeroDebut") String  numeroDebut);

        @Query("select cp from qPageCarnet cp where cp.qcarnet = :carnetDebut and cp.numeroOrdrePage>=:debutNumberL and cp.numeroOrdrePage<=:finNumberL")
        public List<qPageCarnet> retPages(@Param("carnetDebut") qCarnet  carnetDebut,@Param("debutNumberL") long  debutNumberL ,@Param("finNumberL") long  finNumberL);


    }

