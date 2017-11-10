package view.movimentacao;

import java.util.Date;
import java.util.List;
import model.Movimentacao;
import model.TabelaPreco;
import model.Vaga;
import model.constant.TipoPrecoEnum;
import model.util.FormatacaoUtils;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class MovimentacaoTableModel extends TableModel {

    private List<Movimentacao> dados;
    private String[] colunas = {"Placa", "Vaga", "Área", "Tipo de Veículo", "Data Entrada", "Data Saída", "Valor", "ID"};

    public MovimentacaoTableModel(List<Movimentacao> movimentacoes) {
        this.dados = movimentacoes;
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
                return FormatacaoUtils.formatarPlaca(dados.get(rowIndex).getPlaca());
            case 1:
                Vaga vaga = dados.get(rowIndex).getVaga();
                return vaga != null ? vaga.getCodigo() : null;
            case 2:
                return dados.get(rowIndex).getVaga().getArea().getDescricao();
            case 3:
                return dados.get(rowIndex).getVaga().getTipoVeiculo().getDescricao();
            case 4:
                return FormatacaoUtils.getDataHoraString(dados.get(rowIndex).getDataHoraEntrada());
            case 5:
                Date dataSaida = dados.get(rowIndex).getDataHoraSaida();
                return dataSaida != null ? FormatacaoUtils.getDataHoraString(dataSaida) : null;
            case 6:
                Double valor = dados.get(rowIndex).getValor();
                return valor != null ? FormatacaoUtils.formatarStringValor(valor) : null;
            case 7:
                return dados.get(rowIndex).getId();
        }
        return null;
    }

    public List<Movimentacao> getDados() {
        return dados;
    }

    public void setDados(List<Movimentacao> dados) {
        this.dados = dados;
    }
}
