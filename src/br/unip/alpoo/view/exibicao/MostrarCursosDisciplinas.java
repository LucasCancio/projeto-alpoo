package br.unip.alpoo.view.exibicao;

import javax.swing.DefaultListModel;

import br.unip.alpoo.model.ListaCursos;
import br.unip.alpoo.model.TipoDisciplina;

public class MostrarCursosDisciplinas extends TemplateMostrar {

	public MostrarCursosDisciplinas() {
		var titulo = "Cursos/Disciplinas";
		var titulo1 = "Cursos";
		var model1 = getListModelCursos();

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

	public DefaultListModel getListModelCursos() {
		DefaultListModel listModel = new DefaultListModel();
		ListaCursos[] tipos = ListaCursos.values();
		for (ListaCursos tipo : tipos) {
			listModel.addElement(tipo.getDescricao());
		}

		return listModel;
	}
}