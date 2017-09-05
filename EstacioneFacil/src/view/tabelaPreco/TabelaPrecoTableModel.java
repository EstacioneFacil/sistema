package view.tabelaPreco;

import java.util.Date;
import java.util.List;
import model.TabelaPreco;
import util.DateUtils;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class TabelaPrecoTableModel extends TableModel {

    private List<TabelaPreco> dados;
    private String[] colunas = {"Data de Início", "Tipo de Veículo", "Área", "Vaga", "Tipo", "Valor", "Data de Fim", "ID"};

    public TabelaPrecoTableModel(List<TabelaPreco> tabelasPreco) {
        this.dados = tabelasPreco;
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
                return DateUtils.getDataString((Date) dados.get(rowIndex).getDataInicio());
            case 1:
                return dados.get(rowIndex).getTipoVeiculo().getDescricao();
            case 2:
                return dados.get(rowIndex).getArea().getDescricao();
            case 3:
                return dados.get(rowIndex).getVaga().getCodigo();
            case 4:
                return dados.get(rowIndex).getTipo();
            case 5:
                return dados.get(rowIndex).getValor();
            case 6:
                return DateUtils.getDataString((Date) dados.get(rowIndex).getDataFim());
            case 7:
                return dados.get(rowIndex).getId();
        }
        return null;
    }

    public List<TabelaPreco> getDados() {
        return dados;
    }

    public void setDados(List<TabelaPreco> dados) {
        this.dados = dados;
    }
}
