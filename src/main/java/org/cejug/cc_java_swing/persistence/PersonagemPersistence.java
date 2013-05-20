package org.cejug.cc_java_swing.persistence;

import javax.persistence.EntityManager;

import org.cejug.cc_java_swing.persistence.entity.Personagem;

/**
 * Enum PersonagemPersistence responsável pela persistencia dos dados 
 * da entidade Personagem.
 * 
 * @author helio frota http://www.heliofrota.com
 *
 */
public enum PersonagemPersistence {

    /**
     * A instância da enum PersonagemPersistence.
     */
    INSTANCE;

    private EntityManager entityManager;

    private PersonagemPersistence() {
        this.entityManager = PersistenceManager.INSTANCE.getEntityManager();
    }

    /**
     * Persiste um personagem no banco de dados.
     * @param personagem Personagem
     */
    public void persist(Personagem personagem) {
        entityManager.getTransaction().begin();
        entityManager.persist(personagem);
        entityManager.getTransaction().commit();
    }

}
