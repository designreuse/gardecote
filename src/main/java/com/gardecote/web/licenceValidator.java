package com.gardecote.web;


import com.gardecote.business.service.qConcessionService;
import com.gardecote.business.service.qLicenceService;
import com.gardecote.business.service.qRegistreNavireService;

import com.gardecote.business.service.qTypeConcessionService;
import com.gardecote.entities.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.*;

/**
 * Created by Dell on 28/11/2016.
 */
@Component
public class licenceValidator implements Validator {
    @Autowired
    private qLicenceService licService;
    @Autowired
    private qRegistreNavireService regnavService;
    @Autowired
    private qConcessionService concessionService;
    @Autowired
    private qLicenceService licenceService;
    @Autowired
    private qTypeConcessionService typeconcessionService;

    @Override
    public boolean supports(Class<?> aClass) {
      //  return lic.class.equals(aClass);
        return qLic.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        qLic lic = (qLic) o;
        String stringincomp="";
        List<qCategRessource> lstConcess=null,lstLic=null;
        Set<qCategRessource> a=null,b=null,e=null;

        lstConcess=new ArrayList<qCategRessource>();
        lstLic=new ArrayList<qCategRessource>();

        qLic licenceDoublon=licenceService.findById(lic.getNumlic());
        qNavireLegale navireDoublon=regnavService.findLegalById(lic.getNumimm());
        if(licenceDoublon!=null)
            errors.rejectValue("licence.numlic", "lic.numlic.doublon");
        else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numlic", "lic.numlic.empty");
             }
        if(navireDoublon!=null) {
            errors.rejectValue("licence.numimm", "lic.numimm.doublon");
        }
        else
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numimm", "lic.numlic.empty");

        if((lic.getDateDebutAuth()!=null) && (lic.getDateFinAuth()!=null)) {
            if (lic.getDateDebutAuth().after(lic.getDateFinAuth()))
                errors.rejectValue("licence.dateDebutAuth", "lic.dateDebutAuth.debutafterfin");
        }

        if(lic.getQcatressources()==null)
       {
        errors.rejectValue("licence.numlic", "licence.qconcession");
      }
        if (lic instanceof qLicenceNational ) {
            if(((qLicenceNational) lic).getQconcession()==null ) {
                errors.rejectValue("licence.qconcession.refConcession","licence.qconcession");
//                System.out.println("concession Null :"+((qLicenceNational) lic).getQconcession());
            }
            if(((qLicenceNational) lic).getQconcession()==null ) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qconcession.refConcession", "licence.qconcession");
                if( lic.getQcatressources()==null) errors.rejectValue("licence.qconcession","lic.qcatressources.mistm");
            }
            if( ((qLicenceNational) lic).getQconcession().getCategoriesRessources()!=null && ((qLicenceNational) lic).getQcatressources()!=null) {
                lstConcess= new ArrayList<qCategRessource>(((qLicenceNational) lic).getQconcession().getCategoriesRessources().size());
                lstLic=new ArrayList<qCategRessource>(((qLicenceNational) lic).getQcatressources().size());
                for(qCategRessource qq:((qLicenceNational) lic).getQconcession().getCategoriesRessources()){
                    lstConcess.add(new qCategRessource(qq.getTypeconcessionConcernee(),qq.getTypeSupport(),qq.getQlicences(),qq.getEngins(),qq.getAncienCategoriePeche()));
                }
                for(qCategRessource qq:((qLicenceNational) lic).getQcatressources()){
                    lstLic.add(new qCategRessource(qq.getTypeconcessionConcernee(),qq.getTypeSupport(),qq.getQlicences(),qq.getEngins(),qq.getAncienCategoriePeche()));

                }
                lstLic.removeAll(lstConcess);
            }
            System.out.println(stringincomp);
            if (lstLic.size()>0) errors.rejectValue("licence.qconcession.refConcession", "lic.numlic.categoriesincompatible");

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numlic", "lic.numlic.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.dateDebutAuth", "lic.dateDebutAuth.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.dateFinAuth", "lic.dateFinAuth.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.anneeconstr", "lic.anneeconstr.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.balise", "lic.balise.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.calpoids", "lic.calpoids.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.eff", "lic.eff.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.gt", "lic.gt.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.imo", "lic.imo.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.kw", "lic.kw.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.larg", "lic.larg.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.longg", "lic.longg.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.nbrhomm", "lic.nbrhomm.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.nomar", "lic.nomar.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.port", "lic.port.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.puimot", "lic.puimot.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.radio", "lic.radio.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.tjb", "lic.tjb.empty");

        if( lic.getTypb()==null) errors.rejectValue("licence.typb","lic.qtypnav.mistm");
        if( lic.getZone()==null) errors.rejectValue("licence.zone","lic.qtypnav.mistm");
        if( lic.getNation()==null) errors.rejectValue("licence.nation","lic.qtypnav.mistm");



   if( lic.getTypb()==null) errors.rejectValue("licence.typb","lic.typb.mistm");


           if(lic instanceof qLicenceLibre) {
               if(((qLicenceLibre) lic).getAccord()==null)
                   errors.rejectValue("licence.accord","lic.typencad.mistm");
             }
//       lic  qlc=licService.findById(lic.getNumlic());
  //     qNavire nav=regnavService.findById(lic.getQnavire().getNumimm());
  //      if(qlc!=null) errors.rejectValue("numlic", "doublon de licence");
    //    if(nav!=null) errors.rejectValue("qnavire", "Bâteau existant");
    }
}
