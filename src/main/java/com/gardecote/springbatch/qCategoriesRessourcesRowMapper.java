package com.gardecote.springbatch;

import com.gardecote.models.qCategRessourceModelInput;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 16/02/2017.
 */
public class qCategoriesRessourcesRowMapper implements RowMapper<qCategRessourceModelInput> {
    @Override
    public qCategRessourceModelInput mapRow(ResultSet rs, int i) throws SQLException {
        qCategRessourceModelInput qcategressource = new qCategRessourceModelInput();
         List eng=new ArrayList<String>();

        qcategressource.setIdcateg(rs.getInt("idcateg"));
        qcategressource.setTypeSupport(rs.getInt("typeSupport"));
        qcategressource.setTypeconcessionConcernee_qtypeconcessionpk(rs.getInt("typeconcessionConcernee_qtypeconcessionpk"));

        return qcategressource;
    }
}
