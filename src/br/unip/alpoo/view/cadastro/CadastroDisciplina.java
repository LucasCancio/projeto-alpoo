package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.util.FuncoesUteis;
import br.unip.alpoo.util.PainelBotoesCrud;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class CadastroDisciplina extends JFrame {
	private Operacoes operacao;

	private PainelDisciplina painelDisciplina;
	private PainelBotoesCrud painelBotoes;
	private JLabel lblTitulo;

	public CadastroDisciplina(Operacoes operacao) {
		this.operacao = operacao;
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		String titulo;
		switch (operacao) {
		case CADASTRO:
			titulo = "Cadastro de Disciplina";
			break;
		case CONSULTA:
		default:
			titulo = "Consulta de Disciplina";
			break;
		}

		this.setTitle(titulo);
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.add(Box.createRigidArea(new Dimension(0, 5)));

		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(lblTitulo);

		painelDisciplina = new PainelDisciplina();

		painelBotoes = new PainelBotoesCrud(operacao);

		JPanel painelCadastro = new JPanel();
		var layout = new BoxLayout(painelCadastro, BoxLayout.PAGE_AXIS);
		painelCadastro.setLayout(layout);

		painelCadastro.add(painelDisciplina);
		painelCadastro.add(painelBotoes);

		this.add(painelCadastro);
		FuncoesUteis.pintarPaineis(painelCadastro, new Color(250, 251, 252));

	}

	public class PainelDisciplina extends JPanel {

		private JLabel codDisc;
		private JTextField txtDisc;
		private JLabel nomeCurso;
		private JTextField txtCurso;
		private JLabel cargaHoraria;
		private JTextField txtcargaHoraria;

		private JLabel lblAulaSemanal;
		private PainelSelecaoRadio painelSelecaoRadio;

		public PainelDisciplina() {
			this.add(painelDisciplina());

		}

		private JPanel painelDisciplina() {
			JPanel painelDisciplina = new JPanel();
			var layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			int top = 10;
			int left = 10;
			int bottom = 2;
			int right = 30;
			c.insets = new Insets(top, left, bottom, right);

			painelDisciplina.setLayout(layout);

			codDisc = new JLabel("Codigo:");
			txtDisc = new JTextField();
			txtDisc.setEditable(true);

			nomeCurso = new JLabel("Nome do Curso:");
			txtCurso = new JTextField();
			txtCurso.setEditable(true);

			cargaHoraria = new JLabel("Carga Horaria:");
			txtcargaHoraria = new JTextField();
			txtcargaHoraria.setEditable(true);

			painelSelecaoRadio = new PainelSelecaoRadio();
			lblAulaSemanal = new JLabel("Aulas Semanais");

			int linhaAtual = 0;
			c.fill = GridBagConstraints.HORIZONTAL;

			c.gridy = linhaAtual++;

			c.gridx = 0;
			painelDisciplina.add(codDisc, c);
			c.gridx = 1;
			painelDisciplina.add(txtDisc, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			painelDisciplina.add(nomeCurso, c);
			c.gridx = 1;
			painelDisciplina.add(txtCurso, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			painelDisciplina.add(cargaHoraria, c);
			c.gridx = 1;
			painelDisciplina.add(txtcargaHoraria, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			painelDisciplina.add(lblAulaSemanal, c);
			
			c.gridx = 1;
			painelDisciplina.add(painelSelecaoRadio, c);

			return painelDisciplina;
		}
	}

	public class PainelSelecaoRadio extends JPanel {
		private JRadioButton radio1;
		private JRadioButton radio2;
		private JRadioButton radio3;
		private JRadioButton radio4;
		private JRadioButton radio5;
		private JRadioButton radio6;
		private JTextField txtDisc;

		public PainelSelecaoRadio() {

			this.add(montaPainelRadio());
		}

		private JPanel montaPainelRadio() {
			JPanel painelRadio = new JPanel(new GridLayout(2, 3));

			radio1 = new JRadioButton("1");
			radio2 = new JRadioButton("2");
			radio3 = new JRadioButton("3");
			radio4 = new JRadioButton("4");
			radio5 = new JRadioButton("5");
			radio6 = new JRadioButton("6");

			radio1.setActionCommand("1");
			radio2.setActionCommand("2");
			radio3.setActionCommand("3");
			radio4.setActionCommand("4");
			radio5.setActionCommand("5");
			radio6.setActionCommand("6");

			ButtonGroup grupo = new ButtonGroup();
			grupo.add(radio1);
			grupo.add(radio2);
			grupo.add(radio3);
			grupo.add(radio4);
			grupo.add(radio5);
			grupo.add(radio6);

			painelRadio.add(radio1);
			painelRadio.add(radio2);
			painelRadio.add(radio3);
			painelRadio.add(radio4);
			painelRadio.add(radio5);
			painelRadio.add(radio6);
			return painelRadio;
		}
	}

}
