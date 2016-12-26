package com.gardecote.web;

import com.gardecote.business.service.qDocService;
import com.gardecote.business.service.qSeqService;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qModelJP;
import com.gardecote.entities.qSeq;
import com.gardecote.entities.qSeqPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 07/12/2016.
 */
@Component
public class deleteDocValidator implements Validator {
    @Autowired
    qDocService docService;

    @Autowired
    qSeqService seqService;

    @Override
    public boolean supports(Class<?> aClass) {

        return frmSearchPgsForDocCrea.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        qDoc docToBeDelete= (qDoc) o;
        Date dateDepart = null, dateRetour = null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");




        if(!docToBeDelete.isBloquerDeletion())
        {
            errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.deleteImpossible");


        }
        else
            docService.delete(docToBeDelete.getqDocPK());

    }
}
