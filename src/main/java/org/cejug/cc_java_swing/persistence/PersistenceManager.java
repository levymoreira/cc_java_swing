package org.cejug.cc_java_swing.persistence;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Enum PersistenceManager.
 * @author helio frota http://www.heliofrota.com
 *
 */
public enum PersistenceManager {

    /**
     * A instância da enum PersistenceManager.
     */
    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("cc_java_swing_pu");
        }
        return entityManagerFactory.createEntityManager();
    }
    
    public EntityManager getEntityManager(Map<String, String> persistenceUnitProperties) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("cc_java_swing_pu", persistenceUnitProperties);
        }
        return entityManagerFactory.createEntityManager();
    }

}
