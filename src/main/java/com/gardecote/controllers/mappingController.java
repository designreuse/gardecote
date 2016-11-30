package com.gardecote.controllers;

import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import com.gardecote.web.CreateDocForm;
import com.gardecote.web.creationLicForm;
import com.gardecote.web.lstLicForm;
import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value="/start")
    public String ggg(){
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

        qNavire currentNav=null;



                currentNav=new qNavire();
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

        qNavire currentNav=null;



        currentNav=new qNavire();
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
        List<qNavire> lstBat;
        qNavire currentNav=null;

        if(LicForm.getRegime().equals("National") && LicForm.getNumSelected()!=null) {
            currentNav=registrenavireService.findById(LicForm.getNumSelected().toString());
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
                currentNav=registrenavireService.findById(LicForm.getNumSelected().toString());
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
        List<qNavire> lstBat;
        if(LicForm.getTypeOperation().equals("Nouveau"))
        {
            urlVal="creationLicenceExist";
        }
        if(LicForm.getTypeOperation().equals("Existant"))
        {
            System.out.println("Test");
            lstBat=registrenavireService.findAll();
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
    @RequestMapping(value="/saveDocument",method = RequestMethod.POST)
    public String saveDocument(final CreateDocForm docForm, final BindingResult result,final ModelMap model) {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind  disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
      System.out.println(docForm.getCurrentDoc().getDepart());
      System.out.println(docForm.getCurrentDoc().getNumImm());
      //System.out.println(docForm.getCurrentDoc().getqDocPK().toString());
      docService.save(docForm.getCurrentDoc());
      docForm.setCurrentPage(0);
        return "docEdit";
    }
    @RequestMapping(value="/creerDoc",method = RequestMethod.GET)
    public String creerDocument(final ModelMap model,final CreateDocForm docForm, @RequestParam(name="debut",defaultValue = "PC0") String debut, @RequestParam(name="fin",defaultValue = "PC0") String fin,@RequestParam(name="dateDepart1") String dateDepart1) {
      //  CreateDocForm docForm=new CreateDocForm();
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        String titre=null;
        qDoc currentDoc=null;
        String typeDoc=null;
        Object docExact=null;
        Object traitExact=null;
        List<qTraitement> lstTraitements=new ArrayList<qTraitement>();
        qSeqPK qseqpk=new qSeqPK(debut,fin);

        qSeq qseq=seqService.findById(qseqpk);
      //  Date dateDepart=null,dateRetour=null;

        System.out.println("debut="+debut+"fin"+fin);
       // boolean flg=docService.checkSaisie(qseqpk);
         SimpleDateFormat sdfmt1= new SimpleDateFormat("yyyy-MM-dd");
        qSeqPK spk=new qSeqPK(debut,fin);
        qSeq se=seqService.findById(spk);
        if(se==null) se=new qSeq(debut,fin,null);
        //	System.out.println(nav.getNumimm());
        Date  dateDepart=null, dateRetour=null;

        try {
            dateDepart= sdfmt1.parse(dateDepart1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
            System.out.println("RRRRRRRRRRRR");
            Map<String, Set<Object>> result = docService.detecterDestructionDonnees(qseqpk,dateDepart);

            System.out.println("2 EME ETAPE");
            for (Map.Entry<String, Set<Object>> entry : result.entrySet()) {

                if (entry.getKey() == "lstExact")
                    for (Iterator<Object> it = entry.getValue().iterator(); it.hasNext(); ) {
                        Object f = it.next();
                        if(f!=null && f instanceof qDoc)    docExact= f;
                        if(f!=null && f instanceof qTraitement)    traitExact= f;
                    }

                if (entry.getKey() == "lstDoc")
                    for (Iterator<Object> it = entry.getValue().iterator(); it.hasNext(); ) {
                        Object f = it.next();
                  if(f!=null )      lstDoc.add((qDoc) f);
                    }
                if (entry.getKey() == "lstTraitements")
                    for (Iterator<Object> it = entry.getValue().iterator(); it.hasNext(); ) {
                        Object f = it.next();
                        if(f!=null )    lstTraitements.add((qTraitement) f);
                    }

            }
   //     lstDoc.remove( docExact);
        System.out.println(lstDoc.size());
     //   lstTraitements.remove(traitExact);
       // qDoc docact=docService.findById(spk);
            if(lstDoc.size()==0 && docExact==null && traitExact==null ) {


                currentDoc=docService.creerDoc(dateDepart,null,se);

            }
        else {
                if(lstDoc.size()==1 && (docExact!=null))   {   System.out.println("here");       currentDoc=(qDoc) docExact;}
                if(lstTraitements.size()==1 && ( traitExact!=null))             currentDoc=(qDoc) traitExact;
                if(lstDoc.size()>0 && (docExact==null)&& ( traitExact!=null)) currentDoc=null;

            }
     //      if(lstDoc.size()==1 && docDuplicat==null && traitDuplicat==null) {
     //         currentDoc=lstDoc.get(0);
    //   }

      //      result.put("currentDoc",currentDoc);
        if(currentDoc instanceof qMarree) {typeDoc="M";titre="Marrée";}
        if(currentDoc instanceof qDebarquement){ typeDoc="D";titre="Débarquement"+((qDebarquement) currentDoc).getTypeDeb().toString();}

        docForm.setCurrentPage(0);
        docForm.setPageFin(fin);
        docForm.setCurrentDoc(currentDoc);
        docForm.setLstDoc(lstDoc);
        docForm.setLstTraitement(lstTraitements);
        docForm.setTypeDoc(typeDoc);
        docForm.setTitre(titre);


        model.addAttribute("docForm", docForm);
        System.out.println("Liste de documents  :");
        System.out.println(lstDoc);
        System.out.println("Liste de traitements :");
        System.out.println(lstTraitements);
        System.out.println("Document encours :");
        System.out.println(currentDoc);
        System.out.println("doc duplicat :");
        System.out.println(docExact);
        System.out.println("Dtrait dupl :");
        System.out.println(traitExact);
        System.out.println("type de doc :");
        System.out.println(currentDoc.getEnumtypedoc().toString());
        System.out.println("categories size :");
        System.out.println(((qDebarquement)currentDoc).getCategories().size());
     for(qCategDeb cat:((qDebarquement)currentDoc).getCategories())
        System.out.println(cat.getCat().getTypeconcessionConcernee());
        return "docEdit";
    }
}
