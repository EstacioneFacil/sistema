package view.tipoVeiculo;

import java.util.List;
import model.TipoVeiculo;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class TipoVeiculoTableModel extends TableModel {

    private List<TipoVeiculo> dados;
    private String[] colunas = {"Descriçao", "ID"};

    public TipoVeiculoTableModel(List<TipoVeiculo> tiposVeiculo) {
        this.dados = tiposVeiculo;
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

    public List<TipoVeiculo> getDados() {
        return dados;
    }

    public void setDados(List<TipoVeiculo> dados) {
        this.dados = dados;
    }
}
