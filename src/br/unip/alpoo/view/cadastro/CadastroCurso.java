package br.unip.alpoo.view.cadastro;

import javax.swing.JFrame;

public class CadastroCurso  extends JFrame {
	private static CadastroCurso instance;

	public static CadastroCurso getInstance() {
		if (instance == null) {
			instance = new CadastroCurso();
		}
		return instance;
	}
	
	private CadastroCurso() {
		this.setTitle("Cadastro de Cursos");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}
}
