package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import view.grupoPermissao.GrupoPermissaoLista;
import view.usuario.UsuarioLista;

/**
 *
 * @author Douglas
 */
public class MenuAcoes implements ActionListener {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    private String metodo;

    public MenuAcoes(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Method m = getClass().getDeclaredMethod(metodo, new Class<?>[]{});
            m.invoke(this, new Object[] {});
        } catch (NoSuchMethodException ex) {
            logger.error("Nao foi entrado o metodo "+metodo, ex);
        } catch (IllegalAccessException ex) {
            logger.error("O metodo "+metodo+" não está acessível", ex);
        } catch (InvocationTargetException ex) {
            logger.error("Ocorreu um erro ao chamar o metodo "+metodo, ex);
        }
    }

    private void menuGrupoPermissao() {
        new GrupoPermissaoLista().setVisible(true);
    }

    private void menuUsuario() {
        new UsuarioLista().setVisible(true);
    }
}
