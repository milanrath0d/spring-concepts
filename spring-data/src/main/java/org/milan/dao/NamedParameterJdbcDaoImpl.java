package org.milan.dao;

import org.milan.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Custom Implementation for {@link NamedParameterJdbcDaoSupport}
 *
 * @author Milan Rathod
 */
@Repository
public class NamedParameterJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    @Autowired
    public NamedParameterJdbcDaoImpl(DataSource datasource) {
        setDataSource(datasource);
    }

    public void createCircle(Circle circle) {
        String sql = "INSERT INTO CIRCLE(id, name) VALUES(:id, :name)";

        SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId())
            .addValue("name", circle.getName());
        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
    }
}
