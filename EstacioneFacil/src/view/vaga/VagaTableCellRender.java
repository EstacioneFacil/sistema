package view.vaga;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Douglas
 */
public class VagaTableCellRender extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        if (column == 4) {
            setHorizontalAlignment(CENTER);
        } else {
            setHorizontalAlignment(LEFT);
        }
        return this;
    }
}
