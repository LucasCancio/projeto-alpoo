package br.unip.alpoo.dao;

import java.util.List;

import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

public interface ICursoDao {
	Curso getPorId(Integer id) throws DadosException;
	List<Curso> getPorTipo(TipoCurso tipo) throws DadosException;
	List<Curso> getTodas() throws DadosException;
	void incluir(Curso curso) throws DadosException;
	void excluirPorId(Integer id) throws DadosException;
	void atualizar(Curso curso) throws DadosException;
}
