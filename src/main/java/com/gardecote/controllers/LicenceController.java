package com.gardecote.controllers;
import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.data.repository.jpa.qHystoriquesRepository;
import com.gardecote.dto.StatusResponse;
import com.gardecote.entities.*;
import com.gardecote.reports.bateauExcelView;
import com.gardecote.reports.licencesExcelView;
import com.gardecote.reports.zoneExcelView;
import org.apache.commons.lang.SerializationUtils;
import com.gardecote.web.*;
import org.jgroups.util.*;
import org.jgroups.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dell on 12/11/2016.
*/

@Controller
@SessionAttributes(types = {qModelJP.class,frmSearchPgsForDocCrea.class,lstBateauAchoisirForm.class,creationLicForm.class,creationConcessionForm.class,attributionCarnetForm.class})
@RequestMapping("/")

public class LicenceController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired @Qualifier("licencesExportJob")
    Job job1;
    private static String UPLOAD_LOCATION="C:/mytemp/";
    @Autowired
    FileValidator fileValidator;
    @Autowired
    MultiFileValidator multiFileValidator;
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    @Autowired
    private concessionValidator concessionValidator;
    @Autowired
    private MessageByLocaleService messageByLocaleService;
    @Autowired
    private saveDocValidator saveDocValidator;
    @Autowired
    private licenceValidator licValidator;
    @Autowired
    private licenceValidatorBatExistant licValidatorBE;
    @Autowired
    private attrUsineValidator attrUsineValidator;
    @Autowired
    private attrTypeConcessionModif attrTypeConcessionModifValidator;
    @Autowired
    attrTypeConcession attrTypeConcessionValidator;
    @Autowired
    private usineValidator usineValidator;
    @Autowired
    private deleteDocValidator deleteDocValidator;
    @Autowired
    private modelValidateur modelValidateur;
    @Autowired
    private attributionValidator attrValidator;
    @Autowired
    private creerDocValidator creerValidateur;
    @Autowired
    private creerAnnexeValidateur creerAnnexeValidateur;
    @Autowired
    private qPrefixService prefService;
    @Autowired
    private qCarnetService carnetService;
    @Autowired
    private qConcessionService concessionService;
    @Autowired
    private  qTraitementService traitementService;

    @Autowired
    private qConsignataireService consignataireService;
    @Autowired
    private qEnginsLicenceService enginsLicenceService;
    @Autowired
    private qEnginPecheMarService enginsPechMarService;
    @Autowired
    private qEnginPecheDebarService enginsPecheDebarService;
    @Autowired
    private qAccordPecheService accordPecheService;

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
    private qUsineService usineService;
    @Autowired
    private LicenceAc ourLic;
    @Autowired
    private qMareeAnnexeService mareeAnnexService;
    @Autowired
    private qEnginAuthoriseeService engAuth;
    @Autowired
    private qHystoriquesRepository changRepo;

    public licenceValidator getLicValidator() {
        return licValidator;
    }

    public void setLicValidator(licenceValidator licValidator) {
        this.licValidator = licValidator;
    }
    // pour les liste deroulantes.
   @ModelAttribute("allEnginsDeb")
    public List<enumEnginDeb> getEnginsDeb() {

        return Arrays.asList(enumEnginDeb.values());
     }

    @ModelAttribute("allEnginsMar")
    public List<enumEngin> getEnginsMar() {

        return Arrays.asList(enumEngin.values());
    }

