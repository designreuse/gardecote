package com.gardecote.controllers;

import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import com.gardecote.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Dell on 12/11/2016.
 */
@Controller

@SessionAttributes(types = {CreateDocForm.class,lstBateauAchoisirForm.class,creationLicForm.class,attributionCarnetForm.class})

@RequestMapping("/")
public class LicenceController {
  //  licenceValidatorBatExistant

    @Autowired
    MessageByLocaleService messageByLocaleService;
    @Autowired
    licenceValidator licValidator;
    @Autowired
    attributionValidator attrValidator;


    @Autowired
    private qPrefixService prefService;
    @Autowired
    private qCarnetService carnetService;
    @Autowired
    private qConcessionService concessionService;
    @Autowired
    private qConsignataireService consignataireService;
    @Autowired
    private qEnginsLicenceService enginsLicenceService;
    @Autowired
    private qEnginPecheMarService enginsPechMarService;
    @Autowired
    private qEnginPecheDebarService enginsPecheDebarService;


    @Autowired
    private qEspeceService especesService;
    @Autowired
    private qJourMereService jourMereService;
    @Autowired
    private qLicenceService licenceService;
    @Autowired
    private qModelJPService modeljpService;
    @Autowired
    private qPageCarnetService pagecarnetService;

    @Autowired
    private qTypeConcessionService typeconcessionService;
    @Autowired
    private qZoneService zoneService;

    @Autowired
    private qCategRessourceService categService;
    @Autowired
    private qDocService docService;
    @Autowired
    private qEspeceTypeeService especetypeeService;
    @Autowired
    private qEspeceService especeService;
    @Autowired
    private qJourDebService   jourdebService;
    @Autowired
    private qNationService  nationService;

    @Autowired
    private qRegistreNavireService registrenavireService;
    @Autowired
    private qSeqService seqService;
    @Autowired
    private qTypeLicService typelicService;
    @Autowired
    private qTypeNavService typenavService;

    @Autowired
    private LicenceAc ourLic;

    public licenceValidator getLicValidator() {
        return licValidator;
    }

    public void setLicValidator(licenceValidator licValidator) {
        this.licValidator = licValidator;
    }
// pour les liste deroulantes.

    @ModelAttribute("allTypesEncad")
    public List<qTypeEnc> populateTypeEnc() {
        return Arrays.asList(qTypeEnc.values());
    }

    @ModelAttribute("allConcessions")
    public List<qConcession> populateConcessions() {
        return concessionService.findAll();
    }


    @ModelAttribute("allPrefixes")
    public List<qPrefix> getPrefixes() {
        return prefService.findAll();
    }
    @ModelAttribute("typeDocs")
    public List<enumTypeDoc> getTypesDoc() {
        return Arrays.asList(enumTypeDoc.values());

    }


    @ModelAttribute("allTypesBat")
    public List<enumTypeBat> populateTypeNav() {
        return Arrays.asList(enumTypeBat.values());
    }
    @ModelAttribute("allZones")
    public List<qZone> populateZones() {
        return this.zoneService.findAll();
    }
    @ModelAttribute("allNations")
    public List<qNation> populateNations() {
        return this.nationService.findAll();
    }
    @ModelAttribute("allCategoriesConcessions1")
    public List<qCategRessource> populateCategories() {
        System.out.println("fffff");
        return this.categService.findAll();
    }
    @ModelAttribute("allTypesConcession1")
    public List<qTypeConcession> populateTypesConcession() {
        return this.typeconcessionService.findAll();
    }
    // pour les traitement utilisateurs
    @RequestMapping(value="/listBatASelectionner",method = RequestMethod.POST)
    public String listBatASelectionner(final lstBateauAchoisirForm batForm , final ModelMap model){
       return "redirect:listBatASelectionner?pag=0&terme="+batForm.getSearchName().toString();
    }

