package view.auditoria;

import java.util.Date;
import java.util.List;
import model.Auditoria;
import model.AuditoriaDetalhe;
import model.util.FormatacaoUtils;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class AuditoriaDetalheTableModel extends TableModel {

    private List<AuditoriaDetalhe> dados;
    private String[] colunas = {"Coluna", "Valor antigo", "Valor novo"};

    public AuditoriaDetalheTableModel(List<AuditoriaDetalhe> auditorias) {
        this.dados = auditorias;
    }

    @Override
    public int getRowCount() {
        if (dados != null) {
            return dados.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) { 
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getColuna();
            case 1:
                return dados.get(rowIndex).getValorantigo();
            case 2:
                return dados.get(rowIndex).getValornovo();            
        }
        return null;
    }

    public List<AuditoriaDetalhe> getDados() {
        return dados;
    }

    public void setDados(List<AuditoriaDetalhe> dados) {
        this.dados = dados;
    }
}
