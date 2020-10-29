package br.unip.alpoo.view.cadastro;

import br.unip.alpoo.model.TipoDisciplina;
import br.unip.alpoo.util.FuncoesUteis;
import br.unip.alpoo.util.PainelBotoesCrud;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import br.unip.alpoo.model.Operacoes;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class CadastroAluno extends JFrame {
	private Operacoes operacao;
	private PainelAluno painelAluno;
	private PainelBotoesCrud painelBotoes;
	private JLabel lblTitulo;

	public CadastroAluno(Operacoes operacao) {
		this.operacao = operacao;

		String titulo;
		switch (operacao) {
		case CADASTRO:
			titulo = "Cadastro de Alunos";
			break;
		case CONSULTA:
		default:
			titulo = "Consulta de Alunos";
			break;
		}

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(titulo);

		this.add(Box.createRigidArea(new Dimension(0, 5)));

		lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 25));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(lblTitulo);

		JPanel painelCadastro = new JPanel();
		painelAluno = new PainelAluno();
		painelBotoes = new PainelBotoesCrud(operacao);

		var layout = new BoxLayout(painelCadastro, BoxLayout.PAGE_AXIS);
		painelCadastro.setLayout(layout);

		painelCadastro.add(painelAluno);
		painelCadastro.add(painelBotoes);

		this.add(painelCadastro);
		FuncoesUteis.pintarPaineis(painelCadastro, new Color(250, 251, 252));
	}

	public class PainelAluno extends JPanel {

		private JLabel Matricula;
		private JTextField txtMatricula;
		private JLabel Nome;
		private JTextField txtNome;
		private JLabel DataNasc;
		private JTextField txtDataNasc;
		private JLabel CodCurso;
		private JTextField txtCodCurso;
		private JLabel NomeCurso;
		private JTextField txtNomeCurso;
		
		private JLabel NotaP1;
		private JTextField txtNotaP1;
		private JLabel NotaP2;
		private JTextField txtNotaP2;
		private JLabel Media;
		private JTextField txtMedia;
		private JLabel Faltas;
		private JTextField txtFaltas;

		private PainelSelecaoEmListas painelSelecaoEmListas;
		public PainelAluno() {
			var layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			int top = 10;
			int left = 10;
			int bottom = 2;
			int right = 30;
			c.insets = new Insets(top, left, bottom, right);

			this.setLayout(layout);

			Matricula = new JLabel("Matricula:");
			txtMatricula = new JTextField();
			txtMatricula.setEditable(true);
			txtMatricula.setColumns(10);

			Nome = new JLabel("Nome:");
			txtNome = new JTextField();
			txtNome.setEditable(true);
			txtNome.setColumns(10);

			DataNasc = new JLabel("Data Nascimento:");
			DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.SHORT);
			txtDataNasc = new JFormattedTextField(formatadorData);
			txtDataNasc.setColumns(10);

			CodCurso = new JLabel("Nome:");
			txtCodCurso = new JTextField();
			txtCodCurso.setEditable(true);
			txtCodCurso.setColumns(10);

			NomeCurso = new JLabel("Nome:");
			txtNomeCurso = new JTextField();
			txtNomeCurso.setEditable(true);
			txtNomeCurso.setColumns(10);
			
			NotaP1 = new JLabel("Nota NP1: ");
			txtNotaP1 = new JTextField();
			txtNotaP1.setEditable(true);
			txtNotaP1.setColumns(10);

			NotaP2 = new JLabel("Nota NP2: ");
			txtNotaP2 = new JTextField();
			txtNotaP2.setEditable(true);
			txtNotaP2.setColumns(10);

			Media = new JLabel("Média: ");
			txtMedia = new JTextField();
			txtMedia.setEditable(true);
			txtMedia.setColumns(10);

			Faltas = new JLabel("Faltas: ");
			txtFaltas = new JTextField();
			txtFaltas.setEditable(true);
			txtFaltas.setColumns(10);
			
			painelSelecaoEmListas = new PainelSelecaoEmListas();

			int linhaAtual = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(Matricula, c);
			c.gridx = 1;
			this.add(txtMatricula, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(Nome, c);
			c.gridx = 1;
			this.add(txtNome, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(DataNasc, c);
			c.gridx = 1;
			this.add(txtDataNasc, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(CodCurso, c);
			c.gridx = 1;
			this.add(txtCodCurso, c);

			c.gridy = linhaAtual++;

			c.gridx = 0;
			this.add(NomeCurso, c);
			c.gridx = 1;
			this.add(txtNomeCurso, c);

			c.gridy = linhaAtual++;
			
			c.gridx = 0;
			c.weightx = 0;
			c.gridwidth = 2;
			c.gridheight = 3;
			
			c.fill = GridBagConstraints.BOTH;
			this.add(painelSelecaoEmListas, c);
			
			c.gridwidth = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridheight = 1;
			
			linhaAtual += 2;
			c.gridy = linhaAtual++;
			
			c.gridx = 0;
			this.add(NotaP1, c);;
			c.gridx = 1;
			this.add(txtNotaP1, c);

			c.gridy = linhaAtual++;
			
			c.gridx = 0;
			this.add(NotaP2, c);
			c.gridx = 1;
			this.add(txtNotaP2, c);;

			c.gridy = linhaAtual++;
			
			c.gridx = 0;
			this.add(Media, c);
			c.gridx = 1;
			this.add(txtMedia, c);

			c.gridy = linhaAtual++;
			
			c.gridx = 0;
			this.add(Faltas, c);
			c.gridx = 1;
			this.add(txtFaltas, c);
		}
	}

	public class PainelSelecaoEmListas extends JPanel {

		private JList listaSelecionados;
		private JList listaNaoSelecionados;
		private JLabel labelSelecionados;
		private JLabel labelDeselecionados;
		private JButton botaoSelecionaTodos;
		private JButton botaoDeselecionaTodos;
		private JButton botaoSelecionaUm;
		private JButton botaoDeselecionaUm;
		
	

		public PainelSelecaoEmListas() {
			this.add(montaPainelSelecao());
		}

		private JPanel montaPainelSelecao() {
			JPanel painel = new JPanel();
			BoxLayout layout = new BoxLayout(painel, BoxLayout.LINE_AXIS);
			painel.setLayout(layout);
			
			painel.add(montaPainelListaEsquerda());
			painel.add(montaPainelBotoes());
			painel.add(montaPainelListaDireita());
			return painel;
		}

		private JPanel montaPainelListaEsquerda() {
			JPanel painelListaEsquerda = new JPanel();
			BoxLayout layout = new BoxLayout(painelListaEsquerda, BoxLayout.PAGE_AXIS);
			painelListaEsquerda.setLayout(layout);

			labelDeselecionados = new JLabel("Disciplinas");
			DefaultListModel listModel = new DefaultListModel();
			TipoDisciplina[] tipos = TipoDisciplina.values();
			for (TipoDisciplina tipo : tipos) {
				listModel.addElement(tipo.getDescricao());
			}
			listaNaoSelecionados = new JList(listModel);

			listaNaoSelecionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scrollLista = new JScrollPane(listaNaoSelecionados);

			painelListaEsquerda.add(labelDeselecionados);
			painelListaEsquerda.add(Box.createRigidArea(new Dimension(0, 5)));
			painelListaEsquerda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			painelListaEsquerda.add(scrollLista);

			return painelListaEsquerda;
		}

		private JPanel montaPainelBotoes() {
			JPanel painelBotoes = new JPanel();
			BoxLayout layout = new BoxLayout(painelBotoes, BoxLayout.Y_AXIS);
			painelBotoes.setLayout(layout);

			botaoSelecionaTodos = new JButton(">>");

			botaoSelecionaTodos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultListModel modelNaoSelecionados = (DefaultListModel) listaNaoSelecionados.getModel();
					DefaultListModel modelSelecionados = (DefaultListModel) listaSelecionados.getModel();
					while (modelNaoSelecionados.size() > 0) {
						Object elementoRemovido = modelNaoSelecionados.remove(0);
						modelSelecionados.addElement(elementoRemovido);
					}
				}
			});
			botaoDeselecionaTodos = new JButton("<<");
			botaoDeselecionaTodos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DefaultListModel modelNaoSelecionados = (DefaultListModel) listaNaoSelecionados.getModel();
					DefaultListModel modelSelecionados = (DefaultListModel) listaSelecionados.getModel();
					while (modelSelecionados.size() > 0) {
						Object elementoRemovido = modelSelecionados.remove(0);
						modelNaoSelecionados.addElement(elementoRemovido);
					}
				}
			});
			botaoSelecionaUm = new JButton(" > ");
			botaoSelecionaUm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean selecionou = listaNaoSelecionados.getSelectedIndex() >= 0;
					if (selecionou) {
						DefaultListModel modelNaoSelecionados = (DefaultListModel) listaNaoSelecionados.getModel();
						DefaultListModel modelSelecionados = (DefaultListModel) listaSelecionados.getModel();
						Object elementoSelecionado = modelNaoSelecionados
								.getElementAt(listaNaoSelecionados.getSelectedIndex());
						modelNaoSelecionados.removeElement(elementoSelecionado);
						modelSelecionados.insertElementAt(elementoSelecionado, modelSelecionados.size());
					}
				}
			});

			botaoDeselecionaUm = new JButton(" < ");
			botaoDeselecionaUm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean selecionou = listaSelecionados.getSelectedIndex() >= 0;
					if (selecionou) {
						DefaultListModel modelNaoSelecionados = (DefaultListModel) listaNaoSelecionados.getModel();
						DefaultListModel modelSelecionados = (DefaultListModel) listaSelecionados.getModel();
						Object elementoSelecionado = modelSelecionados
								.getElementAt(listaSelecionados.getSelectedIndex());
						modelSelecionados.removeElement(elementoSelecionado);
						modelNaoSelecionados.insertElementAt(elementoSelecionado, modelNaoSelecionados.size());
					}
				}
			});
			botaoSelecionaTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
			botaoDeselecionaTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
			botaoSelecionaUm.setAlignmentX(Component.CENTER_ALIGNMENT);
			botaoDeselecionaUm.setAlignmentX(Component.CENTER_ALIGNMENT);

			painelBotoes.add(botaoSelecionaTodos);
			painelBotoes.add(botaoDeselecionaTodos);
			painelBotoes.add(botaoSelecionaUm);
			painelBotoes.add(botaoDeselecionaUm);
			return painelBotoes;
		}

		private JPanel montaPainelListaDireita() {
			JPanel painelListaDireita = new JPanel();
			BoxLayout layout = new BoxLayout(painelListaDireita, BoxLayout.PAGE_AXIS);
			painelListaDireita.setLayout(layout);
			labelSelecionados = new JLabel("Disciplinas Selecionada");
			DefaultListModel listModel = new DefaultListModel();
			listaSelecionados = new JList(listModel);
			listaSelecionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollLista = new JScrollPane(listaSelecionados);

			painelListaDireita.add(labelSelecionados);
			painelListaDireita.add(Box.createRigidArea(new Dimension(0, 5)));
			painelListaDireita.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			painelListaDireita.add(scrollLista);

			return painelListaDireita;
		}

	}

}
