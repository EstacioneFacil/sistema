package util;

import java.awt.Component;

/**
 *
 * @author Douglas
 */
public class ExceptionUtils {
    
    public static void mostrarErro(Component component, String mensagem) {
        MensageiroUtils.mensagemErro(component, mensagem);
    }
}
