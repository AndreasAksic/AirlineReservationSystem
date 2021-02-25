package database.mysql;

import database.DatabaseConnector;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class MySQLConnector implements DatabaseConnector {
    private static SessionFactory sessionFactory;

    public MySQLConnector(SessionFactory sf) {
        sessionFactory = sf;
    }

    @Override
    public Integer persistObject(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer entityId = 0;

        try {
            transaction = session.beginTransaction();

            entityId = (Integer) session.save(entity);

            transaction.commit();
        } catch (HibernateException e) {
            handleHibernateException(transaction, e);
        } finally {
            session.close();
        }

        return entityId;
    }

    public <T> List<T> loadAllDataForType(Class<T> type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.from(type);

            transaction.commit();

            return session.createQuery(criteriaQuery).list();
        } catch (HibernateException e) {
            handleHibernateException(transaction, e);
        } finally {
            session.close();
        }

        return Collections.emptyList();
    }

    public <T> T loadForTypeWithId(Class<T> type, Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            CriteriaQuery<T> criteriaQuery = createCriteriaQueryForTypeAndId(type, id, session);

            transaction.commit();

            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            handleHibernateException(transaction, e);
        } finally {
            session.close();
        }

        return null;
    }

    private <T> CriteriaQuery<T> createCriteriaQueryForTypeAndId(Class<T> type, Integer id, Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        criteriaQuery.where(predicate);
        return criteriaQuery;
    }

    private void handleHibernateException(Transaction transaction, HibernateException e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
