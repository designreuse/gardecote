package com.gardecote.web;


import com.gardecote.business.service.qLicenceService;
import com.gardecote.business.service.qRegistreNavireService;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qLicenceLibre;
import com.gardecote.entities.qLicenceNational;
import com.gardecote.entities.qNavire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dell on 28/11/2016.
 */
@Component
public class licenceValidator implements Validator {
    @Autowired
    qLicenceService licService;
    @Autowired
    qRegistreNavireService regnavService;

    @Override
    public boolean supports(Class<?> aClass) {
      //  return lic.class.equals(aClass);
        return qLic.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

       qLic lic = (qLic) o;
        System.out.println("numlic"+lic.getNumlic());
       System.out.println("ooooo;;;"+lic.getQnavire().getAnneeconstr());
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

          if(lic instanceof qLicenceNational) {
         if(((qLicenceNational) lic).getQconcession()==null)
             errors.rejectValue("licence.qconcession","lic.qconcession.mistm");
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
