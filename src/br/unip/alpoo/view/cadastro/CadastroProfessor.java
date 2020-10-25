package br.unip.alpoo.view.cadastro;

import javax.swing.JFrame;

import br.unip.alpoo.model.Operacoes;

public class CadastroProfessor extends JFrame {
	
	private Operacoes operacao;
	public CadastroProfessor(Operacoes operacao) {
		this.operacao=operacao;
		this.setTitle("Cadastro de Professores");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}
}
