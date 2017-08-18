package view.classes;

import config.ConfiguracaoSistema;
import javax.swing.JOptionPane;
import util.MensageiroUtils;

/**
 *
 * @author Douglas
 */
public class JDialogLista<Entity> extends JDialogView {
    
    public JDialogLista(String nome) {
        super(nome);
    }
    
    public boolean excluir(Entity entity) {
        if (entity == null) {
            MensageiroUtils.mensagemAlerta(this, ConfiguracaoSistema.MSG_SELECIONAR);
            return false;
        } 
        int opcao = JOptionPane.showConfirmDialog(this, ConfiguracaoSistema.MSG_EXCLUIR, ConfiguracaoSistema.NOME, JOptionPane.YES_NO_OPTION);
        return opcao == JOptionPane.YES_OPTION;
    }
    
    public void mostrarMensagemEditar() {
        MensageiroUtils.mensagemAlerta(this, ConfiguracaoSistema.MSG_EDITAR);
    }
    
    public void mostrarMensagemExcluido() {
        MensageiroUtils.mensagemSucesso(this, ConfiguracaoSistema.MSG_REGISTRO_EXCLUIR_SUCESSO);
    }
}
