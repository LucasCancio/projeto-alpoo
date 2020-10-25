package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import br.unip.alpoo.model.Operacoes;

public class CadastroAluno extends JFrame{
	
	private Operacoes operacao;
	public CadastroAluno(Operacoes operacao) {
		this.operacao=operacao;
		this.setTitle("Cadastro de Alunos");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
	}

}
