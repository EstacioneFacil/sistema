package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.Logger;
import model.util.ExceptionUtils;
import view.classes.JDialogLista;

/**
 *
 * @author Douglas
 */
public class MenuAcoes implements ActionListener {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    private String tela;

    public MenuAcoes(String tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JDialogLista classeLista = (JDialogLista) Class.forName(tela).newInstance();
            classeLista.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("Nao foi possível abrir a tela", ex);
            ExceptionUtils.mostrarErro(null, "Não foi possível abrir a tela!");
        }
    }
}
