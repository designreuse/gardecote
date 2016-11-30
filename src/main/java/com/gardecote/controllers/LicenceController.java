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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dell on 12/11/2016.
 */
@Controller

@SessionAttributes(types = {CreateDocForm.class,lstBateauAchoisirForm.class,creationLicForm.class})

@RequestMapping("/")
public class LicenceController {
    @Autowired
    licenceValidator licValidator;

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
    public String validatethenadd(final  @Valid @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {

        String validateURL=null;

        qLic licAct=LicForm.getLicence();
        if (concessionService.validate(((qLicenceNational)licAct).getQconcession().getRefConcession()) == false)
        {
            LicForm.setRefMessage("concession invalide");
            System.out.println(LicForm.getRefMessage());
            return "qshowNewLicNational";
        }
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
    public String addRowLBNB( final  @ModelAttribute("LicForm")  creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String url=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();

        if (LicForm.getLicence().getQcatressources() == null) {
            ens.add(new qCategRessource());
            LicForm.getLicence().setQcatressources(ens);
        } else LicForm.getLicence().getQcatressources().add(new qCategRessource());

        model.addAttribute("LicForm",LicForm);
if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNational";
        if(LicForm.getLicence() instanceof qLicenceLibre) url="qshowNewLicLibre";
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

    @RequestMapping(value="/NouvLicenceBatExistant",method = RequestMethod.GET)
    public String NouvelLicenceBatExistant(@RequestParam(name="numimm") String numimm,@RequestParam(name="action") String action , final ModelMap model){
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

    @RequestMapping(value="/NouvLicenceBatNouveau",params={"firstEtape"},method = RequestMethod.POST)
    public String NouvelLicenceBatNouv(final  creationLicForm LicForm, final ModelMap model) {
        qLic lic=null;
        String url=null;
        qNavire navire=new qNavire();

       if(LicForm.getTypeOperation().equals("National")) {lic=new qLicenceNational();lic.setQnavire(navire); url="qShowNewLicNationalNewBat";}
       if(LicForm.getTypeOperation().equals("Etranger")) {lic=new qLicenceLibre();lic.setQnavire(navire);url="qShowNewLicLibreNewBat";}

        LicForm.setLicence(lic);
        LicForm.setRefMessage("hh");
        model.addAttribute("LicForm",LicForm);
        return url;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1", method = RequestMethod.GET)
    public String NouvelLicenceBatNouv77(final ModelMap model) {
       System.out.println("tttttttttttttttttttt");
        //if(!model.containsAttribute("LicForm"))
         model.addAttribute("LicForm", new creationLicForm());
          return "qChoixModeNouvBat";
    }

    @RequestMapping(value="/NouvLicenceBatNouveau", params={"addRow"},method = RequestMethod.POST)
    public String NouvLicenceBatExistantaddrow(final    creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {
        String url=null;
        List<qCategRessource> ens=new ArrayList<qCategRessource>();

        if (LicForm.getLicence().getQcatressources() == null) {
            ens.add(new qCategRessource());
            LicForm.getLicence().setQcatressources(ens);
        } else LicForm.getLicence().getQcatressources().add(new qCategRessource());
       model.addAttribute("LicForm",LicForm);
       if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNationalNewBat";
       if(LicForm.getLicence() instanceof qLicenceLibre) url="qshowNewLicLibreNewBat";
        return url;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau",params = {"addNewLicNat"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd(final  @Valid  creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {

        String validateURL=null;
        qLic licAct=LicForm.getLicence();
        System.out.println(LicForm.getLicence().getQnavire().getBalise());
      //  LicForm.getLicence().setBalise(LicForm.getLicence().getQnavire().getBalise());LicForm.getLicence().setAnneeconstr(LicForm.getLicence().getQnavire().getAnneeconstr());LicForm.getLicence().setCalpoids(LicForm.getLicence().getQnavire().getCalpoids());LicForm.getLicence().setGt(LicForm.getLicence().getQnavire().getGt());LicForm.getLicence().setImo(LicForm.getLicence().getQnavire().getImo());
      //  LicForm.getLicence().setKw(LicForm.getLicence().getQnavire().getKw());LicForm.getLicence().setLarg(LicForm.getLicence().getQnavire().getLarg());LicForm.getLicence().setLongg(LicForm.getLicence().getQnavire().getLongg());LicForm.getLicence().setNomnav(LicForm.getLicence().getQnavire().getNomnav());LicForm.getLicence().setRadio(LicForm.getLicence().getQnavire().getRadio());LicForm.getLicence().setPuimot(LicForm.getLicence().getQnavire().getPuimot());


        if (concessionService.validate(((qLicenceNational)LicForm.getLicence()).getQconcession().getRefConcession()) == false)
        {
            LicForm.setRefMessage("concession invalide");
            System.out.println(LicForm.getRefMessage());
            return "qshowNewLicNationalNewBat";
        }
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
    @RequestMapping(value="/NouvLicenceBatNouveau",params = {"addNewLicLib"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd1(final  @Valid   creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {
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
