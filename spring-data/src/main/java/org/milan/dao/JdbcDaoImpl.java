package org.milan.dao;

import lombok.Getter;
import lombok.Setter;
import org.milan.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Jdbc Dao class responsible for getting circle related data from database
 *
 * @author Milan Rathod
 */
@Getter
@Setter
@Repository
public class JdbcDaoImpl {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createCircle(Circle circle) {
        String sql = "INSERT INTO CIRCLE(id, name) VALUES(?, ?)";
        jdbcTemplate.update(sql, circle.getId(), circle.getName());
    }

    public Circle getCircle(int circleId) {
        String sql = "SELECT * FROM circle where id = ?";

        // Use BeanPropertyRowMapper if database table columns names and pojo members names are same
        // return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, new BeanPropertyRowMapper<>(Circle.class));

        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, new CircleMapper());
    }

    public String getCircleName(int circleId) {
        String sql = "SELECT name FROM circle where id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, String.class);
    }

    public int getCircleCount() {
        String sql = "SELECT COUNT(*) FROM CIRCLE";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        return count != null ? count : -1;
    }

    public List<Circle> getAllCircles() {
        String sql = "SELECT * FROM circle";

        return jdbcTemplate.query(sql, new CircleMapper());
    }

    public boolean deleteCircle(int id) {
        String sql = "DELETE FROM circle WHERE id = ?";

        int update = jdbcTemplate.update(sql, id);

        return update != 0;
    }

    private static final class CircleMapper implements RowMapper<Circle> {

        @Override
        public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Circle circle = new Circle();
            circle.setId(resultSet.getInt("id"));
            circle.setName(resultSet.getString("name"));
            return circle;
        }
    }
}
