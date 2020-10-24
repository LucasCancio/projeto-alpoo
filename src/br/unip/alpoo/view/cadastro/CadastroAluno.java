package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CadastroAluno extends JFrame {
	private static CadastroAluno instance;

	public static CadastroAluno getInstance() {
		if (instance == null) {
			instance = new CadastroAluno();
		}
		return instance;
	}

	private CadastroAluno() {
		this.setTitle("Cadastro de Alunos");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}

}
