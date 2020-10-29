package br.unip.alpoo.view;

import br.unip.alpoo.model.Operacoes;
import br.unip.alpoo.view.cadastro.*;
import br.unip.alpoo.view.exibicao.MostrarAlunoDisciplinas;
import br.unip.alpoo.view.exibicao.MostrarCursosDisciplinas;
import br.unip.alpoo.view.exibicao.MostrarCursosProfessores;
import br.unip.alpoo.view.exibicao.MostrarProfessoresDisciplinas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame implements WindowListener {

	private static TelaPrincipal instance;

	private TelaPrincipal() {
		this.setTitle("FaculSystem - Trabalho LPOO");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(this);

		JLabel background = new JLabel(new ImageIcon("images/background.jpg"));
		this.add(background); 
		this.pack();
		// painelBuscaAtividades = PainelBuscaAtividades.getInstance();

		// this.add(painelBuscaAtividades, BorderLayout.CENTER);
		this.setJMenuBar(montaMenu());

		this.setVisible(true);
		this.setResizable(false);
	}

	public static TelaPrincipal getInstance() {
		if (instance == null) {
			instance = new TelaPrincipal();
		}
		return instance;
	}

	// implementacao do padrao Singleton

	public ImageIcon getScalledIcon(String path, int width, int heigth) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	private JMenuBar montaMenu() {
		ImageIcon cadastrarIcon = getScalledIcon("images/cadastrar.png",15,18);
		ImageIcon mostrarIcon = getScalledIcon("images/mostrar.png",15,18);
		ImageIcon sairIcon = getScalledIcon("images/sair.png",15,18);
		
		JMenuBar barraMenu = new JMenuBar();

		JMenu menuCadastro = new JMenu("Cadastrar");
		menuCadastro.setFont(new Font("Serif", Font.BOLD,21));
		menuCadastro.setMnemonic(KeyEvent.VK_T);

		var itemsCadastro = new LinkedHashMap<String, ActionListener>();
		itemsCadastro.put("Curso", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroCurso(Operacoes.CADASTRO);
			   frame.setVisible(true);
			}
		});
		itemsCadastro.put("Disciplina", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroDisciplina(Operacoes.CADASTRO);
			   frame.setVisible(true);
			}
		});
		itemsCadastro.put("Aluno", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroAluno(Operacoes.CADASTRO);
			   frame.setVisible(true);
			}
		});
		itemsCadastro.put("Professor", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroProfessor(Operacoes.CADASTRO);
			   frame.setVisible(true);
			}
		});
		montaItemsMenu(menuCadastro, itemsCadastro);
		menuCadastro.setIcon(cadastrarIcon);

		JMenu menuMostrar = new JMenu("Mostrar");
		menuMostrar.setFont(new Font("Serif", Font.BOLD,21));
		menuMostrar.setMnemonic(KeyEvent.VK_A);

		var itemsMostra = new LinkedHashMap<String, ActionListener>();
		itemsMostra.put("Curso", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroCurso(Operacoes.CONSULTA);
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Disciplina", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroDisciplina(Operacoes.CONSULTA);
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Aluno", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroAluno(Operacoes.CONSULTA);
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Professor", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new CadastroProfessor(Operacoes.CONSULTA);
			   frame.setVisible(true);
			}
		});
		
		itemsMostra.put("Cursos/Professores", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new MostrarCursosProfessores();
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Cursos/Disciplinas", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new MostrarCursosDisciplinas();
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Professores/Disciplinas", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new MostrarProfessoresDisciplinas();
			   frame.setVisible(true);
			}
		});
		itemsMostra.put("Aluno/Disciplina", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   var frame = new MostrarAlunoDisciplinas();
			   frame.setVisible(true);
			}
		});
		montaItemsMenu(menuMostrar, itemsMostra);
		menuMostrar.setIcon(mostrarIcon);

		JMenu menuSair = new JMenu("Sair");
		menuSair.setFont(new Font("Serif", Font.BOLD,21));
		menuSair.setForeground(Color.red);
		menuSair.setMnemonic(KeyEvent.VK_C);

		JMenuItem itemSair = new JMenuItem("Encerrar o aplicativo");
		itemSair.setMnemonic(KeyEvent.VK_S);
		itemSair.setFont(new Font("Serif", Font.PLAIN,18));
		
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

	public void montaItemsMenu(JMenu menu, LinkedHashMap<String, ActionListener> items) {
		items.forEach((name, action) -> {

			var menuItem = new JMenuItem(name);

			menuItem.addActionListener(action);
			menuItem.setFont(new Font("Serif", Font.PLAIN,18));
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
