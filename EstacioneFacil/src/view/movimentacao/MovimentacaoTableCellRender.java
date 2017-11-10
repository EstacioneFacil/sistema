package view.movimentacao;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Douglas
 */
public class MovimentacaoTableCellRender extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        if (column == 0 || column == 4 || column == 5 || column == 7) {
            setHorizontalAlignment(CENTER);
        } else if (column == 6) {
            setHorizontalAlignment(RIGHT);
        } else {
            setHorizontalAlignment(LEFT);
        }
        return this;
    }
}
