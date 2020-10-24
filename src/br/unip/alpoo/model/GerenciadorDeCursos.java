package br.unip.alpoo.model; 


import java.util.Date;
import java.util.List;

import br.unip.alpoo.dao.IAtividadeDao;
import br.unip.alpoo.dao.CursoJdbc;
import br.unip.alpoo.util.DadosException;

public class GerenciadorDeCursos {

    //implementacao do Singleton
    private static GerenciadorDeCursos instance;

    private GerenciadorDeCursos() {
    }

    public static GerenciadorDeCursos getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeCursos();
        }
        return instance;
    }
    //implementacao do Singleton
    
    private IAtividadeDao dao = CursoJdbc.getInstance();

    public AtividadeComplementar getNovaAtividade(){
        AtividadeComplementar atividade = new AtividadeComplementar();
        atividade.setId(new Integer(0));
        atividade.setTipo(TipoCurso.CULTURAL);
        atividade.setDataRealizacao(new Date());
        atividade.setQtdeHoras(0);
        atividade.setSituacao(Situacao.ENTREGUE);
        return atividade;
    }  
    public void salvar(AtividadeComplementar atividade) throws DadosException {
        boolean ehNova = atividade != null && atividade.getId() != null
                && !(atividade.getId() > 0);
        if (ehNova) {
            dao.incluir(atividade);
        } else {
            dao.atualizar(atividade);
        }
    }

    public void excluir(AtividadeComplementar atividade) throws DadosException {
        dao.excluir(atividade);
    }

    public AtividadeComplementar getPorId(Integer id) throws DadosException {
        return dao.getPorId(id);
    }

    public List<AtividadeComplementar> getPorTipo(TipoAtividade tipo) throws DadosException {
        if (tipo != null) {
            return dao.getPorTipo(tipo);
        } else {
            return dao.getTodas();
        }
    }

    public List<AtividadeComplementar> getTodas() throws DadosException {
        return dao.getTodas();
    }

}
