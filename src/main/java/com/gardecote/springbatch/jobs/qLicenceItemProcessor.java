package com.gardecote.springbatch.jobs;
import com.gardecote.LicenceAc;
import com.gardecote.business.service.*;
import com.gardecote.entities.*;
import  com.gardecote.models.qLicenceModel;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 09/02/2017.
 */
public class qLicenceItemProcessor implements ItemProcessor<qLicenceModel, qLic> {

    @Autowired
    private qConcessionService concessionService;
    @Autowired
    private qZoneService zoneService;
    @Autowired
    private qNationService  nationService;
    @Autowired
    private qRegistreNavireService registrenavireService;
    @Autowired
    private qTypeLicService typelicService;
    @Autowired
    private qTypeNavService typenavService;
    @Autowired
    private qAccordPecheService accService;
    qLic currentLic=null;
    @Override
    public qLic process(qLicenceModel qLicModelInput) throws Exception {
        Date DEBAUT=null,FINAUTO=null;
        Date createdOn=null;
        qNavireLegale insertedNavire=null;
        qTypeLic currentTypLic=typelicService.findById(qLicModelInput.getQtypnavIdTypelic());
        qZone currentZone=zoneService.findById(qLicModelInput.getZoneIdzone());
        qNation currentNation=nationService.findById(qLicModelInput.getNationIdnation());
        qNavireLegale currentNavire=registrenavireService.findLegalById(qLicModelInput.getNumimm());
        qTypeNav currentTypNav=typenavService.findById(qLicModelInput.getTypb().toString());
        qConcession currentconcession=null;
        qAccordPeche currentEncadrement= null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-mm-dd");
        try {
            DEBAUT=sdfmt1.parse(qLicModelInput.getDebaut());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            FINAUTO=sdfmt1.parse(qLicModelInput.getFinauto());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if ("LICENCENATIONAL".equals(qLicModelInput.getTypelicence()))
        {
            if(currentNavire!=null) {
                insertedNavire=currentNavire;
                createdOn=currentNavire.getUpdatedOn();
                System.out.println(createdOn);
                System.out.println(DEBAUT);
                if(createdOn.before(DEBAUT) || createdOn.compareTo(DEBAUT)==0)
                {currentNavire.setNomnav(qLicModelInput.getNomnav());
                    currentNavire.setUpdatedOn(DEBAUT);
               //     navService.update(currentNavire);
                    }
            }
            else
            {


                qNavireLegale newnavire=new qNavireLegale(qLicModelInput.getNumimm(),qLicModelInput.getNomnav(),qLicModelInput.getLongueur(),qLicModelInput.getPuimot(),currentNation,qLicModelInput.getLarg(),qLicModelInput.getCount(),qLicModelInput.getNbrhomm(),
                        qLicModelInput.getEff(),qLicModelInput.getAncons(),qLicModelInput.getCalpoids(),Float.valueOf(qLicModelInput.getGt().toString()),Float.valueOf(qLicModelInput.getKw().toString()),Float.valueOf(qLicModelInput.getTjb().toString()),qLicModelInput.getImo(),qLicModelInput.getPort(),qLicModelInput.getRadio(),qLicModelInput.getBalise(),DEBAUT,
                        qLicModelInput.getNumlic(),enumModePeche.NATIONAL,DEBAUT,FINAUTO,null,null,qLicModelInput.getNomar(),null);

                insertedNavire=newnavire;
                System.out.println("nouv bat"+newnavire.getNomnav());
            }
            currentconcession= concessionService.findById(qLicModelInput.getQconcessionid());
            currentLic=new qLicenceNational(currentTypLic,currentZone,currentNation,null,insertedNavire,enumTypeBat.INDEF,
                    DEBAUT,FINAUTO,qLicModelInput.getAncons(),qLicModelInput.getBalise(),qLicModelInput.getCalpoids(),qLicModelInput.getCount(),
                    qLicModelInput.getEff(),Float.valueOf(Double.valueOf(qLicModelInput.getGt()).toString()),Integer.valueOf(qLicModelInput.getImo()),
                    Float.valueOf(qLicModelInput.getKw().toString()),qLicModelInput.getLarg().toString(),qLicModelInput.getLongueur().toString(),qLicModelInput.getNbrhomm().toString(),
                    qLicModelInput.getNomar(),qLicModelInput.getNomnav(),qLicModelInput.getNumlic(),qLicModelInput.getPort(),
                    qLicModelInput.getPuimot(),qLicModelInput.getRadio(),Float.valueOf(qLicModelInput.getTjb().toString()),currentconcession,null,enumModePeche.NATIONAL);
        }
        if ("LICENCELIBRE".equals(qLicModelInput.getTypelicence()))
        {
            if(qLicModelInput.getTypencad()==1) currentEncadrement=accService.findById(1);
            if(qLicModelInput.getTypencad()==2) currentEncadrement=accService.findById(2);
            if(qLicModelInput.getTypencad()==3) currentEncadrement=accService.findById(3);
            if(qLicModelInput.getTypencad()==4) currentEncadrement=accService.findById(4);
            if(qLicModelInput.getTypencad()==5) currentEncadrement=accService.findById(5);
            if(qLicModelInput.getTypencad()==6) currentEncadrement=accService.findById(6);
            if(currentNavire!=null) {
                insertedNavire=currentNavire;
                createdOn=currentNavire.getUpdatedOn();
                System.out.println(createdOn);
                System.out.println(DEBAUT);
                if(createdOn.before(DEBAUT))
                {currentNavire.setNomnav(qLicModelInput.getNomnav());
                    currentNavire.setUpdatedOn(DEBAUT);
                    //     navService.update(currentNavire);
                }
            }
            else
            {
                qNavireLegale newnavire=new qNavireLegale(qLicModelInput.getNumimm(),qLicModelInput.getNomnav(),qLicModelInput.getLongueur(),qLicModelInput.getPuimot(),currentNation,qLicModelInput.getLarg(),qLicModelInput.getCount(),qLicModelInput.getNbrhomm(),
                        qLicModelInput.getEff(),qLicModelInput.getAncons(),qLicModelInput.getCalpoids(),Float.valueOf(qLicModelInput.getGt().toString()),Float.valueOf(qLicModelInput.getKw().toString()),Float.valueOf(qLicModelInput.getTjb().toString()),qLicModelInput.getImo(),qLicModelInput.getPort(),qLicModelInput.getRadio(),qLicModelInput.getBalise(),DEBAUT,
                        qLicModelInput.getNumlic(),enumModePeche.ETRANGER,DEBAUT,FINAUTO,null,null,qLicModelInput.getNomar(),null);

                System.out.println("nouv bat"+newnavire.getNomnav());

                insertedNavire=newnavire;
            }
            currentLic=new qLicenceLibre(currentTypLic,currentZone,currentNation,null,insertedNavire,enumTypeBat.INDEF,
                    DEBAUT,FINAUTO,qLicModelInput.getAncons(),qLicModelInput.getBalise(),qLicModelInput.getCalpoids(),qLicModelInput.getCount(),
                    qLicModelInput.getEff(),Float.valueOf(Double.valueOf(qLicModelInput.getGt()).toString()),Integer.valueOf(qLicModelInput.getImo()),
                    Float.valueOf(qLicModelInput.getKw().toString()),qLicModelInput.getLarg().toString(),qLicModelInput.getLongueur().toString(),qLicModelInput.getNbrhomm().toString(),
                    qLicModelInput.getNomar(),qLicModelInput.getNomnav(),qLicModelInput.getNumlic(),qLicModelInput.getPort(),
                    qLicModelInput.getPuimot(),qLicModelInput.getRadio(),Float.valueOf(qLicModelInput.getTjb().toString()),currentEncadrement,null,enumModePeche.ETRANGER);
        }

        return currentLic;
    }
}
