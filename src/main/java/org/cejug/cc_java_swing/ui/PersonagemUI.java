package org.cejug.cc_java_swing.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.cejug.cc_java_swing.persistence.PersonagemPersistence;
import org.cejug.cc_java_swing.persistence.entity.Personagem;

/**
 * Classe que irá conter o JFrame da aplicação.
 * @author helio frota http://www.heliofrota.com
 *
 */
public class PersonagemUI {

    private JFrame jFrame;
    
    private PersonagemTableModel personagemTableModel;
    
    public PersonagemUI() {
        iniciarComponentesVisuais();
    }
    
    /**
     * Inicia componentes visuais...
     * Acessa classe da camada de persistência...
     * Aplica alguns controles...
     * 
     * MegaZord anti-pattern.
     */
    private void iniciarComponentesVisuais() {
        // Cria um JFrame.
        jFrame = new JFrame();
        
        // Atualiza o título da janela.
        jFrame.setTitle("CC_Java_Swing");
        
        // Quando clicar no x para fechar a janela finaliza o programa.
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Posiciona a janela no lado esquerdo e superior (0,0) com a largura e altura definidas.
        jFrame.setBounds(0, 0, 800, 600);
        
        // A janela iniciará maximizada. 
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Não será possível redimensionar.
        jFrame.setResizable(false);
        
        // Cria objeto JTable.
        final JTable table = new JTable();
        
        // Cria objeto PersonagemTableModel.
        personagemTableModel = new PersonagemTableModel();
        
        // Obtém os personagens da base de dados e atualiza os personagens do tableModel.
        personagemTableModel.setPersonagens(PersonagemPersistence.INSTANCE.getPersonagens());
        
        // Liga o tableModel com o JTable.
        table.setModel(personagemTableModel);
        
        // Cria objeto scrollPane para que o java swing mostre uma barra de rolagem vertical
        // quando vários registros forem cadastrados...
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Painel com os botões.
        JPanel painelBotoes = new JPanel();
        
        painelBotoes.add(new JButton("Novo"));
        painelBotoes.add(new JButton("Alterar"));
        
        
        // Cria o botão com o texto excluir.
        JButton excluir = new JButton("Excluir");
        // Adiciona um listener para que esse botão possa efetuar uma ação.
        excluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Verifica se alguma linha da tabela foi selecionada.
                if (table.getSelectedRow() >= 0) {
                    // Obtém o personagem da linha selecionada.
                    Personagem personagem = personagemTableModel.getPersonagemAt(table.getSelectedRow());
                    // Remove o personagem do banco.
                    PersonagemPersistence.INSTANCE.remove(personagem);
                    // Obtém os personagens da base de dados e atualiza os personagens do tableModel.
                    personagemTableModel.setPersonagens(PersonagemPersistence.INSTANCE.getPersonagens());
                    // Liga o tableModel com o JTable.
                    table.setModel(personagemTableModel);
                    // Atualiza o componente visual.
                    table.updateUI();
                } else {
                    // Mostra mensagem de atenção se nenhum registro foi selecionado.
                    JOptionPane.showMessageDialog(null, "Selecione um registro.", "Atenção:", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Adiciona o botão no painel.
        painelBotoes.add(excluir);
        
        // Adiciona o scrollPane ( que contém a tabela ) dentro de uma aba JTabbedPane. 
        JTabbedPane aba = new JTabbedPane();
        aba.addTab("Personagens", scrollPane);

        // Adiciona o painel com os botões na parte superior da janela.
        jFrame.getContentPane().add(painelBotoes, BorderLayout.NORTH);
        
        // Adiciona a aba com o conteúdo no centro.
        jFrame.getContentPane().add(aba, BorderLayout.CENTER);
        
        // Torna a janela visível.
        jFrame.setVisible(true);
    }
    
}
