package view.permissao.botao;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PermissaoBotao;

/**
 *
 * @author Douglas
 */
public class PermissaoBotaoTableModel extends AbstractTableModel {

    private List<PermissaoBotao> dados;
    private String[] colunas = {"Botão", "Permissão"};

    public PermissaoBotaoTableModel(List<PermissaoBotao> permissaoBotao) {
        this.dados = permissaoBotao;
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
                return dados.get(rowIndex).getMenuBotao().getBotao().getDescricao();
            case 1:
                return dados.get(rowIndex).isTemPermissao();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PermissaoBotao permissaoBotao = dados.get(rowIndex);
        if (columnIndex == 1) {
            permissaoBotao.setTemPermissao(!permissaoBotao.isTemPermissao());
        }
    }

    public List<PermissaoBotao> getDados() {
        return dados;
    }

    public void setDados(List<PermissaoBotao> dados) {
        this.dados = dados;
    }
}
