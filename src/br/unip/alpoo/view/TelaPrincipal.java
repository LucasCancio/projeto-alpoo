package br.unip.alpoo.view;

import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.view.cadastro.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame implements WindowListener {

	// private PainelBuscaAtividades painelBuscaAtividades;

	// private GerenciadorDeCursos gerenciador;

	// implementacao do padrao Singleton
	private static TelaPrincipal instance;

	private TelaPrincipal() {

		// gerenciador = GerenciadorDeCursos.getInstance();

		this.setTitle("Controle de Atividades");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(this);

		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
		this.setJMenuBar(montaMenu());

		this.setVisible(true);

	}

	public static TelaPrincipal getInstance() {
		if (instance == null) {
			instance = new TelaPrincipal();
		}
		return instance;
	}

	// implementacao do padrao Singleton

	private JMenuBar montaMenu() {
		JMenuBar barraMenu = new JMenuBar();

		JMenu menuCadastro = new JMenu("Cadastrar");
		menuCadastro.setMnemonic(KeyEvent.VK_T);

		var itemsCadastro = new LinkedHashMap<String, JFrame>();
		itemsCadastro.put("Curso", new CadastroCurso(Operacoes.CADASTRO));
		itemsCadastro.put("Disciplina", new CadastroDisciplina(Operacoes.CADASTRO));
		itemsCadastro.put("Aluno", new CadastroAluno(Operacoes.CADASTRO));
		itemsCadastro.put("Professor", new CadastroProfessor(Operacoes.CADASTRO));
		montaItemsMenu(menuCadastro, itemsCadastro);

		JMenu menuMostrar = new JMenu("Mostrar");
		menuMostrar.setMnemonic(KeyEvent.VK_A);

		var itemsMostra = new LinkedHashMap<String, JFrame>();
		itemsMostra.put("Curso", new CadastroCurso(Operacoes.CONSULTA));
		itemsMostra.put("Disciplina", new CadastroDisciplina(Operacoes.CONSULTA));
		itemsMostra.put("Aluno", new CadastroAluno(Operacoes.CONSULTA));
		itemsMostra.put("Professor", new CadastroProfessor(Operacoes.CONSULTA));
		
		itemsMostra.put("Cursos/Professores", new CadastroProfessor(Operacoes.CONSULTA));
		itemsMostra.put("Cursos/Disciplinas", new CadastroProfessor(Operacoes.CONSULTA));
		itemsMostra.put("Professores/Disciplinas", new CadastroProfessor(Operacoes.CONSULTA));
		itemsMostra.put("Aluno/Disciplina", new CadastroProfessor(Operacoes.CONSULTA));
		montaItemsMenu(menuMostrar, itemsMostra);

		JMenu menuSair = new JMenu("Sair");
		menuSair.setMnemonic(KeyEvent.VK_C);

		JMenuItem itemSair = new JMenuItem("Encerrar o aplicativo");
		itemSair.setMnemonic(KeyEvent.VK_S);
		itemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja encerrar o aplicativo?", "Alerta!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcao == JOptionPane.YES_OPTION) {
					System.exit(1);
				}
			}
		});

		menuSair.add(itemSair);

		barraMenu.add(menuCadastro);
		barraMenu.add(menuMostrar);
		barraMenu.add(menuSair);
		return barraMenu;
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TelaPrincipal tela = TelaPrincipal.getInstance();
				/*
				 * CadastroCurso tela= CadastroCurso.getInstance(); tela.setVisible(true);
				 * tela.setDefaultCloseOperation(EXIT_ON_CLOSE);
				 */
			}
		});
	}

	public void montaItemsMenu(JMenu menu, LinkedHashMap<String, JFrame> items) {
		items.forEach((name, frame) -> {

			var menuItem = new JMenuItem(name);

			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
				}
			});

			menu.add(menuItem);
		});
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("Minimizou");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
