package com.gardecote.springbatch;

import com.gardecote.models.qZoneModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 17/02/2017.
 */
public class qZoneRowMapper implements RowMapper<qZoneModel> {

    @Override
    public qZoneModel mapRow(ResultSet rs, int i) throws SQLException {
        qZoneModel zonemodel = new qZoneModel();

        zonemodel.setIdZone(rs.getInt("IdZone"));
        zonemodel.setNom(rs.getString("nom"));
        return zonemodel;
    }
}
