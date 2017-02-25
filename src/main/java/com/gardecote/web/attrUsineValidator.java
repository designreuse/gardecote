package com.gardecote.web;

import com.gardecote.business.service.qCarnetService;
import com.gardecote.business.service.qPrefixService;
import com.gardecote.entities.qCarnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dell on 28/12/2016.
 */
@Component
public class attrUsineValidator implements Validator {
    @Autowired
    private qPrefixService prefService;
    @Autowired
    private qCarnetService carnetService;

    @Override
    public boolean supports(Class<?> aClass) {
        return qCarnet.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        qCarnet crn = (qCarnet) o;
        qCarnet carnetDoublon=carnetService.findById(crn.getCarnetPK());

        boolean priznak=carnetService.checkIfNumeroDebutValable(((qCarnet) o).getNumeroDebutPage());
        if(carnetDoublon!=null)    errors.rejectValue("carnetSelected.numeroDebutPage","carnetSelected.qprefix.doublon");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "carnetSelected.numeroDebutPage", "carnetSelected.qprefix.empty");

        if(priznak==false)  errors.rejectValue("numeroDebutPage","carnetSelected.numeroDebut.invalide");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "carnetSelected.numeroDebutPage", "carnetSelected.numeroDebutPage.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "carnetSelected.nbrPages", "carnetSelected.nbrPages.empty");
        if(crn.getQusine()==null)  errors.rejectValue("carnetSelected.numeroDebutPage","carnetSelected.qusine.invalid");
        if(crn.getQnavire()!=null)  errors.rejectValue("carnetSelected.numeroDebutPage","carnetSelected.qnavire.notnull");
    }
}
