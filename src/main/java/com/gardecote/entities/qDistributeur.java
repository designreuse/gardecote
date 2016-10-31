package com.gardecote.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Dell on 31/10/2016.
 */
public class qDistributeur implements Serializable {

    public qDistributeur() {
    }

    public qCarnet distribuer(qCarnet qcarnet) {
       List<qPageCarnet>  pgs=new ArrayList<qPageCarnet>();
        if (qcarnet.getTypeDoc().equals(enumTypeDoc.Journal_Peche)){
            qcarnet.setNbrLigneParPage(10);

            for(int i=0;i<qcarnet.getNbrLigneParPage();i++) {
                qPageCarnet  qp= new qPageMarree(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),
                        qcarnet.getNbrLigneParPage(),qcarnet,null,null );
                //   qp.setQcarnet(this);

                pgs.add(qp);
            }
        }
        if (qcarnet.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
            qcarnet.setNbrLigneParPage(9);

            for(int i=0;i<qcarnet.getNbrLigneParPage();i++) {
                qPageCarnet  qp= new qPageDebarquement(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),
                        qcarnet.getNbrLigneParPage(),qcarnet,null,null);
                pgs.add(qp);
            }
        }
        if(qcarnet.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {
             qcarnet.setNbrLigneParPage(8);
            for(int i=0;i<qcarnet.getNbrLigneParPage();i++) {
                qPageCarnet  qp= new qPageTraitement(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),
                        qcarnet.getNbrLigneParPage(),qcarnet,null,null,null);


                pgs.add(qp);
            }
        }
        qcarnet.setPages(pgs);
return qcarnet;
}
   public qCarnet distribuer(qCarnet qcarnet,qRegistreNavire qnavire, qUsine qusine){
        qcarnet.setQnavire(qnavire);
        qcarnet.setQusine(qusine);
       return qcarnet;
    }
}
