package com.gardecote.springbatch;

import com.gardecote.models.qConcessionModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 16/02/2017.
 */
public class qConcessionRowMapper implements RowMapper<qConcessionModel> {
    @Override
    public qConcessionModel mapRow(ResultSet rs, int i) throws SQLException {
        qConcessionModel concessionModel = new qConcessionModel();
        concessionModel.setRefConcession(rs.getString(""));
        concessionModel.setQuotaEnTonne(rs.getInt(""));
        concessionModel.setQconsignataire(rs.getString(""));
        concessionModel.setDureeConcession(rs.getInt(""));
        concessionModel.setDateConcession(rs.getDate(""));
        concessionModel.setDateFin(rs.getDate(""));
        concessionModel.setDateDebut(rs.getDate(""));
        concessionModel.setCategoriesRessources(null);
        concessionModel.setDateConcession(rs.getDate(""));
        return concessionModel ;
    }
}
