package view.classes;

import config.ConfiguracaoSistema;
import javax.swing.ImageIcon;

/**
 *
 * @author Douglas
 */
public class JDialogView extends javax.swing.JDialog {
        
    private Object cadastroAnterior;
    
    public JDialogView() {
        this.setResizable(false);
        this.setModal(true);
        this.setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        this.setTitle(ConfiguracaoSistema.NOME);
    }
    
    public JDialogView(String nome) {
        this.setResizable(false);
        this.setModal(true);
        this.setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        this.setTitle(ConfiguracaoSistema.NOME + " - " + nome);
    }

    public Object getCadastroAnterior() {
        return cadastroAnterior;
    }

    public void setCadastroAnterior(Object cadastroAnterior) {
        this.cadastroAnterior = cadastroAnterior;
    }
}
