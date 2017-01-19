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
    concessionValidator concessionValidator;
    @Autowired
    MessageByLocaleService messageByLocaleService;
    @Autowired
    saveDocValidator saveDocValidator;
    @Autowired
    licenceValidator licValidator;
    @Autowired
    attrUsineValidator attrUsineValidator;
    @Autowired
    attrTypeConcessionModif attrTypeConcessionModifValidator;
    @Autowired
    attrTypeConcession attrTypeConcessionValidator;
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
    @Autowired
    private qMareeAnnexeService mareeAnnexService;

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
               qNavire navireselected=registrenavireService.findById(CarnetAttribue.getNavireSelected().getNumimm());
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


    @RequestMapping(value="createDocumentSave",method = RequestMethod.POST)
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

        if(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc() instanceof qDebarquement)  {
            lics=docService.retLicences(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getQseq(),frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getEnumtypedoc());

            docService.save((qDebarquement)frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc());
            model.addAttribute("licencesRef", lics);
            urlNav="docEditDebarquement";
        }

        if(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc() instanceof qMarree)  {
            lics=docService.retLicences(frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getQseq(),frmSearchPgsForDocCrea.getCreateDocFormm().getCurrentDoc().getEnumtypedoc());

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
        else   {
            List<qMarreeAnnexe> lstAnnexes=mareeAnnexService.findAll();
            model.addAttribute("lstAnnexes", lstAnnexes);
            return "Documents/listAnnexe";}


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
     //   qSeq seq = currentDoc.getQseq();
        //     lics=docService.retLicences(seq);
        //modelValidateur.validate(currentDoc, bindingresult);
        if (currentDoc.isBloquerDeletion() == false) {
            redirectAttributes.addFlashAttribute("deletefeedback", "suppression impossible");
        } else {

            mareeAnnexService.delete(currentDoc.getqDocPK());
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

        qEnginsLicence qEng1 = new qEnginsLicence(enumEnginDeb.Casier, enumEngin.Indefini, 70);
        qEnginsLicence qEng2 = new qEnginsLicence(enumEnginDeb.Palangre, enumEngin.Indefini, 30);

        List<qEnginsLicence> qEnginsDeb = new ArrayList<qEnginsLicence>();
        qEnginsDeb.add(qEng1);
        qEnginsDeb.add(qEng2);

        List<qEnginsLicence> qEnginsdiff = new ArrayList<qEnginsLicence>();
        qEnginsLicence f = enginsLicenceService.findById(new qEnginsLicencePK(enumEnginDeb.Casier, enumEngin.Indefini, 70));
        qEnginsLicence d = enginsLicenceService.findById(new qEnginsLicencePK(enumEnginDeb.Palangre, enumEngin.Indefini, 30));
        qEnginsdiff.add(f);
        qEnginsdiff.add(d);

        enginsLicenceService.create(qEng1);
        enginsLicenceService.create(qEng2);

        typeconcessionService.create(paCeph);
        typeconcessionService.create(paCrust);
        typeconcessionService.create(paDem);
        typeconcessionService.create(paPel);
        typeconcessionService.create(paAlAut);

        qCategRessource qPACep = new qCategRessource(typeconcessionService.findById(1), enumSupport.Collectif, null, qEnginsdiff);

        qCategRessource qPACrust = new qCategRessource(paCrust, enumSupport.Collectif, null, qEnginsdiff);

        qCategRessource qPADem = new qCategRessource(paDem, enumSupport.Collectif, null, qEnginsdiff);

        qCategRessource qPAPel = new qCategRessource(paPel, enumSupport.Collectif, null, qEnginsdiff);

        qCategRessource qPAAlAut = new qCategRessource(paAlAut, enumSupport.Collectif, null, qEnginsdiff);


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

        qCategRessource qPCNPCep = new qCategRessource(pcNPCeph, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCNPCrust = new qCategRessource(pcNPCrust, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCNPDem = new qCategRessource(pcNPDem, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCNPPel1 = new qCategRessource(pcNPPelSenneursM26, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCNPAlAut = new qCategRessource(pcNPAutreMol, enumSupport.Collectif, null, qEnginsdiff);


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


        qCategRessource qPCPCep = new qCategRessource(pcPCeph, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCPCrust = new qCategRessource(pcPCrust, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCPDem = new qCategRessource(pcPDem, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCPPel1 = new qCategRessource(pcPPelSenneursM26, enumSupport.Collectif, null, qEnginsdiff);
        qCategRessource qPCPAlAut = new qCategRessource(pcPAutreMol, enumSupport.Collectif, null, qEnginsdiff);

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


        qCategRessource qRCphNCeph = new qCategRessource(phNCeph, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNAutres = new qCategRessource(phNAutres, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNCrab = new qCategRessource(phNCrab, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNCrv = new qCategRessource(phNCrv, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNLangRose = new qCategRessource(phNMerlu, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNMerlu = new qCategRessource(phNPel, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNPel = new qCategRessource(phNDemAQM, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphNDemAQM = new qCategRessource(phNThon, enumSupport.Individuel, null, qEnginsdiff);
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

        qCategRessource qRCphACeph = new qCategRessource(phACeph, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphAAutres = new qCategRessource(phAAutres, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphACrab = new qCategRessource(phACrab, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphACrv = new qCategRessource(phACrv, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qphALangRose = new qCategRessource(phALangRose, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphAMerlu = new qCategRessource(phAMerlu, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphAPel = new qCategRessource(phAPel, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphADemAQM = new qCategRessource(phADemAQM, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphAThon = new qCategRessource(phAThon, enumSupport.Individuel, null, qEnginsdiff);


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

        qCategRessource qRCphLCeph = new qCategRessource(phLCeph, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphLAutres = new qCategRessource(phLAutres, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphLCrab = new qCategRessource(phLCrab, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCphLCrv = new qCategRessource(phLCrv, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qphLLangRose = new qCategRessource(phLLangRose, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCLphALangRose = new qCategRessource(phLMerlu, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCLphAMerlu = new qCategRessource(phLPel, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCLphAPel = new qCategRessource(phLDemAQM, enumSupport.Individuel, null, qEnginsdiff);
        qCategRessource qRCLphADemAQM = new qCategRessource(phLThon, enumSupport.Individuel, null, qEnginsdiff);
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
        qTypeLic qtyplic1 = new qTypeLic('A', 'A', "Affreté artisanal");
        qTypeLic qtyplic2 = new qTypeLic('A', 'C', "Affreté collecte pêche artisanal");
        qTypeLic qtyplic3 = new qTypeLic('A', 'D', "Affreté demersal(poisson+ ceph)");
        qTypeLic qtyplic4 = new qTypeLic('A', 'E', "Affreté demersal(crust+ ceph)");
        qTypeLic qtyplic5 = new qTypeLic('A', 'L', "Affreté Langouste");
        qTypeLic qtyplic6 = new qTypeLic('A', 'M', "Affreté Merlu");
        qTypeLic qtyplic7 = new qTypeLic('A', 'P', "Affreté Pelagique");
        qTypeLic qtyplic8 = new qTypeLic('A', 'T', "Affreté thon");
        qTypeLic qtyplic9 = new qTypeLic('A', 'B', "Affreté crabes");
        qTypeLic qtyplic10 = new qTypeLic('A', 'Q', "Affreté coquillage");
        qTypeLic qtyplic11 = new qTypeLic('A', 'R', "Affreté recherche");
        qTypeLic qtyplic12 = new qTypeLic('A', 'S', "Affreté requin");
        qTypeLic qtyplic13 = new qTypeLic('A', 'V', "Affreté crevettes");
        qTypeLic qtyplic14 = new qTypeLic('A', '1', "Affreté crevettes+langoustes");
        qTypeLic qtyplic15 = new qTypeLic('A', 'F', "Affreté demersal+lang verte");


        qTypeLic qtyplic20 = new qTypeLic('L', 'A', "Licence artisanal");
        qTypeLic qtyplic21 = new qTypeLic('L', 'C', "Licence collecte peche artisanal");
        qTypeLic qtyplic22 = new qTypeLic('L', 'D', "Licence dem poss+ceph");
        qTypeLic qtyplic23 = new qTypeLic('L', 'L', "Licence langouste");
        qTypeLic qtyplic24 = new qTypeLic('L', 'M', "Licence merlu");
        qTypeLic qtyplic25 = new qTypeLic('L', 'P', "Licence pelagique");
        qTypeLic qtyplic26 = new qTypeLic('L', 'T', "Licence thon");
        qTypeLic qtyplic27 = new qTypeLic('L', 'V', "Licence crust sauf langouste");
        qTypeLic qtyplic28 = new qTypeLic('L', 'B', "Licence crabes");
        qTypeLic qtyplic29 = new qTypeLic('L', 'H', "Licence esp dem profond");
        qTypeLic qtyplic30 = new qTypeLic('L', 'Q', "Licence coquillage");
        qTypeLic qtyplic31 = new qTypeLic('L', 'R', "Licence recherche");
        qTypeLic qtyplic32 = new qTypeLic('L', 'S', "Licence requin");
        qTypeLic qtyplic33 = new qTypeLic('L', '1', "Licence crevettes +langouste");


        qTypeLic qtyplic35 = new qTypeLic('N', 'A', "National artisanal");
        qTypeLic qtyplic36 = new qTypeLic('N', 'C', "National collecte peche artisanal");
        qTypeLic qtyplic37 = new qTypeLic('N', 'D', "National dem poiss+ ceph");
        qTypeLic qtyplic38 = new qTypeLic('N', 'E', "National dem cep+ crust");
        qTypeLic qtyplic39 = new qTypeLic('N', 'L', "National langouste");
        qTypeLic qtyplic40 = new qTypeLic('N', 'M', "National merlu");
        qTypeLic qtyplic41 = new qTypeLic('N', 'P', "National pelagique");
        qTypeLic qtyplic42 = new qTypeLic('N', 'T', "National thon");
        qTypeLic qtyplic43 = new qTypeLic('N', 'B', "National crabes");
        qTypeLic qtyplic44 = new qTypeLic('N', 'Q', "National coquillage");
        qTypeLic qtyplic45 = new qTypeLic('N', 'R', "National recherche");
        qTypeLic qtyplic46 = new qTypeLic('N', 'S', "National requin");
        qTypeLic qtyplic47 = new qTypeLic('N', 'V', "National crevettes");
        qTypeLic qtyplic48 = new qTypeLic('N', '1', "National crevettes+langouste");

        qTypeLic qtyplic49 = new qTypeLic('L', 'G', "Licence Esp dem autre que merlu");
        qTypeLic qtyplic50 = new qTypeLic('L', 'E', "Licence Esp dem");
        qTypeLic qtyplic51 = new qTypeLic('A', 'V', "AU");
        qTypeLic qtyplic52 = new qTypeLic('L', 'I', "Licence Thons+Espadons");
        qTypeLic qtyplic53 = new qTypeLic('N', 'N', "NN");
        qTypeLic qtyplic54 = new qTypeLic('N', 'I', "NI");
        qTypeLic qtyplic55 = new qTypeLic('N', 'U', "NU");
        qTypeLic qtyplic56 = new qTypeLic('L', 'T', "LT");
        qTypeLic qtyplic57 = new qTypeLic('X', 'P', "XP");
        qTypeLic qtyplic58 = new qTypeLic('Z', 'Z', "INDET");
        qTypeLic qtyplic59 = new qTypeLic('L', '0', "Licence INDET");
        qTypeLic qtyplic60 = new qTypeLic('N', '0', "National");
        qTypeLic qtyplic61 = new qTypeLic('A', '0', "Affrete INDET");
        qTypeLic qtyplic62 = new qTypeLic('A', 'H', "National ravitalleur pel");

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
    public String SupprimerConcessionnaire(@RequestParam(name="refConcessionnairePK") String refConcessionnairePK,final ModelMap model) {
    //   List<qTypeLic> typeslic=typelicService.findAll();
qConsignataire deletedConsignataire=consignataireService.findById(refConcessionnairePK);
        if( deletedConsignataire!=null) {
            if(deletedConsignataire.getQconcession()!=null)
            {  consignataireService.delete(refConcessionnairePK);
            model.addAttribute("feedback","supprimé");}
            else {model.addAttribute("feedback","impossible de supprimer");}
        }
        else model.addAttribute("feedback","impossible de supprimer");

        return "autres/typeslic";
    }
    @RequestMapping(value="/AjouterConcession",method = RequestMethod.GET)
    public String AjouterConcession(final @RequestParam(name="refConcessionnairePK") String refConcessionnairePK,final ModelMap model) {
        qConcession newConcession=new qConcession();
        qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
     if(currentConsignataire!=null)   newConcession.setQconsignataire(currentConsignataire);
        else System.out.println("impssiiiiiiiiiiiiiiiible");

        model.addAttribute("newConcession",newConcession);

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
    @RequestMapping(value="/submitConcession", params={"addRow"},method = RequestMethod.POST)
    public String submitConcessionAddRow(final   qConcession newConcession,final BindingResult bindingresult,final ModelMap model) {
        List<qCategRessource> ens=new ArrayList<qCategRessource>();
        System.out.println(newConcession.getRefConcession());
        System.out.println(newConcession.getQconsignataire());
        System.out.println(newConcession.getDateConcession());
        System.out.println(newConcession.getDateDebut());
        System.out.println(newConcession.getDateFin());
        System.out.println(newConcession.getQuotaEnTonne());
        model.addAttribute("newConcession",newConcession);
        if (newConcession.getCategoriesRessources() == null) {
            ens.add(new qCategRessource());
            newConcession.setCategoriesRessources(ens);
        } else newConcession.getCategoriesRessources().add(new qCategRessource());
        return "concessions/nouvelleConcession";
    }
    @RequestMapping(value="/submitConcession", params={"saveConcession"},method = RequestMethod.POST)
    public String submitConcessionSave(final  @ModelAttribute("newConcession") qConcession newConcession,final BindingResult bindingresult,final ModelMap model) {

        concessionValidator.validate(newConcession, bindingresult);

      if(!bindingresult.hasErrors()) {
          concessionService.create(newConcession);
          model.addAttribute("feedback","la concession est sauvgardée");
          qConcession newConc=new qConcession();
          newConc.setQconsignataire(newConcession.getQconsignataire());
          model.addAttribute("newConcession",newConc);
          return "concessions/nouvelleConcession";
      }
        else {
          System.out.println(bindingresult.getAllErrors().size());
          for( ObjectError err:bindingresult.getAllErrors()) {
             System.out.println(err.getCode());
              System.out.println(err.getObjectName());

          }
          System.out.println(bindingresult.getAllErrors().size());
           model.addAttribute("newConcession",newConcession);
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
    public String AjouterCategorieSave(final qCategRessource modifCat,final ModelMap model) {
        categService.save(modifCat);
        return "redirect:listCategRessources";
    }
    @RequestMapping(value="/ModifierCategorie",method = RequestMethod.GET)
    public String ModifierCategorie(final @RequestParam(name="catPK") Integer catPK,final ModelMap model) {
qCategRessource categRessource=categService.findById(catPK);
        //  qConsignataire currentConsignataire=consignataireService.findById(refConcessionnairePK);
        model.addAttribute("modifCat",categRessource);
        return "categories/modifCat";
    }

    @RequestMapping(value="/ModifierCategorieSave",method = RequestMethod.POST)
    public String ModifierCategorieSave(final @ModelAttribute("modifCat") qCategRessource modifCat,final ModelMap model) {
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

}