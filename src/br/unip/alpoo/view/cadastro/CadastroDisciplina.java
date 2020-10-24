package br.unip.alpoo.view.cadastro;

import javax.swing.JFrame;

public class CadastroDisciplina  extends JFrame {
	private static CadastroDisciplina instance;

	public static CadastroDisciplina getInstance() {
		if (instance == null) {
			instance = new CadastroDisciplina();
		}
		return instance;
	}
	
	private CadastroDisciplina() {
		this.setTitle("Cadastro de Disciplinas");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}
}
