package com.gardecote.springbatch;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qLicenceLibre;
import com.gardecote.entities.qLicenceNational;
import com.gardecote.models.qLicenceModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 07/02/2017.
 */
public class qLicRowMapper implements RowMapper<qLicenceModel> {
    @Override
    public qLicenceModel mapRow(ResultSet rs, int i) throws SQLException {
        qLicenceModel currentLic = new qLicenceModel();

        currentLic.setNumlic(rs.getString("numlic"));
        currentLic.setTypelicence(rs.getString("typelicence"));
        currentLic.setAncons(rs.getInt("ancons"));
        currentLic.setBalise(rs.getString("balise"));
        currentLic.setCalpoids(rs.getString("calpoids"));
        currentLic.setCount(rs.getString("count"));
        currentLic.setCreatedAt(rs.getString("created_at"));
        currentLic.setDebaut(rs.getString("debaut"));
        currentLic.setFinauto(rs.getString("finauto"));
        currentLic.setEff(rs.getString("eff"));
        currentLic.setGt(rs.getDouble("gt"));
        currentLic.setImo(rs.getInt("imo"));
        currentLic.setKw(rs.getDouble("kw"));
        currentLic.setLarg(rs.getString("larg"));
        currentLic.setLongueur(rs.getString("longueur"));


        currentLic.setNbrhomm(rs.getString("nbrhomm"));
        currentLic.setNomar(rs.getString("nomar"));
        currentLic.setNomnav(rs.getString("nomnav"));
        currentLic.setNumimm(rs.getString("numimm"));
        currentLic.setPort(rs.getString("port"));
        currentLic.setPuimot(rs.getString("puimot"));
        currentLic.setRadio(rs.getString("radio"));
        currentLic.setTjb(rs.getDouble("tjb"));
        currentLic.setTypb(rs.getInt("typb"));
        currentLic.setUpdatedAt(rs.getString("updated_at"));
        currentLic.setTypencad(rs.getInt("typencad"));
        currentLic.setNationIdnation(rs.getInt("nation_idNation"));
        currentLic.setQnavireNumimm(rs.getString("qnavire_numimm"));

        currentLic.setQtypnavIdTypelic(rs.getInt("qtypnav_id_typelic"));
        currentLic.setZoneIdzone(rs.getInt("zone_IdZone"));
        currentLic.setQconcessionid(rs.getString("qConcessionid"));

      return currentLic;
    }
}
