package org.cejug.cc_java_swing.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe entidade JPA Personagem. 
 * @author helio frota http://www.heliofrota.com
 *
 */
@Entity
@Table(name = "personagem")
public class Personagem implements Serializable {

    private static final long serialVersionUID = -2865520761184673696L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    public Personagem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
