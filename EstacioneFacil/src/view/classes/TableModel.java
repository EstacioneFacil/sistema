package view.classes;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas
 */
public abstract class TableModel extends AbstractTableModel {
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
