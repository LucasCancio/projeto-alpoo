package br.unip.alpoo.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import br.unip.alpoo.model.Operacoes;

public class PainelBotoesCrud extends JPanel {
	public JButton btnSalvar;
	public JButton btnVoltar;

	public JButton btnEditar;
	public JButton btnDeletar;
	public JButton btnCancelar;

	public JPanel painelBotaoSuperior;
	public JPanel painelBotaoInferior;

	public PainelBotoesCrud(Operacoes operacao) {
		btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic(KeyEvent.VK_S);
		btnSalvar.setForeground(Color.GREEN);
		btnSalvar.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(Color.GREEN, 4), 
		        BorderFactory.createEmptyBorder(5, 5, 10, 10)));

		btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.CYAN);
		btnVoltar.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(Color.CYAN, 4), 
		        BorderFactory.createEmptyBorder(5, 5, 10, 10)));

		if (operacao == Operacoes.CONSULTA) {
			btnEditar = new JButton("Editar");
			btnEditar.setForeground(Color.BLUE);
			btnEditar.setBorder(BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(Color.BLUE, 4), 
			        BorderFactory.createEmptyBorder(5, 5, 10, 10)));
			
			btnDeletar = new JButton("Deletar");
			btnDeletar.setForeground(Color.RED);
			btnDeletar.setBorder(BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(Color.RED, 4), 
			        BorderFactory.createEmptyBorder(5, 5, 10, 10)));
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setForeground(Color.RED);
			btnCancelar.setBorder(BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(Color.RED, 4), 
			        BorderFactory.createEmptyBorder(5, 5, 10, 10)));

			montarLayoutBotaoConsulta();
		} else {
			montarLayoutBotaoCadastro();
		}
		

		var font = new Font("Serif", Font.BOLD,16);
		FuncoesUteis.setarFonteEmBotoesDoPainel(this, font);
	}

	
	private void montarLayoutBotaoCadastro() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.add(btnSalvar);
		this.add(Box.createRigidArea(new Dimension(30, 0)));
		this.add(btnVoltar);
	}

	private void montarLayoutBotaoConsulta() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		painelBotaoSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));

		painelBotaoSuperior.add(btnEditar);
		this.add(Box.createRigidArea(new Dimension(30, 0)));
		painelBotaoSuperior.add(btnDeletar);
		this.add(Box.createRigidArea(new Dimension(30, 0)));
		painelBotaoSuperior.add(btnVoltar);

		this.add(painelBotaoSuperior);

		painelBotaoInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelBotaoInferior.setVisible(false);

		painelBotaoInferior.add(btnSalvar);
		painelBotaoInferior.add(btnCancelar);

		this.add(painelBotaoInferior);
	}

}