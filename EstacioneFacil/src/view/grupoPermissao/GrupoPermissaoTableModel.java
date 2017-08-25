package view.grupoPermissao;

import java.util.List;
import model.GrupoPermissao;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class GrupoPermissaoTableModel extends TableModel {

    private List<GrupoPermissao> dados;
    private String[] colunas = {"Descrição", "ID"};

    public GrupoPermissaoTableModel(List<GrupoPermissao> gruposPermissao) {
        this.dados = gruposPermissao;
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

    public List<GrupoPermissao> getDados() {
        return dados;
    }

    public void setDados(List<GrupoPermissao> dados) {
        this.dados = dados;
    }
}
