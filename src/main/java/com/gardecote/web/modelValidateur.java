package com.gardecote.web;

import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dell on 07/12/2016.
 */
@Component
public class modelValidateur implements Validator {
    @Autowired
    qModelJPService modelService;
    @Autowired
    qEspeceService espService;
    @Autowired
    qEspeceTypeeService esptypeeService;

    @Autowired
    qSeqService seqService;
    @Autowired
    private qPrefixService prefService;

    @Override
    public boolean supports(Class<?> aClass) {

        return qModelJP.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        qModelJP modelValidated = (qModelJP) o;
        Date dateDepart = null, dateRetour = null;
        qEspeceTypee jj=null;
        qPrefix pr=null;
        List<qEspeceTypee> espTypees=null;
        qModelJP jp=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");

        pr= prefService.findById(modelValidated.getPrefixPK());
        List<qEspeceTypee> oldEspTypees=modelService.findEspTypees(pr.getPrefix());
        List<qEspeceTypee> newLL=new ArrayList<qEspeceTypee>();
        List<qEspeceTypee> esp=modelValidated.getEspecestypees();

        for(qEspeceTypee h:esp) {

            if (h.getTypeesptypee().equals(enumTypeEspTypee.DYNAMIC))
                jj = new qEspeceTypee(h.getEnumesptype(), null, null, h.getNumOrdre(), pr.getPrefix(), h.getTypeesptypee());
            else {
                qEspece es = espService.findById(h.getQespeceId());
                jj = new qEspeceTypee(h.getEnumesptype(), es, null, h.getNumOrdre(), pr.getPrefix(), h.getTypeesptypee());
                 }
            newLL.add(jj);
        }
        Set<qEspeceTypee> a = new TreeSet<qEspeceTypee>(newLL);
        Set<qEspeceTypee> b = new TreeSet<qEspeceTypee>(oldEspTypees);

        List<qEspeceTypee> newlst=new ArrayList<qEspeceTypee>(a);
        b.removeAll(a);

        modelValidated.setQprefix(pr);
        modelValidated.setEspecestypees(newlst);

    }
}
