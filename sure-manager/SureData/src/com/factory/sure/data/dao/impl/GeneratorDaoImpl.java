package com.factory.sure.data.dao.impl;

import com.factory.sure.data.dao.GeneratorDao;
import com.factory.sure.data.pojos.Generator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GeneratorDaoImpl implements GeneratorDao {

    private final String HQL_FIND_ALL = "FROM Generator";
    private SessionFactory m_pSessionFactory = null;
    private Session m_pSession;

    public GeneratorDaoImpl(SessionFactory sessionFactory) {
        this.m_pSessionFactory = sessionFactory;
        this.m_pSession = this.m_pSessionFactory.openSession();
    }

    @Override
    public List<Generator> findAll() {
        List<Generator> generatorList = null;
        try {
            this.m_pSession.beginTransaction();
            Query q = this.m_pSession.createQuery(HQL_FIND_ALL);
            generatorList = (List<Generator>)q.list();
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("findAll: Cannot query");
        }

        return generatorList;
    }

    @Override
    public Generator read(Integer id) {
        Generator generator = null;
        try {
            this.m_pSession.beginTransaction();
            generator = (Generator) this.m_pSession.get(Generator.class, id);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }

        return generator;
    }

    @Override
    public void saveOrUpdate(Generator transientObject) {
        try {
            this.m_pSession.beginTransaction();
            this.m_pSession.saveOrUpdate(transientObject);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }
    }

    @Override
    public void delete(Generator persistentObject) {
        try {
            this.m_pSession.beginTransaction();
            this.m_pSession.delete(persistentObject);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }
    }
}
