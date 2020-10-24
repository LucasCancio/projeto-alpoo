package br.unip.alpoo.dao;

import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoJdbc {

    /*private static String SQL_SELECT_POR_ID
            = "SELECT ID, TITULO, TIPO, DATA_REAL, QTDE_HORAS, SITUACAO "
            + " FROM TB_cursoS WHERE ID=?;";

    @Override
    public Curso getPorId(Integer id) throws DadosException {
        Connection connection
                = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Curso curso = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_POR_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) { //se tiver registro
                String titulo = rs.getString("TITULO");
                String tipoStr = rs.getString("TIPO");
                Tipocurso tipo = Tipocurso.valueOf(tipoStr);
                Date data = rs.getDate("DATA_REAL");
                java.util.Date date
                        = new java.util.Date(data.getTime());
                int qtde = rs.getInt("QTDE_HORAS");
                String situacaoStr = rs.getString("SITUACAO");
                Situacao situacao = Situacao.valueOf(situacaoStr);
                curso = new Curso(id, titulo, tipo, date, qtde, situacao);
            }
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel selecionar", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement, rs);
        }
        return curso;
    }
    private static String SQL_SELECT_POR_TIPO
            = "SELECT ID, TITULO, TIPO, DATA_REAL, QTDE_HORAS, SITUACAO "
            + " FROM TB_cursoS WHERE TIPO = ?;";

    @Override
    public List<Curso> getPorTipo(TipoCurso tipo) throws DadosException {
        Connection connection
                = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Curso> listacursos = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_POR_TIPO);
            statement.setString(1, tipo.name());
            rs = statement.executeQuery();
            while (rs.next()) { //enquanto tiver *registros
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");
                Date data = rs.getDate("DATA_REAL");
                java.util.Date date
                        = new java.util.Date(data.getTime());
                int qtde = rs.getInt("QTDE_HORAS");
                String situacaoStr = rs.getString("SITUACAO");
                Situacao situacao = Situacao.valueOf(situacaoStr);
                Curso curso = 
                        new Curso(id, titulo, tipo, date, qtde, situacao);
                listacursos.add(curso);
            }
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel selecionar", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement, rs);
        }
        return listacursos;
    }

    private static String SQL_SELECT_ALL
            = "SELECT ID, TITULO, TIPO, DATA_REAL, QTDE_HORAS, SITUACAO "
            + " FROM TB_cursoS;";

    @Override
    public List<Curso> getTodas() throws DadosException {
        Connection connection
                = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Curso> listacursos = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            rs = statement.executeQuery();
            while (rs.next()) { //enquanto tiver *registros
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");
                String tipoStr = rs.getString("TIPO");
                TipoCurso tipo = TipoCurso.valueOf(tipoStr);
                Date data = rs.getDate("DATA_REAL");
                java.util.Date date
                        = new java.util.Date(data.getTime());
                int qtde = rs.getInt("QTDE_HORAS");
                String situacaoStr = rs.getString("SITUACAO");
                Situacao situacao = Situacao.valueOf(situacaoStr);
                Curso curso = new Curso(id, titulo, tipo, date, qtde, situacao);                listacursos.add(curso);
            }
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel selecionar", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement, rs);
        }
        return listacursos;
    }
    private static String SQL_INSERT
            = "INSERT INTO TB_cursoS (TITULO, TIPO, DATA_REAL, QTDE_HORAS, SITUACAO) "
            + "VALUES (?, ?, ?, ?, ?);";
                    // 1  2  3  4  5

    @Override
    public void incluir(Curso curso) throws DadosException {
        Connection connection = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, curso.getTitulo());
            statement.setString(2, curso.getTipo().name());
            java.sql.Date dataSql
                    = new java.sql.Date(curso.getDataRealizacao().getTime());
            statement.setDate(3, dataSql);
            statement.setInt(4, curso.getQtdeHoras());
            statement.setString(5, curso.getSituacao().name());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel incluir", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }
    private static String SQL_DELETE
            = "DELETE FROM TB_cursoS WHERE ID = ?;";

    @Override
    public void excluir(Curso curso) throws DadosException {
        Connection connection = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, curso.getId());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel excluir", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }

    private static String SQL_UPDATE
            = "UPDATE TB_cursoS SET TITULO = ?, TIPO = ? , DATA_REAL = ? , QTDE_HORAS = ? , SITUACAO= ?  WHERE ID = ?;";
    //1          2            3           4            5                  6

    @Override
    public void atualizar(Curso curso) throws DadosException {
        Connection connection = GerenciadorConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, curso.getTitulo());
            statement.setString(2, curso.getTipo().name());
            java.sql.Date dataSql
                    = new java.sql.Date(curso.getDataRealizacao().getTime());
            statement.setDate(3, dataSql);
            statement.setInt(4, curso.getQtdeHoras());
            statement.setString(5, curso.getSituacao().name());
            statement.setInt(6, curso.getId());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException(
                    "Não foi possivel atualizar", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }
    // implementacao do padrao Singleton
    private static CursoJdbc instance;

    public static CursoJdbc getInstance() {
        if (instance == null) {
            instance = new CursoJdbc();
        }
        return instance;
    }*/
}