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
    qLicenceService licService;
    @Autowired
    qRegistreNavireService regnavService;
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
        List<qTypeConcession> lstTypesOrg=null,lstTypesNew=null;
        Set<qTypeConcession> a=null,b=null,e=null;

        lstTypesOrg=new ArrayList<qTypeConcession>();
        lstTypesNew=new ArrayList<qTypeConcession>();


 //    System.out.println("numlic"+lic.getNumlic());
//       System.out.println("ooooo;;;"+lic.getQnavire().getAnneeconstr());
        if(lic.getQnavire()==null)
       lic.setNomar(lic.getQnavire().getNomar());
       lic.getQnavire().setCount(lic.getCount());
       lic.setLongg(lic.getQnavire().getLongg());
       lic.setPort(lic.getQnavire().getPort());
       lic.getQnavire().setNation(lic.getNation());
       lic.setNomar(lic.getQnavire().getNomar());
       lic.setImo(lic.getQnavire().getImo());
       lic.setCalpoids(lic.getQnavire().getCalpoids());
       lic.getQnavire().setEff(lic.getEff());
       lic.setAnneeconstr(lic.getQnavire().getAnneeconstr());
       lic.setPuimot(lic.getQnavire().getPuimot());
       lic.setLarg(lic.getQnavire().getLarg());
       lic.setRadio(lic.getQnavire().getRadio());
       lic.setNomnav(lic.getQnavire().getNomnav());
       lic.getQnavire().setNbrhomm(lic.getNbrhomm());
       lic.setBalise(lic.getQnavire().getBalise());
       lic.setCalpoids(lic.getQnavire().getCalpoids());
     //   lic.setTypb(lic.getQnavire().getTypb());
       lic.getQnavire().setTypb( lic.getTypb());
        qLic licenceDoublon=licenceService.findById(lic.getNumlic());
        if(licenceDoublon!=null)
            errors.rejectValue("licence.numlic", "lic.numlic.doublon");

        else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numlic", "lic.numlic.empty");
        }
        if((lic.getDateDebutAuth()!=null) && (lic.getDateFinAuth()!=null)) {
            if (lic.getDateDebutAuth().after(lic.getDateFinAuth()))
                errors.rejectValue("licence.dateDebutAuth", "lic.dateDebutAuth.debutafterfin");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numlic", "lic.numlic.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.dateDebutAuth", "lic.dateDebutAuth.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.dateFinAuth", "lic.dateFinAuth.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.anneeconstr", "lic.anneeconstr.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.balise", "lic.balise.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.calpoids", "lic.calpoids.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.eff", "lic.eff.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.gt", "lic.gt.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.imo", "lic.imo.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.kw", "lic.kw.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.larg", "lic.larg.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.longg", "lic.longg.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.nbrhomm", "lic.nbrhomm.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.nomar", "lic.nomar.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.numimm", "lic.numimm.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.port", "lic.port.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.puimot", "lic.puimot.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.radio", "lic.radio.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.tjb", "lic.tjb.empty");

      //  if( lic.getQtypnav()==null) errors.rejectValue("licence.qtypnav","lic.qtypnav.mistm");
        if( lic.getZone()==null) errors.rejectValue("licence.zone","lic.qtypnav.mistm");
        if( lic.getNation()==null) errors.rejectValue("licence.nation","lic.qtypnav.mistm");
        if( lic.getQcatressources()==null) errors.rejectValue("licence.qcatressources","lic.qcatressources.mistm");
        if( lic.getQnavire()==null) errors.rejectValue("licence.qnavire","lic.qnavire.mistm");

        if( lic.getTypb()==null) errors.rejectValue("licence.typb","lic.typb.mistm");
//      System.out.println(((qLicenceNational) lic).getQconcession());
              if(lic instanceof qLicenceNational) {
                  qConcession concessionActif=concessionService.findById(((qLicenceNational) lic).getQconcession().getRefConcession());
               System.out.println("oooo");

         if(concessionActif==null) {
             System.out.println("ahhhhh");
             errors.rejectValue("licence.qconcession", "lic.qconcession.mistm");
         }
                  else {
             //
           //  lstTypesOrg=concessionActif.getCategoriesRessources();//concessionService.findAll();
             for(qCategRessource catres1:concessionActif.getCategoriesRessources()) {
                 lstTypesOrg.add(catres1.getTypeconcessionConcernee());
             }
             for(qCategRessource catres:lic.getQcatressources()) {
                 lstTypesNew.add(catres.getTypeconcessionConcernee());
             }
             a = new TreeSet<qTypeConcession>(lstTypesOrg);
             b = new TreeSet<qTypeConcession>(lstTypesNew);
             e = new TreeSet<qTypeConcession>(b);
             e.removeAll(a);
             if(e.size()!=0) {
                 String str=StringUtils.join(a, ',');
                 System.out.println("orroooo"+e.size());
                 String[] toppings=new String[1] ;
                 toppings[0]=str;
                 errors.rejectValue("licence.qconcession", "lic.numlic.categoriesincompatible" , new Object[]{str}, "");

             }
             //

         }
               }
           if(lic instanceof qLicenceLibre) {
               if(((qLicenceLibre) lic).getTypencad()==null)
                   errors.rejectValue("licence.typencad","lic.typencad.mistm");
             }
//       lic  qlc=licService.findById(lic.getNumlic());
  //     qNavire nav=regnavService.findById(lic.getQnavire().getNumimm());
  //      if(qlc!=null) errors.rejectValue("numlic", "doublon de licence");
    //    if(nav!=null) errors.rejectValue("qnavire", "Bâteau existant");
    }
}
