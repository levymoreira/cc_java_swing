package org.cejug.cc_java_swing;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.SwingUtilities;

import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * Classe principal para inciar o sistema.
 * 
 * @author helio frota https://www.heliofrota.com
 * 
 */
public class CCJavaSwing {

    /**
     * Método main.
     * 
     * @param String... args
     */
    public static void main(String... args) {

        Map<String, String> persistenceUnitProperties = new HashMap<>();
        
        persistenceUnitProperties.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");
        persistenceUnitProperties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, "database");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cc_java_swing_pu", persistenceUnitProperties);
        entityManagerFactory.createEntityManager();
        entityManagerFactory.createEntityManager(persistenceUnitProperties);

        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
            }
        });
  
    }

}
