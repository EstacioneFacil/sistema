package view.classes;

import config.ConfiguracaoSistema;
import model.util.MensageiroUtils;

/**
 *
 * @author Douglas
 */
public class JDialogCadastro extends JDialogView {
    
    public JDialogCadastro(String nome) {
        super("Cadastro de " + nome);
    }
    
    public void mostrarMensagemExistente() {
        MensageiroUtils.mensagemAlerta(this, ConfiguracaoSistema.MSG_REGISTRO_EXISTENTE);
    }
    
    public void mostrarMensagemSucesso() {
        MensageiroUtils.mensagemSucesso(this, ConfiguracaoSistema.MSG_REGISTRO_SUCESSO);
    }
}
