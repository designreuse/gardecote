package com.gardecote.web;


import com.gardecote.business.service.qLicenceService;
import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qLicenceLibre;
import com.gardecote.entities.qLicenceNational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dell on 28/11/2016.
 */
@Component
public class licenceValidatorBatExistant implements Validator {
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
 //       System.out.println("numlic"+lic.getNumlic());
//       System.out.println("ooooo;;;"+lic.getQnavire().getAnneeconstr());



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.numlic", "lic.numlic.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.dateDebutAuth", "lic.dateDebutAuth.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.nomnav", "lic.dateDebutAuth.empty");
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
     //   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.qnavire.numimm", "lic.numimm.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.port", "lic.port.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.puimot", "lic.puimot.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.radio", "lic.radio.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licence.tjb", "lic.tjb.empty");

      //  if( lic.getQtypnav()==null) errors.rejectValue("licence.qtypnav","lic.qtypnav.mistm");
        if( lic.getZone()==null) errors.rejectValue("licence.zone","lic.qtypnav.mistm");
        if( lic.getNation()==null) errors.rejectValue("licence.nation","lic.qtypnav.mistm");
        if( lic.getQcatressources()==null) errors.rejectValue("licence.zone.IdZone","lic.qcatressources.mistm");
        if( lic.getQnavire()==null) errors.rejectValue("licence.nomnav","lic.qnavire.mistm");
        if( lic.getTypb()==null) errors.rejectValue("licence.typb","lic.typb.mistm");

              if(lic instanceof qLicenceNational) {
               System.out.println("oooo");
                  System.out.println(((qLicenceNational) lic).getQconcession());
         if(((qLicenceNational) lic).getQconcession()==null) {
             System.out.println("ahhhhh");
             errors.rejectValue("licence.qconcession", "lic.qconcession.mistm");
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
