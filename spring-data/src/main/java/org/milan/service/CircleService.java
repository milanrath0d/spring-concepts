package org.milan.service;

import org.milan.dao.HibernateDaoImpl;
import org.milan.dao.JdbcDaoImpl;
import org.milan.dao.NamedParameterJdbcDaoImpl;
import org.milan.model.Circle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Circle
 *
 * @author Milan Rathod
 */
@Service
public class CircleService {

    private static final String JDBC_DAO_TYPE = "NamedParameter";

    private static final String DB_LAYER_TYPE = "Hibernate";

    private JdbcDaoImpl jdbcDaoImpl;

    private HibernateDaoImpl hibernateDaoImpl;

    private NamedParameterJdbcDaoImpl namedParameterJdbcDaoImpl;

    CircleService(JdbcDaoImpl jdbcDaoImpl, HibernateDaoImpl hibernateDaoImpl,
                  NamedParameterJdbcDaoImpl namedParameterJdbcDaoImpl) {
        this.jdbcDaoImpl = jdbcDaoImpl;
        this.hibernateDaoImpl = hibernateDaoImpl;
        this.namedParameterJdbcDaoImpl = namedParameterJdbcDaoImpl;
    }

    public void create(Circle circle) {
        create(JDBC_DAO_TYPE, circle);
    }

    public Circle get(int id) {
        return jdbcDaoImpl.getCircle(id);
    }

    public List<Circle> getAll() {
        return jdbcDaoImpl.getAllCircles();
    }

    public int getCount() {
        return getCount(DB_LAYER_TYPE);
    }

    private int getCount(String type) {
        if (type == null) {
            System.out.println("Unsupported type");
            return Integer.MIN_VALUE;
        } else if (type.equals("Hibernate")) {
            return hibernateDaoImpl.getCircleCount();
        } else {
            return jdbcDaoImpl.getCircleCount();
        }
    }

    private void create(String type, Circle circle) {
        if (type == null) {
            System.out.println("Unsupported type");
        } else if (type.equals("NamedParameter")) {
            namedParameterJdbcDaoImpl.createCircle(circle);
        } else {
            jdbcDaoImpl.createCircle(circle);
        }
    }
}
