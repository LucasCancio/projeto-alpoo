package br.unip.alpoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.unip.alpoo.util.DadosException;

public class GerenciadorConexao {

    private static final String URL
            = "jdbc:mysql://127.0.0.1:3306/sakila?zeroDateTimeBehavior=convertToNull&useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345";

    public static Connection getConnection() throws DadosException {
        Connection connection = null;
        try {
            connection
                    = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel conectar ao banco de dados",
                    ex);
        }
        return connection;
    }

    public static void fechar(Connection connection) throws DadosException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        }
    }

    public static void fechar(Connection connection,
            Statement statement) throws DadosException {
        try {
            statement.close();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        } finally {
            GerenciadorConexao.fechar(connection);
        }
    }

    public static void fechar(Connection connection,
            Statement statement,
            ResultSet resultSet) throws DadosException {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }

}
