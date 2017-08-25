package view.classes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.vo.SelectItemVO;


/**
 *
 * @author Douglas
 */
public class ComboModel implements ComboBoxModel {

    private List<SelectItemVO> lista;
    private SelectItemVO select;

    public ComboModel() {
        super();
        this.lista = new ArrayList<>();
    }

    public ComboModel(List<SelectItemVO> lista) {
        super();
        this.lista = new ArrayList<>();
        if (lista != null && !lista.isEmpty()) {
            this.lista.addAll(lista);
        }
    }

    @Override
    public SelectItemVO getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public SelectItemVO getSelectedItem() {
        return select;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        select = (SelectItemVO) anItem;
    }
    
    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
    
    public void setSelectedIndex(int index) {
        select = (SelectItemVO) lista.get(index);
    }

    public void setLista(List<SelectItemVO> lista) {
        this.lista = lista;
    }
}
