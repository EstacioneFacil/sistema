package view.area;

import java.util.List;
import model.Area;
import model.Area;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class AreaTableModel extends TableModel {

    private List<Area> dados;
    private String[] colunas = {"Descriçao", "ID"};

    public AreaTableModel(List<Area> areas) {
        this.dados = areas;
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
                return dados.get(rowIndex).getDescricao();
            case 1:
                return dados.get(rowIndex).getId();            
        }
        return null;
    }

    public List<Area> getDados() {
        return dados;
    }

    public void setDados(List<Area> dados) {
        this.dados = dados;
    }
}