    @RequestMapping(value="/listBatASelectionner",method = RequestMethod.GET)
    public String listBatASelectionner1(final lstBateauAchoisirForm batForm ,final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="terme",defaultValue = "") String terme){
        lstBateauAchoisirForm lstBat=new lstBateauAchoisirForm();
        int[] pages;
        Page<qNavire>   pgBat=registrenavireService.findAll(page,20,terme);
        pages=new int[pgBat.getTotalPages()];
        for(int i=0;i<pgBat.getTotalPages();i++) pages[i]=i;
        lstBat.setLstBat(pgBat);
        lstBat.setPageCount(pgBat.getTotalPages());
        lstBat.setNumPages(pages);
        lstBat.setPageCourante(page);
        lstBat.setSearchName(terme);
        model.addAttribute("lstBat",lstBat);
         return "qlistBat";
    }


    @RequestMapping(value="/NouvLicenceBatExistant", params={"addLicenceNational"},method = RequestMethod.POST)
    public String validatethenadd(final  @Valid  @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model,SessionStatus session) {

        String validateURL=null;

        qLic licAct=LicForm.getLicence();
     //   if (concessionService.validate(((qLicenceNational)licAct).getQconcession().getRefConcession()) == false)
      //  {
        //    LicForm.setRefMessage("concession invalide");
            System.out.println(LicForm.getLicence().getQnavire());
          //  return "qshowNewLicNational";
       // }
        licValidator.validate(licAct, bindingresult);
        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            model.addAttribute("LicForm",LicForm);
            validateURL="qshowNewLicNational";
        }
        else        {

            licenceService.create(LicForm.getLicence());
        //

            model.clear();
            validateURL = "redirect:afficherLstLicence";
       }
        return validateURL;
    }
    @RequestMapping(value="/NouvLicenceBatExistant", params={"addLicenceLibre"},method = RequestMethod.POST)
    public String validatelibre(final  @Valid @ModelAttribute("LicForm")  creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {

        String validateURL=null;
        qLic licAct=LicForm.getLicence();
        licValidator.validate(licAct, bindingresult);
        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            model.addAttribute("LicForm",LicForm);
            validateURL="qshowNewLicLibre";
        }
        else        {

            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
        }
        return validateURL;
    }
    @RequestMapping(value="/NouvLicenceBatExistant", params={"addRow"},method = RequestMethod.POST)
    public String addRowLBNB(final @ModelAttribute("LicForm") creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String url=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();

        if (LicForm.getLicence().getQcatressources() == null) {
            ens.add(new qCategRessource());
            LicForm.getLicence().setQcatressources(ens);
        } else LicForm.getLicence().getQcatressources().add(new qCategRessource());

        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNational";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibre";
            else   System.out.println("jjjj");
        }
        return url;
    }
    @RequestMapping(value="/afficherLstLicence", method = RequestMethod.GET)
    public String afficherListLicence(final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page) {
        lstLicForm lst1=new lstLicForm();
        int[] pages;
        Page<qLic>   pgLic=licenceService.findAll(page,20);

        pages=new int[pgLic.getTotalPages()];
        for(int i=0;i<pgLic.getTotalPages();i++) pages[i]=i;
        lst1.setLicences(pgLic);
        lst1.setPageCount(pgLic.getTotalPages());
        lst1.setNumPages(pages);
        lst1.setPageCourante(page);
        System.out.println(" Total est : "+lst1.getLicences().getTotalElements());
        model.addAttribute("lstLicForm",lst1);
        return "qlistLic";


    }

    @RequestMapping(value="/getTypeDocPrefixesValues",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getTypeDocPrefixesValues(@RequestParam("carnetSelected.typeDoc") String typeDoc) {
        Map<String, String> prefixValues = new HashMap<>();
        List<enumPrefix> prefixes=null;
    if(typeDoc==enumTypeDoc.Fiche_Debarquement.toString()) {
    prefixValues.put(String.valueOf(enumTypeDoc.Fiche_Debarquement), String.valueOf(enumPrefix.PA));
    prefixValues.put(String.valueOf(enumTypeDoc.Fiche_Debarquement), String.valueOf(enumPrefix.PC));
     }
   if(typeDoc==enumTypeDoc.Fiche_Debarquement.toString()) {
      prefixValues.put(String.valueOf(enumTypeDoc.Journal_Peche), String.valueOf(enumPrefix.PE));
      prefixValues.put(String.valueOf(enumTypeDoc.Journal_Peche), String.valueOf(enumPrefix.CEPH));
      prefixValues.put(String.valueOf(enumTypeDoc.Journal_Peche), String.valueOf(enumPrefix.CRUS));
      prefixValues.put(String.valueOf(enumTypeDoc.Journal_Peche), String.valueOf(enumPrefix.DEM));
  }


        return prefixValues;
    }




       @RequestMapping(value="/attributionCarnet",method = RequestMethod.POST)
        public String attribuerCarnets(@ModelAttribute(value="CarnetAttribue") attributionCarnetForm CarnetAttribue , final ModelMap model, final BindingResult bindingresult) {
           String urlNav=null;
           System.out.println(CarnetAttribue);
           qCarnet currentCarnet=new qCarnet(CarnetAttribue.getCarnetSelected().getQprefix(),CarnetAttribue.getCarnetSelected().getNumeroDebutPage(),CarnetAttribue.getCarnetSelected().getNbrPages());

           attrValidator.validate(currentCarnet,bindingresult);
           if(!bindingresult.hasErrors()) {

               qCarnet crn = carnetService.entrerDansLeSystem(currentCarnet);
               carnetService.attribuerCarnetAuNavire(crn, CarnetAttribue.getNavireSelected(), CarnetAttribue.getLicenceSelected(), null);
               CarnetAttribue.setMessage("success");
           }
           else {
               CarnetAttribue.setMessage("echec");
           }
           return "carnets/attribution";
        }

    @RequestMapping(value="/listCarnets",method = RequestMethod.GET)
    public String  listCarnets(final lstCarnetsAchoisirForm carnetForm ,final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="searchCarnet",defaultValue = "") String searchCarnet) {
        int[] pages;
        lstCarnetsAchoisirForm lstCarnet=new lstCarnetsAchoisirForm();
        Page<qCarnet>  pgCarnet=carnetService.findAll(page,20);

        pages=new int[pgCarnet.getTotalPages()];
        for(int i=0;i<pgCarnet.getTotalPages();i++) pages[i]=i;
        lstCarnet.setLstCarnets(pgCarnet);
        lstCarnet.setPageCount(pgCarnet.getTotalPages());
        lstCarnet.setNumPages(pages);
        lstCarnet.setPageCourante(page);
        lstCarnet.setSearchCarnet(searchCarnet);
        lstCarnet.setFailedAnnulation("");
        model.addAttribute("listCarnets",lstCarnet);
        return "carnets/listCarnets";
    }

    @RequestMapping(value="/ActionCarnet",method = RequestMethod.GET)
    public String  listCarnetsAA(final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="prefixPK") String prefixPK,@RequestParam(name="debutPagePK") Long debutPagePK) {
        int[] pages;Integer compteurMarees=0,compteurDeb=0,compteurTrait=0;
        System.out.println("kkkkkkkkkkkkkkk");
        lstCarnetsAchoisirForm lstCarnet=new lstCarnetsAchoisirForm();
        // supprimer la carnet et le valider avant ca
       qCarnetPK crnpk=new qCarnetPK(prefixPK,debutPagePK);

        qCarnet carnetAnnulle=carnetService.findById(crnpk);
        System.out.println(carnetAnnulle);
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Journal_Peche) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageMarree)qp).getQmarree()!=null)
                    compteurMarees++;
                          }
         }
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Fiche_Debarquement) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageDebarquement)qp).getQdebarquement()!=null)
                    compteurDeb++;
            }
        }
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Fiche_Traitement) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageTraitement)qp).getOpTraitements()!=null)
                    compteurTrait++;
            }
        }
        if(compteurDeb==0 && compteurMarees==0 && compteurTrait==0) {
            carnetService.delete(crnpk);
            lstCarnet.setFailedAnnulation("annulle avec success");
        }
        else {
            lstCarnet.setFailedAnnulation("Impossible d'annuler");
        }
        //
        Page<qCarnet>  pgCarnet=carnetService.findAll(page,20);

        pages=new int[pgCarnet.getTotalPages()];
        for(int i=0;i<pgCarnet.getTotalPages();i++) pages[i]=i;
        lstCarnet.setLstCarnets(pgCarnet);
        lstCarnet.setPageCount(pgCarnet.getTotalPages());
        lstCarnet.setNumPages(pages);
        lstCarnet.setPageCourante(page);

        model.addAttribute("listCarnets",lstCarnet);
        return "carnets/listCarnets";
    }


        @RequestMapping(value="/AttribuerCarnet",method = RequestMethod.GET)
        public String attribuerCarnet(@RequestParam(name="numimm") String numimm,@RequestParam(name="action") String action , final ModelMap model)
        {
            String urlNavigation=null;
            attributionCarnetForm attrCrn=null;
            attrCrn=new attributionCarnetForm();
            qCarnet createdCarnet=new qCarnet();
            qPrefix prefi=new qPrefix();
            createdCarnet.setQprefix(prefi);
            qNavire selectedNavire=registrenavireService.findById(numimm);
            List<qLic> licencesActives=registrenavireService.retActLicences(selectedNavire);

            attrCrn.setCarnetSelected(createdCarnet);
            attrCrn.setNavireSelected(selectedNavire);
            attrCrn.setLicenceActives(licencesActives);

    // chercher les licences actives pour ce navire pour les afficher et choisisser une

            model.addAttribute("CarnetAttribue",attrCrn);

            if(action.equals("attribuerCarnet")) {

           }

             if(attrCrn.getLicenceActives().size()==0) urlNavigation="attributionImpossible";
             else  urlNavigation="carnets/attribution";
            return urlNavigation;
        }
        @RequestMapping(value="/NouvLicenceBatExistant",method = RequestMethod.GET)
    public String NouvelLicenceBatExistant(@RequestParam(name="numimm") String numimm,@RequestParam(name="action") String action , final ModelMap model,HttpSession session){


     //   model.addAttribute("LicForm", LicForm);

        qLic currentLicence=null;
        creationLicForm licform=new creationLicForm();
        qNavire currentNav=registrenavireService.findById(numimm);
         String forwardURL=null;
       if(action.equals("createlicenceNational")) {
           currentLicence=new qLicenceNational();
           currentLicence.setQnavire(currentNav);

           currentLicence.setBalise(currentNav.getBalise());currentLicence.setAnneeconstr(currentNav.getAnneeconstr());currentLicence.setCalpoids(currentNav.getCalpoids());currentLicence.setCount(currentNav.getCount());currentLicence.setEff(currentNav.getEff());currentLicence.setGt(currentNav.getGt());currentLicence.setImo(currentNav.getImo());
           currentLicence.setKw(currentNav.getKw());currentLicence.setNbrhomm(currentNav.getNbrhomm());currentLicence.setLarg(currentNav.getLarg());currentLicence.setLongg(currentNav.getLongg());currentLicence.setNomnav(currentNav.getNomnav());currentLicence.setRadio(currentNav.getRadio());currentLicence.setPuimot(currentNav.getPuimot());
           currentLicence.setNomar(currentNav.getNomar());
           licform.setLicence(currentLicence);
           model.addAttribute("LicForm",licform);
           forwardURL="qshowNewLicNational";
       }
       if(action.equals("createlicenceLibre")) {
           currentLicence=new qLicenceLibre();
           currentLicence.setQnavire(currentNav);
           currentLicence.setBalise(currentNav.getBalise());currentLicence.setAnneeconstr(currentNav.getAnneeconstr());currentLicence.setCalpoids(currentNav.getCalpoids());currentLicence.setCount(currentNav.getCount());currentLicence.setEff(currentNav.getEff());currentLicence.setGt(currentNav.getGt());currentLicence.setImo(currentNav.getImo());
           currentLicence.setKw(currentNav.getKw());currentLicence.setNbrhomm(currentNav.getNbrhomm());currentLicence.setLarg(currentNav.getLarg());currentLicence.setLongg(currentNav.getLongg());currentLicence.setNomnav(currentNav.getNomnav());currentLicence.setRadio(currentNav.getRadio());currentLicence.setPuimot(currentNav.getPuimot());
           currentLicence.setNomar(currentNav.getNomar());
           licform.setLicence(currentLicence);
           model.addAttribute("LicForm",licform);
           forwardURL="qshowNewLicLibre";
       }
        return forwardURL;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1",params={"firstEtape"},method = RequestMethod.POST)
    public String NouvelLicenceBatNouv(final @ModelAttribute("LicForm") creationLicForm LicForm, final ModelMap model) {
        qLic lic=null;
        String url=null;
        qNavire navire=new qNavire();
         System.out.println("kkkkkkkkkkkkkkkkkk");
       if(LicForm.getTypeOperation().equals("National")) {lic=new qLicenceNational();lic.setQnavire(navire); url="qShowNewLicNationalNewBat";}
       if(LicForm.getTypeOperation().equals("Etranger")) {lic=new qLicenceLibre();lic.setQnavire(navire);url="qShowNewLicLibreNewBat";}

        LicForm.setLicence(lic);
        LicForm.setRefMessage("hh");
        model.addAttribute("LicForm",LicForm);
        return url;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1", method = RequestMethod.GET)
    public String NouvelLicenceBatNouv77(@RequestParam(name="zeroEtape") boolean zeroEtape,final ModelMap model) {
       System.out.println("tttttttttttttttttttt");
        creationLicForm licf=new creationLicForm();
        licf.setTypeOperation("National");
        String urlnav=null;
        //if(!model.containsAttribute("LicForm"))
      if(zeroEtape==true)   {
          model.addAttribute("LicForm", licf);

          urlnav="qChoixModeNouvBat";
      }
        else { urlnav="redirect:start"; }
        return urlnav;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1", params={"addRow"},method = RequestMethod.POST)
    public String NouvLicenceBatExistantaddrow(final  @ModelAttribute("LicForm") creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {
        String url=null;
        List<qCategRessource> ens=new ArrayList<qCategRessource>();

        if (LicForm.getLicence().getQcatressources() == null) {
            ens.add(new qCategRessource());
            LicForm.getLicence().setQcatressources(ens);
        } else LicForm.getLicence().getQcatressources().add(new qCategRessource());
       model.addAttribute("LicForm",LicForm);
       if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNationalNewBat";
        else {
           if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibreNewBat";
           else System.out.println("jjjj");
       }
        return url;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1",params = {"addNewLicNat"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd(final  @Valid @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {

        String validateURL=null;
        qLic licAct=LicForm.getLicence();
        System.out.println(LicForm.getLicence().getQnavire().getBalise());
      //  LicForm.getLicence().setBalise(LicForm.getLicence().getQnavire().getBalise());LicForm.getLicence().setAnneeconstr(LicForm.getLicence().getQnavire().getAnneeconstr());LicForm.getLicence().setCalpoids(LicForm.getLicence().getQnavire().getCalpoids());LicForm.getLicence().setGt(LicForm.getLicence().getQnavire().getGt());LicForm.getLicence().setImo(LicForm.getLicence().getQnavire().getImo());
      //  LicForm.getLicence().setKw(LicForm.getLicence().getQnavire().getKw());LicForm.getLicence().setLarg(LicForm.getLicence().getQnavire().getLarg());LicForm.getLicence().setLongg(LicForm.getLicence().getQnavire().getLongg());LicForm.getLicence().setNomnav(LicForm.getLicence().getQnavire().getNomnav());LicForm.getLicence().setRadio(LicForm.getLicence().getQnavire().getRadio());LicForm.getLicence().setPuimot(LicForm.getLicence().getQnavire().getPuimot());


    //    if (concessionService.validate(((qLicenceNational)LicForm.getLicence()).getQconcession().getRefConcession()) == false)
    //    {
     //       LicForm.setRefMessage("concession invalide");
    //        System.out.println(LicForm.getRefMessage());
   //         return "qshowNewLicNationalNewBat";
    //    }
        model.addAttribute("LicForm",LicForm);

        licValidator.validate(licAct, bindingresult);

        if(bindingresult.hasErrors()) {
           for(ObjectError obj:bindingresult.getFieldErrors()) {
              System.out.println(obj);
           }
            System.out.println(bindingresult.getAllErrors().size());

            validateURL="qshowNewLicNationalNewBat";
        }
        else        {
            qNavire bat=registrenavireService.create(LicForm.getLicence().getQnavire());
            LicForm.getLicence().setQnavire(bat);
            LicForm.getLicence().setBalise(bat.getBalise());LicForm.getLicence().setAnneeconstr(bat.getAnneeconstr());LicForm.getLicence().setCalpoids(bat.getCalpoids());LicForm.getLicence().setCount(bat.getCount());LicForm.getLicence().setEff(bat.getEff());LicForm.getLicence().setGt(bat.getGt());LicForm.getLicence().setImo(bat.getImo());
            LicForm.getLicence().setKw(bat.getKw());LicForm.getLicence().setNbrhomm(bat.getNbrhomm());LicForm.getLicence().setLarg(bat.getLarg());LicForm.getLicence().setLongg(bat.getLongg());LicForm.getLicence().setNomnav(bat.getNomnav());LicForm.getLicence().setRadio(bat.getRadio());LicForm.getLicence().setPuimot(bat.getPuimot());
            LicForm.getLicence().setNomar(bat.getNomar());

            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
        }
        return validateURL;
    }
    @RequestMapping(value="/NouvLicenceBatNouveau1",params = {"addNewLicLib"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd1(final  @Valid  @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {
        String validateURL=null;

        qLic licAct=LicForm.getLicence();

        licValidator.validate(licAct, bindingresult);
        model.addAttribute("LicForm",LicForm);
        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            validateURL="qshowNewLicLibreNewBat";
        }
        else        {
            qNavire bat=registrenavireService.create(LicForm.getLicence().getQnavire());
            LicForm.getLicence().setQnavire(bat);
            LicForm.getLicence().setBalise(bat.getBalise());LicForm.getLicence().setAnneeconstr(bat.getAnneeconstr());LicForm.getLicence().setCalpoids(bat.getCalpoids());LicForm.getLicence().setCount(bat.getCount());LicForm.getLicence().setEff(bat.getEff());LicForm.getLicence().setGt(bat.getGt());LicForm.getLicence().setImo(bat.getImo());
            LicForm.getLicence().setKw(bat.getKw());LicForm.getLicence().setNbrhomm(bat.getNbrhomm());LicForm.getLicence().setLarg(bat.getLarg());LicForm.getLicence().setLongg(bat.getLongg());LicForm.getLicence().setNomnav(bat.getNomnav());LicForm.getLicence().setRadio(bat.getRadio());LicForm.getLicence().setPuimot(bat.getPuimot());
            LicForm.getLicence().setNomar(bat.getNomar());

            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
                    }
        return validateURL;
    }
    }
