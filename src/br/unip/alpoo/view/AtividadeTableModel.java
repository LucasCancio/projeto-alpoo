package br.unip.alpoo.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import br.unip.alpoo.model.AtividadeComplementar;
import br.unip.alpoo.model.Situacao;
import br.unip.alpoo.model.TipoAtividade;

public class AtividadeTableModel implements TableModel{
    
    private List<AtividadeComplementar> atividades;

    public AtividadeTableModel(List<AtividadeComplementar> atividades) {
        this.atividades = atividades;
    }

    @Override
    public int getRowCount() {
        return atividades.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int coluna) {
        switch(coluna){
            case 0: return "Id";
            case 1: return "Titulo";
            case 2: return "Tipo";
            case 3: return "Data";
            case 4: return "Horas";
            case 5: return "Situação";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch(coluna){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return TipoAtividade.class;
            case 3: return String.class;
            case 4: return int.class;
            case 5: return Situacao.class;
        }
        return void.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        AtividadeComplementar atividadeAtual = atividades.get(linha);
        switch(coluna){
            case 0: return atividadeAtual.getId();
            case 1: return atividadeAtual.getTitulo();
            case 2: return atividadeAtual.getTipo();
            case 3: Date data = atividadeAtual.getDataRealizacao();
                    return DateFormat.getDateInstance().format(data);
            case 4: return atividadeAtual.getQtdeHoras();
            case 5: return atividadeAtual.getSituacao();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
    AtividadeComplementar getAtividade(int linha){
        return atividades.get(linha);
    }

    void setAtividades(List<AtividadeComplementar> atividades) {
        this.atividades = atividades;
    }
    
}
