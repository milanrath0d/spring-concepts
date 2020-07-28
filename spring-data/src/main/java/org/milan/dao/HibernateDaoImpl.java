package org.milan.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate related Dao Class
 *
 * @author Milan Rathod
 */
@Repository
public class HibernateDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public int getCircleCount() {
        String hql = "select count(*) from Circle";
        Query query = sessionFactory.openSession().createQuery(hql);
        return ((Long) query.uniqueResult()).intValue();
    }


}
