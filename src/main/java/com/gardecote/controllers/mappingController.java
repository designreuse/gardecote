package com.gardecote.controllers;

import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Dell on 12/11/2016.
 */
@Controller

public class mappingController {


    @Autowired
    private qCarnetService carnetService;
    @Autowired
    private qConcessionService concessionService;
    @Autowired
    private qConsignataireService consignataireService;
    @Autowired
    private qEnginPecheService enginsPecheService;

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

    @RequestMapping("/start")
    public String ggg(){

        return "index";
    }

    @RequestMapping("/creerDoc")
    public @ResponseBody
    Object creerDocument(Model model, @RequestParam("debut") String debut, @RequestParam("fin") String fin) {
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        qDoc currentDoc=null;
        List<qTraitement> lstTraitements=new ArrayList<qTraitement>();
        qSeqPK qseqpk=new qSeqPK(debut,fin);
        qSeq qseq=seqService.findById(qseqpk);
        Date dateDepart=null,dateRetour=null;
        List<qEnginPecheDeb> choixEnginsDeb=null;
        List<qEnginPeche> choixEnginsMar=null;

        boolean flg=docService.checkSaisie(qseqpk);
        if(flg==true) {
            Map<String, Set<Object>> result = docService.detecterDestructionDonnees(qseqpk);
            qDistributeur distributeur = null;

            for (Map.Entry<String, Set<Object>> entry : result.entrySet()) {
                if (entry.getKey() == "lstDoc")
                    for (Iterator<Object> it = entry.getValue().iterator(); it.hasNext(); ) {
                        Object f = it.next();
                        lstDoc.add((qDoc) f);
                    }
                if (entry.getKey() == "lstTraitements")
                    for (Iterator<Object> it = entry.getValue().iterator(); it.hasNext(); ) {
                        Object f = it.next();
                        lstTraitements.add((qTraitement) f);
                    }

            }
            if(lstDoc.size()==0) {
                qSeq seq=new qSeq(debut,fin,null);
                currentDoc=docService.creerDoc(dateDepart,dateRetour,seq,choixEnginsDeb,choixEnginsMar);

            }
            if(lstDoc.size()==1) {
                currentDoc=lstDoc.get(0);

            }
            if(lstDoc.size()>1)  {
                currentDoc=null;
            }
      //      result.put("currentDoc",currentDoc);
        }

        model.addAttribute("lstDoc",lstDoc);
        model.addAttribute("lstTraitement",lstTraitements);


        model.addAttribute("currentDoc",currentDoc);
        System.out.println("Liste de documents interference :");
        System.out.println(lstDoc);
        System.out.println("Liste de traitements :");
        System.out.println(lstTraitements);
        System.out.println("Document encour :");
        System.out.println(currentDoc);


        return "index";
    }
}
