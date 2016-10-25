package com.gardecote.entities;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Date;
/**
 * Created by Dell on 25/10/2016.
 */
public class qTraitement extends qDoc {


    qCategRessource qcategconcernee;
    private  List<enumSegPeche> segs;
    private qUsine qusine;
    private List<qQuantiteExportee> qQteExp;
    private Long qteDechu;
    private Date dateTraitement;
    @OneToMany
    private List<qPageTraitement> pagesTraitement;

}
