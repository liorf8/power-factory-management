package com.factory.sure.data;

import com.factory.sure.data.dao.GeneratorDao;
import com.factory.sure.data.dao.GeneratorDataDao;
import com.factory.sure.data.dao.impl.GeneratorDaoImpl;
import com.factory.sure.data.dao.impl.GeneratorDataDaoImpl;
import com.factory.sure.data.exception.WrongGeneratorException;
import com.factory.sure.data.pojos.Factory;
import com.factory.sure.data.pojos.Generator;
import com.factory.sure.data.pojos.GeneratorData;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.hibernate.SessionFactory;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = FactoryDataAssistance.class)
public class FactoryDataAssistance {

    private SessionFactory m_pSessionFactory = null;
    private GeneratorDataDao m_pGeneratorDataDao;
    private GeneratorDao m_pGeneratorDao;
    private Timer m_pDatabaseTimer;
    private DatabaseTimerTask m_pDatabaseTimerTask;

    public FactoryDataAssistance() {
        m_pSessionFactory = HibernateUtil.getSessionFactory();
        this.m_pGeneratorDataDao = new GeneratorDataDaoImpl(m_pSessionFactory);
        this.m_pGeneratorDao = new GeneratorDaoImpl(m_pSessionFactory);
        this.m_pDatabaseTimer = new Timer();
        this.m_pDatabaseTimerTask = new DatabaseTimerTask();
    }

    /**
     * Initialize the factory instance containing the generators stored in the
     * database
     *
     * @param modbusSet The modbus address set
     * @return null if fail, a Factory instance if the database has contained
     * available generator rows.
     */
    public Factory initializeFactoryData(Set<Byte> modbusSet) throws WrongGeneratorException {
        int numOfGenerators = modbusSet.size();
        List<Generator> generatorList = this.m_pGeneratorDao.findAll();
        if (generatorList != null) {
            if (generatorList.size() == numOfGenerators) {
                Factory factory = new Factory(generatorList);
                return factory;
            } else {
                throw new WrongGeneratorException("The size of the generator list is not expected");
            }
        } else {    // generatorList == null
            throw new WrongGeneratorException("Generator list fetched from database is null");
        }
    }

    public void startDatabaseTimer() {
        this.m_pDatabaseTimer.schedule(this.m_pDatabaseTimerTask, 0, 30000);
    }

    public void stop() {
        this.m_pDatabaseTimer.cancel();
    }

    public void update(GeneratorData generatorData) {
        m_pDatabaseTimerTask.updateCurrentGeneratorDataMap(generatorData);
    }

    private class DatabaseTimerTask extends TimerTask {
        // Init value is null
        // Every time this is updated to the 
        private Map<Byte, GeneratorData> m_pCurrentGeneratorDataMap;

        public DatabaseTimerTask() {
            super();
            m_pCurrentGeneratorDataMap = new ConcurrentHashMap<Byte, GeneratorData>();
        }

        @Override
        public void run() {
            for (GeneratorData generatorData : this.m_pCurrentGeneratorDataMap.values()) {
                if (generatorData != null) {
                    store(generatorData);                    
                }
            }
            this.m_pCurrentGeneratorDataMap.clear();
        }

        private void store(GeneratorData generatorData) {
            if (m_pGeneratorDataDao != null) {
                System.out.println("Save generatorData...");
                m_pGeneratorDataDao.saveOrUpdate(generatorData);
            }
        }

        public void updateCurrentGeneratorDataMap(GeneratorData generatorData) {
            this.m_pCurrentGeneratorDataMap.put(generatorData.getGenerator().getModbusAddress(), generatorData);
        }
    }
}
