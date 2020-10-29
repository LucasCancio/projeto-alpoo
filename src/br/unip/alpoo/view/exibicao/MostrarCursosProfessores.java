package br.unip.alpoo.view.exibicao;

import br.unip.alpoo.model.ListaCursos;
import br.unip.alpoo.model.ListaProfessores;
import br.unip.alpoo.model.Operacoes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MostrarCursosProfessores extends TemplateMostrar {
	public MostrarCursosProfessores() {
		var titulo = "Cursos/Professores";
		var titulo1 = "Cursos";
		var model1 = getListModelCursos();

		var titulo2 = "Professores";
		var model2 = getListModelProfessor();
		this.montaFrame(titulo, model1, titulo1, model2, titulo2);
	}

	public DefaultListModel getListModelProfessor() {
		DefaultListModel listModel = new DefaultListModel();
		ListaProfessores[] tipos = ListaProfessores.values();
		for (ListaProfessores tipo : tipos) {
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