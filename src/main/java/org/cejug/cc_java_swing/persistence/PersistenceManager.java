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
    
    private EntityManager entityManager;

    /**
     * Cria o entityManager com algumas propriedades específicas para 
     * criação do banco de dados embarcado.
     * 
     * Depois que o banco é criado pela primeira vez, quando executado novamente, 
     * esse método lança uma exception que pode ser ignorada ( pelo menos foi o que eu li em um tutorial da oracle )
     * ver o cc_console..
     * 
     * @param persistenceUnitProperties Map<String, String>
     * @return EntityManager
     */
    public EntityManager getEntityManager(Map<String, String> persistenceUnitProperties) {
        // Verifica se o entityManagerFactory é nulo ou seja se ainda não foi obtido. 
        if (entityManagerFactory == null) {
            // Cria o entityManagerFactory baseado na persistence unit definida no persistence.xml
            // e no mapa de propriedades específicas para a criação do banco de dados no derby embarcado.
            entityManagerFactory = Persistence.createEntityManagerFactory("cc_java_swing_pu", persistenceUnitProperties);
        }
        
        // Cria o entityManager.
        entityManager = entityManagerFactory.createEntityManager();
        
        // Retorna o entityManager.
        return entityManager;
    }
    
    /**
     * Retorna o EntityManager
     * @return entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
