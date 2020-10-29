package br.unip.alpoo.view.exibicao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.util.FuncoesUteis;

public abstract class TemplateMostrar extends JFrame {
	protected JLabel lblTitulo;
	protected JLabel lblModel1;
	protected JLabel lblModel2;

	protected Operacoes operacao;

	protected void montaFrame(String titulo, DefaultListModel model1, String titulo1, DefaultListModel model2,
			String titulo2) {
		this.operacao = Operacoes.CONSULTA;

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		this.setTitle(titulo);
		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(lblTitulo);

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		lblModel1 = new JLabel(titulo1);
		var painel1 = montaPainelJList(model1);

		lblModel2 = new JLabel(titulo2);
		var painel2 = montaPainelJList(model2);

		var layout = new GridBagLayout();
		JPanel painelListas = new JPanel(layout);

		GridBagConstraints c = new GridBagConstraints();
		int top = 10;
		int left = 10;
		int bottom = 2;
		int right = 30;
		c.insets = new Insets(top, left, bottom, right);

		int linhaAtual = 0;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelListas.add(lblModel1, c);
		c.gridx = 1;
		painelListas.add(painel1, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelListas.add(lblModel2, c);
		c.gridx = 1;
		painelListas.add(painel2, c);

		this.setBackground(new Color(250, 251, 252));
		FuncoesUteis.pintarPaineis(painelListas, new Color(250, 251, 252));
		this.add(painelListas);
	}

	protected JPanel montaPainelJList(DefaultListModel listModel) {
		JPanel painel = new JPanel();

		BoxLayout layout = new BoxLayout(painel, BoxLayout.PAGE_AXIS);
		painel.setLayout(layout);

		var lista = new JList(listModel);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollLista = new JScrollPane(lista);
		lista.setPreferredSize(new Dimension(200, 200));

		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		painel.add(scrollLista);

		return painel;
	}
}
