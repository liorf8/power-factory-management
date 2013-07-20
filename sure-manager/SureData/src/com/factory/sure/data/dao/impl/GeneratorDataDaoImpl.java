/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.data.dao.impl;

import com.factory.sure.data.dao.GeneratorDataDao;
import com.factory.sure.data.pojos.GeneratorData;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author 50801_000
 */
public class GeneratorDataDaoImpl implements GeneratorDataDao{

    private final String HQL_FIND_ALL = "FROM GeneratorData";
     private SessionFactory m_pSessionFactory = null;
     private Session m_pSession;
     
     public GeneratorDataDaoImpl(SessionFactory sessionFactory) {
         this.m_pSessionFactory = sessionFactory;
         this.m_pSession = this.m_pSessionFactory.openSession();
     }
    
    @Override
    public List<GeneratorData> findAll() {
        List<GeneratorData> generatorDataList = null;
        try {
            this.m_pSession.beginTransaction();
            Query q = this.m_pSession.createQuery(HQL_FIND_ALL);
            generatorDataList = (List<GeneratorData>)q.list();
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("findAll: Cannot query");
        }

        return generatorDataList;
    }

    @Override
    public GeneratorData read(Integer id) {
        GeneratorData generatorData = null;
        try {
            this.m_pSession.beginTransaction();
            generatorData = (GeneratorData) this.m_pSession.get(GeneratorData.class, id);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }

        return generatorData;
    }

    @Override
    public void saveOrUpdate(GeneratorData transientObject) {
        try {
            this.m_pSession.beginTransaction();
            this.m_pSession.saveOrUpdate(transientObject);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }
    }

    @Override
    public void delete(GeneratorData persistentObject) {
        try {
            this.m_pSession.beginTransaction();
            this.m_pSession.delete(persistentObject);
            this.m_pSession.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            System.err.println("saveOrUpdate: Cannot query");
        }
    }
    
}
