package org.cejug.cc_java_swing;

import java.util.HashMap;
import java.util.Map;

import org.cejug.cc_java_swing.persistence.PersistenceManager;
import org.cejug.cc_java_swing.ui.PersonagemUI;
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

        // Mapa para guardar as propriedades que também são definidas no persistence.xml
        // Mas aqui no caso faremos programaticamente.
        Map<String, String> persistenceUnitProperties = new HashMap<>();
        
        // Propriedade para criar as tabelas também existe na versão xml.
        persistenceUnitProperties.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");
        // Vai gerar no banco e não criar script de criação.
        persistenceUnitProperties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, "database");

        // Criar o entityManager para gerar o banco e já servir de instância única no sistema.
        PersistenceManager.INSTANCE.getEntityManager(persistenceUnitProperties);
        
        // Cria a parte visual jFrame componentes etc...
        new PersonagemUI();
        
    }

}
