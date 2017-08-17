package com.gardecote.controllers;
import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import com.gardecote.web.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.List;

/**
 * Created by Dell on 12/11/2016.
 */
@Controller

@SessionAttributes(types = {CreateDocForm.class,creationLicForm.class})

@RequestMapping("/")
public class mappingController {
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
    private qAccordPecheService accordPecheService;
    @Autowired
    private qEspeceTypeeService especetypeeService;
    @Autowired
    private qEspeceService especeService;
    @Autowired
    private qJourDebService   jourdebService;
    @Autowired
    private qNationService  nationService;
    @Autowired
    private qTaskBarService  taskbarService;


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
    private qTaskProgressBar task=null;

    @RequestMapping(value="/start")
    public String ggg(final ModelMap model,HttpServletRequest request){
        HttpSession httpSession=request.getSession();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        searchAccueil sacc=new searchAccueil();
        searchBetweenTwoDates searchBetweenTwoDates=new searchBetweenTwoDates();
        searchBetweenTwoDatesByConcession searchBetweenTwoDatesByConcession=new searchBetweenTwoDatesByConcession();
        searchBetweenTwoDatesByConcession.setExclureDetails(false);
       List<choixTypeConcession> types=new ArrayList<choixTypeConcession>();
        choixTypeConcession h=null;
        for(qTypeConcession tc:typeconcessionService.findAll()){
           h=new choixTypeConcession();
            h.setChoix(false);
            h.setTypeConcession(tc);

            types.add(h);
        }
        searchBetweenTwoDatesByConcession.setTypes(types);


        this.task=taskbarService.findById(username);
        searchBetweenTwoDatesByConcession.setProgressBar(task);

        model.addAttribute("page","");
        model.addAttribute("sacc",sacc);
        model.addAttribute("searchBetweenTwoDates",searchBetweenTwoDates);
        model.addAttribute("searchBetweenTwoDatesByConcession",searchBetweenTwoDatesByConcession);
        return "index";
    }

    @RequestMapping(value="/startDP")
    public String startDP(){
        return "startDP";
    }
    @RequestMapping(value="/docGeneration")
    public String docgeneration(){
        return "saisieDP";
    }
    @RequestMapping(value="/docEdit")
    public String docedit(){
        return "docEdit";
    }

    @RequestMapping(value="/creationLicenceNouv",method = RequestMethod.POST)
    public String NVBat(final creationLicForm lf ,final ModelMap model){


        return  "comBat";
    }

    @ModelAttribute("allTypesEncad")
    public List<qAccordPeche> populateTypeEnc() {

        return accordPecheService.findAll();
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
    @ModelAttribute("allCategoriesConcessions")
    public List<qCategRessource> populateCategories() {
        return this.categService.findAll();
    }
    @ModelAttribute("allTypesConcession")
    public List<qTypeConcession> populateTypesConcession() {
        return this.typeconcessionService.findAll();
    }

    @RequestMapping(value="/creationLicenceExist",method = RequestMethod.GET)
    public String creationLicenceExist(creationLicForm lf ,final ModelMap model){
         return "existBat";
    }

    @RequestMapping(value="/validateNewLic", method = RequestMethod.POST)
    public String validateNewLic( final @Valid @ModelAttribute("LicForm") creationLicForm LicForm,final BindingResult bindingresult,final ModelMap model) {
        lstLicForm listLicform = new lstLicForm();
        System.out.println("jjjjjjjjjjj");
        String validateURL = null;

        if (concessionService.validate(((qLicenceNational)LicForm.getLicence()).getQconcession().getRefConcession()) == false)
        {LicForm.setRefMessage("concession invalide");
            System.out.println(LicForm.getRefMessage());
            return "showNewLicNational";}
        if(bindingresult.hasErrors()) {
                for(ObjectError obj:bindingresult.getFieldErrors()) {
                   System.out.println(obj);
                }
                System.out.println(bindingresult.getAllErrors().size());
                model.addAttribute("LicForm",LicForm);
                if(LicForm.getLicence() !=null) validateURL="showNewLicNational";
                if(LicForm.getLicence()!=null)    validateURL="showNewLicLibre";
            }

else        {

               if(LicForm.getLicence() !=null) licenceService.create(LicForm.getLicence());

                model.clear();
                Page<qLic> ls = licenceService.findAll(0, 20);
                listLicform.setLicences(ls);
                for (qLic tt : listLicform.getLicences()) System.out.println(tt);
                model.addAttribute("lstLicForm", listLicform);
                validateURL = "redirect:afficherLstLicence";

            }

        return validateURL;
    }



        @RequestMapping(value="/validateNewLic", params={"addRow"},method = RequestMethod.POST)
    public String addRow( final  creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String urlret=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();
        if(LicForm.getLicence() !=null) {

            if (LicForm.getLicence().getQcatressources() == null) {
                ens.add(new qCategRessource());
                LicForm.getLicence().setQcatressources(ens);
            } else LicForm.getLicence().getQcatressources().add(new qCategRessource());
        }
        if(LicForm.getLicence() !=null) {
            if (LicForm.getLicence().getQcatressources() == null) {
                ens.add(new qCategRessource());
                LicForm.getLicence().setQcatressources(ens);
            } else LicForm.getLicence().getQcatressources().add(new qCategRessource());
        }
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence() !=null) {

            urlret="showNewLicNational";
        }
        if(LicForm.getLicence() !=null) urlret="showNewLicLibre";
        return urlret;
    }

