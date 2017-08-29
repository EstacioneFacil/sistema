package view.permissao;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Permissao;

/**
 *
 * @author Douglas
 */
public class PermissaoTableModel extends AbstractTableModel {

    private List<Permissao> dados;
    private String[] colunas = {"Tela", "Visualizar", "Permissões da tela"};

    public PermissaoTableModel(List<Permissao> permissoes) {
        this.dados = permissoes;
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
        if (columnIndex == 1) {
            return Boolean.class;
        }
        return Object.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getMenu().getDescricao();
            case 1:
                return dados.get(rowIndex).isVisualizar();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Permissao permissao = dados.get(rowIndex);
        if (columnIndex == 1) {
            permissao.setVisualizar(!permissao.isVisualizar());
        }
    }

    public List<Permissao> getDados() {
        return dados;
    }

    public void setDados(List<Permissao> dados) {
        this.dados = dados;
    }
}
