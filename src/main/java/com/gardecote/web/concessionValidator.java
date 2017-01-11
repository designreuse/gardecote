package com.gardecote.web;

import com.gardecote.business.service.qConcessionService;
import com.gardecote.entities.qConcession;
import org.joda.time.*;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dell on 10/01/2017.
 */
@Component
public class concessionValidator implements Validator {
    @Autowired
    private qConcessionService concessionService;
    @Override
    public boolean supports(Class<?> aClass) {
      return  qConcession.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        qConcession currentConcession = (qConcession) o;
        qConcession concessionDoublon = concessionService.findById(currentConcession.getRefConcession());
        int nbrDays=0;
        Calendar cal2 = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        if (currentConcession.getCategoriesRessources()==null)
            errors.rejectValue("refConcession", "concession.categorievide");
        if (currentConcession.getDateDebut() != null && currentConcession.getDateFin() != null)
        { cal.setTime(currentConcession.getDateDebut());

        cal2.setTime(currentConcession.getDateFin());
           nbrDays=Days.daysBetween(new DateTime(cal.getTime()),new DateTime(cal2.getTime())).getDays();

            if (currentConcession.getDateFin().before(currentConcession.getDateDebut()))
                errors.rejectValue("dateDebut", "concession.debutAfterFin");

            if (currentConcession.getDateDebut().before(currentConcession.getDateConcession()))
                errors.rejectValue("dateDebut", "concession.debutAfterFin");
        }








        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDebut", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateFin", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateConcession", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "refConcession", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qconsignataire.nomconsignataire", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quotaEnTonne", "concession.champvide");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dureeConcession", "concession.champvide");

        if(concessionDoublon!=null)    errors.rejectValue("refConcession","concession.doublon");



    }
}