    @RequestMapping(value="/validateNewLic", params={"removeRow"},method = RequestMethod.POST)
    public String removeRow(@Valid final  creationLicForm LicForm, final ModelMap model,final HttpServletRequest req,BindingResult bindingresult) {

        String urlret=null;
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
       if(LicForm.getLicence()!=null) LicForm.getLicence().getQcatressources().remove(rowId.intValue());
        if(LicForm.getLicence()!=null) LicForm.getLicence().getQcatressources().remove(rowId.intValue());
        model.addAttribute("LicForm",LicForm);
        if(LicForm.getLicence()!=null) urlret="showNewLicNational";
        if(LicForm.getLicence()!=null) urlret="showNewLicLibre";
        return urlret;
    }
    @RequestMapping(value="/validateNewLicLBNB", params={"addRow"},method = RequestMethod.POST)
    public String addRowLBNB( final  creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String urlret=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();

            if (LicForm.getLicence().getQcatressources() == null) {
                ens.add(new qCategRessource());
                LicForm.getLicence().setQcatressources(ens);
            } else LicForm.getLicence().getQcatressources().add(new qCategRessource());

        model.addAttribute("LicForm",LicForm);

         urlret="showNewLicLibreLBNB";
        return urlret;
    }

    @RequestMapping(value="/validateNewLicLBNB", params={"removeRow"},method = RequestMethod.POST)
    public String removeRowLBNB(@Valid final  creationLicForm LicForm, final ModelMap model,final HttpServletRequest req,BindingResult bindingresult) {

        String urlret=null;
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));

        LicForm.getLicence().getQcatressources().remove(rowId.intValue());
        model.addAttribute("LicForm",LicForm);

       urlret="showNewLicLibreLBNB";
        return urlret;
    }
    @RequestMapping(value="/validateNewLicLBNB", method = RequestMethod.POST)
    public String validateNewLicLBNB( final @Valid @ModelAttribute("LicForm") creationLicForm LicForm,final BindingResult bindingresult,final ModelMap model) {
        lstLicForm listLicform = new lstLicForm();
        System.out.println("jjjjjjjjjjj");
        String validateURL = null;

        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            model.addAttribute("LicForm",LicForm);
               validateURL="showNewLicLibre";
        }

        else        {

           licenceService.create(LicForm.getLicence());
            model.clear();
            Page<qLic> ls = licenceService.findAll(0, 20);
            listLicform.setLicences(ls);
            for (qLic tt : listLicform.getLicences()) System.out.println(tt);
            model.addAttribute("lstLicForm", listLicform);
            validateURL = "redirect:afficherLstLicence";

        }

        return validateURL;
    }

    @RequestMapping(value="/creationLicenceLBNB",method = RequestMethod.GET)
    public String creationLicenceLBNB(final creationLicForm LicForm ,final ModelMap model){
        String urlret=null;

        qLicenceLibre newLic1=null;

        qNavireLegale currentNav=null;



                currentNav=new qNavireLegale();
                newLic1=new qLicenceLibre();
                newLic1.setQnavire(currentNav);

                LicForm.setLicence(newLic1);
                model.addAttribute("LicForm",LicForm);
                urlret="showNewLicLibreBat";

        return urlret;
    }
    @RequestMapping(value="/validateNewLicLNNB", params={"addRow"},method = RequestMethod.POST)
    public String addRowLNNB( final  creationLicForm LicForm, final ModelMap model,final BindingResult bindingresult) {

        String urlret=null;

        List<qCategRessource> ens=new ArrayList<qCategRessource>();

        if (LicForm.getLicence().getQcatressources() == null) {
            ens.add(new qCategRessource());
            LicForm.getLicence().setQcatressources(ens);
        } else LicForm.getLicence().getQcatressources().add(new qCategRessource());

        model.addAttribute("LicForm",LicForm);

        urlret="showNewLicNationalLBNB";
        return urlret;
    }

    @RequestMapping(value="/validateNewLicLNNB", params={"removeRow"},method = RequestMethod.POST)
    public String removeRowLNNB(@Valid final  creationLicForm LicForm, final ModelMap model,final HttpServletRequest req,BindingResult bindingresult) {

        String urlret=null;
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));

        LicForm.getLicence().getQcatressources().remove(rowId.intValue());
        model.addAttribute("LicForm",LicForm);

        urlret="showNewLicLibreLNNB";
        return urlret;
    }
    @RequestMapping(value="/validateNewLicLNNB", method = RequestMethod.POST)
    public String validateNewLicLNNB( final @Valid @ModelAttribute("LicForm") creationLicForm LicForm,final BindingResult bindingresult,final ModelMap model) {
        lstLicForm listLicform = new lstLicForm();
        System.out.println("jjjjjjjjjjj");
        String validateURL = null;

        if(bindingresult.hasErrors()) {
            for(ObjectError obj:bindingresult.getFieldErrors()) {
                System.out.println(obj);
            }
            System.out.println(bindingresult.getAllErrors().size());
            model.addAttribute("LicForm",LicForm);
            validateURL="showNewLicNational";
        }

        else        {

            licenceService.create(LicForm.getLicence());
            model.clear();
            Page<qLic> ls = licenceService.findAll(0, 20);
            listLicform.setLicences(ls);
            for (qLic tt : listLicform.getLicences()) System.out.println(tt);
            model.addAttribute("lstLicForm", listLicform);
            validateURL = "redirect:afficherLstLicence";

        }

        return validateURL;
    }

    @RequestMapping(value="/creationLicenceLNNB",method = RequestMethod.GET)
    public String creationLicenceLNNB(final creationLicForm LicForm ,final ModelMap model){
        String urlret=null;

        qLicenceNational newLic=null;

        qNavireLegale currentNav=null;



        currentNav=new qNavireLegale();
        newLic=new qLicenceNational();
        newLic.setQnavire(currentNav);

        LicForm.setLicence(newLic);
        model.addAttribute("LicForm",LicForm);
        urlret="showNewLicNationqalBat";

        return urlret;
    }

    @RequestMapping(value="/creationLicence2",method = RequestMethod.POST)
    public String creationLicence2(final creationLicForm LicForm ,final ModelMap model){
        String urlVal=null,urlret=null;
        qLicenceNational newLic=null;
        qLicenceLibre newLic1=null;
        List<qNavireLegale> lstBat;
        qNavireLegale currentNav=null;

        if(LicForm.getRegime().equals("National") && LicForm.getNumSelected()!=null) {
            currentNav=registrenavireService.findLegalById(LicForm.getNumSelected().toString());
            newLic=new qLicenceNational();
            newLic.setQnavire(currentNav);
            newLic.setBalise(currentNav.getBalise());newLic.setAnneeconstr(currentNav.getAnneeconstr());newLic.setCalpoids(currentNav.getCalpoids());newLic.setCount(currentNav.getCount());newLic.setEff(currentNav.getEff());newLic.setGt(currentNav.getGt());newLic.setImo(currentNav.getImo());
            newLic.setKw(currentNav.getKw());newLic.setNbrhomm(currentNav.getNbrhomm());newLic.setLarg(currentNav.getLarg());newLic.setLongg(currentNav.getLongg());newLic.setNomnav(currentNav.getNomnav());newLic.setRadio(currentNav.getRadio());newLic.setPuimot(currentNav.getPuimot());
            newLic.setNomar(currentNav.getNomar());
            LicForm.setLicence(newLic);
            LicForm.setRefCon(null);
            model.addAttribute("LicForm",LicForm);
            urlret="showNewLicNational";}
        else {
            if(LicForm.getRegime().equals("Etranger") && LicForm.getNumSelected()!=null) {
                currentNav=registrenavireService.findLegalById(LicForm.getNumSelected().toString());
                newLic1=new qLicenceLibre();
                newLic1.setQnavire(currentNav);
                newLic1.setBalise(currentNav.getBalise());newLic.setAnneeconstr(currentNav.getAnneeconstr());newLic.setCalpoids(currentNav.getCalpoids());newLic.setCount(currentNav.getCount());newLic.setEff(currentNav.getEff());newLic.setGt(currentNav.getGt());newLic.setImo(currentNav.getImo());
                newLic1.setKw(currentNav.getKw());newLic.setNbrhomm(currentNav.getNbrhomm());newLic.setLarg(currentNav.getLarg());newLic.setLongg(currentNav.getLongg());newLic.setNomnav(currentNav.getNomnav());newLic.setRadio(currentNav.getRadio());newLic.setPuimot(currentNav.getPuimot());
                newLic1.setNomar(currentNav.getNomar());


                LicForm.setLicence(newLic1);
                LicForm.setRefCon(" ");
                model.addAttribute("LicForm",LicForm);
                urlret="showNewLicLibre";}
            else  {urlret="creationLicenceNouv";}
        }

        return urlret;
    }

    @RequestMapping(value="/creationLicence1",method = RequestMethod.POST)
    public String creationLicence1(final creationLicForm LicForm ,final ModelMap model){
        String urlVal=null;
        List<qNavireLegale> lstBat;
        if(LicForm.getTypeOperation().equals("Nouveau"))
        {
            urlVal="creationLicenceExist";
        }
        if(LicForm.getTypeOperation().equals("Existant"))
        {
            System.out.println("Test");
            lstBat=registrenavireService.findAllLegal();
            System.out.println(lstBat.size());
            LicForm.setLstBat(lstBat);
            model.addAttribute("LicForm",LicForm);
            urlVal="creationLicenceNouv";
        }
        return urlVal;
    }


    @RequestMapping(value="/creationLicence",method = RequestMethod.GET)
    public String creationLicence(ModelMap model){
         creationLicForm licform=new creationLicForm();

         model.addAttribute("LicForm",licform);

        return "creationLicenc";
    }

    @InitBinder public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("numimm", "enumtypedoc");
    }
    @RequestMapping(value="/retActPage",method = RequestMethod.POST)
    public String retActPage(final CreateDocForm docForm, final BindingResult result,@RequestParam(name="nump",defaultValue = "0") String nump,@RequestParam(name="indexp",defaultValue = "0") Integer indexp,final ModelMap model) {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind  disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        System.out.println(docForm.getCurrentDoc().getDepart());
        System.out.println(docForm.getCurrentDoc().getNumImm());
        // System.out.println(docForm.getCurrentDoc().getqDocPK().toString());
        qDoc dc=docService.save(docForm.getCurrentDoc());
        docForm.setCurrentPage(indexp);
        return "docEdit";
    }

    }
