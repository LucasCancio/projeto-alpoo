package br.unip.alpoo.view.exibicao;

import javax.swing.DefaultListModel;

import br.unip.alpoo.model.ListaProfessores;
import br.unip.alpoo.model.TipoDisciplina;

public class MostrarProfessoresDisciplinas extends TemplateMostrar {
	public MostrarProfessoresDisciplinas() {
		var titulo = "Professores/Disciplinas";
		var titulo1 = "Professores";
		var model1 = getListModelProfessores();

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

	public DefaultListModel getListModelProfessores() {
		DefaultListModel listModel = new DefaultListModel();
		ListaProfessores[] tipos = ListaProfessores.values();
		for (ListaProfessores tipo : tipos) {
			listModel.addElement(tipo.getDescricao());
		}


		return listModel;
	}
}