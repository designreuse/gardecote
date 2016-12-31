package com.gardecote.controllers;

import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import com.gardecote.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@SessionAttributes(types = {qModelJP.class,frmSearchPgsForDocCrea.class,CreateDocForm.class,lstBateauAchoisirForm.class,creationLicForm.class,attributionCarnetForm.class})

@RequestMapping("/")
public class LicenceController {
  //  licenceValidatorBatExistant

    @Autowired
    MessageByLocaleService messageByLocaleService;
    @Autowired
    saveDocValidator saveDocValidator;
    @Autowired
    licenceValidator licValidator;
    @Autowired
    attrUsineValidator attrUsineValidator;
    @Autowired
    usineValidator usineValidator;
    @Autowired
    deleteDocValidator deleteDocValidator;
    @Autowired
    modelValidateur modelValidateur;
    @Autowired
    attributionValidator attrValidator;
    @Autowired
    creerDocValidator creerValidateur;
    @Autowired
    creerAnnexeValidateur creerAnnexeValidateur;
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
    private qUsineService usineService;
    @Autowired
    private LicenceAc ourLic;

    public licenceValidator getLicValidator() {
        return licValidator;
    }

    public void setLicValidator(licenceValidator licValidator) {
        this.licValidator = licValidator;
    }
// pour les liste deroulantes.

    @ModelAttribute("allEspeces")
    public List<qEspece> populateEspeces() {
       return especeService.findAll();

    }


    @ModelAttribute("allTypesPeches")
    public List<enumJP> populateSegPeche() {
        return Arrays.asList(enumJP.values());
    }


    @ModelAttribute("allTypesDoc")
    public List<enumTypeDoc> populateAllTypesDoc() {
        return Arrays.asList(enumTypeDoc.values());
    }
    @ModelAttribute("supports")
    public List<enumSupport> populateSupport() {
        return Arrays.asList(enumSupport.values());
    }

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