@ModelAttribute("allTypesConcessionArtisanal")
public List<enumTypeConcessionArtisanal> populateConcessionArtisanal() {
    return Arrays.asList(enumTypeConcessionArtisanal.values());

}
@ModelAttribute("allTypesPecheHautiriere")
public List<enumTypePecheHautiriere> populateTypesPecheHautiriere() {
    return Arrays.asList(enumTypePecheHautiriere.values());

}
    @ModelAttribute("allTypesConcessionCotiere")
    public List<enumTypeConcessionCotiere> populateConcessionHautiriere() {
        return Arrays.asList(enumTypeConcessionCotiere.values());

    }
    @ModelAttribute("allTypesConcessionHautiriere")
    public List<enumTypeConcessionCotiere> populateConcessionCotiere() {
        return Arrays.asList(enumTypeConcessionCotiere.values());

    }

    @ModelAttribute("allEspeces")
    public List<qEspece> populateEspeces() {
       return especeService.findAll();

    }


    @ModelAttribute("allTypesPeches")
    public List<enumJP> populateSegPeche() {
        return Arrays.asList(enumJP.values());
    }
    @ModelAttribute("allTypesDocc")
    public List<enumTypeDoc> populateAllTypesDocc() {
        List<enumTypeDoc> lst=new ArrayList<enumTypeDoc>();
        lst.add(enumTypeDoc.Fiche_Debarquement);
        lst.add(enumTypeDoc.Journal_Peche);
        lst.add(enumTypeDoc.Fiche_Traitement);
        lst.add(enumTypeDoc.Journal_Annexe);
        return lst;
    }

    @ModelAttribute("allTypesDoc")
    public List<enumTypeDoc> populateAllTypesDoc() {
        List<enumTypeDoc> lst=new ArrayList<enumTypeDoc>();
        lst.add(enumTypeDoc.Fiche_Debarquement);
                lst.add(enumTypeDoc.Journal_Peche);
        lst.add(enumTypeDoc.Fiche_Traitement);
        return lst;
    }
    @ModelAttribute("supports")
    public List<enumSupport> populateSupport() {
        return Arrays.asList(enumSupport.values());
    }

    @ModelAttribute("allTypesEncadLibre")
    public List<qAccordPeche> populateTypeEnc() {
        List<qAccordPeche> lstLibre=new ArrayList<qAccordPeche>();
        for(qAccordPeche tt:accordPecheService.findAll())
        { if(tt.getModePeche().equals(enumModePeche.ETRANGER))
            lstLibre.add(tt);
        }

        return lstLibre;
    }

    @ModelAttribute("allTypesEncad")
    public List<qAccordPeche> populateTypeEncLibre() {
        List<qAccordPeche> lstAll=new ArrayList<qAccordPeche>();
        for(qAccordPeche tt:accordPecheService.findAll())
        {
            lstAll.add(tt);
        }
        return lstAll;
    }

    @ModelAttribute("allTypesEncadNational")
    public List<qAccordPeche> populateTypeEncNational() {
        List<qAccordPeche> lstNational=new ArrayList<qAccordPeche>();
        for(qAccordPeche tt:accordPecheService.findAll())
        { if(tt.getModePeche().equals(enumModePeche.NATIONAL))
            lstNational.add(tt);
        }

      //  lstNational.add(qTypeEnc.Autres);

        return lstNational;
    }

    @ModelAttribute("allConcessions")
    public List<qConcession> populateConcessions() {
        return concessionService.findAll();
    }


    @ModelAttribute("allPrefixes")
    public List<qPrefix> getPrefixes() {
        return prefService.findAll();
    }

    @ModelAttribute("allPrefixess")
    public List<String> getPrefixess() {
        return prefService.findAlls();
    }
    @ModelAttribute("typeDocs")
    public List<enumTypeDoc> getTypesDoc() {
        return Arrays.asList(enumTypeDoc.values());
    }
    @ModelAttribute("allEspTypes")
    public List<enumEspType> populateEspTypes() {
        return Arrays.asList(enumEspType.values());
    }

    @ModelAttribute("allTypesBat")
    public List<enumTypeBat> populateTypeNav() {
        return Arrays.asList(enumTypeBat.values());
    }
    @ModelAttribute("allZones")
    public List<qZone> populateZones() {
        List<qZone> zones=new ArrayList<qZone>(),zonesToRendre=new ArrayList<qZone>();;
        zones=this.zoneService.findAll();
        for(qZone zz:zones) {
            if (zz.getIdZone()>0) {
                zonesToRendre.add(zz);
            }
        }

        return zonesToRendre;
    }
    @ModelAttribute("allNations")
    public List<qNation> populateNations() {
        return this.nationService.findAll();
    }
    @ModelAttribute("allNationsLibre")
    public List<qNation> populateNationsLibre() {
        List<qNation> nationalites=new ArrayList<qNation>();
        List<qNation> nationalitess=new ArrayList<qNation>();
        nationalites=this.nationService.findAll();
        for(qNation rr:nationalites) {
            if(rr.getIdNation()!=22)
                nationalitess.add(rr);
        }

        return nationalitess;
    }
    @ModelAttribute("allNationsNational")
    public List<qNation> populateNationsNational() {
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

    @RequestMapping(value = "/PrefixesByTypeDoc/{typeDoc}", method = RequestMethod.GET)
    public String getPrefixesByTypeDoc(@PathVariable("typeDoc") String typeDoc,Model model) {
        System.out.println("string id"+enumTypeDoc.valueOf(typeDoc));
        System.out.println(prefService.PrefixesByTypeDoc(enumTypeDoc.valueOf(typeDoc)).size());
        model.addAttribute("selectedPrefixes",prefService.PrefixesByTypeDoc(enumTypeDoc.valueOf(typeDoc)));
           return "carnets/attribution::renderPrefixes";
    }
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public String lgout(final ModelMap model) {
        return "logout";
    }

    @RequestMapping(value="/recupererChangements",method = RequestMethod.GET)
    public String recupererChangements(final ModelMap model) {
        qLic currentprev=null;
        int index=0;
        qNavireLegale currentNav=null;
        List<qNavireLegale> Navs=registrenavireService.findAllLegal();
        for(qNavireLegale nvleg:Navs) {
            index=0;
            currentNav=nvleg;
            List<qLic> currentLics=registrenavireService.findLicences(nvleg);
            for(qLic lc:currentLics) {
                System.out.println(index);
                qNavireHistoriqueChangements nch=null;
               // currentprev=lc;
            if(index==0)   currentprev = (qLic) SerializationUtils.clone(lc);
                else   {
                    if(!lc.getNomnav().equals(currentprev.getNomnav()))
                    {
                        nch=new qNavireHistoriqueChangements(enumHistoriqueNavire.changement_Nom,nvleg,lc);
                        nch.setDescriptif(currentprev.getNomnav()+"--->"+lc.getNomnav());
                        changRepo.save(nch);
                    }
                if(!lc.getNation().getIdNation().equals(currentprev.getNation().getIdNation()))
                {
                   nch=new qNavireHistoriqueChangements(enumHistoriqueNavire.changementNationalite,nvleg,lc);
                    nch.setDescriptif(currentprev.getNation().getDesignation()+"--->"+lc.getNation().getDesignation());
                   changRepo.save(nch);
                }
                currentprev= (qLic) SerializationUtils.clone(lc);
                       }
                    index++;
            }
        }

        return null;
    }
    @RequestMapping(value="/consulterHistoriques",method = RequestMethod.GET)
    public String consulterHistoriques(@RequestParam(name="numimm") String numimm,final ModelMap model) {
        qNavireLegale  currentNav=registrenavireService.findLegalById(numimm);
        List<qNavireHistoriqueChangements> changements=changRepo.findChangNavById(currentNav);
        model.addAttribute("changements",changements);
        return "changements";
            }

        @RequestMapping(value="/listBatASelectionner",method = RequestMethod.GET)
    public String listBatASelectionner1(final lstBateauAchoisirForm batForm ,final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="terme",defaultValue = "") String terme){
        lstBateauAchoisirForm lstBat=new lstBateauAchoisirForm();
        int[] pages;
        Page<qNavireLegale>   pgBat=registrenavireService.findAllLegal(page,20,terme);
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
            qNavireLegale currnv=registrenavireService.findLegalById(LicForm.getLicence().getNumimm());
            currnv.setConcession(((qLicenceNational) LicForm.getLicence()).getQconcession());
            //   registrenavireService.save(navireLegale);
            currnv.setAccordPeche(null);
            currnv.setNomnav(LicForm.getLicence().getNomnav());
            currnv.setLongg(LicForm.getLicence().getLongg());
            currnv.setPuimot(LicForm.getLicence().getPuimot());
            currnv.setNation(LicForm.getLicence().getNation());
            currnv.setLarg(LicForm.getLicence().getLarg());
            currnv.setCount(LicForm.getLicence().getCount());
            currnv.setNbrhomm(LicForm.getLicence().getNbrhomm());
            currnv.setEff(LicForm.getLicence().getEff());
            currnv.setAnneeconstr(LicForm.getLicence().getAnneeconstr());
            currnv.setCalpoids(LicForm.getLicence().getCalpoids());
            currnv.setGt(LicForm.getLicence().getGt());
            currnv.setKw(LicForm.getLicence().getKw());
            currnv.setTjb(LicForm.getLicence().getTjb());
            currnv.setImo(LicForm.getLicence().getImo());
            currnv.setPort(LicForm.getLicence().getPort());
            currnv.setRadio(LicForm.getLicence().getRadio());
            currnv.setBalise(LicForm.getLicence().getBalise());
            currnv.setUpdatedOn(LicForm.getLicence().getUpdatedAt());
            currnv.setNumlic(LicForm.getLicence().getNumlic());
            currnv.setModePeche( enumModePeche.ETRANGER);
            currnv.setDateDebutAuth(LicForm.getLicence().getDateDebutAuth());
            currnv.setQcatressources(LicForm.getLicence().getQcatressources());
            currnv.setEnginsAuthorisees(LicForm.getLicence().getEnginsAuthorisees());
            currnv.setNomar(LicForm.getLicence().getNomar());
            currnv.setTypb(LicForm.getLicence().getTypb());
            currnv.setAccordPeche(((qLicenceLibre) LicForm.getLicence()).getAccord());
            LicForm.getLicence().setQnavire(currnv);

            licenceService.create(LicForm.getLicence());
        //
            model.clear();
            validateURL = "redirect:afficherLstLicence";
       }
        return validateURL;
    }
    @RequestMapping(value="/NouvLicenceBatExistant", params={"addLicenceLibre"},method = RequestMethod.POST)
    public String validatelibre(final   @ModelAttribute("LicForm")  creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {
        String validateURL=null;
        qNavireLegale currentnav=registrenavireService.findLegalById(LicForm.getNumSelected());
        List<qEnginAuthorisee> engins=engAuth.getEnginsAuthorisees(currentnav);
        List<qCategRessource> categs=engAuth.getCategoriesRattachees(currentnav);
        qLic licAct=LicForm.getLicence();
        licValidatorBE.validate(licAct, bindingresult);
        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            LicForm.setCategoriesRattaches(categs);
            LicForm.setEnginsAuthorisees(engins);
            model.addAttribute("LicForm",LicForm);
            validateURL="qshowNewLicLibre";
        }
        else        {


            // trouver le numimm et le mettre a jour
            qNavireLegale currnv=registrenavireService.findLegalById(LicForm.getLicence().getNumimm());
            currnv.setNomnav(LicForm.getLicence().getNomnav());
            currnv.setLongg(LicForm.getLicence().getLongg());
            currnv.setPuimot(LicForm.getLicence().getPuimot());
            currnv.setNation(LicForm.getLicence().getNation());
            currnv.setLarg(LicForm.getLicence().getLarg());
            currnv.setCount(LicForm.getLicence().getCount());
            currnv.setNbrhomm(LicForm.getLicence().getNbrhomm());
            currnv.setEff(LicForm.getLicence().getEff());
            currnv.setAnneeconstr(LicForm.getLicence().getAnneeconstr());
            currnv.setCalpoids(LicForm.getLicence().getCalpoids());
            currnv.setGt(LicForm.getLicence().getGt());
            currnv.setKw(LicForm.getLicence().getKw());
            currnv.setTjb(LicForm.getLicence().getTjb());
            currnv.setImo(LicForm.getLicence().getImo());
            currnv.setPort(LicForm.getLicence().getPort());
            currnv.setRadio(LicForm.getLicence().getRadio());
            currnv.setBalise(LicForm.getLicence().getBalise());
            currnv.setUpdatedOn(LicForm.getLicence().getUpdatedAt());
            currnv.setNumlic(LicForm.getLicence().getNumlic());
            currnv.setModePeche( enumModePeche.ETRANGER);
            currnv.setDateDebutAuth(LicForm.getLicence().getDateDebutAuth());
            currnv.setQcatressources(LicForm.getLicence().getQcatressources());
            currnv.setEnginsAuthorisees(LicForm.getLicence().getEnginsAuthorisees());
            currnv.setNomar(LicForm.getLicence().getNomar());
            currnv.setTypb(LicForm.getLicence().getTypb());
            currnv.setAccordPeche(((qLicenceLibre) LicForm.getLicence()).getAccord());
            LicForm.getLicence().setQnavire(currnv);
            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
        }
        return validateURL;
    }


    @RequestMapping(value="/ajouterUsine",method = RequestMethod.GET)
    public String ajouterUsine(final ModelMap model) {
        creationUsineForm usineform=new creationUsineForm() ;
        String url=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();



        model.addAttribute("UsineForm",usineform);

        return "usine/newUsineForm";
    }

    @RequestMapping(value="/saveUsine",method = RequestMethod.POST)
    public String saveUsine(final creationUsineForm UsineForm, final ModelMap model,final BindingResult bindingresult) {

        String urlnav=null;


        usineValidator.validate(UsineForm, bindingresult);
        model.addAttribute("UsineForm",UsineForm);
        if(!bindingresult.hasErrors()) {
            System.out.println("jjjjj");
          usineService.create(UsineForm.getCurrentusine());
            urlnav="redirect:listUsines";
        }
        else {
            urlnav="usine/newUsineForm";
        }

        return urlnav;

    }

    @RequestMapping(value="/attributionCarnetAuUsine",method = RequestMethod.POST)
    public String attribuerCarnetsp(final attributionCarnetForm CarnetAttribue , final ModelMap model, final BindingResult bindingresult) {
        String urlNav=null;
        if(CarnetAttribue!=null && CarnetAttribue.getCarnetSelected()!=null && CarnetAttribue.getCarnetSelected().getQprefix().getPrefix()!=null && CarnetAttribue.getCarnetSelected().getQprefix().getTypeDoc()!=null) {
            System.out.println(CarnetAttribue);
            qPrefixPK prefpk = new qPrefixPK(CarnetAttribue.getCarnetSelected().getQprefix().getPrefix(), CarnetAttribue.getCarnetSelected().getQprefix().getTypeDoc());

            System.out.println(" perf num "+CarnetAttribue.getCarnetSelected().getQprefix().getPrefix());
            System.out.println("type doc "+CarnetAttribue.getCarnetSelected().getQprefix().getTypeDoc());
            qPrefix pref = prefService.findById(prefpk);
            qCarnet currentCarnet = new qCarnet(pref, CarnetAttribue.getCarnetSelected().getNumeroDebutPage(), CarnetAttribue.getCarnetSelected().getNbrPages(), null, CarnetAttribue.getUsineSelected());

            attrUsineValidator.validate(currentCarnet, bindingresult);
            if (!bindingresult.hasErrors()) {
                qCarnet crn = carnetService.entrerDansLeSystem(currentCarnet);
                carnetService.attribuerCarnetAuNavire(crn,null, null,CarnetAttribue.getUsineSelected());
                CarnetAttribue.setMessage("success");
                urlNav= "redirect:listCarnets";

            } else {
                CarnetAttribue.setMessage("echec");
                urlNav="usine/attribution";
            }
        }
        model.addAttribute("CarnetAttribue",CarnetAttribue);
        return urlNav;
    }

        @RequestMapping(value="/attribuerCarnetAuUsine",method = RequestMethod.GET)
    public String attribuerCarnetAuUsine(@RequestParam(name="refAgr") String refAgr,@RequestParam(name="action") String action , final ModelMap model)
    {
        String urlNavigation=null;
        attributionUsineForm attrCrn=null;
        attrCrn=new attributionUsineForm();
        qPrefix prefi=new qPrefix();
        qCarnet createdCarnet=new qCarnet();
        createdCarnet.setQprefix(prefi);
        createdCarnet.setNbrPages(50);
        //(prefi,78L,50,null,null);

        qUsine selectedUsine=usineService.findById(refAgr);

        attrCrn.setCarnetSelected(createdCarnet);
        attrCrn.setNavireSelected(null);
        attrCrn.setUsineSelected(selectedUsine);
        createdCarnet.setTypeDoc(enumTypeDoc.Fiche_Traitement);
        qPrefixPK prefPK=new qPrefixPK("TR",enumTypeDoc.Fiche_Traitement);
        qPrefix prefSelected=prefService.findById(prefPK);
        createdCarnet.setQprefix(prefSelected);
        createdCarnet.setNbrPages(50);
        createdCarnet.setQusine(selectedUsine);
        // chercher les licences actives pour ce navire pour les afficher et choisisser une
        attrCrn.setCarnetSelected(createdCarnet);
        attrCrn.setMessage("ok");
     //   carnetService.create(createdCarnet);
        model.addAttribute("CarnetAttribue",attrCrn);

        urlNavigation="usine/attribution";
        return urlNavigation;
    }
    @RequestMapping(value="/NouvLicenceBatNouveau1LN",params={"removeRow2"},method = RequestMethod.POST)
    public String removeRow2ln2(final @ModelAttribute("LicForm") creationLicForm LicForm,final HttpServletRequest req, final ModelMap model) {
        String url=null;
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow2"));

        if(LicForm.getEnginsAuthorisees()!=null) {LicForm.getEnginsAuthorisees().remove(rowId.intValue());
        }
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNationalNewBat";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibreNewBat";
            else   System.out.println("jjjj");
        }
        return url;

    }
    @RequestMapping(value="/NouvLicenceBatExistant",params={"removeRow2"},method = RequestMethod.POST)
    public String removeRow2(final @ModelAttribute("LicForm") creationLicForm LicForm,final HttpServletRequest req, final ModelMap model) {
        String url=null;
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow2"));

        if(LicForm.getEnginsAuthorisees()!=null) {LicForm.getEnginsAuthorisees().remove(rowId.intValue());
        }
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNational";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibre";
            else   System.out.println("jjjj");
        }
        return url;

    }
    @RequestMapping(value="/NouvLicenceBatExistant",params={"removeRow3"},method = RequestMethod.POST)
    public String removeRow(final @ModelAttribute("LicForm") creationLicForm LicForm,final HttpServletRequest req, final ModelMap model) {

        final Integer rowId = Integer.valueOf(req.getParameter("removeRow3"));
        String url=null;
        List<qEnginAuthorisee> enginsToBeDelete=new ArrayList<qEnginAuthorisee>();
        qCategRessource currentCateg=LicForm.getCategoriesRattaches().get(rowId.intValue());


        if(currentCateg!=null) {
            for(qEnginAuthorisee eng:LicForm.getEnginsAuthorisees())
            { if(eng.getCategorieLicence().equals(currentCateg))
                enginsToBeDelete.add(eng);
            }
            LicForm.getEnginsAuthorisees().removeAll(enginsToBeDelete);
            LicForm.getCategoriesRattaches().remove(rowId.intValue());

        }
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNational";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibre";
            else   System.out.println("jjjj");
        }
        return url;

    }
        @RequestMapping(value="/NouvLicenceBatExistant", params={"addRow"},method = RequestMethod.POST)
    public String addRowLBNB(final @ModelAttribute("LicForm") creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

            String url=null;
            List<qCategRessource> ens=new ArrayList<qCategRessource>();
            qCategRessource currentToBeAdd=categService.findById(LicForm.getSelectedCategorieRessource().getIdtypeConcession());
            List<qEnginAuthorisee> engs=new ArrayList<qEnginAuthorisee>();
            System.out.println("new categ "+currentToBeAdd);
            if (LicForm.getCategoriesRattaches() == null) {
                ens.add(currentToBeAdd);
                LicForm.setCategoriesRattaches(ens);
                for(qEnginsLicence engl: currentToBeAdd.getEngins()){
                    engs.add(new qEnginAuthorisee(currentToBeAdd,engl,LicForm.getLicence(),null,0));
                }
                LicForm.setEnginsAuthorisees(engs);
                //   System.out.println(currentToBeAdd.getEngins().size());
            } else {
                if (!LicForm.getCategoriesRattaches().contains( currentToBeAdd))
                {  LicForm.getCategoriesRattaches().add(currentToBeAdd);
                    for(qCategRessource cat:LicForm.getCategoriesRattaches()){
                        for(qEnginsLicence engl:cat.getEngins()){
                            engs.add(new qEnginAuthorisee(cat,engl,LicForm.getLicence(),null,0));
                        }
                    }
                    LicForm.setEnginsAuthorisees(engs);

                }

            }
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNational";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibre";
            else   System.out.println("jjjj");
        }
        return url;
    }

    @RequestMapping(value="/listUsines", method = RequestMethod.GET)
    public String afficherListUsines(final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page) {
        lstUsineForm lst1=new lstUsineForm();
        int[] pages;
        Page<qUsine>   pgUsine=usineService.findAll(page,20);

        pages=new int[pgUsine.getTotalPages()];
        for(int i=0;i<pgUsine.getTotalPages();i++) pages[i]=i;
        lst1.setUsines(pgUsine);
        lst1.setPageCount(pgUsine.getTotalPages());
        lst1.setNumPages(pages);
        lst1.setPageCourante(page);
        System.out.println(" Total est : "+lst1.getUsines().getTotalElements());
        model.addAttribute("lstUsineForm",lst1);
        return "usine/lstUsines";


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
        public String attribuerCarnets(final @ModelAttribute(value="CarnetAttribue") attributionCarnetForm CarnetAttribue , final ModelMap model, final BindingResult bindingresult) {
           String urlNav=null;

           if(CarnetAttribue!=null && CarnetAttribue.getCarnetSelected()!=null && CarnetAttribue.getCarnetSelected().getPrefixNumerotation()!=null && CarnetAttribue.getCarnetSelected().getTypeDoc()!=null) {
               System.out.println(CarnetAttribue);
               qPrefixPK prefpk = new qPrefixPK(CarnetAttribue.getCarnetSelected().getPrefixNumerotation(), CarnetAttribue.getCarnetSelected().getTypeDoc());
               qPrefix pref = prefService.findById(prefpk);
               qNavireLegale navireselected=registrenavireService.findLegalById(CarnetAttribue.getNavireSelected().getNumimm());
               qCarnet currentCarnet = new qCarnet(pref, CarnetAttribue.getCarnetSelected().getNumeroDebutPage(), CarnetAttribue.getCarnetSelected().getNbrPages(), navireselected, null);

               attrValidator.validate(currentCarnet, bindingresult);
               if (!bindingresult.hasErrors()) {

                   qCarnet crn = carnetService.entrerDansLeSystem(currentCarnet);
                   carnetService.attribuerCarnetAuNavire(crn, CarnetAttribue.getNavireSelected(), CarnetAttribue.getLicenceSelected(), null);
                   CarnetAttribue.setMessage("success");
               } else {
                   CarnetAttribue.setMessage("echec");
               }
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
    public String  listCarnetsAA(final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="typeDoc") String typeDoc,@RequestParam(name="prefixPK") String prefixPK,@RequestParam(name="debutPagePK") Long debutPagePK) {
        int[] pages;Integer compteurMarees=0,compteurDeb=0,compteurTrait=0,compteurAnnexe=0;
        System.out.println("kkkkkkkkkkkkkkk");
        lstCarnetsAchoisirForm lstCarnet=new lstCarnetsAchoisirForm();
        // supprimer la carnet et le valider avant ca
       qCarnetPK crnpk=new qCarnetPK(prefixPK,debutPagePK,enumTypeDoc.valueOf(typeDoc));

        qCarnet carnetAnnulle=carnetService.findById(crnpk);
        System.out.println(carnetAnnulle);
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Journal_Peche) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageMarree)qp).getQmarree()!=null)
                    compteurMarees++;
                          }
            if(compteurMarees==0) {
            //    carnetAnnulle.setPages(null);
                for(qPageCarnet qp:carnetAnnulle.getPages()) {
             //  qp.setQcarnet(null);
              //      pagecarnetService.save(qp);
                //    pagecarnetService.delete(qp.getPageCarnetPK());
                }

           //     carnetService.save(carnetAnnulle);
                carnetService.delete(crnpk);
                lstCarnet.setFailedAnnulation("annulle avec success");
            }
         }
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Fiche_Debarquement) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageDebarquement)qp).getQdebarquement()!=null)
                    compteurDeb++;
            }
           if(compteurDeb==0) {
            //   carnetAnnulle.setPages(null);
               for(qPageCarnet qp:carnetAnnulle.getPages()) {
               //    qp.setQcarnet(null);pagecarnetService.delete(qp.getPageCarnetPK());
               }

             //  carnetService.save(carnetAnnulle);
               carnetService.delete(crnpk);
               lstCarnet.setFailedAnnulation("annulle avec success");
           }
        }
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Fiche_Traitement) {

            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageTraitement)qp).getQtraitement()!=null)
                    compteurTrait++;
            }
            if(compteurTrait==0) {
               // carnetAnnulle.setPages(null);
                for(qPageCarnet qp:carnetAnnulle.getPages()) {
                    //qp.setQcarnet(null);pagecarnetService.delete(qp.getPageCarnetPK());
                    }

             //   carnetService.save(carnetAnnulle);
                carnetService.delete(crnpk);
                lstCarnet.setFailedAnnulation("annulle avec success");
            }
        }
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Journal_Annexe) {
            carnetAnnulle.setPages(null);
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageAnnexe)qp).getQmarreeAnexe()!=null)
                    compteurAnnexe++;
            }
            if(compteurAnnexe==0) {
               // carnetAnnulle.setPages(null);
                for(qPageCarnet qp:carnetAnnulle.getPages()) {
                 //   qp.setQcarnet(null);pagecarnetService.delete(qp.getPageCarnetPK());
                }
                //carnetService.save(carnetAnnulle);
                carnetService.delete(crnpk);
                lstCarnet.setFailedAnnulation("annulle avec success");
            }
        }
        if(compteurDeb!=0 || compteurMarees!=0 || compteurTrait!=0 || compteurAnnexe!=0) {
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
            qPrefix prefi=new qPrefix();
            qCarnet createdCarnet=new qCarnet();
            createdCarnet.setQprefix(prefi);
            createdCarnet.setNbrPages(50);
            //(prefi,78L,50,null,null);

            qNavireLegale selectedNavire=registrenavireService.findLegalById(numimm);
            List<qLic> licencesActives=registrenavireService.retActLicences(selectedNavire);

            attrCrn.setCarnetSelected(createdCarnet);
            attrCrn.setNavireSelected(selectedNavire);
            attrCrn.setLicenceActives(licencesActives);

            // chercher les licences actives pour ce navire pour les afficher et choisisser une
            List<enumTypeDoc> ltsTypeDoc=new ArrayList<enumTypeDoc>();
            if(((qBateau)selectedNavire).getTypb().equals(enumTypeBat.PIROG))  ltsTypeDoc.add(enumTypeDoc.Fiche_Debarquement);
            else   ltsTypeDoc.add(enumTypeDoc.Journal_Peche);
            attrCrn.setLstTypeDoc(ltsTypeDoc);
            model.addAttribute("CarnetAttribue",attrCrn);
            model.addAttribute("TypesDo",ltsTypeDoc);

            if(action.equals("attribuerCarnet")) {

           }

   //          if(attrCrn.getLicenceActives().size()==0) urlNavigation="carnets/attributionImpossible";
     //        else  urlNavigation="carnets/attribution";
            urlNavigation="carnets/attribution";
            return urlNavigation;
        }
        @RequestMapping(value="/NouvLicenceBatExistant",method = RequestMethod.GET)
    public String NouvelLicenceBatExistant(@RequestParam(name="numimm") String numimm,@RequestParam(name="action") String action , final ModelMap model,HttpSession session){

     //   model.addAttribute("LicForm", LicForm);
         qLic currentLicence=null;
         creationLicForm licform=new creationLicForm();
         qNavireLegale currentNav=registrenavireService.findLegalById(numimm);
         String forwardURL=null;
       if(action.equals("createlicenceNational")) {
           currentLicence=new qLicenceNational();
           licform.setCategoriesRattaches(currentNav.getQcatressources());
           licform.setEnginsAuthorisees(currentNav.getEnginsAuthorisees());
     //      currentLicence.setQcatressources(currentNav.getQcatressources());
       //    currentLicence.setEnginsAuthorisees(currentNav.getEnginsAuthorisees());
           ((qLicenceNational)currentLicence).setQconcession(currentNav.getConcession());
           currentLicence.setQnavire(currentNav);
           currentLicence.setBalise(currentNav.getBalise());
           currentLicence.setAnneeconstr(currentNav.getAnneeconstr());
           currentLicence.setEff(currentNav.getEff());
           currentLicence.setNbrhomm(currentNav.getNbrhomm());
           currentLicence.setCount(currentNav.getCount());
           currentLicence.setTypb(currentNav.getTypb());
           currentLicence.setCalpoids(currentNav.getCalpoids());
           currentLicence.setCount(currentNav.getCount());
           currentLicence.setEff(currentNav.getEff());
           currentLicence.setGt(currentNav.getGt());
           currentLicence.setImo(currentNav.getImo());
           currentLicence.setKw(currentNav.getKw());
           currentLicence.setNbrhomm(currentNav.getNbrhomm());
           currentLicence.setPort(currentNav.getPort());
           currentLicence.setLarg(currentNav.getLarg());
           currentLicence.setLongg(currentNav.getLongg());
           currentLicence.setNomnav(currentNav.getNomnav());
           currentLicence.setRadio(currentNav.getRadio());
           currentLicence.setPuimot(currentNav.getPuimot());
           currentLicence.setNomar(currentNav.getNomar());
           licform.setLicence(currentLicence);
           model.addAttribute("LicForm",licform);
           forwardURL="qshowNewLicNational";
       }
       if(action.equals("createlicenceLibre")) {
           currentLicence=new qLicenceLibre();
           currentLicence.setQcatressources(currentNav.getQcatressources());
           currentLicence.setEnginsAuthorisees(currentNav.getEnginsAuthorisees());
           currentLicence.setQnavire(currentNav);
           currentLicence.setBalise(currentNav.getBalise());
           currentLicence.setAnneeconstr(currentNav.getAnneeconstr());
           currentLicence.setCalpoids(currentNav.getCalpoids());
           currentLicence.setCount(currentNav.getCount());
           currentLicence.setEff(currentNav.getEff());
           currentLicence.setGt(currentNav.getGt());
           currentLicence.setImo(currentNav.getImo());
           currentLicence.setKw(currentNav.getKw());
           currentLicence.setPort(currentNav.getPort());
           currentLicence.setNbrhomm(currentNav.getNbrhomm());
           currentLicence.setLarg(currentNav.getLarg());
           currentLicence.setLongg(currentNav.getLongg());
           currentLicence.setNomnav(currentNav.getNomnav());
           currentLicence.setRadio(currentNav.getRadio());
           currentLicence.setPuimot(currentNav.getPuimot());
           currentLicence.setNomar(currentNav.getNomar());
           ((qLicenceLibre)currentLicence).setAccord(currentNav.getAccordPeche());
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

        qNavireLegale navire=new qNavireLegale();
        System.out.println("kkkkkkkkkkkkkkkkkk");
      //  sessionStatus.setComplete();

       if(LicForm.getTypeOperation().equals("National")) {
           lic=new qLicenceNational();lic.setQnavire(navire); url="qShowNewLicNationalNewBat";
       }
       if(LicForm.getTypeOperation().equals("Etranger")) {lic=new qLicenceLibre();lic.setQnavire(navire);url="qShowNewLicLibreNewBat";}
        LicForm.setLicence(lic);
        LicForm.setCategoriesRattaches(null);
        LicForm.setEnginsAuthorisees(null);

        LicForm.setRefMessage("hh");
        model.addAttribute("LicForm",LicForm);
        return url;
    }


    @RequestMapping(value="/NouvLicenceBatNouveau1", method = RequestMethod.GET)
    public String NouvelLicenceBatNouv77(@RequestParam(name="zeroEtape") boolean zeroEtape,final ModelMap model, SessionStatus sessionStatus, HttpServletRequest request) {
       System.out.println("tttttttttttttttttttt");
        HttpSession session = request.getSession();
        creationLicForm licf=new creationLicForm();
        licf.setTypeOperation("National");
        String urlnav=null;
        //if(!model.containsAttribute("LicForm"))
      if(zeroEtape==true)   {


          licf.setCategoriesRattaches(null);
          licf.setEnginsAuthorisees(null);
          session.setAttribute("LicForm",  licf);
          model.addAttribute("LicForm", licf);

          urlnav="qChoixModeNouvBat";
      }
        else { urlnav="redirect:start"; }
        return urlnav;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1LN", params={"addRow"},method = RequestMethod.POST)
    public String NouvLicenceBatExistantaddrow(final  @ModelAttribute("LicForm") creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String url=null;
        List<qCategRessource> ens=new ArrayList<qCategRessource>();
        qCategRessource currentToBeAdd=categService.findById(LicForm.getSelectedCategorieRessource().getIdtypeConcession());
        List<qEnginAuthorisee> engs=new ArrayList<qEnginAuthorisee>();
        System.out.println("new categ "+currentToBeAdd);
        if (LicForm.getCategoriesRattaches() == null) {
            ens.add(currentToBeAdd);
            LicForm.setCategoriesRattaches(ens);
            for(qEnginsLicence engl: currentToBeAdd.getEngins()){
                engs.add(new qEnginAuthorisee(currentToBeAdd,engl,LicForm.getLicence(),null,0));
            }
            LicForm.setEnginsAuthorisees(engs);
            //   System.out.println(currentToBeAdd.getEngins().size());
        } else {
            if (!LicForm.getCategoriesRattaches().contains( currentToBeAdd))
            {  LicForm.getCategoriesRattaches().add(currentToBeAdd);
                for(qCategRessource cat:LicForm.getCategoriesRattaches()){
                    for(qEnginsLicence engl:cat.getEngins()){
                        engs.add(new qEnginAuthorisee(cat,engl,LicForm.getLicence(),null,0));
                    }
                }
                LicForm.setEnginsAuthorisees(engs);

            }

        }

        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNationalNewBat";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibreNewBat";
            else   System.out.println("jjjj");
        }
        return url;
    }



    @RequestMapping(value="/NouvLicenceBatNouveau1LN", params={"removeRow3"},method = RequestMethod.POST)
    public String NouvLicenceBatExistantRemoveRow(final  @ModelAttribute("LicForm") creationLicForm LicForm, final HttpServletRequest req,final ModelMap model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow3"));
        String url=null;
        List<qEnginAuthorisee> enginsToBeDelete=new ArrayList<qEnginAuthorisee>();
        qCategRessource currentCateg=LicForm.getCategoriesRattaches().get(rowId.intValue());


        if(currentCateg!=null  ) {
            for(qEnginAuthorisee eng:LicForm.getEnginsAuthorisees())
            { if(eng.getCategorieLicence().equals(currentCateg))
                enginsToBeDelete.add(eng);
            }
            LicForm.getEnginsAuthorisees().removeAll(enginsToBeDelete);
            LicForm.getCategoriesRattaches().remove(rowId.intValue());

        }

        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() instanceof qLicenceNational) url="qshowNewLicNationalNewBat";
        else {
            if (LicForm.getLicence() instanceof qLicenceLibre) url = "qshowNewLicLibreNewBat";
            else   System.out.println("jjjj");
        }
        return url;
    }

    @RequestMapping(value="/NouvLicenceBatNouveau1LN",params = {"addNewLicNat"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd(final  @Valid @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {
         String validateURL=null;
        qLic licAct=LicForm.getLicence();
        qConcession currentConcession=null;
        if(LicForm.getCategoriesRattaches()!=null) {LicForm.getLicence().setQcatressources(LicForm.getCategoriesRattaches());
        if(LicForm.getEnginsAuthorisees()!=null)   LicForm.getLicence().setEnginsAuthorisees(LicForm.getEnginsAuthorisees());}
        if(LicForm.getLicence() instanceof qLicenceNational && ((qLicenceNational) LicForm.getLicence()).getQconcession().getRefConcession()!=null ) {
            currentConcession = concessionService.findById(((qLicenceNational) LicForm.getLicence()).getQconcession().getRefConcession());
            ((qLicenceNational) LicForm.getLicence()).setQconcession(currentConcession);
        }
      licValidator.validate(licAct, bindingresult);

        if(bindingresult.hasErrors()) {
           for(ObjectError obj:bindingresult.getFieldErrors()) {
              System.out.println(obj);
           }

            System.out.println(bindingresult.getAllErrors().size());
            model.addAttribute("LicForm",LicForm);
            validateURL="qshowNewLicNationalNewBat";
        }
        else    {
            List<qCategRessource> VV=new ArrayList<qCategRessource>();
            for(qCategRessource cc:LicForm.getLicence().getQcatressources())
            {
                VV.add(new qCategRessource(cc.getTypeconcessionConcernee(),cc.getTypeSupport(),cc.getQlicences(),cc.getEngins(),
                        cc.getAncienCategoriePeche()));
            }
              qNavireLegale navireLegale=new qNavireLegale(LicForm.getLicence().getNumimm(), LicForm.getLicence().getNomnav(),  LicForm.getLicence().getLongg(), LicForm.getLicence().getPuimot(), LicForm.getLicence().getNation(), LicForm.getLicence().getLarg(), LicForm.getLicence().getCount(), LicForm.getLicence().getNbrhomm(), LicForm.getLicence().getEff(), LicForm.getLicence().getAnneeconstr(), LicForm.getLicence().getCalpoids(), LicForm.getLicence().getGt(),LicForm.getLicence().getKw(), LicForm.getLicence().getTjb(), LicForm.getLicence().getImo(), LicForm.getLicence().getPort(),
              LicForm.getLicence().getRadio(), LicForm.getLicence().getBalise(), LicForm.getLicence().getUpdatedAt(), LicForm.getLicence().getNumlic(), enumModePeche.NATIONAL, LicForm.getLicence().getDateDebutAuth(), LicForm.getLicence().getDateFinAuth(), null, LicForm.getLicence().getEnginsAuthorisees(),LicForm.getLicence().getNomar());
            //qNavireLegale bat=(qNavireLegale) registrenavireService.create(navireLegale);
            navireLegale.setTypb( LicForm.getLicence().getTypb());
            //navireLegale.setConcession(LicForm.getLicence().get);
           // (qBateau)navireLegale.set
            navireLegale.setConcession(((qLicenceNational) LicForm.getLicence()).getQconcession());
         //   registrenavireService.save(navireLegale);
            navireLegale.setAccordPeche(null);
            LicForm.getLicence().setQnavire(navireLegale);

            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
        }
        return validateURL;
    }
    @RequestMapping(value="/NouvLicenceBatNouveau1LN",params = {"addNewLicLib"},method = RequestMethod.POST)
    public String NouvLicenceBatNouveauadd1(final  @Valid  @ModelAttribute("LicForm") creationLicForm LicForm, final BindingResult bindingresult,final ModelMap model) {
        String validateURL=null;

        qLic licAct=LicForm.getLicence();

        if(LicForm.getCategoriesRattaches()!=null) {
            LicForm.getLicence().setQcatressources(LicForm.getCategoriesRattaches());
        if(LicForm.getEnginsAuthorisees()!=null)   LicForm.getLicence().setEnginsAuthorisees(LicForm.getEnginsAuthorisees());
        }


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
            qNavireLegale navireLegale=new qNavireLegale(LicForm.getLicence().getNumimm(), LicForm.getLicence().getNomnav(),  LicForm.getLicence().getLongg(), LicForm.getLicence().getPuimot(), LicForm.getLicence().getNation(), LicForm.getLicence().getLarg(), LicForm.getLicence().getCount(), LicForm.getLicence().getNbrhomm(), LicForm.getLicence().getEff(), LicForm.getLicence().getAnneeconstr(), LicForm.getLicence().getCalpoids(), LicForm.getLicence().getGt(),LicForm.getLicence().getKw(), LicForm.getLicence().getTjb(), LicForm.getLicence().getImo(), LicForm.getLicence().getPort(),
                    LicForm.getLicence().getRadio(), LicForm.getLicence().getBalise(), LicForm.getLicence().getUpdatedAt(), LicForm.getLicence().getNumlic(), enumModePeche.ETRANGER, LicForm.getLicence().getDateDebutAuth(), LicForm.getLicence().getDateFinAuth(), LicForm.getLicence().getQcatressources(), LicForm.getLicence().getEnginsAuthorisees(),LicForm.getLicence().getNomar());
            //qNavireLegale bat=(qNavireLegale) registrenavireService.create(navireLegale);
            navireLegale.setTypb( LicForm.getLicence().getTypb());
            // (qBateau)navireLegale.set

            navireLegale.setAccordPeche(((qLicenceLibre) LicForm.getLicence()).getAccord());
            LicForm.getLicence().setQnavire(navireLegale);

            licenceService.create(LicForm.getLicence());
            model.clear();
            validateURL = "redirect:afficherLstLicence";
                    }
        return validateURL;
    }

    @RequestMapping(value="/createDocument",method = RequestMethod.GET)
    public String creerDocument(final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page) {
      List<qDoc> lstDoc=new ArrayList<qDoc>();
      frmSearchPgsForDocCrea frmSearchPgsForDocCrea1=new frmSearchPgsForDocCrea();
       int[] pages;
  //  public String  listCarnets(final lstCarnetsAchoisirForm carnetForm ,final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="searchCarnet",defaultValue = "") String searchCarnet) {
       Page<qDoc>  pgDoc=docService.findAll(page,20);
       pages=new int[pgDoc.getTotalPages()];
        for(int i=0;i<pgDoc.getTotalPages();i++) pages[i]=i;
        frmSearchPgsForDocCrea1.setLstDocuments(pgDoc);
        frmSearchPgsForDocCrea1.setPageCount(pgDoc.getTotalPages());
        frmSearchPgsForDocCrea1.setNumPages(pages);
        frmSearchPgsForDocCrea1.setPageCourante(page);
        frmSearchPgsForDocCrea1.setDisplaydatefrg(true);
        frmSearchPgsForDocCrea1.setDisplayFinListfrg(true);
        frmSearchPgsForDocCrea1.setDisplayFinListfrg(true);
        frmSearchPgsForDocCrea1.setDisplaydateRetourfrg(true);
        frmSearchPgsForDocCrea1.setDisplayButton(true);

        //  frmSearchPgsForDocCrea1.setSearchCarnet(searchCarnet);
       // frmSearchPgsForDocCrea1.setFailedAnnulation("");
         model.addAttribute("frmSearchPgsForDocCrea",frmSearchPgsForDocCrea1);
         model.addAttribute("page","");
     //   frmSearchPgsForDocCrea1.setLstDocuments();
        return "Documents/listDocuments";
   }

    @RequestMapping(value="/finList",method = RequestMethod.GET)
    public String getFinList(final ModelMap model, @RequestParam(name="debut") String debut,@RequestParam(name="typeDoc") String typeDoc) {
        // creer les categories de ressource 5 PA  de 1 a 5
        boolean displaydatefrg=false,displaybuttonfrg=false,displayButton=false;
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea=new frmSearchPgsForDocCrea();
        System.out.println("numero de page  : "+debut+"type de  doc "+typeDoc);
        List<String> numsfin=new ArrayList<>();
        List<qPageCarnet> pq=pagecarnetService.getFinList(debut,enumTypeDoc.valueOf(typeDoc));
        for(qPageCarnet q:pq) numsfin.add(q.getNumeroPage().toString());
        //  return numsfin;
        if(numsfin.size()>0)  {

            if(typeDoc.equals("Fiche_Traitement")) {
                System.out.println("jjjjjjjjj");
                frmSearchPgsForDocCrea.setDisplaydateRetourfrg(true);
                frmSearchPgsForDocCrea.setDateRetour(new Date());
            }
            else {

                frmSearchPgsForDocCrea.setDisplaydateRetourfrg(false);
                frmSearchPgsForDocCrea.setDisplayButton(false);
                displaydatefrg=false;displaybuttonfrg=false;
                displayButton=false;
            }


        }
        else  {frmSearchPgsForDocCrea.setDisplaydateRetourfrg(true);
            frmSearchPgsForDocCrea.setDisplayButton(true);}


        frmSearchPgsForDocCrea.setDateDebut(new Date());
        frmSearchPgsForDocCrea.setNumeroFin(debut);
        frmSearchPgsForDocCrea.setNumeroFin(null);
        frmSearchPgsForDocCrea.setNumsfin(numsfin);
        frmSearchPgsForDocCrea.setDisplayButton(displaybuttonfrg);
        frmSearchPgsForDocCrea.setDisplaydatefrg(displaydatefrg);


        frmSearchPgsForDocCrea.setDisplayFinListfrg(displaybuttonfrg);
        frmSearchPgsForDocCrea.setDisplayDocForm(false);
        System.out.println("fins de list  : "+numsfin.size());
        model.addAttribute("frmSearchPgsForDocCrea",frmSearchPgsForDocCrea);

        return "Documents/listDocuments :: finListFrag";

    }



        @RequestMapping(value="/finListAnnexe",method = RequestMethod.GET)
    public String getFinListAnnexe(final ModelMap model, @RequestParam(name="debut") String debut,@RequestParam(name="typeDoc") String typeDoc) {
        // creer les categories de ressource 5 PA  de 1 a 5
        boolean displaydatefrgAnnexe=false,displaybuttonfrgAnnexe=false;
        frmAnnexe frmAnnexe1=new frmAnnexe();
        System.out.println("numero de page  : "+debut+"type de  doc "+typeDoc);
        List<String> numsfin=new ArrayList<>();
        List<qPageCarnet> pq=pagecarnetService.getFinList(debut,enumTypeDoc.valueOf(typeDoc));
        for(qPageCarnet q:pq) numsfin.add(q.getNumeroPage().toString());
        //  return numsfin;
        if(numsfin.size()>0)  {displaydatefrgAnnexe=false;displaybuttonfrgAnnexe=false;}
        else  {displaydatefrgAnnexe=true;displaybuttonfrgAnnexe=true;}


        frmAnnexe1.setNumeroFinAnnexe(debut);
        //frmAnnexe1.setNumeroFinAnnexe(null);
        frmAnnexe1.setNumsfinAnnexe(numsfin);
        frmAnnexe1.setDisplayButtonAnnexe(displaybuttonfrgAnnexe);
        frmAnnexe1.setDisplaydatefrgAnnexe(displaydatefrgAnnexe);
        frmAnnexe1.setDisplayFinListfrgAnnexe(displaybuttonfrgAnnexe);

        System.out.println("fins de list  : "+numsfin.size());
        model.addAttribute("frmAnnexe",frmAnnexe1);

        return "Documents/Annexe :: finListFragAnnexe";

    }


    @RequestMapping(value="/creerDocDoublon",method = RequestMethod.GET)
    public String creerDocumentDoublon(final ModelMap model,final CreateDocForm docForm, @RequestParam(name="numeroDebut") String debut, @RequestParam(name="numeroFin") String fin,@RequestParam(name="dateDebut") String dateDepart1) {
     return "docEdit";
    }

    @RequestMapping(value="/createDocumentSave",method = RequestMethod.POST)
    @Transactional
    public String saveDocument(final  frmSearchPgsForDocCrea frmSearchPgsForDocCrea, final BindingResult result,final ModelMap model) {
    //  qDoc currentDoc=new qDoc(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
        qDoc currentDoc=frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc();
        String urlNav=null;
        List<qLic> lics=new ArrayList<qLic>();
        System.out.println("date de depart");
        System.out.println(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getDepart());
        System.out.println("numimm");
        System.out.println(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getNumImm());
       // String[] suppressedFields = result.getSuppressedFields();
       //  if (suppressedFields.length > 0) {
        //    throw new RuntimeException("Attempting to bind  disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        // }
        System.out.println(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getDepart());
        System.out.println(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getNumImm());

        //System.out.println(docForm.getCurrentDoc().getqDocPK().toString());
        if(currentDoc instanceof qDebarquement)  {
            lics=docService.retLicences(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getQseq(),frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getEnumtypedoc());
           docService.save((qDebarquement)frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            for(qPageCarnet PD:((qDebarquement) frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc()).getPages())
            pagecarnetService.save(PD);
            model.addAttribute("licencesRef", lics);
            urlNav="docEditDebarquement";
        }

        if(currentDoc instanceof qMarree)  {
          qMarreeAnnexe annex=((qMarree) frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc()).getMarreeAnnexe();
         lics=docService.retLicences(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getQseq(),frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getEnumtypedoc());
         docService.save(currentDoc);
         for(qPageCarnet PC:((qMarree) frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc()).getPages())
         pagecarnetService.save(PC);
           if(annex!=null) {
               mareeAnnexService.save(((qMarree) currentDoc).getMarreeAnnexe());
               for(qPageCarnet PM:((qMarreeAnnexe) ((qMarree) frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc()).getMarreeAnnexe()).getPages())
                   pagecarnetService.save(PM);
           }

            model.addAttribute("licencesRef", lics);
            urlNav="docEditMarree";
        }
        if(currentDoc instanceof qTraitement)  {
            saveDocValidator.validate(currentDoc, result);
        // traitementService.update((qTraitement)currentDoc);
         // docService.save((qTraitement)((frmSearchPgsForDocCrea) o).getCreateDocFormm().getCurrentDoc());
        //  pagecarnetService.save(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            for(qPageTraitement pagetr: ((qTraitement) currentDoc).getPagesTraitement()) {
        //     pagecarnetService.save(pagetr);
            }
            urlNav="docEditTraitement";
        }


        model.addAttribute("frmSearchPgsForDocCrea", frmSearchPgsForDocCrea);
        frmSearchPgsForDocCrea.getCreateDocFormm().setCurrentPage(0);
        return  urlNav;
    }

    @RequestMapping(value="/createAnnexe",method = RequestMethod.POST)
    public String createAnnexe(final frmAnnexe frmAnnexe,final ModelMap model) {
          System.out.println(frmAnnexe.getDateDepartAnnexe());
     //   model.addAttribute("frmAnnexe", frmAnnexe1);
        return "Documents/Annexe";
    }
    @RequestMapping(value="/ajouterAnnexe",method = RequestMethod.GET)
    public String ajouterAnnexe(@RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart,@RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {
        frmAnnexe  frmAnnexe1=new frmAnnexe();
        Date dateDepart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        qDocPK pk=new qDocPK(numimm,dateDepart);
        qDoc currentDoc=null;qMarreeAnnexe currMAnnexe=null;
        if(pk!=null)
        {currentDoc=docService.findById(pk);
        currMAnnexe=mareeAnnexService.findById(pk);}
        if(currMAnnexe==null) {
            qPageCarnet pc = pagecarnetService.findById(new qPageCarnetPK(currentDoc.getQseq().getDebut(), enumTypeDoc.Journal_Peche));
            frmAnnexe1.setPrefixeAnnexe(pc.getQcarnet().getPrefixNumerotation());
            frmAnnexe1.setTypeDocAnnexe(enumTypeDoc.Journal_Annexe);
            frmAnnexe1.setNumImmAnnexe(numimm);
            frmAnnexe1.setDateDepartAnnexe(depart);
            frmAnnexe1.setNomnavAnnexe(currentDoc.getQnavire().getNomnav());
            System.out.println(currentDoc.getQnavire().getNomnav());
            model.addAttribute("frmAnnexe", frmAnnexe1);
            return "Documents/Annexe";
        }
        else {
            List<qMarreeAnnexe> lstAnnexes=mareeAnnexService.findAll();
            model.addAttribute("lstAnnexes", lstAnnexes);
            return "Documents/listAnnexe";
        }


    }


    @RequestMapping(value="/editDoc",method = RequestMethod.GET)
    public String modifyDocument(@RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart,@RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea=new frmSearchPgsForDocCrea();
        String urlNav=null;
        CreateDocForm docForm=new CreateDocForm();
        Date dateDepart=null;
        String titre=null;
        List<qLic> lics=new ArrayList<qLic>();
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
          dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
          e.printStackTrace();
        }

        qDocPK dpk=new qDocPK(numimm,dateDepart);
        qDoc currentDoc =docService.findById(dpk);
        qSeq seq=currentDoc.getQseq();
        if (currentDoc instanceof qMarree) {
            lics=docService.retLicences(seq,enumTypeDoc.valueOf(typeDoc));
            docForm.setTypeDoc(enumTypeDoc.Journal_Peche);
            docForm.setTitre("Marrée");
            urlNav= "docEditMarree";
        }

        if (currentDoc instanceof qDebarquement) {
            lics=docService.retLicences(seq,enumTypeDoc.valueOf(typeDoc));
            docForm.setTypeDoc(enumTypeDoc.Fiche_Debarquement);

            titre = "Débarquement" + ((qDebarquement) currentDoc).getTypeDeb().toString();
            docForm.setTitre(titre);
            urlNav= "docEditDebarquement";
        }
        if (currentDoc instanceof qTraitement) {
            lics=null;
            docForm.setTypeDoc(enumTypeDoc.Fiche_Traitement);

            titre = "Fiche de Traitement" ;
            docForm.setTitre(titre);
            urlNav= "docEditTraitement";
        }
        docForm.setCurrentPage(0);
        docForm.setPageFin(seq.getFin());
        docForm.setCurrentDoc(currentDoc);
        System.out.println("date de depart");
        System.out.println(currentDoc.getDepart());
        System.out.println("numimm");
        System.out.println(currentDoc.getNumImm());
        frmSearchPgsForDocCrea.setCreateDocFormm(docForm);
        if(currentDoc instanceof  qMarree) {
            for (qPageMarree ju : ((qMarree) currentDoc).getPages()) {
                System.out.println(ju.getNumeroPage().toString());
                for (qJourMere qjm : ju.getListJours()) {
                    System.out.println(qjm.getDatejourMere().toString() + qjm.getDateJour().toString());

                }
            }
        }
        model.addAttribute("licencesRef", lics);
        model.addAttribute("frmSearchPgsForDocCrea", frmSearchPgsForDocCrea);
        return urlNav;

    }
    @RequestMapping(value="/listConcessionnaires",method = RequestMethod.GET)
    //public String creerDocument(final frmSearchPgsForDocCrea frmSearchPgsForDocCrea ,final ModelMap model, @RequestParam(name="numeroDebut") String debut, @RequestParam(name="numeroFin") String fin,@RequestParam(name="dateDebut") String dateDepart1,@RequestParam(name="dateRetour") String dateRetour1,BindingResult bindingresult) {
    public String listConcessionnaires(@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="searchConcessionnaire",defaultValue = "") String searchConcessionnaire,final ModelMap model) {
        int[] pages;
        listConcessionnaires listConcessionnaires=new listConcessionnaires();
        Page<qConsignataire>  pgConcessionnaires=consignataireService.findAll(page,20);

        pages=new int[pgConcessionnaires.getTotalPages()];
        for(int i=0;i<pgConcessionnaires.getTotalPages();i++) pages[i]=i;
        listConcessionnaires.setLstConcessionnaires(pgConcessionnaires);
        listConcessionnaires.setPageCount(pgConcessionnaires.getTotalPages());
        listConcessionnaires.setNumPages(pages);
        listConcessionnaires.setPageCourante(page);
        listConcessionnaires.setSearchConcessionnaire(searchConcessionnaire);
        listConcessionnaires.setFailedSuppression("");
        model.addAttribute("listConcessionnaires",listConcessionnaires);
        return "concessions/concessionnaires";
   }
    @RequestMapping(value="/listConcessions",method = RequestMethod.GET)
    //public String creerDocument(final frmSearchPgsForDocCrea frmSearchPgsForDocCrea ,final ModelMap model, @RequestParam(name="numeroDebut") String debut, @RequestParam(name="numeroFin") String fin,@RequestParam(name="dateDebut") String dateDepart1,@RequestParam(name="dateRetour") String dateRetour1,BindingResult bindingresult) {
    public String listConcessions(@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="searchConcessionnaire",defaultValue = "") String searchConcession,final ModelMap model) {
        int[] pages;
        listConcessions lstConcession=new listConcessions();
        Page<qConcession>  pgConcession=concessionService.findAll(page,20);

        pages=new int[pgConcession.getTotalPages()];
        for(int i=0;i<pgConcession.getTotalPages();i++) pages[i]=i;
        lstConcession.setLstConcession(pgConcession);
        lstConcession.setPageCount(pgConcession.getTotalPages());
        lstConcession.setNumPages(pages);
        lstConcession.setPageCourante(page);
        lstConcession.setSearchConcession(searchConcession);
        lstConcession.setFailedSuppression("");
        model.addAttribute("listConcessions",lstConcession);
        return "concessions/concessions";

    }
        @RequestMapping(value="/createAnnexe",params = {"creerAnnexe"},method = RequestMethod.POST)
    //public String creerDocument(final frmSearchPgsForDocCrea frmSearchPgsForDocCrea ,final ModelMap model, @RequestParam(name="numeroDebut") String debut, @RequestParam(name="numeroFin") String fin,@RequestParam(name="dateDebut") String dateDepart1,@RequestParam(name="dateRetour") String dateRetour1,BindingResult bindingresult) {
    public String creerAnnexe(final frmAnnexe frmAnnexe,final ModelMap model,BindingResult bindingresult) {
        qMarreeAnnexe currentDoc=null;
        Date dateDepart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(frmAnnexe.getDateDepartAnnexe());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        creerAnnexeValidateur.validate(frmAnnexe, bindingresult);

        if(!bindingresult.hasErrors()) {
            qDocPK pkk=new qDocPK(frmAnnexe.getNumImmAnnexe(),dateDepart);

            qDoc qCurrentMaree=docService.findById(pkk);

            qSeqPK spk = new qSeqPK(frmAnnexe.getNumeroDebutAnnexe(), frmAnnexe.getNumeroFinAnnexe());
            qSeq se = seqService.findById(spk);
            if (se == null) se = new qSeq(frmAnnexe.getNumeroDebutAnnexe(), frmAnnexe.getNumeroFinAnnexe(), null);

            enumTypeDoc selectedTypeDoc=frmAnnexe.getTypeDocAnnexe();

             currentDoc =  docService.creerNouvAnnexe(dateDepart,(qMarree) qCurrentMaree, spk,enumTypeDoc.Journal_Annexe);
            ((qMarree) qCurrentMaree).setMarreeAnnexe(currentDoc);
            docService.save(qCurrentMaree);
             return "redirect:editDoc?numimm="+frmAnnexe.getNumImmAnnexe()+"&typeDoc=Journal_Peche&depart="+frmAnnexe.getDateDepartAnnexe()+"&action=modifyDoc";

            }

           return "Documents/Annexe";
    }

    @RequestMapping(value="/createDocument",params = {"creer"},method = RequestMethod.POST)
    public String creerDocument(final frmSearchPgsForDocCrea frmSearchPgsForDocCrea ,final ModelMap model,BindingResult bindingresult) {
        String urlNav=null;
        List<qEnginPecheMar> enginsmarvides=new ArrayList<qEnginPecheMar>();
        List<qEnginPecheDebar> enginsdebarvides=new ArrayList<qEnginPecheDebar>();
        CreateDocForm docForm=new CreateDocForm();
        docForm.setTypeDoc(frmSearchPgsForDocCrea.getTypeDoc());
        System.out.println("ggggg");

        List<qDoc> lstDoc = new ArrayList<qDoc>();
        String titre = null, infoInterference = null;
        qDoc currentDoc = null,documentDoublon;
        String typeDoc = null;
        Object docExact = null;
        Object traitExact = null;
        List<qLic> lics=new ArrayList<>();

        creerValidateur.validate(frmSearchPgsForDocCrea, bindingresult);

        if(!bindingresult.hasErrors()) {
//            List<qTraitement> lstTraitements = new ArrayList<qTraitement>();
//            qSeqPK qseqpk = new qSeqPK(frmSearchPgsForDocCrea.getNumeroDebut(), frmSearchPgsForDocCrea.getNumeroFin());
  //          qSeq qseq = seqService.findById(qseqpk);
            // Date dateDepart=null,dateRetour=null;
            System.out.println("debut=" + frmSearchPgsForDocCrea.getNumeroDebut()+ "fin" + frmSearchPgsForDocCrea.getNumeroFin());
            // boolean flg=docService.checkSaisie(qseqpk);
            SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
            qSeqPK spk = new qSeqPK(frmSearchPgsForDocCrea.getNumeroDebut(), frmSearchPgsForDocCrea.getNumeroFin());
            qSeq se = seqService.findById(spk);
            if (se == null) se = new qSeq(frmSearchPgsForDocCrea.getNumeroDebut(), frmSearchPgsForDocCrea.getNumeroFin(), null);
            //	System.out.println(nav.getNumimm());
            Date dateDepart = null, dateRetour = null;
            dateDepart = frmSearchPgsForDocCrea.getDateDebut();
            dateRetour = frmSearchPgsForDocCrea.getDateRetour();

            enumTypeDoc selectedTypeDoc=frmSearchPgsForDocCrea.getTypeDoc();

            System.out.println(dateDepart);
            if (selectedTypeDoc.equals(enumTypeDoc.Fiche_Debarquement)|| selectedTypeDoc.equals(enumTypeDoc.Journal_Peche) || selectedTypeDoc.equals(enumTypeDoc.Fiche_Traitement) ) {
            currentDoc = (qDoc) docService.creerNouvDoc(dateDepart, dateRetour, se, selectedTypeDoc);

            lics=docService.retLicences(se,selectedTypeDoc);
            currentDoc.setBloquerDeletion(true);
            currentDoc.setDebloquerModification(false);
            if (currentDoc instanceof qMarree) {
              // docForm.setTypePeche(currentDoc.getPrefix());
                docForm.setSegmentPeche(((qMarree) currentDoc).getTypeJP().toString());
                docForm.setPrefix(currentDoc.getPrefix());
                docForm.setTypeDoc(enumTypeDoc.Journal_Peche);
                docForm.setTitre("Marrée");
                qEnginPecheMar engMar1=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Chalut,enumEnginDeb.Indefini,0,true);
                qEnginPecheMar engMar2=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Casier,enumEnginDeb.Indefini,0,false);
                qEnginPecheMar engMar3=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Nasses,enumEnginDeb.Indefini,0,false);
                qEnginPecheMar engMar4=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Pots,enumEnginDeb.Indefini,0,false);
                qEnginPecheMar engMar5=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Filet_tremail,enumEnginDeb.Indefini,0,false);
                qEnginPecheMar engMar6=new qEnginPecheMar( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Turlutte,enumEnginDeb.Indefini,0,false);
                enginsmarvides.add(engMar1); enginsmarvides.add(engMar2); enginsmarvides.add(engMar3); enginsmarvides.add(engMar4); enginsmarvides.add(engMar5);
                enginsmarvides.add(engMar6);
                ((qMarree) currentDoc).setqEngins(enginsmarvides);
                urlNav= "docEditMarree";
            }
            if (currentDoc instanceof qDebarquement) {
                docForm.setSegmentPeche(((qDebarquement) currentDoc).getTypeDeb().toString());
                docForm.setPrefix(currentDoc.getPrefix());
                docForm.setTypeDoc(enumTypeDoc.Fiche_Debarquement);
                titre = "Débarquement" + ((qDebarquement) currentDoc).getTypeDeb().toString();
                docForm.setTitre(titre);

                qEnginPecheDebar engDeb1=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Filet_maillant,0,true);
                qEnginPecheDebar  engDeb2=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Filet_Enc_ST,0,false);
                qEnginPecheDebar  engDeb3=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Palangre,0,false);
                qEnginPecheDebar  engDeb4=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Filet_tremail,0,false);
                qEnginPecheDebar  engDeb5=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Casier,0,false);
                qEnginPecheDebar  engDeb6=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Ligne,0,false);

                qEnginPecheDebar  engDeb7=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Pots,0,false);
                qEnginPecheDebar  engDeb8=new qEnginPecheDebar ( currentDoc.getNumImm(), currentDoc.getDepart(),enumEngin.Indefini,enumEnginDeb.Turlutte,0,false);
                enginsdebarvides.add(engDeb1); enginsdebarvides.add(engDeb2); enginsdebarvides.add(engDeb3); enginsdebarvides.add(engDeb4); enginsdebarvides.add(engDeb5);
                enginsdebarvides.add(engDeb6); enginsdebarvides.add(engDeb7); enginsdebarvides.add(engDeb8);
                ((qDebarquement) currentDoc).setEngins(enginsdebarvides);

                urlNav= "docEditDebarquement";
            }
                if (currentDoc instanceof  qTraitement ) {


                    //       lics=docService.retLicences(se,selectedTypeDoc);
                    currentDoc.setBloquerDeletion(true);
                    currentDoc.setDebloquerModification(false);
                    if (currentDoc instanceof qTraitement) {
                        qTraitement trEnCours=((qTraitement)currentDoc);
                        // docForm.setTypePeche(currentDoc.getPrefix());
                        docForm.setSegmentPeche(trEnCours.getSegPeche().toString());
                        docForm.setPrefix(trEnCours.getPrefix());
                        docForm.setTypeDoc(enumTypeDoc.Fiche_Traitement);
                        docForm.setTitre("Fiche de traitement");
                        docForm.setSegmentPeches(trEnCours.getSegs());
                        docForm.setPagesTraitements(trEnCours.getPagesTraitement());
                        docForm.setQteExportees(trEnCours.getqQteExp());
                        docForm.setQteTraitees(trEnCours.getqQteTraitees());
                        docForm.setQteDechu(trEnCours.getQteDechu());


                        urlNav= "docEditTraitement";
                    }
                }
            docForm.setCurrentPage(0);
            docForm.setPageFin(frmSearchPgsForDocCrea.getNumeroFin());
            docForm.setCurrentDoc(currentDoc);
            System.out.println("date de depart");
            System.out.println(currentDoc.getDepart());
            System.out.println("numimm");
            System.out.println(currentDoc.getNumImm());
            frmSearchPgsForDocCrea.setCreateDocFormm(docForm);
            model.addAttribute("frmSearchPgsForDocCrea", frmSearchPgsForDocCrea);
            model.addAttribute("page", "");
            model.addAttribute("licencesRef", lics);
            }

            return urlNav;
        }

    else {
            return "Documents/listDocuments";
        }

    }


    @RequestMapping(value="/modifyModel",method = RequestMethod.GET)
    public String modifyModel(final ModelMap model,@RequestParam(name="prefix") String prefix,@RequestParam(name="typeDoc") String typeDoc) {
        qPrefixPK prfpk=new qPrefixPK(prefix,enumTypeDoc.valueOf(typeDoc));
        qModelJP modelEncours=modeljpService.findById(prfpk);
        model.addAttribute("editedModel",modelEncours);
        return "Documents/editModel";
    }

    @RequestMapping(value="/parametrerModels",method = RequestMethod.GET)
    public String  parametrerModels(final ModelMap model) {
        int[] pages;
        lstModelsForm modelsForm=new lstModelsForm();
        List<qModelJP>  pgModel=modeljpService.findAll();

        pages=new int[pgModel.size()];
        for(int i=0;i<pgModel.size();i++) pages[i]=i;
        modelsForm.setLstModels(pgModel);

        modelsForm.setNumPages(pages);
        modelsForm.setPageCourante(0);

        model.addAttribute("listModelsfrm",modelsForm);
        return "Documents/listModels";
    }


    @RequestMapping(value="/saveModel",params={"addEspece"},method = RequestMethod.POST)
    public String saveModel(final @ModelAttribute("editedModel") qModelJP editedModel, final ModelMap model) {

       if(editedModel.getEspecestypees()!=null) editedModel.getEspecestypees().add(new qEspeceTypee());
        else {
           List<qEspeceTypee> espts=new ArrayList<>();
           espts.add(new qEspeceTypee());
           editedModel.setEspecestypees(espts);
      }
        model.addAttribute("editedModel",editedModel);
        return "Documents/editModel";

    }
    @RequestMapping(value="/saveModel",params={"deleteEspece"},method = RequestMethod.POST)
    public String deleteEspeceTypee(final @ModelAttribute("editedModel") qModelJP editedModel,final HttpServletRequest req, final ModelMap model) {
        final Integer rowId = Integer.valueOf(req.getParameter("deleteEspece"));

        if(editedModel.getEspecestypees()!=null) editedModel.getEspecestypees().remove(rowId.intValue());
        model.addAttribute("editedModel",editedModel);
        return "Documents/editModel";

    }

    @RequestMapping(value="/saveModel",params={"saveEspece"},method = RequestMethod.POST)
    public String saveModell(final @ModelAttribute("editedModel") qModelJP editedModel, final ModelMap model,BindingResult bindingresult) {
        qPrefix pr=null; qPrefixPK prefPK=null;qPrefix prefcurrent=null;
        List<qEspeceTypee> espTypees=null;
        qModelJP jp=null;
        modelValidateur.validate(editedModel, bindingresult);

        if(!bindingresult.hasErrors()) {
            prefPK=new qPrefixPK(editedModel.getPrefix(),editedModel.getTypeDoc());
            prefcurrent=prefService.findById(prefPK);
            if(prefcurrent!=null)
            { jp=new qModelJP(prefcurrent,editedModel.getEspecestypees());
            modeljpService.save(jp);}
            model.addAttribute("editedModel",editedModel);
              }
        return "Documents/editModel";
    }

    @RequestMapping(value="/debloquerSuppressionAnnexe",method = RequestMethod.GET)
    public String debloquerAnnexe(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qMarreeAnnexe currentMareeAnnexe= mareeAnnexService.findById(dpk);
       // qSeq seq = currentMareeAnnexe.getQseq();
        currentMareeAnnexe.setBloquerDeletion(true);
        mareeAnnexService.save(currentMareeAnnexe);
        redirectAttributes.addFlashAttribute("blocagefeedback", "blocage de suppression est enlevée");
        return "redirect:ajouterAnnexe?numimm="+currentMareeAnnexe.getNumImm()+"&typeDoc=Journal_Peche&depart="+currentMareeAnnexe.getDepart()+"&action=ajouterAnnexe";

    }
    @RequestMapping(value="/debloquerSuppression",method = RequestMethod.GET)
    public String debloquerDoc(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qDoc currentDoc = docService.findById(dpk);
        qSeq seq = currentDoc.getQseq();
        currentDoc.setBloquerDeletion(true);
        docService.save(currentDoc);
        redirectAttributes.addFlashAttribute("blocagefeedback", "blocage de suppression est enlevée");
        return "redirect:createDocument?firstEtp=0";

    }

    @RequestMapping(value="/bloquerModification",method = RequestMethod.GET)
    public String debloquerModification1(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qDoc currentDoc = docService.findById(dpk);
        qSeq seq = currentDoc.getQseq();
        currentDoc.setDebloquerModification(true);
        docService.save(currentDoc);
        redirectAttributes.addFlashAttribute("deblocagefeedback", "deblocage de modification est appliquée");
        return "redirect:createDocument?firstEtp=0";

    }

    @RequestMapping(value="/debloquerModification",method = RequestMethod.GET)
    public String debloquerModification(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qDoc currentDoc = docService.findById(dpk);
        qSeq seq = currentDoc.getQseq();
        currentDoc.setDebloquerModification(false);
        docService.save(currentDoc);
        redirectAttributes.addFlashAttribute("deblocagefeedback", "deblocage de modification est desactivé");
        return "redirect:createDocument?firstEtp=0";

    }

    @RequestMapping(value="/bloquerSuppressionAnnexe",method = RequestMethod.GET)
    public String bloquerAnnexe(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        //frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
      //  CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qMarreeAnnexe currentDoc = mareeAnnexService.findById(dpk);
    //   qSeq seq = currentDoc.getQseq();
        currentDoc.setBloquerDeletion(false);
        mareeAnnexService.save(currentDoc);
        redirectAttributes.addFlashAttribute("blocagefeedback", "blocage de suppression est appliquée");
     //   return "redirect:createDocument?firstEtp=0";
        return "redirect:ajouterAnnexe?numimm="+currentDoc.getNumImm()+"&typeDoc=Journal_Peche&depart="+currentDoc.getDepart()+"&action=ajouterAnnexe";


    }
    @RequestMapping(value="/bloquerSuppression",method = RequestMethod.GET)
    public String bloquerDoc(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;

        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qDoc currentDoc = docService.findById(dpk);
        qSeq seq = currentDoc.getQseq();
        currentDoc.setBloquerDeletion(false);
        docService.save(currentDoc);
        redirectAttributes.addFlashAttribute("blocagefeedback", "blocage de suppression est appliquée");
        return "redirect:createDocument?firstEtp=0";

    }

    @RequestMapping(value="/deleteDoc",method = RequestMethod.GET)
    public String deleteDoc(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;
        List<qLic> lics = new ArrayList<qLic>();
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qDoc currentDoc = docService.findById(dpk);
        qSeq seq = currentDoc.getQseq();
        //     lics=docService.retLicences(seq);
        //modelValidateur.validate(currentDoc, bindingresult);
        if (currentDoc.isBloquerDeletion() == false) {
            redirectAttributes.addFlashAttribute("deletefeedback", "suppression impossible");



        } else {

            docService.delete(currentDoc.getqDocPK());
            redirectAttributes.addFlashAttribute("deletefeedback", "supprime");
    }
         return "redirect:createDocument?firstEtp=0";

    }

    @RequestMapping(value="/deleteAnnexe",method = RequestMethod.GET)
    public String deleteAnnexe(final RedirectAttributes redirectAttributes, @RequestParam(name="numimm") String numimm, @RequestParam(name="depart") String depart, final ModelMap model) {
   //     frmSearchPgsForDocCrea frmSearchPgsForDocCrea = new frmSearchPgsForDocCrea();
        String urlNav = null;
        System.out.println("llllll");
        CreateDocForm docForm = new CreateDocForm();
        Date dateDepart = null;
        String titre = null;
        List<qLic> lics = new ArrayList<qLic>();
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse(depart);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        qDocPK dpk = new qDocPK(numimm, dateDepart);
        qMarreeAnnexe currentDoc = mareeAnnexService.findById(dpk);
        qMarree currentDocM = (qMarree)docService.findById(dpk);
     //   qSeq seq = currentDoc.getQseq();
        //     lics=docService.retLicences(seq);
        //modelValidateur.validate(currentDoc, bindingresult);
        if (currentDoc.isBloquerDeletion() == false) {
            redirectAttributes.addFlashAttribute("deletefeedback", "la suppression est bloquée");
        } else {
            currentDoc.setMarreePrincipal(null);
            currentDocM.setMarreeAnnexe(null);
            mareeAnnexService.save(currentDoc);
            mareeAnnexService.delete(currentDoc.getqDocPK());
            docService.save(currentDocM);
            redirectAttributes.addFlashAttribute("deletefeedback", "supprime");
        }
     //   return "redirect:createDocument?firstEtp=0";lllllll
        return "redirect:ajouterAnnexe?numimm="+currentDoc.getNumImm()+"&typeDoc=Journal_Peche&depart="+currentDoc.getDepart()+"&action=ajouterAnnexe";

    }

    @RequestMapping(value="/genererCATEGMODELS",method = RequestMethod.GET)
    public String genererCATEGMODELS(final ModelMap model) {
    {
        qPrefix prefixPA=new qPrefix("PA",enumTypeDoc.Fiche_Debarquement,10,"typde de document pour la pêche artisanal");
        qPrefix prefixPC=new qPrefix("PC",enumTypeDoc.Fiche_Debarquement,10,"typde de document pour la pêche cotiere");

        qPrefix prefixPE=new qPrefix("PE",enumTypeDoc.Journal_Peche,10,"typde de document pour la pêche hautirier pelagique");
        qPrefix prefixDEM=new qPrefix("DEM",enumTypeDoc.Journal_Peche,10,"typde de document pour la pêche hautirier demersal");
        qPrefix prefixCRUST=new qPrefix("CRUST",enumTypeDoc.Journal_Peche,10,"typde de document pour la pêche hautirier crustacé");
        qPrefix prefixCEPH=new qPrefix("CEPH",enumTypeDoc.Journal_Peche,10,"typde de document pour la pêche hautirier ceph ");
        qPrefix prefixIND=new qPrefix("IND",enumTypeDoc.Journal_Peche,10,"typde de document pour la pêche hautirier ceph ");

        qPrefix prefixTR=new qPrefix("TR",enumTypeDoc.Fiche_Traitement,10,"typde de document pour les usines");

        qPrefix prefixPE2=new qPrefix("PE",enumTypeDoc.Journal_Annexe,10,"typde de document pour la pêche hautirier pelagique");
        qPrefix prefixDEM2=new qPrefix("DEM",enumTypeDoc.Journal_Annexe,10,"typde de document pour la pêche hautirier demersal");
        qPrefix prefixCRUST2=new qPrefix("CRUST",enumTypeDoc.Journal_Annexe,10,"typde de document pour la pêche hautirier crustacé");
        qPrefix prefixCEPH2=new qPrefix("CEPH",enumTypeDoc.Journal_Annexe,10,"typde de document pour la pêche hautirier ceph ");


        prefService.save(prefixPA);
        prefService.save(prefixPC);
        prefService.save(prefixPE);
        prefService.save(prefixDEM);
        prefService.save(prefixCRUST);
        prefService.save(prefixCEPH);

        prefService.save(prefixPE2);
        prefService.save(prefixDEM2);
        prefService.save(prefixCRUST2);
        prefService.save(prefixCEPH2);
        prefService.save(prefixIND);
        prefService.save(prefixTR);





        qTypeConcession paCeph = new qTypeConcessionArtisanal(1, prefixPA, "Artisanal Cephalopode");
        qTypeConcession paCrust = new qTypeConcessionArtisanal(2, prefixPA, "Artisanal Crustacé");
        qTypeConcession paDem = new qTypeConcessionArtisanal(3, prefixPA, "Artisanal Demersal");
        qTypeConcession paPel = new qTypeConcessionArtisanal(4, prefixPA, "Artisanal pelagique");
        qTypeConcession paAlAut = new qTypeConcessionArtisanal(5, prefixPA, "Artisanal Algues et autres Mollusques");

        qEnginsLicence qEng1 = new qEnginsLicence(enumEnginDeb.Casier, enumEngin.Indefini, 70,false);
        qEnginsLicence qEng2 = new qEnginsLicence(enumEnginDeb.Palangre, enumEngin.Indefini, 30,false);

        List<qEnginsLicence> qEnginsDeb = new ArrayList<qEnginsLicence>();
        qEnginsDeb.add(qEng1);
        qEnginsDeb.add(qEng2);

        List<qEnginsLicence> qEnginsdiff = new ArrayList<qEnginsLicence>();
        qEnginsLicence f = enginsLicenceService.findById(new qEnginsLicencePK(enumEnginDeb.Casier, enumEngin.Indefini));
        qEnginsLicence d = enginsLicenceService.findById(new qEnginsLicencePK(enumEnginDeb.Palangre, enumEngin.Indefini));
        qEnginsdiff.add(f);
        qEnginsdiff.add(d);

        enginsLicenceService.create(qEng1);
        enginsLicenceService.create(qEng2);

        typeconcessionService.create(paCeph);
        typeconcessionService.create(paCrust);
        typeconcessionService.create(paDem);
        typeconcessionService.create(paPel);
        typeconcessionService.create(paAlAut);

        qCategRessource qPACep = new qCategRessource(typeconcessionService.findById(1), enumSupport.Collectif, null, qEnginsdiff,null);

        qCategRessource qPACrust = new qCategRessource(paCrust, enumSupport.Collectif, null, qEnginsdiff,null);

        qCategRessource qPADem = new qCategRessource(paDem, enumSupport.Collectif, null, qEnginsdiff,null);

        qCategRessource qPAPel = new qCategRessource(paPel, enumSupport.Collectif, null, qEnginsdiff,null);

        qCategRessource qPAAlAut = new qCategRessource(paAlAut, enumSupport.Collectif, null, qEnginsdiff,null);


        categService.create(qPACep);
        categService.create(qPACrust);
        categService.create(qPADem);
        categService.create(qPAPel);
        categService.create(qPAAlAut);
        //-------------------------------------------------------------------------------------------



        // creer les categories de ressource 10 PC de 6 a 12
        qTypeConcessionCotiere pcNPCeph = new qTypeConcessionCotiere(6, prefixCEPH, "Cepholopode Non Pontée", enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere pcNPCrust = new qTypeConcessionCotiere(7, prefixCRUST, "Crustaces Non Pontée", enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere pcNPDem = new qTypeConcessionCotiere(8, prefixDEM, "Demersaux Non Pontée", enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere pcNPPelSenneursM26 = new qTypeConcessionCotiere(9, prefixPE, "Pelagique Non Pontée" , enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere pcNPAutreMol = new qTypeConcessionCotiere(12,prefixIND, "Autres_Mollusques Non Pontée", enumTypePechCotiere.NON_PONTEE);
        typeconcessionService.create(pcNPCeph);
        typeconcessionService.create(pcNPCrust);
        typeconcessionService.create(pcNPDem);
        typeconcessionService.create(pcNPPelSenneursM26);
        typeconcessionService.create(pcNPAutreMol);

        qCategRessource qPCNPCep = new qCategRessource(pcNPCeph, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCNPCrust = new qCategRessource(pcNPCrust, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCNPDem = new qCategRessource(pcNPDem, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCNPPel1 = new qCategRessource(pcNPPelSenneursM26, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCNPAlAut = new qCategRessource(pcNPAutreMol, enumSupport.Collectif, null, qEnginsdiff,null);


        categService.create(qPCNPCep);
        categService.create(qPCNPCrust);
        categService.create(qPCNPDem);
        categService.create(qPCNPPel1);
        categService.create(qPCNPAlAut);

        // peche cotier pontee 13 a 19


        qTypeConcessionCotiere pcPCeph = new qTypeConcessionCotiere(13, prefixPC, "Cepholopode Pontée", enumTypePechCotiere.PONTEE);
        qTypeConcessionCotiere pcPCrust = new qTypeConcessionCotiere(14, prefixPC, "Crustaces Pontée", enumTypePechCotiere.PONTEE);
        qTypeConcessionCotiere pcPDem = new qTypeConcessionCotiere(15, prefixPC, "Demersaux Pontée", enumTypePechCotiere.PONTEE);
        qTypeConcessionCotiere pcPPelSenneursM26 = new qTypeConcessionCotiere(16, prefixPC, "Pelagique Pontée", enumTypePechCotiere.PONTEE);
        qTypeConcessionCotiere pcPAutreMol = new qTypeConcessionCotiere(19, prefixPC, "Autres_Mollusques Pontée", enumTypePechCotiere.PONTEE);

        typeconcessionService.create(pcPCeph);
        typeconcessionService.create(pcPCrust);
        typeconcessionService.create(pcPDem);
        typeconcessionService.create(pcPPelSenneursM26);
        typeconcessionService.create(pcPAutreMol);


        qCategRessource qPCPCep = new qCategRessource(pcPCeph, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCPCrust = new qCategRessource(pcPCrust, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCPDem = new qCategRessource(pcPDem, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCPPel1 = new qCategRessource(pcPPelSenneursM26, enumSupport.Collectif, null, qEnginsdiff,null);
        qCategRessource qPCPAlAut = new qCategRessource(pcPAutreMol, enumSupport.Collectif, null, qEnginsdiff,null);

        categService.create(qPCPCep);
        categService.create(qPCPCrust);
        categService.create(qPCPDem);
        categService.create(qPCPPel1);
        categService.create(qPCPAlAut);

        // creer les categories de ressource 9 PH de 19 a  27


        qTypeConcessionHautiriere phNCeph = new qTypeConcessionHautiriere(20, prefixCEPH, "Cephalopode-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNAutres = new qTypeConcessionHautiriere(21, prefixIND, "Autres_Mollusques-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNCrab = new qTypeConcessionHautiriere(22, prefixIND, "Crabe_profond-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNCrv = new qTypeConcessionHautiriere(23, prefixIND, "Crevettes-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNLangRose = new qTypeConcessionHautiriere(24, prefixIND, ".Langouste_rose-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNMerlu = new qTypeConcessionHautiriere(25, prefixIND, "Merlus-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNPel = new qTypeConcessionHautiriere(26, prefixPE, "Pelagique-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNDemAQM = new qTypeConcessionHautiriere(27, prefixDEM, "Demersaux_autre_que_merlu-National", enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere phNThon = new qTypeConcessionHautiriere(28, prefixIND, "Thons-National", enumTypePecheHautiriere.National);

        typeconcessionService.create(phNCeph);
        typeconcessionService.create(phNAutres);
        typeconcessionService.create(phNCrab);
        typeconcessionService.create(phNCrv);
        typeconcessionService.create(phNLangRose);
        typeconcessionService.create(phNMerlu);
        typeconcessionService.create(phNPel);
        typeconcessionService.create(phNDemAQM);
        typeconcessionService.create(phNThon);


        qCategRessource qRCphNCeph = new qCategRessource(phNCeph, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNAutres = new qCategRessource(phNAutres, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNCrab = new qCategRessource(phNCrab, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNCrv = new qCategRessource(phNCrv, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNLangRose = new qCategRessource(phNMerlu, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNMerlu = new qCategRessource(phNPel, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNPel = new qCategRessource(phNDemAQM, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphNDemAQM = new qCategRessource(phNThon, enumSupport.Individuel, null, qEnginsdiff,null);
        categService.create(qRCphNCeph);
        categService.create(qRCphNAutres);
        categService.create(qRCphNCrab);
        categService.create(qRCphNCrv);
        categService.create(qRCphNLangRose);
        categService.create(qRCphNMerlu);
        categService.create(qRCphNPel);
        categService.create(qRCphNDemAQM);


        qTypeConcessionHautiriere phACeph = new qTypeConcessionHautiriere(29, prefixCEPH, "Cephalopode-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phAAutres = new qTypeConcessionHautiriere(30, prefixIND, "Autres_Mollusques-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phACrab = new qTypeConcessionHautiriere(31, prefixIND, "Crabe_profond-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phACrv = new qTypeConcessionHautiriere(32, prefixIND, "Crevettes-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phALangRose = new qTypeConcessionHautiriere(33, prefixIND, "Langouste_rose-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phAMerlu = new qTypeConcessionHautiriere(34, prefixIND, "Merlus-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phAPel = new qTypeConcessionHautiriere(35, prefixIND, "Pelagique-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phADemAQM = new qTypeConcessionHautiriere(36, prefixDEM, "Demersaux_autre_que_merlu-Affreté", enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere phAThon = new qTypeConcessionHautiriere(37, prefixIND, "Thons-Affreté", enumTypePecheHautiriere.Affraite);

        typeconcessionService.create(phACeph);
        typeconcessionService.create(phAAutres);
        typeconcessionService.create(phACrab);
        typeconcessionService.create(phACrv);
        typeconcessionService.create(phALangRose);
        typeconcessionService.create(phAMerlu);
        typeconcessionService.create(phAPel);
        typeconcessionService.create(phADemAQM);
        typeconcessionService.create(phAThon);

        qCategRessource qRCphACeph = new qCategRessource(phACeph, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphAAutres = new qCategRessource(phAAutres, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphACrab = new qCategRessource(phACrab, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphACrv = new qCategRessource(phACrv, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qphALangRose = new qCategRessource(phALangRose, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphAMerlu = new qCategRessource(phAMerlu, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphAPel = new qCategRessource(phAPel, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphADemAQM = new qCategRessource(phADemAQM, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphAThon = new qCategRessource(phAThon, enumSupport.Individuel, null, qEnginsdiff,null);


        categService.create(qRCphACeph);
        categService.create(qRCphAAutres);
        categService.create(qRCphACrab);
        categService.create(qRCphACrv);
        categService.create(qphALangRose);
        categService.create(qRCphAMerlu);
        categService.create(qRCphAPel);
        categService.create(qRCphADemAQM);
        categService.create(qRCphAThon);

        qTypeConcessionHautiriere phLCeph = new qTypeConcessionHautiriere(38, prefixCEPH, "Cephalopode-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLAutres = new qTypeConcessionHautiriere(39, prefixIND, "Autres_Mollusques-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLCrab = new qTypeConcessionHautiriere(40, prefixIND, "Crabe_profond-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLCrv = new qTypeConcessionHautiriere(41, prefixIND, "Crevettes-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLLangRose = new qTypeConcessionHautiriere(42, prefixIND, "Langouste_rose-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLMerlu = new qTypeConcessionHautiriere(43, prefixIND,"Merlus-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLPel = new qTypeConcessionHautiriere(44, prefixIND, "Pelagique-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLDemAQM = new qTypeConcessionHautiriere(45, prefixDEM, "Demersaux_autre_que_merlu-Libre", enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere phLThon = new qTypeConcessionHautiriere(46, prefixIND, "Thons-Libre", enumTypePecheHautiriere.Licence);

        typeconcessionService.create(phLCeph);
        typeconcessionService.create(phLAutres);
        typeconcessionService.create(phLCrab);
        typeconcessionService.create(phLCrv);
        typeconcessionService.create(phLLangRose);
        typeconcessionService.create(phLMerlu);
        typeconcessionService.create(phLPel);
        typeconcessionService.create(phLDemAQM);
        typeconcessionService.create(phLThon);

        qCategRessource qRCphLCeph = new qCategRessource(phLCeph, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphLAutres = new qCategRessource(phLAutres, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphLCrab = new qCategRessource(phLCrab, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCphLCrv = new qCategRessource(phLCrv, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qphLLangRose = new qCategRessource(phLLangRose, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCLphALangRose = new qCategRessource(phLMerlu, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCLphAMerlu = new qCategRessource(phLPel, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCLphAPel = new qCategRessource(phLDemAQM, enumSupport.Individuel, null, qEnginsdiff,null);
        qCategRessource qRCLphADemAQM = new qCategRessource(phLThon, enumSupport.Individuel, null, qEnginsdiff,null);
        categService.create(qRCphLCeph);
        categService.create(qRCphLAutres);
        categService.create(qRCphLCrab);
        categService.create(qRCphLCrv);
        categService.create(qphLLangRose);
        categService.create(qRCLphALangRose);
        categService.create(qRCLphAMerlu);
        categService.create(qRCLphAPel);
        categService.create(qRCLphADemAQM);
        //  creer les categories de ressource 9 PH

      	qModelJP qmodelPA=new qModelJP(prefixPA,null);
       	qModelJP qmodelPC=new qModelJP(prefixPC,null);
       	qModelJP qmodelJPPE=new qModelJP(prefixPE,null);
       	qModelJP qmodelJPDEM=new qModelJP(prefixDEM,null);
	   	qModelJP qmodelJPCRUS=new qModelJP(prefixCRUST,null);
       	qModelJP qmodelJPCEPH=new qModelJP(prefixCEPH,null);

        modeljpService.create(qmodelPA);
        modeljpService.create(qmodelPC);
        modeljpService.create(qmodelJPCEPH);
        modeljpService.create(qmodelJPPE);
        modeljpService.create(qmodelJPCRUS);
        modeljpService.create(qmodelJPDEM);
        // artisanal de 1 à 5
        // COTIER DE 6 à 19
        // HAUTIRIERE de 20 à 46
        List<qCategRessource> qcatList1 = new ArrayList<qCategRessource>();
        List<qCategRessource> qc = new ArrayList<qCategRessource>();
        qCategPK g1 = new qCategPK(1);
        qCategPK g2 = new qCategPK(2);
        qCategPK g3 = new qCategPK(3);
        qCategPK g4 = new qCategPK(4);
        qCategPK g5 = new qCategPK(5);
        qCategRessource q1 = categService.findById(1);
        qCategRessource q2 = categService.findById(2);
        qCategRessource q3 = categService.findById(3);
        qCategRessource q4 = categService.findById(4);
        qCategRessource q5 = categService.findById(5);


        qc.add(q1);
        qc.add(q2);
        qc.add(q3);
        qc.add(q4);
        qc.add(q5);
        qTypeLic qtyplic1 = new qTypeLic("A", "A", "Affreté artisanal");
        qTypeLic qtyplic2 = new qTypeLic("A", "C", "Affreté collecte pêche artisanal");
        qTypeLic qtyplic3 = new qTypeLic("A", "D", "Affreté demersal(poisson+ ceph)");
        qTypeLic qtyplic4 = new qTypeLic("A", "E", "Affreté demersal(crust+ ceph)");
        qTypeLic qtyplic5 = new qTypeLic("A", "L", "Affreté Langouste");
        qTypeLic qtyplic6 = new qTypeLic("A", "M", "Affreté Merlu");
        qTypeLic qtyplic7 = new qTypeLic("A", "P", "Affreté Pelagique");
        qTypeLic qtyplic8 = new qTypeLic("A", "T", "Affreté thon");
        qTypeLic qtyplic9 = new qTypeLic("A", "B", "Affreté crabes");
        qTypeLic qtyplic10 = new qTypeLic("A", "Q", "Affreté coquillage");
        qTypeLic qtyplic11 = new qTypeLic("A", "R", "Affreté recherche");
        qTypeLic qtyplic12 = new qTypeLic("A", "S", "Affreté requin");
        qTypeLic qtyplic13 = new qTypeLic("A", "V", "Affreté crevettes");
        qTypeLic qtyplic14 = new qTypeLic("A", "1", "Affreté crevettes+langoustes");
        qTypeLic qtyplic15 = new qTypeLic("A", "F", "Affreté demersal+lang verte");


        qTypeLic qtyplic20 = new qTypeLic("L", "A", "Licence artisanal");
        qTypeLic qtyplic21 = new qTypeLic("L", "C", "Licence collecte peche artisanal");
        qTypeLic qtyplic22 = new qTypeLic("L", "D", "Licence dem poss+ceph");
        qTypeLic qtyplic23 = new qTypeLic("L", "L", "Licence langouste");
        qTypeLic qtyplic24 = new qTypeLic("L", "M", "Licence merlu");
        qTypeLic qtyplic25 = new qTypeLic("L", "P", "Licence pelagique");
        qTypeLic qtyplic26 = new qTypeLic("L", "T", "Licence thon");
        qTypeLic qtyplic27 = new qTypeLic("L", "V", "Licence crust sauf langouste");
        qTypeLic qtyplic28 = new qTypeLic("L", "B", "Licence crabes");
        qTypeLic qtyplic29 = new qTypeLic("L", "H", "Licence esp dem profond");
        qTypeLic qtyplic30 = new qTypeLic("L", "Q", "Licence coquillage");
        qTypeLic qtyplic31 = new qTypeLic("L", "R", "Licence recherche");
        qTypeLic qtyplic32 = new qTypeLic("L", "S", "Licence requin");
        qTypeLic qtyplic33 = new qTypeLic("L", "1", "Licence crevettes +langouste");


        qTypeLic qtyplic35 = new qTypeLic("N", "A", "National artisanal");
        qTypeLic qtyplic36 = new qTypeLic("N", "C", "National collecte peche artisanal");
        qTypeLic qtyplic37 = new qTypeLic("N", "D", "National dem poiss+ ceph");
        qTypeLic qtyplic38 = new qTypeLic("N", "E", "National dem cep+ crust");
        qTypeLic qtyplic39 = new qTypeLic("N", "L", "National langouste");
        qTypeLic qtyplic40 = new qTypeLic("N", "M", "National merlu");
        qTypeLic qtyplic41 = new qTypeLic("N", "P", "National pelagique");
        qTypeLic qtyplic42 = new qTypeLic("N", "T", "National thon");
        qTypeLic qtyplic43 = new qTypeLic("N", "B", "National crabes");
        qTypeLic qtyplic44 = new qTypeLic("N", "Q", "National coquillage");
        qTypeLic qtyplic45 = new qTypeLic("N", "R", "National recherche");
        qTypeLic qtyplic46 = new qTypeLic("N", "S", "National requin");
        qTypeLic qtyplic47 = new qTypeLic("N", "V", "National crevettes");
        qTypeLic qtyplic48 = new qTypeLic("N", "1", "National crevettes+langouste");

        qTypeLic qtyplic49 = new qTypeLic("L", "G", "Licence Esp dem autre que merlu");
        qTypeLic qtyplic50 = new qTypeLic("L", "E", "Licence Esp dem");
        qTypeLic qtyplic51 = new qTypeLic("A", "V", "AU");
        qTypeLic qtyplic52 = new qTypeLic("L", "I", "Licence Thons+Espadons");
        qTypeLic qtyplic53 = new qTypeLic("N", "N", "NN");
        qTypeLic qtyplic54 = new qTypeLic("N", "I", "NI");
        qTypeLic qtyplic55 = new qTypeLic("N", "U", "NU");
        qTypeLic qtyplic56 = new qTypeLic("L", "T", "LT");
        qTypeLic qtyplic57 = new qTypeLic("X", "P", "XP");
        qTypeLic qtyplic58 = new qTypeLic("Z", "Z", "INDET");
        qTypeLic qtyplic59 = new qTypeLic("L", "0", "Licence INDET");
        qTypeLic qtyplic60 = new qTypeLic("N", "0", "National");
        qTypeLic qtyplic61 = new qTypeLic("A", "0", "Affrete INDET");
        qTypeLic qtyplic62 = new qTypeLic("A", "H", "National ravitalleur pel");

        typelicService.create(qtyplic1);
        typelicService.create(qtyplic2);
        typelicService.create(qtyplic3);
        typelicService.create(qtyplic4);
        typelicService.create(qtyplic5);
        typelicService.create(qtyplic6);
        typelicService.create(qtyplic7);
        typelicService.create(qtyplic8);
        typelicService.create(qtyplic9);
        typelicService.create(qtyplic10);
        typelicService.create(qtyplic11);
        typelicService.create(qtyplic12);
        typelicService.create(qtyplic13);
        typelicService.create(qtyplic14);
        typelicService.create(qtyplic15);
        typelicService.create(qtyplic20);
        typelicService.create(qtyplic21);
        typelicService.create(qtyplic23);
        typelicService.create(qtyplic24);
        typelicService.create(qtyplic25);
        typelicService.create(qtyplic26);
        typelicService.create(qtyplic27);
        typelicService.create(qtyplic28);
        typelicService.create(qtyplic29);
        typelicService.create(qtyplic30);
        typelicService.create(qtyplic31);
        typelicService.create(qtyplic32);
        typelicService.create(qtyplic33);

        qZone qZone1 = new qZone(1, "CEE 96 Crustacé sauf Langoust");
        qZone qZone2 = new qZone(1, "CEE 96 Crustacé sauf Langoust");
        qZone qZone3 = new qZone(2, "CEE 96 Pelagique (Chalut)");
        qZone qZone4 = new qZone(3, "CEE 96 Langoust (Casier)");
        qZone qZone5 = new qZone(4, "CEE 96 Thon (palangre,canne)");
        qZone qZone6 = new qZone(5, "CEE 96 Thon (senne)");
        qZone qZone7 = new qZone(6, "CEE 96 Thon(canne ) + appait(sne)");
        qZone qZone8 = new qZone(7, " CEE 96 Cephalopode (chalut)");
        qZone qZone9 = new qZone(8, " CEE 96 Thon(canne ) + appait(sne)");
        qZone qZone10 = new qZone(9, "CEE 96 Cephalopode (chalut)");
        qZone qZone11 = new qZone(10, "CEE 96 Espéce demersal - Merlu (Chalut)");
        qZone qZone12 = new qZone(11, "CEE 96 Espéce demersal - Merlu (Autres)");
        qZone qZone13 = new qZone(12, "RIM - Japon Thon + Espadon)");
        qZone qZone14 = new qZone(13, "ZAP 1 (Petit Pelagique)");
        qZone qZone15 = new qZone(14, "ZAP 2 (Thon)");
        qZone qZone16 = new qZone(15, "ZAP 3 (RIM)");
        qZone qZone17 = new qZone(16, " ZAP 3 (CEE)");
        qZone qZone18 = new qZone(17, "ZAP 4(Demersaux autre que le merlu)");
        qZone qZone19 = new qZone(18, "ZAP 5 (Crabe prophond)");
        qZone qZone20 = new qZone(19, "ZAP6 (Maerlu)");
        qZone qZone21 = new qZone(20, "ZAP7 (Crevette Gamba)");
        qZone qZone22 = new qZone(21, "ZAP8 (Demersaux)");
        qZone qZone23 = new qZone(22, "ZAP9 (Langoust Rose)");
        qZone qZone24 = new qZone(23, "ZAP 10 (Langostinos)");
        qZone qZone25 = new qZone(24, "Artisanal");
        zoneService.create(qZone7);

        qNation qnation1 = new qNation("Mauritanie");
        qNation qnation2 = new qNation("France");
        qNation qnation3 = new qNation("Espagne");
        qNation qnation4 = new qNation("Hollande");
        nationService.create(qnation1);
        nationService.create(qnation2);
        nationService.create(qnation3);
        nationService.create(qnation4);
    }

     return "structure";
    }



    @RequestMapping(value="/listPrefixes",method = RequestMethod.GET)
    public String listPrefixes(final ModelMap model) {

       List<qPrefix> prefixes=prefService.findAll();

        model.addAttribute("prefixes",prefixes);

        return "autres/prefixes";

    }


    @RequestMapping(value="/listTypesConcession",method = RequestMethod.GET)
    public String listTypesConcession(final ModelMap model) {

        List<qTypeConcession> types=typeconcessionService.findAll();

        model.addAttribute("typesConcession",types);

        return "autres/typesConcession";


    }
    @RequestMapping(value="/listCategRessources",method = RequestMethod.GET)
    public String listCategRessources(final ModelMap model) {
        List<qCategRessource> categres=categService.findAll();

        model.addAttribute("categRessources",categres);

        return "autres/categRessources";
    }

    @RequestMapping(value="/listModels",method = RequestMethod.GET)
    public String listModels( final ModelMap model) {
        List<qModelJP> models=modeljpService.findAll();

        model.addAttribute("models",models);

        return "autres/models";
    }
    @RequestMapping(value="/listEspeces",method = RequestMethod.GET)
    public String listEspeces( final ModelMap model) {
        List<qEspece> especes=especeService.findAll();

        model.addAttribute("especes",especes);

        return "autres/especes";
    }
    @RequestMapping(value="/listZones",method = RequestMethod.GET)
    public String listZones(final ModelMap model) {
        List<qZone> zones=zoneService.findAll();

        model.addAttribute("zones",zones);

        return "autres/zones";
    }
    @RequestMapping(value="/listNationalites",method = RequestMethod.GET)
    public String listNationalites( final ModelMap model) {
        List<qNation> nations=nationService.findAll();

        model.addAttribute("nations",nations);

        return "autres/nations";
    }
    @RequestMapping(value="/listTypesLicences",method = RequestMethod.GET)
    public String listTypesLicences(final ModelMap model) {
        List<qTypeLic> typeslic=typelicService.findAll();

        model.addAttribute("typeslic",typeslic);

        return "autres/typeslic";
    }
    @RequestMapping(value="/SupprimerConcessionnaire",method = RequestMethod.GET)
    public String SupprimerConcessionnaire(final RedirectAttributes redirects,@RequestParam(name="refConcessionnairePK") String refConcessionnairePK,final ModelMap model) {
    //   List<qTypeLic> typeslic=typelicService.findAll();
         qConsignataire deletedConsignataire=consignataireService.findById(refConcessionnairePK);
        if( deletedConsignataire!=null) {
            if(deletedConsignataire.getQconcession()==null)
            {  consignataireService.delete(refConcessionnairePK);
                redirects.addFlashAttribute("feedback","le consignataire est bien supprimé");}
            else {redirects.addFlashAttribute("feedback","Impossible de supprimé le consignataire");}
        }
        else redirects.addFlashAttribute("feedback","Impossible de supprimé le consignataire");

        return "redirect:listConcessionnaires";
    }

    @RequestMapping(value="/ActionConcession",method = RequestMethod.GET)
    public String ActionConcession(final RedirectAttributes redirectAttributes,@RequestParam(name="refConcessionPK") String refConcessionPK,final ModelMap model) {
        //   List<qTypeLic> typeslic=typelicService.findAll();
        qConcession deletedConcession=concessionService.findById(refConcessionPK);
        if( deletedConcession!=null) {
            if(deletedConcession.getNavires().size()==0 && deletedConcession.getqLicenceBatLastList().size()==0)
            {
                deletedConcession.setCategoriesRessources(null);
                concessionService.save(deletedConcession);
                concessionService.delete(deletedConcession);
                redirectAttributes.addFlashAttribute("feedback","supprimé");
            }
            else {redirectAttributes.addFlashAttribute("feedback","Impossible de supprimer la concession, il y a une historique");}
        }
        else redirectAttributes.addFlashAttribute("feedback","impossible de supprimer");

        return "redirect:listConcessions";
    }
    @RequestMapping(value="/AjouterConcession",method = RequestMethod.GET)
    public String AjouterConcession(final @RequestParam(name="refConcessionnairePK") String refConcessionnairePK,final ModelMap model) {
        qConcession newConcession=new qConcession();
        qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
     if(currentConsignataire!=null)   newConcession.setQconsignataire(currentConsignataire);
        else System.out.println("impssiiiiiiiiiiiiiiiible");
        creationConcessionForm  creConcess=new creationConcessionForm();
        creConcess.setCurrentConcession(newConcession);
        model.addAttribute("concessionForm",creConcess);

        return "concessions/nouvelleConcession";
    }



    @RequestMapping(value="/AjouterPrefixe",method = RequestMethod.GET)
    public String AjouterPrefixe(final ModelMap model) {
        qPrefix newPrefix=new qPrefix();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);


        model.addAttribute("newPrefixe",newPrefix);

        return "prefixes/newPrefixe";
    }
    @RequestMapping(value="/AjouterPrefixeSave",method = RequestMethod.POST)
    public String AjouterPrefixeSave(final qPrefix prefixe,final ModelMap model) {


    prefService.save(prefixe);



        return "redirect:listPrefixes";
    }
    @RequestMapping(value="/ModifyPrefixeSave",method = RequestMethod.POST)
    public String ModifyPrefixeSave(final ModelMap model) {
        qPrefix newPrefix=new qPrefix();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);


        model.addAttribute("newPrefixe",newPrefix);

        return "prefixes/newPrefixe";
    }
    @RequestMapping(value="/ActionPrefixesModify",method = RequestMethod.GET)

        public String ActionPrefixesModify(final @RequestParam(name="prefixPK") String prefixPK, final @RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {

        qPrefixPK prefPK=new qPrefixPK(prefixPK,enumTypeDoc.valueOf(typeDoc));

            qPrefix modifiedPrefix=prefService.findById(prefPK);
            //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);


            model.addAttribute("modifiedPrefixe",modifiedPrefix);

            return "prefixes/modifyPrefix";
        }

    @RequestMapping(value="/ActionPrefixesDelete",method = RequestMethod.GET)

    public String ActionPrefixesDelete(final RedirectAttributes redirectAttributes,final @RequestParam(name="prefixPK") String prefixPK, final @RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {

        qPrefixPK prefPK=new qPrefixPK(prefixPK,enumTypeDoc.valueOf(typeDoc));

        qPrefix deletedPrefix=prefService.findById(prefPK);
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        boolean flpgs=licenceService.checkPrefix(deletedPrefix);
        boolean fltypcon=typeconcessionService.checkPrefix(deletedPrefix);
        if(flpgs==false && fltypcon==false)
        {
            prefService.delete(deletedPrefix.getPrefixPK());
            redirectAttributes.addFlashAttribute("feedb","prefix est bien supprimé");
        }
        else {
            redirectAttributes.addFlashAttribute("feedb", "echéc de suppression de prefix");
        }

        return "prefixes/modifyPrefix";
    }

    @RequestMapping(value="/ActionPrefixesModifySave",method = RequestMethod.POST)

    public String ActionPrefixesModifySave(final @ModelAttribute("modifiedPrefixe") qPrefix modifiedPrefixe,final ModelMap model) {

        qPrefix modifiedPrefix=prefService.save(modifiedPrefixe);
        model.addAttribute("modifiedPrefixe",modifiedPrefix);

        return "prefixes/modifyPrefix";
    }

    @RequestMapping(value="/AjouterConcessionnaire",method = RequestMethod.GET)
    public String AjouterConcessionnaire(final ModelMap model) {
        qConsignataire newConcessionnaire=new qConsignataire();
      //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);


        model.addAttribute("newConcessionnaire",newConcessionnaire);

        return "concessions/newConcessionnaire";
    }

    @RequestMapping(value="/AjouterConcessionnaireSave",method = RequestMethod.POST)
    public String AjouterConcessionnaireSave(final qConsignataire newConcessionnaire,final ModelMap model) {

        if(newConcessionnaire!=null) {
          consignataireService.create(newConcessionnaire);

      }
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
          model.addAttribute("newConcessionnaire",newConcessionnaire);



        return "concessions/newConcessionnaire";
    }


    @RequestMapping(value="/submitConcession", params={"removeRowX"},method = RequestMethod.POST)
    public String NouvLicenceBatExistantRemoveRowXX(final  @ModelAttribute("concessionForm") creationConcessionForm concessionForm, final HttpServletRequest req,final ModelMap model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRowX"));
        String url=null;

        qCategRessource currentCateg=concessionForm.getCategoriesRattaches().get(rowId.intValue());


        if(currentCateg!=null) {


            concessionForm.getCategoriesRattaches().remove(rowId.intValue());

        }

        model.addAttribute("concessionForm",concessionForm);
        return "concessions/nouvelleConcession";

    }
    @RequestMapping(value="/submitConcession", params={"addRow"},method = RequestMethod.POST)
    public String submitConcessionAddRow(final  @ModelAttribute("concessionForm") creationConcessionForm concessionForm,final BindingResult bindingresult,final ModelMap model) {
        String url=null;
        List<qCategRessource> ens=new ArrayList<qCategRessource>();
        qCategRessource currentToBeAdd=categService.findById(concessionForm.getSelectedCategorieRessource().getIdtypeConcession());
        System.out.println("new categ "+currentToBeAdd);
        if(concessionForm.getCategoriesRattaches() == null) {
            ens.add(currentToBeAdd);
            concessionForm.setCategoriesRattaches(ens);
          //   System.out.println(currentToBeAdd.getEngins().size());
        } else {
            if (!concessionForm.getCategoriesRattaches().contains(currentToBeAdd))
            {
                ens.add(currentToBeAdd);
                concessionForm.getCategoriesRattaches().addAll(ens);
            }
        }
        System.out.println("nombre de ca"+concessionForm.getCategoriesRattaches().size());
        //concessionForm.setCategoriesRattaches(ens);
        model.addAttribute("concessionForm",concessionForm);

        return "concessions/nouvelleConcession";
    }
    @RequestMapping(value="/submitConcession", params={"saveConcession"},method = RequestMethod.POST)
    public String submitConcessionSave(final  @ModelAttribute("concessionForm") creationConcessionForm concessionForm,final BindingResult bindingresult,final ModelMap model) {

        concessionForm.getCurrentConcession().setCategoriesRessources(concessionForm.getCategoriesRattaches());
        concessionValidator.validate(concessionForm.getCurrentConcession(), bindingresult);

      if(!bindingresult.hasErrors()) {
          concessionService.create(concessionForm.getCurrentConcession());
          model.addAttribute("feedback","la concession est sauvgardée");
          qConcession newConc=new qConcession();
          newConc.setQconsignataire(concessionForm.getCurrentConcession().getQconsignataire());
          creationConcessionForm frm=new creationConcessionForm();
          frm.setCurrentConcession(newConc);
          model.addAttribute("concessionForm",frm);
          return "concessions/nouvelleConcession";
      }
        else {
          System.out.println(bindingresult.getAllErrors().size());
          for( ObjectError err:bindingresult.getAllErrors()) {
             System.out.println(err.getCode());
              System.out.println(err.getObjectName());
          }
          System.out.println(bindingresult.getAllErrors().size());
          model.addAttribute("concessionForm",concessionForm);
          model.addAttribute("feedback","");
          return "concessions/nouvelleConcession";
          }
    }





    //------------------------------------------------------------------------
    @RequestMapping(value="/ajouterTypeConcessionSave",method = RequestMethod.POST)
    public String ajouterTypeConcessionSave(final @ModelAttribute("addedTypeConcession") typeConcessionForm typeConcessionform,final ModelMap model,final BindingResult bindingresult) {
        System.out.println("action printed :");
        System.out.println(typeConcessionform.getAction());
        if(typeConcessionform.getAction()!=null) {
            attrTypeConcessionValidator.validate(typeConcessionform, bindingresult);

            if(!bindingresult.hasErrors()) {
           System.out.println("8888888888888888888888888888");
           typeconcessionService.create(typeConcessionform.getTypeConcession());
            System.out.println("99999999999999999999999999");
        } }

    if(typeConcessionform.getAction()==null) {
        qTypeConcession typeConcession=null;
        System.out.println(typeConcessionform.getTypeOp());
        if (typeConcessionform.getTypeOp().equals("Artisanal")) {

             typeConcession=new qTypeConcessionArtisanal();
            typeConcessionform.setTypeConcession(typeConcession);
            model.addAttribute("addedTypeConcession", typeConcessionform);
        }
        if (typeConcessionform.getTypeOp().equals("CotiereNonPontee")) {
            typeConcession=new qTypeConcessionCotiere();
            ((qTypeConcessionCotiere)typeConcession).setEnumTypePecheCotiere(enumTypePechCotiere.NON_PONTEE);
            typeConcessionform.setTypeConcession(typeConcession);
            model.addAttribute("addedTypeConcession", typeConcessionform);
            //typeconcessionform.setTypeConcession(typeConcession);
        }
        if (typeConcessionform.getTypeOp().equals("CotierePontee")) {
            typeConcession=new qTypeConcessionCotiere();
            ((qTypeConcessionCotiere)typeConcession).setEnumTypePecheCotiere(enumTypePechCotiere.PONTEE);
            typeConcessionform.setTypeConcession(typeConcession);

            model.addAttribute("addedTypeConcession", typeConcessionform);
        }
        if (typeConcessionform.getTypeOp().equals("Hautiriere")) {
            typeConcession=new qTypeConcessionHautiriere();

            typeConcessionform.setTypeConcession(typeConcession);
            model.addAttribute("addedTypeConcession", typeConcessionform);
        }
    }
        model.addAttribute("addedTypeConcession", typeConcessionform);

        return "typesConcession/ajouterTypeConcession";

    }

    @RequestMapping(value="/modifierTypeConcessionSave",method = RequestMethod.POST)
    public String modifierTypesConcessionSave(final @ModelAttribute("modifiedTypeConcession") typeConcessionForm qtypeconcessionform,final ModelMap model,final BindingResult bindingresult) {
        qTypeConcession typeConcession=null;
        qPrefixPK pr = null;

        attrTypeConcessionModifValidator.validate(qtypeconcessionform, bindingresult);
        if(!bindingresult.hasErrors()) {
            pr = new qPrefixPK(qtypeconcessionform.getTypeConcession().getPrefixNum(), qtypeconcessionform.getTypeConcession().getTypeDoc());
            qPrefix prefixCurrent = prefService.findById(pr);
            qtypeconcessionform.getTypeConcession().setPrefix(prefixCurrent);
            System.out.println("ggggggg");
            System.out.println( qtypeconcessionform.getTypeConcession().toString());
            qTypeConcession tt=typeconcessionService.findById(qtypeconcessionform.getTypeConcession().getQtypeconcessionpk());
            tt.setPrefix(qtypeconcessionform.getTypeConcession().getPrefix());
            tt.setTypeDoc(qtypeconcessionform.getTypeConcession().getTypeDoc());
            tt.setDesignation(qtypeconcessionform.getTypeConcession().getDesignation());
            tt.setPrefixNum(qtypeconcessionform.getTypeConcession().getPrefixNum());
            typeconcessionService.update(tt);
            //   typeconcessionService.save(qtypeconcession);
            model.addAttribute("feedbacksave","le type de concession a été bien enregistrée");
            model.addAttribute("modifiedTypeConcession", qtypeconcessionform);

        }
        return "typesConcession/modifierTypeConcession";
    }


    @RequestMapping(value="/ supprimerTypeConcession",method = RequestMethod.GET)
    public String  supprimerTypeConcession(final  RedirectAttributes red,final Integer idtypeconcession,final ModelMap model) {
        qTypeConcession typeConcessionDeleted=typeconcessionService.findById(idtypeconcession);
        if(typeConcessionDeleted!=null && typeconcessionService.check(typeConcessionDeleted).size()==0)
        {
            typeconcessionService.delete(idtypeconcession);
        red.addAttribute("suppmessage","supprimé avec succéss");}
        else   red.addAttribute("suppmessage","impossible de supprimer");
        return "redirect:listTypesConcession";
    }
    @RequestMapping(value="/modifierTypeConcession",method = RequestMethod.GET)
    public String modifierTypesConcession(final Integer idtypeconcession,final ModelMap model) {
     String typeOp=null;
     qTypeConcession typeConcession=typeconcessionService.findById(idtypeconcession);
     typeConcessionForm typeconcessionform=new typeConcessionForm();
        typeconcessionform.setTypeConcession(typeConcession);
        if(typeConcession instanceof qTypeConcessionArtisanal)  typeOp="Artisanal";
        if(typeConcession instanceof qTypeConcessionCotiere) {
            if(((qTypeConcessionCotiere) typeConcession).getEnumTypePecheCotiere().equals(enumTypePechCotiere.NON_PONTEE))
            typeOp="CotiereNonPontee";
            if(((qTypeConcessionCotiere) typeConcession).getEnumTypePecheCotiere().equals(enumTypePechCotiere.PONTEE))
                typeOp="CotierePontee";

        }
        if(typeConcession instanceof qTypeConcessionHautiriere)  typeOp="Hautiriere";

        typeconcessionform.setTypeOp(typeOp);
        model.addAttribute("modifiedTypeConcession", typeconcessionform);

        return "typesConcession/modifierTypeConcession";
    }
        @RequestMapping(value="/ajouterTypeConcession",method = RequestMethod.GET)
    public String ajouterTypeConcession(final @RequestParam(name="typeOp") String typeOp,final ModelMap model) {
        qTypeConcession typeConcession=null;

        if(typeOp.equals("Artisanal"))
        {
        typeConcession=new qTypeConcessionArtisanal();
        typeConcessionForm typeconcessionform=new typeConcessionForm();
        typeconcessionform.setTypeOp(typeOp);
        typeconcessionform.setTypeConcession(typeConcession);
        model.addAttribute("addedTypeConcession",typeconcessionform);
        }
        if(typeOp.equals("CotiereNonPontee"))
        {
            typeConcession=new qTypeConcessionCotiere();
            typeConcessionForm typeconcessionform=new typeConcessionForm();
            typeconcessionform.setTypeOp(typeOp);

            ( (qTypeConcessionCotiere)typeConcession).setEnumTypePecheCotiere(enumTypePechCotiere.NON_PONTEE);
            model.addAttribute("addedTypeConcession",typeconcessionform);
            //typeconcessionform.setTypeConcession(typeConcession);
        }
        if(typeOp.equals("CotierePontee"))
        {
            typeConcession=new qTypeConcessionCotiere();
            typeConcessionForm typeconcessionform=new typeConcessionForm();
            typeconcessionform.setTypeOp(typeOp);
            ( (qTypeConcessionCotiere)typeConcession).setEnumTypePecheCotiere(enumTypePechCotiere.PONTEE);
            //typeconcessionform.setTypeConcession(typeConcession);
            model.addAttribute("addedTypeConcession",typeconcessionform);
        }
        if(typeOp.equals("Hautiriere"))
        {
            typeConcession=new qTypeConcessionHautiriere();
            typeConcessionForm typeconcessionform=new typeConcessionForm();
            typeconcessionform.setTypeOp(typeOp);
          //  typeconcessionform.setTypeConcession(typeConcession);
            model.addAttribute("addedTypeConcession",typeconcessionform);
        }

        return "typesConcession/ajouterTypeConcession";
    }
    //------------------------------------------------------------------------

    @RequestMapping(value="/AjouterCategorie",method = RequestMethod.GET)
    public String AjouterCategorie(final ModelMap model) {
        qCategRessource newCat=new qCategRessource();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("newCat",newCat);
        return "categories/newCat";
    }
    @RequestMapping(value="/AjouterCategorieSave",method = RequestMethod.POST)
    public String AjouterCategorieSave(final RedirectAttributes myred,final @ModelAttribute("newCat") qCategRessource newCat,final ModelMap model) {
        newCat.setIdtypeConcession(newCat.getTypeconcessionConcernee().getQtypeconcessionpk());
        qCategRessource founded=categService.findById(newCat.getIdtypeConcession());
        if(founded==null)  {
            categService.create(newCat);
            myred.addFlashAttribute("successmes","ajouté avec success");
            }
        else  {
            myred.addFlashAttribute("successmes","impossible d'ajouter");
        }
        return "redirect:listCategRessources";
    }
    @RequestMapping(value="/ModifierCategorie",method = RequestMethod.GET)
    public String ModifierCategorie(final @RequestParam(name="catPK") Integer catPK,final ModelMap model) {
     qCategRessource categRessource=categService.findById(catPK);
     //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifCat",categRessource);
        return "categories/modifCat";
    }

    @RequestMapping(value="/DeleteCategorie",method = RequestMethod.GET)
    public String DeleteCategorie(final RedirectAttributes redAttributes,final @RequestParam(name="catPK") Integer catPK,final ModelMap model) {
        qCategRessource categRessource=categService.findById(catPK);

       if(categRessource.getQlicences()==null && categRessource.getEngins()==null && categRessource.getQconcession()==null && categRessource.getTypeconcessionConcernee()==null)
       {
           categService.delete(categRessource.getIdtypeConcession());
           redAttributes.addFlashAttribute("successmes","la categorie de ressource est bien supprimé");
       }
       else
       {
           redAttributes.addFlashAttribute("successmes","echec de suppression");
       }


        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);

        return "redirect:listCategRessources";
    }

    @RequestMapping(value="/ModifierCategorieSave",method = RequestMethod.POST)
    public String ModifierCategorieSave(final @ModelAttribute("modifCat") qCategRessource modifCat,final ModelMap model) {
       System.out.println("kkkkk");
        categService.save(modifCat);
        model.addAttribute("modifCat",modifCat);
        return "categories/modifCat";
    }

    @RequestMapping(value="/ModifierCategorieSave", params={"addEngin"},method = RequestMethod.POST)
    public String ModifierCategorieSaveAddRow(final   @ModelAttribute("modifCat") qCategRessource modifCat,final BindingResult bindingresult,final ModelMap model) {
        List<qEnginsLicence> ens=new ArrayList<qEnginsLicence>();
       if (modifCat.getEngins() == null) {
            ens.add(new qEnginsLicence());
            modifCat.setEngins(ens);
        } else modifCat.getEngins().add(new qEnginsLicence());
        model.addAttribute("modifCat",modifCat);
        return "categories/modifCat";
    }
    @RequestMapping(value="/AjouterCategorieSave", params={"addEngin"},method = RequestMethod.POST)
    public String AjouterCategorieSaveAddRow(final   @ModelAttribute("newCat") qCategRessource newCat,final BindingResult bindingresult,final ModelMap model) {
        List<qEnginsLicence> ens=new ArrayList<qEnginsLicence>();
        if (newCat.getEngins() == null) {
            ens.add(new qEnginsLicence());
            newCat.setEngins(ens);
        } else newCat.getEngins().add(new qEnginsLicence());
        model.addAttribute("newfCat",newCat);
        return "categories/newCat";
    }

    @RequestMapping(value="/AjouterModel",method = RequestMethod.GET)
    public String AjouterModel(final ModelMap model) {
        qModelJP newModel=new qModelJP();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("newModel",newModel);
        return "models/newModel";
    }
    @RequestMapping(value="/ModifierModel",method = RequestMethod.GET)
    public String ModifierModel(final @RequestParam(name="modelPK") String modelPK,final @RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {
        qPrefixPK prefPK=new qPrefixPK(modelPK,enumTypeDoc.valueOf(typeDoc));
        qModelJP modeljp=modeljpService.findById(prefPK);
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifiedModel",modeljp);
        return "models/modifyModel";
    }


    @RequestMapping(value="/DeleteModel",method = RequestMethod.GET)
    public String DeleteModel(final RedirectAttributes redAttributes,final @RequestParam(name="modelPK") String modelPK,final @RequestParam(name="typeDoc") String typeDoc,final ModelMap model) {
        qPrefixPK prefPK = new qPrefixPK(modelPK, enumTypeDoc.valueOf(typeDoc));
        qModelJP modeljp = modeljpService.findById(prefPK);
        if (modeljp.getEspecestypees() == null && modeljp.getPrefix() == null) {
            modeljpService.delete(prefPK);
            redAttributes.addAttribute("successmes","le model est bien supprimé");
        } else
        {
            redAttributes.addAttribute("successmes","echéc de suppression de modél");
        }
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);

        return "redirect:listModels";
    }
    @RequestMapping(value="/ActionModelAjoutSave",method = RequestMethod.POST)
    public String ActionModelAjoutSave(final RedirectAttributes myred,final @ModelAttribute("newModel") qModelJP newModel,final ModelMap model) {
qPrefixPK prefPK=new qPrefixPK(newModel.getPrefix(),newModel.getTypeDoc());
        qPrefix pref=prefService.findById(prefPK);
        if(pref!=null) {

        qModelJP founded=modeljpService.findById(newModel.getPrefixPK());
        if(founded==null)  {
            newModel.setQprefix(pref);
            modeljpService.create(newModel);
            myred.addFlashAttribute("successmes","ajouté avec success");
        }
        else  {
            myred.addFlashAttribute("successmes","impossible d'ajouter");
        }}
        else  myred.addFlashAttribute("prefnontrouve","prefix non trouvé");
        return "redirect:listModels";
    }
    @RequestMapping(value="/ActionModelModifySave",method = RequestMethod.POST)
    public String ActionModelModifySave(final @ModelAttribute("modifiedModel") qModelJP modifModel,final ModelMap model) {
        modeljpService.save(modifModel);
        model.addAttribute("modifModel",modifModel);
        return "models/modifyModel";
    }


    // zone urls

    @RequestMapping(value="/AjouterZone",method = RequestMethod.GET)
    public String AjouterZone(final ModelMap model) {
        qZone newZone=new qZone();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("newZone",newZone);
        return "zones/newZone";
    }
    @RequestMapping(value="/ModifierZone",method = RequestMethod.GET)
    public String ModifierZone(final @RequestParam(name="zonePK") Integer zonePK,final ModelMap model) {

        qZone zone=zoneService.findById(zonePK);
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifiedZone",zone);
        return "zones/modifyZone";
    }
    @RequestMapping(value="/DeleteZone",method = RequestMethod.GET)
    public String DeleteZone(final RedirectAttributes redirectAttributes,final @RequestParam(name="zonePK") Integer zonePK,final ModelMap model) {
       qZone zone=zoneService.findById(zonePK);
        if(licenceService.checkZones(zone)==false) {
           zoneService.delete(zone.getIdZone());
            redirectAttributes.addFlashAttribute("feedb","la zone est bien supprimé");
        }
        else {
            redirectAttributes.addFlashAttribute("feedb","echéc de suppression de zone");
            redirectAttributes.addFlashAttribute("","");
        }
        return "redirect:listZones";
    }
    @RequestMapping(value="/ActionZoneAjoutSave",method = RequestMethod.POST)
    public String ActionZoneAjoutSave(final RedirectAttributes myred,final @ModelAttribute("newZone") qZone newZone,final ModelMap model) {


        if(newZone!=null) {
            qZone zn=zoneService.findById(newZone.getIdZone());
            if(zn==null)  {
                zoneService.create(newZone);
                myred.addFlashAttribute("successmes","ajouté avec success");
            }
            else  {
                myred.addFlashAttribute("successmes","impossible d'ajouter");
            }
        }
        else  myred.addFlashAttribute("prefnontrouve","prefix non trouvé");
        return "redirect:listZones";
    }
    @RequestMapping(value="/ActionZoneModifySave",method = RequestMethod.POST)
    public String ActionZoneModifySave(final @ModelAttribute("modifiedZone") qZone modifiedZone,final ModelMap model) {
        zoneService.save(modifiedZone);
        model.addAttribute("modifZone",modifiedZone);
        return "zones/modifyZone";
    }

    // nationalites

    @RequestMapping(value="/AjouterNationalite",method = RequestMethod.GET)
    public String AjouterNationalite(final ModelMap model) {
        qNation newNation=new qNation();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("newNationalite",newNation);
        return "nationalites/newNationalite";
    }
    @RequestMapping(value="/ModifierNationalite",method = RequestMethod.GET)
    public String ModifierNationalite(final @RequestParam(name="nationPK") Integer nationPK,final ModelMap model) {

        qNation nationjp=nationService.findById(nationPK);
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifiedNationalite",nationjp);
        return "nationalites/modifyNationalite";
    }

    @RequestMapping(value="/DeleteNationalite",method = RequestMethod.GET)
    public String DeleteNationalite(final RedirectAttributes redAttributes,final @RequestParam(name="nationPK") Integer nationPK,final ModelMap model) {

        qNation nationjp=nationService.findById(nationPK);
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
       List<qLic> licences= licenceService.checkNation(nationjp);
        if(licences.size()==0) {
            nationService.delete(nationjp.getIdNation());
            redAttributes.addAttribute("successmsg","la nationalité est bien supprimé");
        }
        else {
           // nationService.delete(nationjp.getIdNation());
            redAttributes.addAttribute("successmsg","échec de suppression de nationalité");
        }

        return "redirect:listNationalites";
    }
    @RequestMapping(value="/ActionNationaliteAjoutSave",method = RequestMethod.POST)
    public String ActionNationaliteAjoutSave(final RedirectAttributes myred,final @ModelAttribute("newNationalite") qNation newNationalite,final ModelMap model) {
        if(newNationalite!=null) {
               nationService.create(newNationalite);
               myred.addFlashAttribute("successmes","ajouté avec success");
        }
        else  myred.addFlashAttribute("prefnontrouve","prefix non trouvé");
        return "redirect:listNationalites";
    }

    @RequestMapping(value="/ActionNationaliteModifySave",method = RequestMethod.POST)
    public String ActionNationaliteModifySave(final @ModelAttribute("modifiedNation") qNation modifNation,final ModelMap model) {
        nationService.save(modifNation);
        model.addAttribute("modifiedNationalite",modifNation);
        return "nationalites/modifyNationalite";
    }


    // especes

    @RequestMapping(value="/AjouterEsp",method = RequestMethod.GET)
    public String AjouterEspece(final ModelMap model) {
        qEspece newEspece=new qEspece();
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("newEspece",newEspece);
        return "especes/newEspece";
    }
    @RequestMapping(value="/ModifierEspece",method = RequestMethod.GET)
    public String ModifierEspece(final @RequestParam(name="especePK") String especePK,final ModelMap model) {
      //  qPrefixPK prefPK=new qPrefixPK(especePK,enumTypeDoc.valueOf(typeDoc));
        qEspece espece=especeService.findById(especePK);
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifiedEspece",espece);
        return "especes/modifyEspece";
    }


    @RequestMapping(value="/DeleteEspece",method = RequestMethod.GET)
    public String DeleteModel(final RedirectAttributes redAttributes,final @RequestParam(name="especePK") String especePK,final ModelMap model) {
     //   qPrefixPK prefPK = new qPrefixPK(modelPK, enumTypeDoc.valueOf(typeDoc));
        qEspece esp = especeService.findById(especePK);
        if (esp.getQespecetypee() == null ) {
            especeService.delete(esp.getCodeEsp());
            redAttributes.addAttribute("successmes","l'espece est bien supprimé");
        } else
        {
            redAttributes.addAttribute("successmes","echéc de suppression d'espece");
        }
        //qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);

        return "redirect:listEspeces";
    }
    @RequestMapping(value="/ActionEspeceAjoutSave",method = RequestMethod.POST)
    public String ActionEspeceAjoutSave(final RedirectAttributes myred,final @ModelAttribute("newEspece") qEspece newEspece,final ModelMap model) {
    //    qPrefixPK prefPK=new qPrefixPK(newModel.getPrefix(),newModel.getTypeDoc());
        qEspece esp=especeService.findById(newEspece.getCodeEsp());
        if(esp==null) {
            especeService.create(newEspece);
}
        else  myred.addFlashAttribute("esptrouve","l'espece est déja crée");
        return "redirect:listEspeces";
    }
    @RequestMapping(value="/ActionEspeceModifySave",method = RequestMethod.POST)
    public String ActionEspeceModifySave(final @ModelAttribute("modifiedEspece") qEspece modifEspece,final ModelMap model) {
        especeService.save(modifEspece);
        model.addAttribute("modifEspece",modifEspece);
        return "especes/modifyEspece";
    }


    @RequestMapping(value="/findDocsByPage",method = RequestMethod.POST)
    public String findDocsByPage(final RedirectAttributes redirectAttributes,final ModelMap model, @ModelAttribute("page") String page) {
        String urlnext=null;
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea1=new frmSearchPgsForDocCrea();
        int[] pages;

           Page<qDoc>  pgDoc=docService.findAllMatcheds(page);
        if(pgDoc.getContent().size()!=0) {
            pages = new int[pgDoc.getTotalPages()];
            for (int i = 0; i < pgDoc.getTotalPages(); i++) pages[i] = i;
            frmSearchPgsForDocCrea1.setLstDocuments(pgDoc);
            frmSearchPgsForDocCrea1.setPageCount(pgDoc.getTotalPages());
            frmSearchPgsForDocCrea1.setNumPages(pages);
            frmSearchPgsForDocCrea1.setPageCourante(0);
            //  frmSearchPgsForDocCrea1.setSearchCarnet(searchCarnet);
            // frmSearchPgsForDocCrea1.setFailedAnnulation("");



        }
        else {
            pages = new int[0];
            frmSearchPgsForDocCrea1.setLstDocuments(null);
            frmSearchPgsForDocCrea1.setPageCount(0);
            frmSearchPgsForDocCrea1.setNumPages(pages);
            frmSearchPgsForDocCrea1.setPageCourante(0);
        }
        model.addAttribute("frmSearchPgsForDocCrea", frmSearchPgsForDocCrea1);
          return "Documents/listDocuments";
    }

        @RequestMapping(value="/searchDocCapture",method = RequestMethod.POST)
    public String searchDocCapture(final RedirectAttributes redirectAttributes,final ModelMap model, @ModelAttribute("sacc") searchAccueil searchAccueil) {
       //
        String urlnext=null;
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea1=new frmSearchPgsForDocCrea();
        int[] pages;
        //  public String  listCarnets(final lstCarnetsAchoisirForm carnetForm ,final ModelMap model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="searchCarnet",defaultValue = "") String searchCarnet) {
        Page<qDoc>  pgDoc=docService.findAllMatchedDocs(searchAccueil.getSearchDateCapture(),searchAccueil.getSearchBat());
        if(pgDoc.getContent().size()!=0) {
            pages = new int[pgDoc.getTotalPages()];
            for (int i = 0; i < pgDoc.getTotalPages(); i++) pages[i] = i;
            frmSearchPgsForDocCrea1.setLstDocuments(pgDoc);
            frmSearchPgsForDocCrea1.setPageCount(pgDoc.getTotalPages());
            frmSearchPgsForDocCrea1.setNumPages(pages);
            frmSearchPgsForDocCrea1.setPageCourante(0);
            //  frmSearchPgsForDocCrea1.setSearchCarnet(searchCarnet);
            // frmSearchPgsForDocCrea1.setFailedAnnulation("");
            model.addAttribute("frmSearchPgsForDocCrea", frmSearchPgsForDocCrea1);

            //   frmSearchPgsForDocCrea1.setLstDocuments();
            urlnext="Documents/listDocuments";

        }
        else {
            urlnext="redirect:start";
            redirectAttributes.addFlashAttribute("notfounddoc","Aucun document trouvé");
        }
   return urlnext;
    }

    @RequestMapping(value="/importerDocuments",method = RequestMethod.GET)
    public String importerDocuments(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileDocuments", fileModel);
        return "imports/importerDocument";
    }
    @RequestMapping(value="/importerMarrees",method = RequestMethod.GET)
    public String importerMarrees(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileMarree", fileModel);
        return "imports/importerMarrees";
    }
    @RequestMapping(value="/importerTraitements",method = RequestMethod.GET)
    public String importerTraitement(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileTraitenent", fileModel);
        return "imports/importerTraitements";
    }
    @RequestMapping(value="/importerLicences",method = RequestMethod.GET)
    public String importerLicencesGET(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "imports/importer";
    }

    @RequestMapping(value="/importerLicNV",method = RequestMethod.GET)
    public String importerLicNVGET(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "imports/importerLicNV";
    }

    @RequestMapping(value="/importerDocuments",method = RequestMethod.POST)
    public String importerDocumentsPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerDocuments(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }

        //


        return "redirect:importerDocuments";

    }


    @RequestMapping(value="/importerMarrees",method = RequestMethod.POST)
    public String importerMarrees(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerMarrees(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }


        return "redirect:importerMarrees";

    }
    @RequestMapping(value="/importerTraitements",method = RequestMethod.POST)
    public String importerTraitements(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerTraitements(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }


        return "redirect:importerTraitements";

    }
    @RequestMapping(value="/importerLicNV",method = RequestMethod.POST)
    public String handleFileUploadtrr(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {

        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            licenceService.importerLicenceNV(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }

        //

        return "redirect:importerLicences";
    }


    @RequestMapping(value="/importerLicencestr",method = RequestMethod.POST)
    public String handleFileUploadtr(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {

     //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
        //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
        //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            licenceService.importerLicence(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
         //   return "success";
        }

        //

        return "redirect:importerLicences";
    }

    @RequestMapping(value="/zoneExcel", method=RequestMethod.GET)
    public ModelAndView getZoneData(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("sheetname", "ZonesExport");
        List<qZone> zones=zoneService.findAll();
        System.out.println("size de : "+zones.size());
        model.put("zones", zones);
        return new ModelAndView(new zoneExcelView(),model);
    }

    @RequestMapping(value="/naviresExcel", method=RequestMethod.GET)
    public ModelAndView getNaviresData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("sheetname", "naviresExport");
        List<qNavireLegale> navires=registrenavireService.findAllLegal();
        System.out.println("size de : "+navires.size());
        model.put("navires", navires);
        return new ModelAndView(new bateauExcelView(),model);
    }
    @RequestMapping(value="/licencesExcel", method=RequestMethod.GET)
    public ModelAndView getLicencesData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("sheetname", "licencesExport");
        List<qLic> licences=licenceService.findAll();
        System.out.println("size de : "+licences.size());
        model.put("licences", licences);
        return new ModelAndView(new licencesExcelView(),model);
    }

    @RequestMapping(value="/launchjob", method=RequestMethod.GET)
    public @ResponseBody   StatusResponse job1() {
        System.out.println("jjjjj");
        JobParameters jobParameters =
                new JobParametersBuilder().addString("input.file.name", "cvs/input/fxe_dadsDevice.dat").toJobParameters();


        try {
            Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
            parameters.put("date", new JobParameter(new Date()));
            jobLauncher.run(job1, new JobParameters(parameters));
            return new StatusResponse(true);
       } catch (JobInstanceAlreadyCompleteException ex) {
            return new StatusResponse(false, "This job has been completed already!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value="/importerNationalites",method = RequestMethod.GET)
    public String importerNationalites(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileNationalites", fileModel);
        return "imports/importerNationalites";
    }


    @RequestMapping(value="/importerCategoriesPeche",method = RequestMethod.GET)
    public String importerCategoriesPeche(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileCategoriesPeche", fileModel);
        return "imports/importerCategoriesPeche";
    }
    @RequestMapping(value="/importerTypesConcession",method = RequestMethod.GET)
    public String importerTypesConcession(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileTypesConcession", fileModel);
        return "imports/importerTypesConcession";
    }
    @RequestMapping(value="/importerPrefixes",method = RequestMethod.GET)
    public String importerPrefixes(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("filePrefixes", fileModel);
        return "imports/importerPrefixes";
    }
    @RequestMapping(value="/importerModels",method = RequestMethod.GET)
    public String importerModels(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileModels", fileModel);
        return "imports/importerModels";
    }
    @RequestMapping(value="/importerZones",method = RequestMethod.GET)
    public String importerZones(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileZones", fileModel);
        return "imports/importerZones";
    }
    @RequestMapping(value="/importerCarnets",method = RequestMethod.GET)
    public String importerCarnets(ModelMap model)
    {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileCarnets", fileModel);
        return "imports/importerCarnets";
    }

    //---------------------------

    @RequestMapping(value="/importerNationalites",method = RequestMethod.POST)
    public String importerNationalitesPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerNationalites(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }



        return "redirect:importerNationalites";
    }
    @RequestMapping(value="/importerCategoriesPeche",method = RequestMethod.POST)
    public String importerCategoriesPechePOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerCategoriesPeche(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerCategoriesPeche";
    }
    @RequestMapping(value="/importerTypesConcession",method = RequestMethod.POST)
    public String importerTypesConcessionPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerTypesConcession(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerTypesConcession";
    }
    @RequestMapping(value="/importerPrefixes",method = RequestMethod.POST)
    public String importerPrefixesPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerPrefixes(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerPrefixes";
    }
    @RequestMapping(value="/importerModels",method = RequestMethod.POST)
    public String importerModelsPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerModels(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerModels";
    }
    @RequestMapping(value="/importerZones",method = RequestMethod.POST)
    public String importerZonesPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerZones(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerZones";
    }
    @RequestMapping(value="/importerCarnets",method = RequestMethod.POST)
    public String importerCarnetsPOST(@Valid FileBucket fileBucket, BindingResult result,ModelMap model) throws IOException
    {
        //   storageService.store(file);
        if (result.hasErrors()) {
            System.out.println("validation errors");
            //    return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            //    FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            docService.importerCarnets(multipartFile,UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename(),result);
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            //   return "success";
        }
        return "redirect:importerCarnets";
    }
}