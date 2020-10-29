package br.unip.alpoo.controller;

import java.util.Date;
import java.util.List;

import br.unip.alpoo.dao.CursoJdbc;
import br.unip.alpoo.dao.ICursoDao;
import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

public class CursoController {
	//implementacao do Singleton
    private static CursoController instance;

    private CursoController() {
    }

    public static CursoController getInstance() {
        if (instance == null) {
            instance = new CursoController();
        }
        return instance;
    }
    //implementacao do Singleton
    
    private ICursoDao dao = CursoJdbc.getInstance();

    public void salvar(Curso curso) throws DadosException {
        boolean ehNova = curso != null
                && !(curso.getCodCurso() > 0);
        if (ehNova) {
            dao.incluir(curso);
        } else {
            dao.atualizar(curso);
        }
    }

    public void excluirPorId(Integer id) throws DadosException {
        dao.excluirPorId(id);
    }

    public Curso getPorId(Integer id) throws DadosException {
        return dao.getPorId(id);
    }

    public List<Curso> getPorTipo(TipoCurso tipo) throws DadosException {
        if (tipo != null) {
            return dao.getPorTipo(tipo);
        } else {
            return dao.getTodas();
        }
    }

    public List<Curso> getTodas() throws DadosException {
        return dao.getTodas();
    }
}
