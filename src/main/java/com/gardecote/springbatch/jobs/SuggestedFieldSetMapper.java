package com.gardecote.springbatch.jobs;

import com.gardecote.models.qLicenceModel;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class SuggestedFieldSetMapper implements FieldSetMapper<qLicenceModel> {
    @Override
    public qLicenceModel mapFieldSet(FieldSet rs) throws BindException {
        qLicenceModel currentLic = new qLicenceModel();

        currentLic.setNumlic(rs.readString("numlic"));
        currentLic.setTypelicence(rs.readString("typelicence"));
        currentLic.setAncons(rs.readInt("ancons"));
        currentLic.setBalise(rs.readString("balise"));
        currentLic.setCalpoids(rs.readString("calpoids"));
        currentLic.setCount(rs.readString("count"));
        currentLic.setCreatedAt(rs.readString("created_at"));
        currentLic.setDebaut(rs.readString("debaut"));
        currentLic.setFinauto(rs.readString("finauto"));
        currentLic.setEff(rs.readString("eff"));
        currentLic.setGt(rs.readDouble("gt"));
        currentLic.setImo(rs.readInt("imo"));
        currentLic.setKw(rs.readDouble("kw"));
        currentLic.setLarg(rs.readString("larg"));
        currentLic.setLongueur(rs.readString("longueur"));


        currentLic.setNbrhomm(rs.readString("nbrhomm"));
        currentLic.setNomar(rs.readString("nomar"));
        currentLic.setNomnav(rs.readString("nomnav"));
        currentLic.setNumimm(rs.readString("numimm"));
        currentLic.setPort(rs.readString("port"));
        currentLic.setPuimot(rs.readString("puimot"));
        currentLic.setRadio(rs.readString("radio"));
        currentLic.setTjb(rs.readDouble("tjb"));
        currentLic.setTypb(rs.readInt("typb"));
        currentLic.setUpdatedAt(rs.readString("updated_at"));
        currentLic.setTypencad(rs.readInt("typencad"));
        currentLic.setNationIdnation(rs.readInt("nation_idNation"));
        currentLic.setQnavireNumimm(rs.readString("qnavire_numimm"));

        currentLic.setQtypnavIdTypelic(rs.readInt("qtypnav_id_typelic"));
        currentLic.setZoneIdzone(rs.readInt("zone_IdZone"));
        currentLic.setQconcessionid(rs.readString("qConcessionid"));

        return currentLic;
    }
}