    @RequestMapping(value = "/PrefixesByTypeDoc/{typeDoc}", method = RequestMethod.GET)
    public String getPrefixesByTypeDoc(@PathVariable("typeDoc") String typeDoc,Model model) {
System.out.println("string id"+enumTypeDoc.valueOf(typeDoc));
        System.out.println(prefService.PrefixesByTypeDoc(enumTypeDoc.valueOf(typeDoc)).size());
        model.addAttribute("selectedPrefixes",prefService.PrefixesByTypeDoc(enumTypeDoc.valueOf(typeDoc)));
           return "carnets/attribution::renderPrefixes";
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
            qCarnet currentCarnet = new qCarnet(pref, CarnetAttribue.getCarnetSelected().getNumeroDebutPage(), CarnetAttribue.getCarnetSelected().getNbrPages(), null, null);
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
        // chercher les licences actives pour ce navire pour les afficher et choisisser une
        attrCrn.setCarnetSelected(createdCarnet);
        model.addAttribute("CarnetAttribue",attrCrn);

        urlNavigation="usine/attribution";
        return urlNavigation;
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
               qCarnet currentCarnet = new qCarnet(pref, CarnetAttribue.getCarnetSelected().getNumeroDebutPage(), CarnetAttribue.getCarnetSelected().getNbrPages(), null, null);

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
        if(carnetAnnulle.getTypeDoc()==enumTypeDoc.Journal_Annexe) {
            for (qPageCarnet qp : carnetAnnulle.getPages()) {
                if (((qPageAnnexe)qp).getQmarreeAnexe()!=null)
                    compteurAnnexe++;
            }
        }
        if(compteurDeb==0 && compteurMarees==0 && compteurTrait==0&& compteurAnnexe==0) {
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
            qPrefix prefi=new qPrefix();
            qCarnet createdCarnet=new qCarnet();
            createdCarnet.setQprefix(prefi);
            createdCarnet.setNbrPages(50);
            //(prefi,78L,50,null,null);

            qNavire selectedNavire=registrenavireService.findById(numimm);
            List<qLic> licencesActives=registrenavireService.retActLicences(selectedNavire);

            attrCrn.setCarnetSelected(createdCarnet);
            attrCrn.setNavireSelected(selectedNavire);
            attrCrn.setLicenceActives(licencesActives);

    // chercher les licences actives pour ce navire pour les afficher et choisisser une

            model.addAttribute("CarnetAttribue",attrCrn);

            if(action.equals("attribuerCarnet")) {

           }

             if(attrCrn.getLicenceActives().size()==0) urlNavigation="carnets/attributionImpossible";
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
    //  frmSearchPgsForDocCrea1.setSearchCarnet(searchCarnet);
       // frmSearchPgsForDocCrea1.setFailedAnnulation("");
         model.addAttribute("frmSearchPgsForDocCrea",frmSearchPgsForDocCrea1);
     //   frmSearchPgsForDocCrea1.setLstDocuments();

        return "Documents/listDocuments";
   }

    @RequestMapping(value="/finList",method = RequestMethod.GET)
    public String getFinList(final ModelMap model, @RequestParam(name="debut") String debut,@RequestParam(name="typeDoc") String typeDoc) {
        // creer les categories de ressource 5 PA  de 1 a 5
        boolean displaydatefrg=false,displaybuttonfrg=false;
        frmSearchPgsForDocCrea frmSearchPgsForDocCrea=new frmSearchPgsForDocCrea();
        System.out.println("numero de page  : "+debut+"type de  doc "+typeDoc);
        List<String> numsfin=new ArrayList<>();
        List<qPageCarnet> pq=pagecarnetService.getFinList(debut,enumTypeDoc.valueOf(typeDoc));
        for(qPageCarnet q:pq) numsfin.add(q.getNumeroPage().toString());
        //  return numsfin;
        if(numsfin.size()>0)  {displaydatefrg=false;displaybuttonfrg=false;}
        else  {displaydatefrg=true;displaybuttonfrg=true;}

        frmSearchPgsForDocCrea.setDateDebut(null);
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


    @RequestMapping(value="/createDocument",params = {"save"},method = RequestMethod.POST)
    public String saveDocument(final frmSearchPgsForDocCrea frmSearchPgsForDocCrea, final BindingResult result,final ModelMap model) {
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
        lics=docService.retLicences(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getQseq(),frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getEnumtypedoc());

        if(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc() instanceof qDebarquement)  {
            docService.save((qDebarquement)frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            model.addAttribute("licencesRef", lics);
            urlNav="docEditDebarquement";
        }

        if(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc() instanceof qMarree)  {
            docService.save(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            model.addAttribute("licencesRef", lics);
            urlNav="docEditMarree";
        }
        if(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc() instanceof qTraitement)  {
            saveDocValidator.validate(frmSearchPgsForDocCrea, result);

            if(!result.hasErrors()) {
                docService.save(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            }

               // docService.save(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
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
        qDoc currentDoc=null;
        if(pk!=null) currentDoc=docService.findById(pk);
        qPageCarnet pc=pagecarnetService.findById(new qPageCarnetPK(currentDoc.getQseq().getDebut(),enumTypeDoc.Journal_Peche));
frmAnnexe1.setPrefixeAnnexe(pc.getQcarnet().getPrefixNumerotation());
        frmAnnexe1.setTypeDocAnnexe(enumTypeDoc.Journal_Annexe);
        frmAnnexe1.setNumImmAnnexe(numimm);
        frmAnnexe1.setDateDepartAnnexe(depart);
        frmAnnexe1.setNomnavAnnexe(currentDoc.getQnavire().getNomnav());
        System.out.println(currentDoc.getQnavire().getNomnav());
        model.addAttribute("frmAnnexe", frmAnnexe1);
        return "Documents/Annexe";


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
            List<qTraitement> lstTraitements = new ArrayList<qTraitement>();
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
                        // docForm.setTypePeche(currentDoc.getPrefix());
                        docForm.setSegmentPeche(((qTraitement) currentDoc).getSegPeche().toString());
                        docForm.setPrefix(currentDoc.getPrefix());
                        docForm.setTypeDoc(enumTypeDoc.Fiche_Traitement);
                        docForm.setTitre("Fiche de traitement");
                        docForm.setSegmentPeches(((qTraitement)currentDoc).getSegs());
                        docForm.setPagesTraitements(((qTraitement)currentDoc).getPagesTraitement());
                        docForm.setQteExportees(((qTraitement)currentDoc).getqQteExp());
                        docForm.setQteTraitees(((qTraitement)currentDoc).getqQteTraitees());
                        docForm.setQteDechu(((qTraitement)currentDoc).getQteDechu());


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
        qPrefix pr=null;
        List<qEspeceTypee> espTypees=null;
        qModelJP jp=null;
        modelValidateur.validate(editedModel, bindingresult);

        if(!bindingresult.hasErrors()) {
            jp=new qModelJP(editedModel.getQprefix(),editedModel.getEspecestypees());
            modeljpService.save(editedModel);
            model.addAttribute("editedModel",editedModel);

              }
        return "Documents/editModel";
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





    }