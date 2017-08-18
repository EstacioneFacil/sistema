package util;

import config.ConfiguracaoSistema;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class MensageiroUtils {
    
    public static void mensagemAlerta(Component component, String msg) {
       mensagem(component, msg, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void mensagemErro(Component component, String msg) {
        mensagem(component, msg, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void mensagemSucesso(Component component, String msg) {
        mensagem(component, msg, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void mensagem(Component component, String msg, int icone) {
        JOptionPane.showMessageDialog(component, msg, ConfiguracaoSistema.NOME, icone); 
    }
}
