package com.gardecote.web;

import com.gardecote.business.service.qDocService;
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
 * Created by Dell on 24/12/2016.
 */
@Component
public class creerAnnexeValidateur implements Validator {
    @Autowired
    qDocService docService;
    @Override
    public boolean supports(Class<?> aClass) {
       return frmAnnexe.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        frmAnnexe frmValidated = (frmAnnexe) o;
        Date depart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            depart = sdfmt1.parse(frmValidated.getDateDepartAnnexe());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(frmValidated.getNumImmAnnexe()+"!"+frmValidated.getDateDepartAnnexe());
        qDocPK dpk=new qDocPK(frmValidated.getNumImmAnnexe(), depart);
        qDoc currentDoc=docService.findById(dpk);
        qSeqPK spk = new qSeqPK(frmValidated.getNumeroDebutAnnexe(), frmValidated.getNumeroFinAnnexe());
        boolean isSameNav=docService.checkIfNavAnnexIsSameAsNavPrincipal(depart,spk,(qMarree) currentDoc,enumTypeDoc.Journal_Annexe);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDebutAnnexe", "frmAnnexe.invalidnumero");
        qMarreeAnnexe documentDoublonAnnexe=null;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroFinAnnexe", "frmAnnexe.invalidnumero");

        if( frmValidated.getNumeroDebutAnnexe()!=null && frmValidated.getNumeroFinAnnexe()!=null ) {
            documentDoublonAnnexe = docService.checkIfDupAnnexeExist(depart, spk, frmValidated.getTypeDocAnnexe());
            //Nombre de jours plus que nombre de lignes.
            List<qMarreeAnnexe> lstDocPagesDupliquees = docService.checkIfDupPagesAnnexeExist(depart, spk, frmValidated.getTypeDocAnnexe());
            if (documentDoublonAnnexe != null)
                errors.rejectValue("numeroDebutAnnexe", "frmAnnexe.doublon");
            if (lstDocPagesDupliquees.size() != 0)
                errors.rejectValue("numeroDebutAnnexe", "frmAnnexe.pagesdup");
            if(isSameNav==false)  errors.rejectValue("numeroDebutAnnexe", "frmAnnexe.isnotsame");
        }
    }
}