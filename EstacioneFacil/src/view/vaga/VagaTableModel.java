package view.vaga;

import java.util.List;
import model.Vaga;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class VagaTableModel extends TableModel {

    private List<Vaga> dados;
    private String[] colunas = {"Código", "Área", "Tipo de Veículo", "Descrição", "ID"};

    public VagaTableModel(List<Vaga> vagas) {
        this.dados = vagas;
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
                return dados.get(rowIndex).getCodigo();
            case 1:
                return dados.get(rowIndex).getArea().getDescricao();
            case 2:
                return dados.get(rowIndex).getTipoVeiculo().getDescricao();
            case 3:
                return "";
            case 4:
                return dados.get(rowIndex).getId();
        }
        return null;
    }

    public List<Vaga> getDados() {
        return dados;
    }

    public void setDados(List<Vaga> dados) {
        this.dados = dados;
    }
}
