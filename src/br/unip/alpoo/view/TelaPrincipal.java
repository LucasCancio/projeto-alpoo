package br.unip.alpoo.view;

import br.unip.alpoo.dao.JpaUtil;
import br.unip.alpoo.model.GerenciadorDeCursos;
import br.unip.alpoo.view.cadastro.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame implements WindowListener{

    private PainelBuscaAtividades painelBuscaAtividades;
    
    private GerenciadorDeCursos gerenciador;

    //implementacao do padrao Singleton
    private static TelaPrincipal instance;
    
    private TelaPrincipal() {
        
        gerenciador = GerenciadorDeCursos.getInstance();

        this.setTitle("Controle de Atividades");
        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(this);

        painelBuscaAtividades = PainelBuscaAtividades.getInstance();

        this.add(painelBuscaAtividades, BorderLayout.CENTER);
        this.setJMenuBar(montaMenu());

        this.setVisible(true);

    }

    public static TelaPrincipal getInstance() {
        if (instance == null) {
            instance = new TelaPrincipal();
        }
        return instance;
    }

    
    //implementacao do padrao Singleton
   
    private JMenuBar montaMenu() {
        JMenuBar barraMenu = new JMenuBar();
        
        
        JMenu menuCadastro = new JMenu("Cadastrar");
        menuCadastro.setMnemonic(KeyEvent.VK_T);
        
        var itemsCadastro= new HashMap<String, JFrame>();
        itemsCadastro.put("Curso", CadastroCurso.getInstance());
        itemsCadastro.put("Disciplina", CadastroDisciplina.getInstance());
        itemsCadastro.put("Aluno", CadastroAluno.getInstance());
        itemsCadastro.put("Professor", CadastroProfessor.getInstance());
        
        JMenu menuMostrar = new JMenu("Mostrar");
        menuMostrar.setMnemonic(KeyEvent.VK_A);
        
        JMenu menuSair = new JMenu("Sair");
        menuSair.setMnemonic(KeyEvent.VK_C);
            
        JMenuItem itemSair = new JMenuItem("Encerrar o aplicativo");
        itemSair.setMnemonic(KeyEvent.VK_S);
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int opcao = JOptionPane.showConfirmDialog(null,
                        "Alerta!", "Deseja encerrar o aplicativo?", JOptionPane.YES_NO_OPTION);
            	if(opcao == JOptionPane.YES_OPTION) {
            		System.exit(1);
            	}
            }
        });
        
        menuCadastro.add(itemSair);
        
        barraMenu.add(menuCadastro);
        barraMenu.add(menuMostrar);
        barraMenu.add(menuSair);
        return barraMenu;
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaPrincipal tela = TelaPrincipal.getInstance();
            }
        });
    }
    
    public void montaItemsMenu(JMenu menu,HashMap<String, JFrame> items) {
    	items.forEach((name,frame)->{
    		
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
        JpaUtil.fechar();
    }

    @Override
    public void windowIconified(WindowEvent e){
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
