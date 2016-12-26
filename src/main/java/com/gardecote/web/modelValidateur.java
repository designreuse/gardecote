package com.gardecote.web;

import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        qPrefix pr=null;
        List<qEspeceTypee> espTypees=null;
        qModelJP jp=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");

        pr= prefService.findById(modelValidated.getPrefixPK());

        List<qEspeceTypee> newLL=new ArrayList<qEspeceTypee>();
        List<qEspeceTypee> esp=modelValidated.getEspecestypees();
        for(qEspeceTypee h:esp) {
            qEspece es=espService.findById(h.getQespeceId());
            qEspeceTypee jj=new qEspeceTypee(h.getEnumesptype(),es,null);
            newLL.add(jj);
        }
        Set<qEspeceTypee> a = new TreeSet<qEspeceTypee>(newLL);


            List<qEspeceTypee> newlst=new ArrayList<qEspeceTypee>(a);

        modelValidated.setQprefix(pr);
        modelValidated.setEspecestypees(newlst);

    }
}
