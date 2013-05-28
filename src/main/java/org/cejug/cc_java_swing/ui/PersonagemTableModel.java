package org.cejug.cc_java_swing.ui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cejug.cc_java_swing.persistence.entity.Personagem;

/**
 * PersonagemTableModel é uma forma de padronizar e convencionar eventos que podem ser disparados  
 * em uma tabela do java swing.
 * @author helio frota http://www.heliofrota.com
 *
 */
public class PersonagemTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -7910855068857437700L;

    // Constante para guardar o número de colunas da tabela.
    private static final int NUMERO_COLUNAS = 2;
    
    // Array de String para o cabeçalho das colunas.
    private static final String[] CABECALHO_COLUNAS = {"Id", "Nome"};

    // A lista de objetos personagem.
    private List < Personagem > personagens = new LinkedList<>();

    public PersonagemTableModel() {

    }

    /**
     * Método que precisa ser implementado quando se herda de AbstractTableModel.
     * Vai retornar a quantidade de registros.
     * 
     * @return int 
     */
    @Override
    public int getRowCount() {
        return personagens.size();
    }

    /**
     * Método que precisa ser implementado quando se herda de AbstractTableModel.
     * Vai retornar a quantidade de colunas.
     * 
     * @return int 
     */
    @Override
    public int getColumnCount() {
        return NUMERO_COLUNAS;
    }

    @Override
    public String getColumnName(int column) {
        return CABECALHO_COLUNAS[column];
    }

    /**
     * Método que precisa ser implementado quando se herda de AbstractTableModel.
     * Vai retornar o valor na linha e coluna.
     * 
     * @return Object
     */
    @Override
    public Object getValueAt(int linha, int coluna) {
        Personagem personagem = personagens.get(linha);
        if (coluna == 0) {
            return personagem.getId();
        } else {
            return personagem.getNome();
        }
    }

    /**
     * Atualiza a lista de pensonagens.
     * @param personagens List < Personagem >
     */
    public void setPersonagens(List < Personagem > personagens) {
        this.personagens = personagens;
    }

    /**
     * Retorna o Personagem da determinada linha selecionada.
     * @param linha int
     * @return Personagem
     */
    public Personagem getPersonagemAt(int linha) {
        return personagens.get(linha);
    }

}

