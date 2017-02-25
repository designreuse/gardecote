package com.gardecote.springbatch;

import com.gardecote.models.qTypeNavModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 16/02/2017.
 */
public class qTypesNavireRowMapper implements RowMapper<qTypeNavModel> {
    @Override
    public qTypeNavModel mapRow(ResultSet rs, int i) throws SQLException {
        qTypeNavModel currenttypn = new qTypeNavModel();

        currenttypn.setIdqTypeNav(rs.getString("idqTypeNav"));
        currenttypn.setDescr(rs.getString("descr"));
        return currenttypn;
    }
}
