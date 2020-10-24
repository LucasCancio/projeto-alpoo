package br.unip.alpoo.view;

import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.GerenciadorDecursos;
import br.unip.alpoo.model.TipoCurso;
import br.unip.alpoo.util.DadosException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class PainelCadastrocurso extends JPanel {

    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblTipo;
    private JComboBox cboTipo;
    
    private JLabel lblCargaHoraria;
    private JSpinner spiCargaHoraria;
    
    private JLabel lblIdInstituto;
    private JTextField txtIdInstituto;
    
    private JButton btnSalvar;
    private JButton btnCancelar;

    private GerenciadorDecursos gerenciador;

    private Curso curso;

    //implementação do Singleton
    private static PainelCadastrocurso instance;

    private PainelCadastrocurso() {

        gerenciador = GerenciadorDecursos.getInstance();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        JPanel painelCadastro = montaPainelCadastro();
        JPanel painelBotoes = montaPainelBotoes();
        this.add(painelCadastro, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
    }

    public static PainelCadastrocurso getInstance() {
        if (instance == null) {
            instance = new PainelCadastrocurso();
        }
        return instance;
    }

    //implementação do Singleton
    private JPanel montaPainelCadastro() {
        JPanel painelCadastro = new JPanel();
        GridLayout layout = new GridLayout(6, 2);
        painelCadastro.setLayout(layout);

        lblId = new JLabel("Id:");
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setColumns(10);
        lblNome = new JLabel("Titulo:");
        txtNome = new JTextField();
        
        lblTipo = new JLabel("Tipo:");
        TipoCurso[] tipos = TipoCurso.values();
        cboTipo = new JComboBox(tipos);
     
        lblCargaHoraria = new JLabel("Carga Hor�ria:");
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 100, 5);
        spiCargaHoraria = new JSpinner(spinnerNumberModel);

        painelCadastro.add(lblId);
        painelCadastro.add(txtId);
        painelCadastro.add(lblNome);
        painelCadastro.add(txtNome);
        painelCadastro.add(lblTipo);
        painelCadastro.add(cboTipo);
        painelCadastro.add(lblCargaHoraria);
        painelCadastro.add(spiCargaHoraria);
        return painelCadastro;
    }

    private JPanel montaPainelBotoes() {
        JPanel painelBotoes = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        painelBotoes.setLayout(layout);
        btnSalvar = new JButton("Salvar");
        btnSalvar.setMnemonic(KeyEvent.VK_S);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalvar.setEnabled(false);
                // Salvarcurso que depende de recurso (BD) e pode demorar
                // chamando em uma thread diferente da EDT Event Dispatcher Thread
                Thread threadDao = new Thread(new Salvarcurso());
                threadDao.start();
            }
        });

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic(KeyEvent.VK_C);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setcurso(curso);
            }
        });
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        return painelBotoes;
    }

    void setcurso(Curso curso) {
        this.curso = curso;
        txtId.setText(curso.getId().toString());
        txtTitulo.setText(curso.getTitulo());
        cboTipo.setSelectedItem(curso.getTipo());
        txtData.setValue(curso.getDataRealizacao());
        spiQtde.setValue(curso.getQtdeHoras());
        cboSituacao.setSelectedItem(curso.getSituacao());
    }

    private Curso getcursoAlterada() {
        Curso cursoAlterada = new Curso();
        cursoAlterada.setId(Integer.parseInt(txtId.getText()));
        cursoAlterada.setTitulo(txtTitulo.getText());
        Tipocurso tipo = (Tipocurso) cboTipo.getSelectedItem();
        cursoAlterada.setTipo(tipo);
        Date data = (Date) txtData.getValue();
        cursoAlterada.setDataRealizacao(data);
        Integer qtde = (Integer) spiQtde.getValue();
        cursoAlterada.setQtdeHoras(qtde);
        Situacao situacao = (Situacao) cboSituacao.getSelectedItem();
        cursoAlterada.setSituacao(situacao);
        return cursoAlterada;
    }

    private class Salvarcurso implements Runnable {

        @Override
        public void run() {
            Curso cursoAlterada = getcursoAlterada();
            try {
                gerenciador.salvar(cursoAlterada);
            } catch (DadosException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível confirmar a operação",
                        "Confirmação", JOptionPane.ERROR_MESSAGE);
            }
            curso = cursoAlterada;
            PainelBuscacursos.getInstance().atualizaTabela();
            //Atualizando a tela na EDT Event Dispatcher Thread
            // por meio da classe SwingUtilities
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    btnSalvar.setEnabled(true);
                    txtId.setText(curso.getId().toString());
                }
            });
        }
    }
}
