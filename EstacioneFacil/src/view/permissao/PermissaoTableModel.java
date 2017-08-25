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
    private String[] colunas = {"Tela", "Visualizar", "Inserir", "Editar", "Excluir"};

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
        if (columnIndex == 0) {
            return Object.class;
        }
        return Boolean.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getMenu().getDescricao();
            case 1:
                return dados.get(rowIndex).isVisualizar();
            case 2:
                return dados.get(rowIndex).isInserir();
            case 3:
                return dados.get(rowIndex).isEditar();
            case 4:
                return dados.get(rowIndex).isExcluir();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Permissao permissao = dados.get(rowIndex);
        if (columnIndex == 1) {
            permissao.setVisualizar(!permissao.isVisualizar());
        } else if (columnIndex == 2) {
            permissao.setInserir(!permissao.isInserir());
        } else if (columnIndex == 3) {
            permissao.setEditar(!permissao.isEditar());
        } else if (columnIndex == 4) {
            permissao.setExcluir(!permissao.isExcluir());
        }
    }

    public List<Permissao> getDados() {
        return dados;
    }

    public void setDados(List<Permissao> dados) {
        this.dados = dados;
    }
}
