package br.unip.alpoo.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import br.unip.alpoo.model.Curso;
import br.unip.alpoo.model.TipoCurso;

public class CursoTableModel implements TableModel{
    
    private List<Curso> cursos;

    public CursoTableModel(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public int getRowCount() {
        return cursos.size();
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
            case 2: return TipoCurso.class;
            case 3: return String.class;
            case 4: return int.class;
        }
        return void.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Curso cursoAtual = cursos.get(linha);
        /*switch(coluna){
            case 0: return cursoAtual.getId();
            case 1: return cursoAtual.getTitulo();
            case 2: return cursoAtual.getTipo();
            case 3: Date data = cursoAtual.getDataRealizacao();
                    return DateFormat.getDateInstance().format(data);
            case 4: return cursoAtual.getQtdeHoras();
        }*/
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
    
   /* Curso getAtividade(int linha){
        return cursos.get(linha);
    }

    void setcursos(List<Curso> cursos) {
        this.cursos = cursos;
    }*/
    
}
