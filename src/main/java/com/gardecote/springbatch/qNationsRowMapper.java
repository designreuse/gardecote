package com.gardecote.springbatch;

import com.gardecote.models.qNationModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 17/02/2017.
 */
public class qNationsRowMapper implements RowMapper<qNationModel> {
    @Override
    public qNationModel mapRow(ResultSet rs, int i) throws SQLException {
        qNationModel currenttypn = new qNationModel();

        currenttypn.setIdNation(rs.getInt("idNation"));
        currenttypn.setDesignation(rs.getString("designation"));
        return currenttypn;
    }
}
