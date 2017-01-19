package com.gardecote.web;

import com.gardecote.business.service.qPrefixService;
import com.gardecote.business.service.qTypeConcessionService;
import com.gardecote.entities.qPrefix;
import com.gardecote.entities.qPrefixPK;
import com.gardecote.entities.qTypeConcession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dell on 14/01/2017.
 */

@Component
public class attrTypeConcession implements Validator {
    @Autowired
    qTypeConcessionService typeConcessionService;
    @Autowired
    qPrefixService prefService;
    @Override
    public boolean supports(Class<?> aClass) {
       return typeConcessionForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        typeConcessionForm typeCon = (typeConcessionForm) o;

        qPrefixPK pr = null;
        qTypeConcession typConDoublon = null;
        System.out.println("prefix lllllll");
        System.out.println(typeCon.getTypeConcession().getPrefixNum());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeConcession.qtypeconcessionpk", "qtypeconcession.identempty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeConcession.designation", "qtypeconcession.designempty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeConcession.typeDoc", "qtypeconcession.typdocempty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeConcession.prefixNum", "qtypeconcession.prefempty");


        if (typeCon.getTypeConcession().getQtypeconcessionpk() != null)
        {
            typConDoublon = typeConcessionService.findById(typeCon.getTypeConcession().getQtypeconcessionpk());
            if(typConDoublon!=null)    errors.rejectValue("typeConcession.qtypeconcessionpk", "qtypeconcession.doublon");
        }
        if(typeCon.getTypeConcession().getPrefixNum()!=null && typeCon.getTypeConcession().getTypeDoc()!=null ) {
            pr = new qPrefixPK(typeCon.getTypeConcession().getPrefixNum(), typeCon.getTypeConcession().getTypeDoc());
            qPrefix prefixCurrent = prefService.findById(pr);
            if (prefixCurrent == null) errors.rejectValue("typeConcession.prefixNum", "qtypeconcession.prefixnotfound");
            else typeCon.getTypeConcession().setPrefix(prefService.findById(pr));
        }

    }
}
