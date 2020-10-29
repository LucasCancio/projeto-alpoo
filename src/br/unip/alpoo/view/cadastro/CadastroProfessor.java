package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import br.unip.alpoo.model.EspecialidadeProf;
import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.model.TituloProf;
import br.unip.alpoo.util.FuncoesUteis;
import br.unip.alpoo.util.PainelBotoesCrud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CadastroProfessor extends JFrame {

	private JLabel IdentProf;
	private JTextField txtIdentProf;
	private JLabel NomeProf;
	private JTextField txtNomeProf;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel Numero;
	private JSpinner spiNumero;
	private JLabel Bairro;
	private JTextField txtBairro;
	private JLabel Cidade;
	private JTextField txtCidade;
	private JLabel Estado;
	private JTextField txtEstado;
	private JLabel TelFixo;
	private JTextField txtTelFixo;
	private JLabel TelCel;
	private JTextField txtTelCel;

	private JLabel EspecProf;
	private JCheckBox[] checkBoxesEspec;

	private JLabel lblTituloProf;
	private JCheckBox[] checkBoxes;

	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnDeletar;
	
	private JLabel lblTitulo;

	private Operacoes operacao;

	public CadastroProfessor(Operacoes operacao) {
		this.operacao = operacao;

		String titulo;
		switch (operacao) {
		case CADASTRO:
			titulo = "Cadastro de Professor";
			break;
		case CONSULTA:
		default:
			titulo = "Consulta de Professor";
			break;
		}

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setTitle(titulo);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(lblTitulo);

		JPanel painelCadastro = montaPainelCadastro();
		this.add(painelCadastro, BorderLayout.CENTER);
		FuncoesUteis.pintarPaineis(painelCadastro, new Color(250, 251, 252));
	}

	private JPanel montaPainelCadastro() {
		JPanel painelCadastro = new JPanel();
		IdentProf = new JLabel("Identidade:");
		txtIdentProf = new JTextField();
		txtIdentProf.setEditable(true);
		txtIdentProf.setColumns(10);

		NomeProf = new JLabel("Nome:");
		txtNomeProf = new JTextField();
		txtNomeProf.setEditable(true);
		txtNomeProf.setColumns(10);

		lblRua = new JLabel("Rua:");
		txtRua = new JTextField();
		txtRua.setEditable(true);
		txtRua.setColumns(10);

		Numero = new JLabel("Nº:");
		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 10000, 1);
		spiNumero = new JSpinner(spinnerNumberModel);

		Bairro = new JLabel("Bairro:");
		txtBairro = new JTextField();
		txtBairro.setEditable(true);
		txtBairro.setColumns(10);

		Cidade = new JLabel("Cidade:");
		txtCidade = new JTextField();
		txtCidade.setEditable(true);
		txtCidade.setColumns(10);

		Estado = new JLabel("Estado:");
		txtEstado = new JTextField();
		txtEstado.setEditable(true);
		txtEstado.setColumns(10);

		TelFixo = new JLabel("Cidade:");
		txtTelFixo = new JTextField();
		txtTelFixo.setEditable(true);
		txtTelFixo.setColumns(10);

		TelCel = new JLabel("Estado:");
		txtTelCel = new JTextField();
		txtTelCel.setEditable(true);
		txtTelCel.setColumns(10);

		lblTituloProf = new JLabel("Titulo:");
		JPanel painelTituloProf = montaPainelTitulo();

		EspecProf = new JLabel("Especialidade:");
		JPanel painelEspecialidadeProf = montaPainelEspecialidade();

		JPanel painelBotoes = new PainelBotoesCrud(operacao);

		var layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		int top = 10;
		int left = 10;
		int bottom = 2;
		int right = 30;
		c.insets = new Insets(top, left, bottom, right);

		painelCadastro.setLayout(layout);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		int linhaAtual=0;

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(IdentProf, c);
		c.gridx = 1;
		painelCadastro.add(txtIdentProf, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(NomeProf, c);
		c.gridx = 1;
		painelCadastro.add(txtNomeProf, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(lblRua, c);
		c.gridx = 1;
		painelCadastro.add(txtRua, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(Numero, c);
		c.gridx = 1;
		painelCadastro.add(spiNumero, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(Bairro, c);
		c.gridx = 1;
		painelCadastro.add(txtBairro, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(Cidade, c);
		c.gridx = 1;
		painelCadastro.add(txtCidade, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(Estado, c);
		c.gridx = 1;
		painelCadastro.add(txtEstado, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(TelFixo, c);
		c.gridx = 1;
		painelCadastro.add(txtTelFixo, c);

		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(TelCel, c);
		c.gridx = 1;
		painelCadastro.add(txtTelCel, c);

		
		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(EspecProf, c);
		c.gridx = 1;
		painelCadastro.add(painelEspecialidadeProf, c);
		
		c.gridy = linhaAtual++;

		c.gridx = 0;
		painelCadastro.add(lblTituloProf, c);
		c.gridx = 1;
		painelCadastro.add(painelTituloProf, c);
		
		c.gridy = linhaAtual++;

		c.gridx = 0;
		c.gridwidth = 3;
		painelCadastro.add(painelBotoes, c);

		return painelCadastro;
	}

	private JPanel montaPainelTitulo() {
		JPanel painel = new JPanel(new GridLayout(3,3));
		TituloProf[] tipos = TituloProf.values();

		checkBoxes = new JCheckBox[tipos.length];

		for (int i = 0; i < tipos.length; i++) {
			checkBoxes[i] = new JCheckBox(tipos[i].getDescricao());
			checkBoxes[i].setActionCommand(tipos[i].name());
			painel.add(checkBoxes[i]);
		}

		return painel;
	}

	private JPanel montaPainelEspecialidade() {
		EspecialidadeProf[] tipos = EspecialidadeProf.values();
		JPanel painel = new JPanel(new GridLayout(3,3));
		checkBoxesEspec = new JCheckBox[tipos.length];
		
		
		for (int i = 0; i < tipos.length; i++) {	
			checkBoxesEspec[i] = new JCheckBox(tipos[i].getDescricao());
			checkBoxesEspec[i].setActionCommand(tipos[i].name());
			painel.add(checkBoxesEspec[i]);
		}

		return painel;
	}
}
