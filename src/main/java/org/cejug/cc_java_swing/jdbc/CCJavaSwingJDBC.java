package org.cejug.cc_java_swing.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cejug.cc_java_swing.pojo.Personagem;

/**
 * Classe que irá lidar com o acesso e manipulação dos dados no banco de dados
 * derby embarcado usando JDBC.
 * 
 * @author helio frota https://www.heliofrota.com
 * 
 */
public class CCJavaSwingJDBC {

    /**
     * Referência estática para a conexão com o banco.
     */
    private static Connection connection;

    /**
     * Bloco estático para inciar a conexão e criar uma única vez a tabela.
     */
    static {
        connection = obterConexao();
        criarTabela();
    }

    /**
     * Salva os dados do objeto personagem na base de dados.
     * @param personagem Personagem
     */
    public void salvarPersonagem(Personagem personagem) {
        // Referência para o preparedStatement.
        PreparedStatement preparedStatement = null;
        try {
            // Prepara o preparedStatement com o comando sql.
            preparedStatement = connection.prepareStatement("insert into personagem (nome) values (?)");
            // Adiciona os parâmetros relativos ao comando sql.
            preparedStatement.setString(1, personagem.getNome());
            // Executa o comando no banco de dados.
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // Tentativa de fechar o preparedStatement.
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retorna uma lista de personagens cadastrados.
     * @return List < Personagem >
     */
    public List < Personagem > listarPersonagens() {

        // Referência para o preparedStatement.
        PreparedStatement preparedStatement = null;
        // Referência para o resultSet.
        ResultSet resultSet = null;

        // Lista para adicionar os personagens.
        List < Personagem > personagens = new ArrayList<>();
        
        try {
            
            // Prepara o preparedStatement com o comando sql.
            preparedStatement = connection.prepareStatement("select id, nome from personagem");
            // Executa o comando sql da consulta e retorna o resultSet. 
            resultSet = preparedStatement.executeQuery();
            
            // Faz um loop nos resultados e adiciona os personagens na lista.
            while (resultSet.next()) {

                // Cria objeto personagem e atrbui os valores retornados da consulta.
                Personagem personagem = new Personagem();
                personagem.setId(resultSet.getInt("id"));
                personagem.setNome(resultSet.getString("nome"));
                
                // Adiciona o personagem na lista.
                personagens.add(personagem);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // Tentativa de fechar o resultSet e o preparedStatement.
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Retorna a lista de personagens.
        return personagens;
    }

    /**
     * Atualiza o nome do personagem escolhido.
     * @param personagemId int
     * @param novoNome String
     */
    public void atualizarPersonagem(int personagemId, String novoNome) {
        // Referência para o preparedStatement.
        PreparedStatement preparedStatement = null;
        try {
            // Prepara o preparedStatement com o comando sql.
            preparedStatement = connection.prepareStatement("update personagem set nome = ? where id = ?");
            // Adiciona os parâmetros relativos ao comando sql.
            preparedStatement.setString(1, novoNome);
            preparedStatement.setInt(2, personagemId);
            // Executa o comando no banco de dados.
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // Tentativa de fechar o preparedStatement.
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Remove o personagem pelo Id. 
     * 
     * @param personagemId int
     */
    public void removerPersonagem(int personagemId) {
        // Referência para o preparedStatement.
        PreparedStatement preparedStatement = null;
        try {
            // Prepara o preparedStatement com o comando sql.
            preparedStatement = connection.prepareStatement("delete from personagem where id = ?");
            // Adiciona os parâmetros relativos ao comando sql.
            preparedStatement.setInt(1, personagemId);
            // Executa o comando no banco de dados.
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // Tentativa de fechar o preparedStatement.
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Obtém uma conexão com o banco de dados derby embarcado.
     * 
     * @return Connection
     */
    public static Connection obterConexao() {
        // Referência para java.sql.Connection.
        Connection conn = null;
        // Tenta carregar o driver do derby embarcado, caso contrário
        // imprime uma exceção.
        try {
            // Carrega o driver do derby embarcado.
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // tenta obter a conexão com o banco de dados derby embarcado,
        // caso contrário imprime uma exceção.
        try {
            // Conecta no banco sem usuario e senha.
            conn = DriverManager.getConnection("jdbc:derby:cc_java_swing_db;create=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retorna a referência para a conexão com o banco.
        return conn;
    }
    
    /**
     * Cria a tabela PERSONAGEM no banco de dados embarcado.
     */
    public static void criarTabela() {
        // Referência para o statement.
        Statement statement = null;
        try {

            // Criação do statement.
            statement = connection.createStatement();

            // Comando sql para criar tabela personagem no banco de dados derby.
            String sql = "create table personagem (id integer not null "
                    + "generated always as identity (start with 1, increment by 1), "
                    + "nome varchar(20) not null, "
                    + "constraint primary_key primary key (id))";

            // Executando o statement sql para criação da tabela PERSONAGEM.
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // Verifica se a exceção pode ser ignora quando a tabela já existe.
            if (!ignoreSQLException(e.getSQLState())) {
                e.printStackTrace();
            }
        } finally { // Tentativa de fechar o statement.
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Exemplo similar ao exemplo da oracle.
     * @param sqlState String
     * @return boolean
     */
    private static boolean ignoreSQLException(String sqlState) {
        // X0Y32: tabela já existe no schema.
        if (sqlState.equalsIgnoreCase("X0Y32")) {
            return true;
        }
        return false;
    }

}
