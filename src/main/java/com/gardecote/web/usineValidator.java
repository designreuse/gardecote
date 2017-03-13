package com.gardecote.web;

import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.business.service.qUsineService;
import com.gardecote.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 27/12/2016.
 */
@Component
public class usineValidator implements Validator {

    @Autowired
    qUsineService usineService;
    @Autowired
    qRegistreNavireService navireService;

    @Override
    public boolean supports(Class<?> aClass) {
        return qUsine.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        creationUsineForm usine = (creationUsineForm) o;

        qUsine usinedoublon=usineService.findById(((qUsine) ((creationUsineForm) o).getCurrentusine()).getRefAgrement());
        if(usinedoublon!=null)   errors.rejectValue("refAgrement", "usine.refAgrement.doublon");

           qBateau navireDetected=navireService.findById(((qUsine) ((creationUsineForm) o).getCurrentusine()).getRefAgrement());

        if(navireDetected!=null)
        errors.rejectValue("refAgrement", "usine.refAgrement.doublonNumimm");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.refAgrement", "usine.refAgrement.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.refAgrementUE", "usine.refAgrementUE.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.capaciteStockage", "usine.capaciteStockage.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.capaciteCongelation", "usine.capaciteCongelation.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.nomUsine", "usine.nomUsine.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.address", "usine.address.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.nomResp", "usine.nomResp.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentusine.lieuImplementation", "usine.lieuImplementation.empty");

    }
}
