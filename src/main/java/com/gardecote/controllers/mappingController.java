package com.gardecote.controllers;

import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import com.gardecote.web.CreateDocForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dell on 12/11/2016.
 */
@Controller

@SessionAttributes(types = {CreateDocForm.class})

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
