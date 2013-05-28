package org.cejug.cc_java_swing.persistence;

import java.util.List;

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
        // Inicia a transação.
        entityManager.getTransaction().begin();
        // Persiste.
        entityManager.persist(personagem);
        // Comita a transação.
        entityManager.getTransaction().commit();
    }
    
    /**
     * Remove um personagem no banco de dados.
     * @param personagem Personagem
     */
    public void remove(Personagem personagem) {
        // Inicia a transação.
        entityManager.getTransaction().begin();
        // Remove.
        entityManager.remove(personagem);
        // Comita a transação.
        entityManager.getTransaction().commit();
    }
    
    /**
     * Atualiza um personagem no banco de dados.
     * @param personagem Personagem
     */
    public void merge(Personagem personagem) {
        // Inicia a transação.
        entityManager.getTransaction().begin();
        // Atualiza.
        entityManager.merge(personagem);
        // Comita a transação.
        entityManager.getTransaction().commit();
    }
    
    /**
     * Lista todos os personagens cadastrados no banco de dados.
     * @return List < Personagem >
     */
    public List < Personagem > getPersonagens() {
        return entityManager.createQuery("select p from Personagem p", Personagem.class).getResultList();
    }
    
    /**
     * Busca um personagem pelo id.
     * @param int id
     * @return Personagem
     */
    public Personagem findById(int id) {
        return entityManager.find(Personagem.class, id);
    }

}
