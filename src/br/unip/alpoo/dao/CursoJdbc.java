package br.unip.alpoo.dao;

import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.NomesCursos;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoJdbc implements ICursoDao {

	private static String SQL_SELECT_POR_ID = "SELECT ID,NOME,TIPO,CARGA_HORARIA,COD_INSTITUTO "
			+ " FROM TB_CURSOS WHERE ID=?;";

	@Override
	public Curso getPorId(Integer id) throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Curso curso = null;
		try {
			statement = connection.prepareStatement(SQL_SELECT_POR_ID);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) { // se tiver registro
				String nomeStr = rs.getString("NOME");
				NomesCursos nomeCurso = NomesCursos.valueOf(nomeStr);
				String tipoStr = rs.getString("TIPO");
				TipoCurso tipoCurso = TipoCurso.valueOf(tipoStr);
				int cargaHoraria = rs.getInt("CARGA_HORARIA");
				String codInstituto = rs.getString("COD_INSTITUTO");

				curso = new Curso(id, nomeCurso, tipoCurso, cargaHoraria, codInstituto);
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel selecionar", ex);
		} finally {
			GerenciadorConexao.fechar(connection, statement, rs);
		}
		return curso;
	}

	private static String SQL_SELECT_POR_TIPO = "SELECT ID,NOME,TIPO,CARGA_HORARIA,COD_INSTITUTO "
			+ " FROM TB_CURSOS WHERE TIPO = ?;";

	@Override
	public List<Curso> getPorTipo(TipoCurso tipo) throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Curso> listaCursos = new ArrayList<>();
		try {
			statement = connection.prepareStatement(SQL_SELECT_POR_TIPO);
			statement.setString(1, tipo.name());
			rs = statement.executeQuery();
			while (rs.next()) { // enquanto tiver *registros
				int id = rs.getInt("ID");
				String nomeStr = rs.getString("NOME");
				NomesCursos nomeCurso = NomesCursos.valueOf(nomeStr);
				String tipoStr = rs.getString("TIPO");
				TipoCurso tipoCurso = TipoCurso.valueOf(tipoStr);
				int cargaHoraria = rs.getInt("CARGA_HORARIA");
				String codInstituto = rs.getString("COD_INSTITUTO");

				Curso curso = new Curso(id, nomeCurso, tipoCurso, cargaHoraria, codInstituto);
				listaCursos.add(curso);
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel selecionar", ex);
		} finally {
			GerenciadorConexao.fechar(connection, statement, rs);
		}
		return listaCursos;
	}

	private static String SQL_SELECT_ALL = "SELECT ID,NOME,TIPO,CARGA_HORARIA,COD_INSTITUTO " + " FROM TB_CURSOS;";

	@Override
	public List<Curso> getTodas() throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Curso> listaCursos = new ArrayList<>();
		try {
			statement = connection.prepareStatement(SQL_SELECT_ALL);
			rs = statement.executeQuery();
			while (rs.next()) { // enquanto tiver *registros
				int id = rs.getInt("ID");
				String nomeStr = rs.getString("NOME");
				NomesCursos nomeCurso = NomesCursos.valueOf(nomeStr);
				String tipoStr = rs.getString("TIPO");
				TipoCurso tipoCurso = TipoCurso.valueOf(tipoStr);
				int cargaHoraria = rs.getInt("CARGA_HORARIA");
				String codInstituto = rs.getString("COD_INSTITUTO");

				Curso curso = new Curso(id, nomeCurso, tipoCurso, cargaHoraria, codInstituto);
				listaCursos.add(curso);
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel selecionar", ex);
		} finally {
			GerenciadorConexao.fechar(connection, statement, rs);
		}
		return listaCursos;
	}

	private static String SQL_INSERT = "INSERT INTO TB_CURSOS (NOME, TIPO, CARGA_HORARIA, COD_INSTITUTO) "
			+ "VALUES (?, ?, ?, ?);";
	// 1 2 3 4

	@Override
	public void incluir(Curso curso) throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, curso.getNomeCurso().name());
			statement.setString(2, curso.getTipoCurso().name());
			statement.setInt(3, curso.getCargaHoraria());
			statement.setString(4, curso.getCodInstituto());
			int qtdeRegistros = statement.executeUpdate();

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					curso.setCodCurso(generatedKeys.getInt(1));
				}
			}

		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel incluir", ex);
		} finally {
			GerenciadorConexao.fechar(connection, statement);
		}
	}

	private static String SQL_DELETE = "DELETE FROM TB_CURSOS WHERE ID = ?;";

	@Override
	public void excluirPorId(Integer id) throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_DELETE);
			statement.setInt(1, id);
			int qtdeRegistros = statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel excluir", ex);
		} finally {
			GerenciadorConexao.fechar(connection, statement);
		}
	}

	private static String SQL_UPDATE = "UPDATE TB_CURSOS SET NOME = ?, TIPO = ? , CARGA_HORARIA = ? , COD_INSTITUTO= ?  WHERE ID = ?;";
	// 1 2 3 4 5

	@Override
	public void atualizar(Curso curso) throws DadosException {
		Connection connection = GerenciadorConexao.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, curso.getNomeCurso().name());
			statement.setString(2, curso.getTipoCurso().name());
			statement.setInt(3, curso.getCargaHoraria());
			statement.setString(4, curso.getCodInstituto());
			statement.setInt(5, curso.getCodCurso());
			int qtdeRegistros = statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel atualizar", ex);
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
	}
}