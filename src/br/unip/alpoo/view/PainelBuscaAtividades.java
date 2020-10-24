package br.unip.alpoo.view;

import br.unip.alpoo.model.AtividadeComplementar;
import br.unip.alpoo.model.GerenciadorDeCursos;
import br.unip.alpoo.model.TipoAtividade;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

public class PainelBuscaAtividades extends JPanel {

    private JLabel lblFiltro;
    private JComboBox cboFiltro;
    private JButton btnFiltro;
    private JTable tabelaAtividades;
    private JButton btnSelecionar;
    private JButton btnExcluir;

    private GerenciadorDeCursos gerenciador;

    //implementação do Singleton
    private static PainelBuscaAtividades instance;

    private PainelBuscaAtividades() {
        this.gerenciador = GerenciadorDeCursos.getInstance();

        this.setLayout(new BorderLayout());
        JPanel painelFiltro = montaPainelFiltro();
        JScrollPane painelTabela = montaPainelTabela();
        JPanel painelBotoes = montaPainelBotoes();

        this.add(painelFiltro, BorderLayout.NORTH);
        this.add(painelTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);

    }

    public static PainelBuscaAtividades getInstance() {
        if (instance == null) {
            instance = new PainelBuscaAtividades();
        }
        return instance;
    }
    //implementação do Singleton

    private JPanel montaPainelFiltro() {
        JPanel painelFiltro = new JPanel();
        lblFiltro = new JLabel("Tipo");
        TipoAtividade[] tipos = TipoAtividade.values();
        cboFiltro = new JComboBox(tipos);
        cboFiltro.insertItemAt("TODOS", 0);
        cboFiltro.setSelectedIndex(0);
        btnFiltro = new JButton("Buscar");
        btnFiltro.setMnemonic(KeyEvent.VK_B);
        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaTabela();
            }

        });
        painelFiltro.add(lblFiltro);
        painelFiltro.add(cboFiltro);
        painelFiltro.add(btnFiltro);
        return painelFiltro;
    }

    private JPanel montaPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setMnemonic(KeyEvent.VK_S);
        btnSelecionar.addActionListener(new SelecionarListener());
        btnExcluir = new JButton("Excluir");
        btnExcluir.setMnemonic(KeyEvent.VK_X);
        btnExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnExcluir.setEnabled(false);
                // SalvarAtividade que depende de recurso (BD) e pode demorar
                // chamando em uma thread diferente da EDT Event Dispatcher Thread
                Thread threadDao = new Thread(new ExcluirAtividade());
                threadDao.start();

            }

        });

        painel.add(btnSelecionar);
        painel.add(btnExcluir);
        return painel;
    }

    private JScrollPane montaPainelTabela() {
        TableModel model = null;
        try {
            model = new AtividadeTableModel(gerenciador.getTodas());
        } catch (DadosException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca",
                    "Busca", JOptionPane.ERROR_MESSAGE);
        }
        tabelaAtividades = new JTable(model);
        tabelaAtividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabelaAtividades);
        return scroll;
    }

    void atualizaTabela() {
        new Thread() {
            @Override
            public void run() {
                Object itemSelecionado = cboFiltro.getSelectedItem();
                TipoCurso tipoSelecionado = null;
                if (itemSelecionado instanceof TipoCurso) {
                    tipoSelecionado
                            = (TipoCurso) itemSelecionado;
                }
                try {
                    List<AtividadeComplementar> listaFiltrada
                            = gerenciador.getPorTipo(tipoSelecionado);
                    AtividadeTableModel model = (AtividadeTableModel) tabelaAtividades.getModel();
                    model.setAtividades(listaFiltrada);
                    //Atualizando a tela na EDT Event Dispatcher Thread
                    // por meio da classe SwingUtilities
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            tabelaAtividades.repaint();
                            tabelaAtividades.revalidate();
                        }
                    });
                } catch (DadosException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca",
                            "Busca", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();
    }

    private AtividadeComplementar getAtividadeSelecionada() {
        int linhaSelecionada = tabelaAtividades.getSelectedRow();
        AtividadeComplementar atividadeSelecionada = null;
        if (linhaSelecionada >= 0) {
            AtividadeTableModel model
                    = (AtividadeTableModel) tabelaAtividades.getModel();
            atividadeSelecionada = model.getAtividade(linhaSelecionada);
        } else {
            atividadeSelecionada = null;
        }
        return atividadeSelecionada;
    }

    private class SelecionarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AtividadeComplementar atividadeSelecionada = getAtividadeSelecionada();
            if (atividadeSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha",
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            PainelCadastroAtividade.getInstance().setAtividade(atividadeSelecionada);
        }
    }

    private class ExcluirAtividade implements Runnable {

        @Override
        public void run() {
            AtividadeComplementar atividade = getAtividadeSelecionada();
            if (atividade == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma atividade",
                        "Selecione", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String mensagem = "Confirma a exclusão da atividade "
                    + atividade.getId() + "?";
            int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            try {
                if (opcao == JOptionPane.OK_OPTION) {
                    gerenciador.excluir(atividade);
                    atualizaTabela();
                }
            } catch (DadosException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a exclusão",
                        "Exclusão", JOptionPane.ERROR_MESSAGE);
            }
            //Atualizando a tela na EDT Event Dispatcher Thread
            // por meio da classe SwingUtilities
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    btnExcluir.setEnabled(true);
                }
            });
        }
    }
}
