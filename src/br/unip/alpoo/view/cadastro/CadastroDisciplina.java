package br.unip.alpoo.view.cadastro;

import javax.swing.JFrame;

import br.unip.alpoo.model.Operacoes;

public class CadastroDisciplina  extends JFrame {
	
	private Operacoes operacao;
	public CadastroDisciplina(Operacoes operacao) {
		this.operacao=operacao;
		this.setTitle("Cadastro de Disciplinas");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}
}
