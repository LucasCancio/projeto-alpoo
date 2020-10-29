package br.unip.alpoo.view.cadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import br.unip.alpoo.controller.CursoController;
import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.NomesCursos;
import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;
import br.unip.alpoo.util.FuncoesUteis;
import br.unip.alpoo.util.PainelBotoesCrud;

public class CadastroCurso extends JFrame {
	private JLabel lblTitulo;

	private Operacoes operacao;

	private CursoController controller;

	private PainelBotoesCrud painelBotoesCrud;
	private PainelCadastro painelCadastro;

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

		var layout = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
		this.setLayout(layout);

		this.setTitle(titulo);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.add(Box.createRigidArea(new Dimension(0, 5)));

		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(lblTitulo);

		painelBotoesCrud = new PainelBotoesCrud(operacao);
		painelCadastro = new PainelCadastro(operacao, painelBotoesCrud);
		setarCliques(painelBotoesCrud, painelCadastro);

		this.add(painelCadastro, BorderLayout.CENTER);
		this.setBackground(new Color(250, 251, 252));
		FuncoesUteis.pintarPaineis(painelCadastro, new Color(250, 251, 252));
	}

	

	private void setarCliques(PainelBotoesCrud painelBotoes, PainelCadastro painelCadastro) {
		var painelBusca = painelCadastro.painelBusca;
		
		painelBotoes.btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Curso curso = preencherCurso(painelCadastro);
					controller.salvar(curso);
					if (operacao == Operacoes.CADASTRO) {
						JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso\nId: " + curso.getCodCurso(),
								"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Curso alterado com sucesso", "Sucesso!",
								JOptionPane.INFORMATION_MESSAGE);
					}

					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		painelBotoes.btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para o menu?", "Atenção!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcao == JOptionPane.YES_OPTION) {
					dispose();
				}

			}
		});

		if (operacao == Operacoes.CONSULTA) {
			
			painelBotoes.btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação?", "Atenção!",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (opcao == JOptionPane.YES_OPTION) {
						if (operacao == Operacoes.CADASTRO) {
							dispose();
						} else {
							painelBotoes.painelBotaoInferior.setVisible(false);
							painelBotoes.painelBotaoSuperior.setVisible(true);

							painelCadastro.painelBusca.txtCod.setEditable(true);
							painelCadastro.enable(false);
						}
					}
				}
			});

			painelBusca.btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = Integer.parseInt(painelBusca.txtCod.getText());
					if (id != 0) {
						try {
							Curso curso = controller.getPorId(id);
							if (curso == null) {
								JOptionPane.showMessageDialog(null, "Nenhum curso encontrado!", "Sucesso!",
										JOptionPane.ERROR_MESSAGE);
							} else {
								preencherFormulario(painelCadastro,curso);
							}
						} catch (DadosException e1) {
							e1.printStackTrace();
						}
					}
				}
			});

			painelBotoes.btnEditar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					painelBotoes.painelBotaoInferior.setVisible(true);
					painelBotoes.painelBotaoSuperior.setVisible(false);

					painelBusca.txtCod.setEditable(false);
					painelCadastro.enable(true);
				}
			});

			painelBotoes.btnDeletar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int opcao = JOptionPane.showConfirmDialog(null, "Deseja deletar o curso?", "Atenção!",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (opcao == JOptionPane.YES_OPTION) {
						try {
							var id = Integer.parseInt(painelBusca.txtCod.getText());
							controller.excluirPorId(id);

							JOptionPane.showMessageDialog(null, "Curso deletado com sucesso!", "Sucesso!",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (DadosException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		}
	}

	private Curso preencherCurso(PainelCadastro painel) {
		String strCodCurso = painel.painelBusca.txtCod.getText();
		int codCurso = 0;
		if (!strCodCurso.isEmpty()) {
			codCurso = Integer.parseInt(strCodCurso);
		}

		String codInstituto = painel.txtCodInstituto.getText();
		String spiValue = painel.spiCargaHoraria.getValue().toString();
		int cargaHoraria = Integer.parseInt(spiValue);

		String strNomeCurso = painel.painelNomeCurso.listNomesCursos.getSelectedValue().toString();
		NomesCursos nomeCurso = null;
		for (NomesCursos value : NomesCursos.values()) {
			if (value.getDescricao() == strNomeCurso) {
				nomeCurso = value;
			}
		}

		ButtonModel selecao = painel.painelTipoCurso.grupoTiposCursos.getSelection();
		String strTipoCurso = selecao.getActionCommand();
		TipoCurso tipoCurso = TipoCurso.valueOf(strTipoCurso);

		return new Curso(codCurso, nomeCurso, tipoCurso, cargaHoraria, codInstituto);
	}

	private void preencherFormulario(PainelCadastro painel, Curso curso) {
		painel.txtCodInstituto.setText(curso.getCodInstituto());
		painel.spiCargaHoraria.setValue(curso.getCargaHoraria());

		painel.painelNomeCurso.listNomesCursos.setSelectedIndex(curso.getNomeCurso().ordinal());
		for (Component component : painel.painelTipoCurso.getComponents()) {
			if (component instanceof JRadioButton) {
				var radio = (JRadioButton) component;
				if (radio.getText().equals(curso.getTipoCurso().getDescricao())) {
					painel.painelTipoCurso.grupoTiposCursos.clearSelection();
					radio.setSelected(true);
				}
			}
		}

	}

	private class PainelCadastro extends JPanel {
		private JLabel lblCargaHoraria;
		private JSpinner spiCargaHoraria;

		private JLabel lblCodInstituto;
		private JTextField txtCodInstituto;

		private PainelBusca painelBusca;

		private JLabel lblTiposCurso;
		private PainelTipoCurso painelTipoCurso;

		private JLabel lblNomesCursos;
		private PainelNomeCurso painelNomeCurso;

		public PainelCadastro(Operacoes operacao, PainelBotoesCrud painelBotoes) {
			painelBusca = new PainelBusca(operacao);

			var layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			int top = 10;
			int left = 10;
			int bottom = 2;
			int right = 30;
			c.insets = new Insets(top, left, bottom, right);

			this.setLayout(layout);

			lblCargaHoraria = new JLabel("Qtde:");
			SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 10000, 1);
			spiCargaHoraria = new JSpinner(spinnerNumberModel);

			lblCodInstituto = new JLabel("Código Instituto:");
			txtCodInstituto = new JTextField();

			lblNomesCursos = new JLabel("Nome:");
			painelNomeCurso= new PainelNomeCurso();
			painelNomeCurso.add(lblNomesCursos);

			lblTiposCurso = new JLabel("Tipo:");
			painelTipoCurso= new PainelTipoCurso();

			if (operacao == Operacoes.CONSULTA) {
				this.enable(false);
			}

			int linhaAtual = 0;
			c.fill = GridBagConstraints.HORIZONTAL;

			if (operacao == Operacoes.CONSULTA) {
				c.gridy = linhaAtual++;

				c.gridx = 0;
				this.add(painelBusca.lblCod, c);

				c.gridx = 1;
				this.add(painelBusca, c);
			}

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(lblCargaHoraria, c);
			c.gridx = 1;
			this.add(spiCargaHoraria, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(lblCodInstituto, c);
			c.gridx = 1;
			this.add(txtCodInstituto, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(lblTiposCurso, c);
			c.gridx = 1;
			this.add(painelTipoCurso, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(lblNomesCursos, c);
			c.gridx = 1;
			this.add(painelNomeCurso, c);

			c.gridy = linhaAtual++;

			c.weightx = 0;
			c.gridx = 0;
			c.gridwidth = 3;
			this.add(painelBotoes, c);

		}
		
		

		@Override
		public void enable(boolean status) {
			txtCodInstituto.setEnabled(status);
			spiCargaHoraria.setEnabled(status);
			painelNomeCurso.listNomesCursos.setEnabled(status);

			for (Component component : painelTipoCurso.getComponents()) {
				if (component instanceof JRadioButton)
					component.setEnabled(status);
			}
		}
	}

	public class PainelBusca extends JPanel {
		private JLabel lblCod;
		private JTextField txtCod;
		private JButton btnBuscar;

		public PainelBusca(Operacoes operacao) {
			txtCod = new JTextField();
			txtCod.setEditable(false);
			txtCod.setColumns(10);

			btnBuscar = new JButton("Buscar");
			btnBuscar.setMnemonic(KeyEvent.VK_S);
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			lblCod = new JLabel("Código:");
			if (operacao == Operacoes.CADASTRO) {
				lblCod.setVisible(false);
				txtCod.setVisible(false);
				btnBuscar.setVisible(false);
			} else {
				txtCod.setEditable(true);
			}

			this.setLayout(new FlowLayout());
			this.add(txtCod);
			this.add(btnBuscar);
		}
	}

	private class PainelTipoCurso extends JPanel {
		private ButtonGroup grupoTiposCursos;
		private JPanel painelTiposCursos;

		public PainelTipoCurso() {
			TipoCurso[] tiposCurso = TipoCurso.values();
			var radioButtons = new JRadioButton[tiposCurso.length];

			grupoTiposCursos = new ButtonGroup();

			for (int i = 0; i < tiposCurso.length; i++) {
				radioButtons[i] = new JRadioButton(tiposCurso[i].getDescricao());

				if (i == 0)
					radioButtons[i].setSelected(true);

				radioButtons[i].setActionCommand(tiposCurso[i].name());
				this.add(radioButtons[i]);
				grupoTiposCursos.add(radioButtons[i]);
			}
		}
	}

	private class PainelNomeCurso extends JPanel {

		private JList listNomesCursos;

		public PainelNomeCurso() {
			BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
			this.setLayout(layout);

			DefaultListModel listModel = new DefaultListModel();
			NomesCursos[] nomesCursos = NomesCursos.values();
			for (NomesCursos nomeCurso : nomesCursos) {
				listModel.addElement(nomeCurso.getDescricao());
			}
			listNomesCursos = new JList(listModel);
			listNomesCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listNomesCursos.setSelectedIndex(0);

			JScrollPane scrollLista = new JScrollPane(listNomesCursos);

			this.add(Box.createRigidArea(new Dimension(0, 5)));
			this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(scrollLista);
		}
	}

}
