package view.classes;

import config.ConfiguracaoSistema;
import dao.PermissaoBotaoDao;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.vo.PermissaoBotaoVO;
import util.MensageiroUtils;

/**
 *
 * @author Douglas
 */
public class JDialogLista<Entity> extends JDialogView {
        
    private PermissaoBotaoDao permissaoBotaoDao;
    
    public JDialogLista(String nome) {
        super(nome);
        this.permissaoBotaoDao = new PermissaoBotaoDao();
    }
    
    public void definirPermissoes(JButton... botoes) {
        String tela = getClass().getName();
        List<PermissaoBotaoVO> permissaoBotoes = permissaoBotaoDao.buscarPermissoesPorTela(tela);
        for (int i = 0; i < botoes.length; i++) {
            for (PermissaoBotaoVO permissaoBotaoVO : permissaoBotoes) {
                if (permissaoBotaoVO.getNome().equals(botoes[i].getName())) {
                    botoes[i].setEnabled(permissaoBotaoVO.isPermissao());
                    botoes[i].setToolTipText("Você não tem permissão para acessar este conteúdo!");
                }
            }
        }
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
