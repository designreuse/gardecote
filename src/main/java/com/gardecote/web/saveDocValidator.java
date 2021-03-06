package com.gardecote.web;

import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import org.jgroups.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Dell on 31/12/2016.
 */
@Component

public class saveDocValidator  implements Validator {
    @Autowired
    private qTraitementService traitementService;
    @Autowired
    private qEspeceService espService;

    @Autowired
    private qSeqService seqService;
    @Autowired
    private qPageCarnetService pcService;

    @Override
    public boolean supports(Class<?> aClass) {
        return qTraitement.class.isAssignableFrom(aClass);
    }

    @Override

    public void validate(Object o, Errors errors) {
   // frmSearchPgsForDocCrea docSaved = (frmSearchPgsForDocCrea) o;
     //   qDoc currentSavedDoc=((frmSearchPgsForDocCrea) o).getCreateDocFormm().getCurrentDoc();

               traitementService.save((qTraitement) o);
         //   docService.save((qTraitement)((frmSearchPgsForDocCrea) o).getCreateDocFormm().getCurrentDoc());
            for(qPageTraitement pagetr: ((qTraitement) (o)).getPagesTraitement()) {
                for(qOpTraitement optr:pagetr.getOpTraitements()) {
                    if(optr.getEsp()!=null) {
                        if(!optr.getEsp().getNomFr().isEmpty()) {
                            // rechecher l'espece
                            qEspece currentEsp = espService.findById(optr.getEsp().getCodeEsp());
                            if (currentEsp==null) {
                                qEspece especeToCreate=new qEspece(String.valueOf(UUID.randomUUID()),optr.getEsp().getNomFr(),optr.getEsp().getNomFr(),0);
                                espService.create(especeToCreate);
                                optr.setEsp(especeToCreate);

                            }
                            else   optr.setEsp(currentEsp);
                        }
                    }
                    else {
                        optr.setEsp(null);
                    }
                }
                 pcService.save(pagetr);
            }


    }
}
