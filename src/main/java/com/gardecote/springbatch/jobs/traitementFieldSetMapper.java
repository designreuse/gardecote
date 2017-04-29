package com.gardecote.springbatch.jobs;

import com.gardecote.models.qLicenceModel;
import com.gardecote.web.qModelDoc;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 23/04/2017.
 */
public class traitementFieldSetMapper implements FieldSetMapper<qModelDoc> {
    private Integer counter;


    @Override
    public qModelDoc mapFieldSet(FieldSet rs) throws BindException {

        qModelDoc currentLine= new qModelDoc();
        System.out.println("depart :"+rs.readString("depart"));
        System.out.println("counter :"+counter);
        currentLine.setNpage(rs.readString("Npage"));
        Date dateDepart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
  if(Integer.parseInt(rs.readString("id"))>0) {
if(!rs.readString("depart").equals(""))
      try {
          dateDepart = sdfmt1.parse(rs.readString("depart"));

      } catch (ParseException e) {
          e.printStackTrace();
      }
      currentLine.setDepart(dateDepart);
      currentLine.setSecteur(rs.readString("secteur"));
      currentLine.setRef(rs.readString("Ref"));
      currentLine.setNom(rs.readString("Nom"));
      currentLine.setDate_dpart(rs.readString("depart"));
      currentLine.setNavire(rs.readString("Navire"));
      currentLine.setAnchois(rs.readString("anchois"));
      currentLine.setChinchard(rs.readString("chinchard"));
      currentLine.setSardine(rs.readString("sardine"));
      currentLine.setSardinelle(rs.readString("sardinelle"));
      currentLine.setAnchois(rs.readString("anchois"));
      currentLine.setMaquereau(rs.readString("maquereau"));
      currentLine.setSabres(rs.readString("sabres"));
      currentLine.setThonides(rs.readString("thonides"));
      currentLine.setMulet(rs.readString("mulet"));
      currentLine.setMerlu(rs.readString("merlu"));
      currentLine.setCalamar(rs.readString("calamar"));
      currentLine.setDiversDorades(rs.readString("diversDorades"));
      currentLine.setFish_p_chat(rs.readString("Fish_p_chat"));
      currentLine.setRed_bait(rs.readString("red_bait"));
      currentLine.setSeaBrum(rs.readString("seaBrum"));
      currentLine.setAutrePPel(rs.readString("autrePPel"));
      currentLine.setAutrePDem(rs.readString("autrePDem"));


  }
          return currentLine;
    }
}
