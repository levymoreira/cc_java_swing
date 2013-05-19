package org.cejug.cc_java_swing.pojo;

/**
 * Classe POJO http://en.wikipedia.org/wiki/Plain_Old_Java_Object Personagem. 
 * @author helio frota http://www.heliofrota.com
 *
 */
public class Personagem {

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
