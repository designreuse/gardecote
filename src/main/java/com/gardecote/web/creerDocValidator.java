package com.gardecote.web;

import com.gardecote.business.service.qDocService;
import com.gardecote.business.service.qSeqService;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 07/12/2016.
 */
@Component
public class creerDocValidator implements Validator {
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
        frmSearchPgsForDocCrea frmValidated = (frmSearchPgsForDocCrea) o;
        Date dateDepart = null, dateRetour = null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        qSeqPK spk = new qSeqPK(frmValidated.getNumeroDebut(), frmValidated.getNumeroFin());
        qSeq se = seqService.findById(spk);
        if (se == null)
            se = new qSeq(frmValidated.getNumeroDebut(), frmValidated.getNumeroFin(), null);
        else
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDebut", "frmSearchPgsForDocCrea.invalidseq");
        qDoc documentDoublon=null;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDebut", "frmSearchPgsForDocCrea.numeroDebut.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroFin", "frmSearchPgsForDocCrea.numeroFin.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDebut", "frmSearchPgsForDocCrea.dateDebut.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateRetour", "frmSearchPgsForDocCrea.dateFin.empty");

        if(frmValidated.getDateDebut()!=null && (frmValidated.getDateRetour()!=null) && frmValidated.getNumeroDebut()!=null && frmValidated.getNumeroFin()!=null )
        {
            dateDepart = frmValidated.getDateDebut();
            dateRetour =frmValidated.getDateRetour();
           // try {
            //    dateDepart = sdfmt1.parse(frmValidated.getDateDebut());
             //   dateRetour = sdfmt1.parse(frmValidated.getDateRetour());
           // } catch (ParseException e) {
            //    e.printStackTrace();
            //}
        documentDoublon = docService.checkIfDupDocExist(dateDepart,dateRetour, se,frmValidated.getTypeDoc());
            //Nombre de jours plus que nombre de lignes.
        List<qDoc> lstDocJoursDupliquees = docService.checkIfDupJoursExist(dateDepart,  dateRetour, se,frmValidated.getTypeDoc());
        List<qDoc> lstDocPagesDupliquees = docService.checkIfDupPagesExist(dateDepart,  dateRetour, se,frmValidated.getTypeDoc());
        qModelJP modelEncours = docService.checkIfModelExist(dateDepart,  dateRetour, se,frmValidated.getTypeDoc());
        Boolean flag=docService.checkNombreJoursMoinsNombreLignes(dateDepart,dateRetour, se,frmValidated.getTypeDoc());
        System.out.println(lstDocJoursDupliquees);
            System.out.println(lstDocPagesDupliquees);
    //    if (documentDoublon == null || lstDocJoursDupliquees == null || lstDocPagesDupliquees == null && modelEncours != null && flag==true)
            if (documentDoublon != null)
                errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.doublon");
            if (lstDocJoursDupliquees.size() != 0)
                errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.joursdup");
            if (lstDocPagesDupliquees.size() != 0)
                errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.pagesdup");
            if (modelEncours == null && (!frmValidated.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)))
                errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.modeinnexistant");
            if(flag==false && (!frmValidated.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)))
                errors.rejectValue("numeroDebut", "frmSearchPgsForDocCrea.lignesmoinsquejours");
        }

    }
}
