package view.usuario;

import java.util.List;
import model.Usuario;
import view.classes.TableModel;

/**
 *
 * @author Douglas
 */
public class UsuarioTableModel extends TableModel {

    private List<Usuario> dados;
    private String[] colunas = {"Nome", "Login", "Grupo de Permissão", "ID"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.dados = usuarios;
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
                return dados.get(rowIndex).getNome();
            case 1:
                return dados.get(rowIndex).getLogin();
            case 2:
                return dados.get(rowIndex).getGrupoPermissao().getDescricao();
            case 3:
                return dados.get(rowIndex).getId();
        }
        return null;
    }

    public List<Usuario> getDados() {
        return dados;
    }

    public void setDados(List<Usuario> dados) {
        this.dados = dados;
    }
}
