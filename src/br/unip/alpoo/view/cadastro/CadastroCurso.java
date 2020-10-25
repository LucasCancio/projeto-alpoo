package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import br.unip.alpoo.controller.CursoController;
import br.unip.alpoo.model.NomesCursos;
import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

public class CadastroCurso extends JFrame {
	private JLabel lblTitulo;

	private JLabel lblCod;
	private JTextField txtCod;

	private JLabel lblCargaHoraria;
	private JSpinner spiCargaHoraria;

	private JLabel lblCodInstituto;
	private JTextField txtCodInstituto;

	private JLabel lblNomesCursos;
	private JList listNomesCursos;

	private JLabel lblTiposCurso;

	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnDeletar;

	private Operacoes operacao;

	private CursoController controller;

	public CadastroCurso(Operacoes operacao) {
		this.operacao = operacao;
		this.controller = CursoController.getInstance();

		String titulo;
		switch (operacao) {
		case CADASTRO:
			titulo = "Cadastro de Curso";
			break;
		case CONSULTA:
		default:
			titulo = "Consulta de Curso";
			break;
		}

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setTitle(titulo);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();
		JPanel painelCadastro = montaPainelCadastro();

		lblTitulo = new JLabel(titulo);
		this.add(lblTitulo);
		this.add(painelCadastro, BorderLayout.CENTER);
	}

	private JPanel montaPainelCadastro() {
		JPanel painelCadastro = new JPanel();
		// GridLayout layout = new GridLayout(4, 2);
		// painelCadastro.setLayout(layout);
		var layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		int top = 10;
		int left = 10;
		int bottom = 2;
		int right = 30;
		c.insets = new Insets(top, left, bottom, right);

		painelCadastro.setLayout(layout);

		lblCod = new JLabel("Código:");
		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setColumns(10);

		if (operacao == Operacoes.CADASTRO) {
			lblCod.setVisible(false);
			txtCod.setVisible(false);
		}

		lblCargaHoraria = new JLabel("Qtde:");
		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 10000, 1);
		spiCargaHoraria = new JSpinner(spinnerNumberModel);

		lblCodInstituto = new JLabel("Código Instituto:");
		txtCodInstituto = new JTextField();

		lblNomesCursos = new JLabel("Nome:");
		JPanel painelNomeCurso = montaPainelNomeCurso();
		painelNomeCurso.add(lblNomesCursos);

		lblTiposCurso = new JLabel("Tipo:");
		JPanel painelTipoCurso = montaPainelTipoCurso();
		painelTipoCurso.add(lblTiposCurso);

		JPanel painelBotoes = montaPainelBotoes();

		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridy = 0;

		c.gridx = 0;
		painelCadastro.add(lblCod, c);
		c.gridx = 1;
		painelCadastro.add(txtCod, c);

		c.gridy = 1;

		c.gridx = 0;
		painelCadastro.add(lblCargaHoraria, c);
		c.gridx = 1;
		painelCadastro.add(spiCargaHoraria, c);

		c.gridy = 2;

		c.gridx = 0;
		painelCadastro.add(lblCodInstituto, c);
		c.gridx = 1;
		painelCadastro.add(txtCodInstituto, c);

		c.gridy = 3;

		c.gridx = 0;
		painelCadastro.add(lblTiposCurso, c);
		c.gridx = 1;
		painelCadastro.add(painelTipoCurso, c);

		c.gridy = 4;

		c.gridx = 0;
		painelCadastro.add(lblNomesCursos, c);
		c.gridx = 1;
		painelCadastro.add(painelNomeCurso, c);

		c.gridy = 5;
		
		c.weightx = 0;
		c.gridx=0;
		c.gridwidth=3;
		painelCadastro.add(painelBotoes,c);
		
		/*
		 * var components = painelCadastro.getComponents(); for(Component component :
		 * components) { if(component instanceof JLabel) { var label =
		 * (JLabel)component; label.setHorizontalAlignment(JLabel.CENTER); }
		 * 
		 * }
		 */

		return painelCadastro;
	}

	private JPanel montaPainelBotoes() {
		JPanel painelBotoes= new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes,BoxLayout.LINE_AXIS));
		
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic(KeyEvent.VK_S);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(false);
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação?", "Alerta!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcao == JOptionPane.YES_OPTION) {
					if (operacao == Operacoes.CADASTRO) {
						dispose();
					} else {
						btnSalvar.setVisible(false);
					}
				}
			}
		});
		
		painelBotoes.add(btnCancelar);

		if (operacao == Operacoes.CONSULTA) {
			btnSalvar.setVisible(false);

			btnEditar = new JButton("Editar");
			btnEditar.setMnemonic(KeyEvent.VK_S);
			btnEditar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnSalvar.setVisible(true);
				}
			});
			
			painelBotoes.add(btnEditar);

			btnDeletar = new JButton("Deletar");
			btnDeletar.setMnemonic(KeyEvent.VK_S);
			btnDeletar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnSalvar.setEnabled(false);
				}
			});
			
			painelBotoes.add(btnDeletar);

		}
		
		painelBotoes.add(btnSalvar);
		
		return painelBotoes;
	}

	private JPanel montaPainelNomeCurso() {
		JPanel painelLista = new JPanel();
		BoxLayout layout = new BoxLayout(painelLista, BoxLayout.LINE_AXIS);
		painelLista.setLayout(layout);

		DefaultListModel listModel = new DefaultListModel();
		NomesCursos[] nomesCursos = NomesCursos.values();
		for (NomesCursos nomeCurso : nomesCursos) {
			listModel.addElement(nomeCurso.getDescricao());
		}
		listNomesCursos = new JList(listModel);
		listNomesCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollLista = new JScrollPane(listNomesCursos);

		painelLista.add(Box.createRigidArea(new Dimension(0, 5)));
		painelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		painelLista.add(scrollLista);

		return painelLista;
	}

	private JPanel montaPainelTipoCurso() {
		JPanel painel = new JPanel();

		TipoCurso[] tiposCurso = TipoCurso.values();
		var radioButtons = new JRadioButton[tiposCurso.length];

		ButtonGroup grupo = new ButtonGroup();

		for (int i = 0; i < tiposCurso.length; i++) {
			radioButtons[i] = new JRadioButton(tiposCurso[i].getDescricao());
			radioButtons[i].setActionCommand(tiposCurso[i].name());
			painel.add(radioButtons[i]);
			grupo.add(radioButtons[i]);
		}

		return painel;
	}

}
