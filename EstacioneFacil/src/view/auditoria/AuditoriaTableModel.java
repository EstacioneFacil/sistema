package view.auditoria;

import java.util.Date;
import java.util.List;
import model.Auditoria;
import util.FormatacaoUtils;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class AuditoriaTableModel extends TableModel {

    private List<Auditoria> dados;
    private String[] colunas = {"Data/Hora", "Tabela", "Tipo", "Usuário", "ID"};

    public AuditoriaTableModel(List<Auditoria> auditorias) {
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
                return FormatacaoUtils.getDataHoraString((Date) dados.get(rowIndex).getDataHora());
            case 1:
                return dados.get(rowIndex).getTabela().toUpperCase();
            case 2:
                return dados.get(rowIndex).getAcao();
            case 3:
                return dados.get(rowIndex).getUsuario().getNome();
            case 4:
                return dados.get(rowIndex).getId();
        }
        return null;
    }

    public List<Auditoria> getDados() {
        return dados;
    }

    public void setDados(List<Auditoria> dados) {
        this.dados = dados;
    }
}
