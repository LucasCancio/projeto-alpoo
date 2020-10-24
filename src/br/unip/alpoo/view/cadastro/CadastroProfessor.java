package br.unip.alpoo.view.cadastro;

import javax.swing.JFrame;

public class CadastroProfessor extends JFrame {
	private static CadastroProfessor instance;

	public static CadastroProfessor getInstance() {
		if (instance == null) {
			instance = new CadastroProfessor();
		}
		return instance;
	}
	
	private CadastroProfessor() {
		this.setTitle("Cadastro de Professores");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
		this.setVisible(true);

	}
}
