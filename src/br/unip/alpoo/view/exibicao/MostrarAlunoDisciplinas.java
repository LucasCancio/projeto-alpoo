package br.unip.alpoo.view.exibicao;

import javax.swing.DefaultListModel;

import br.unip.alpoo.model.ListaAluno;
import br.unip.alpoo.model.TipoDisciplina;


public class MostrarAlunoDisciplinas extends TemplateMostrar {
	
	public MostrarAlunoDisciplinas() {
		var titulo = "Aluno/Disciplinas";
		var titulo1 = "Aluno";
		var model1 = getListModelAlunos();

		var titulo2 = "Disciplinas";
		var model2 = getListModelDisciplinas();
		this.montaFrame(titulo, model1, titulo1, model2, titulo2);
	}

	public DefaultListModel getListModelDisciplinas() {
		DefaultListModel listModel = new DefaultListModel();
		TipoDisciplina[] tipos = TipoDisciplina.values();
		for (TipoDisciplina tipo : tipos) {
			listModel.addElement(tipo.getDescricao());
		}
		
		return listModel;
	}

	public DefaultListModel getListModelAlunos() {
		DefaultListModel listModel = new DefaultListModel();
		ListaAluno[] tipos = ListaAluno.values();
		for (ListaAluno tipo : tipos) {
			listModel.addElement(tipo.getDescricao());
		}

		return listModel;
	}
}