package config;

import java.io.File;
import model.Usuario;

/**
 *
 * @author Douglas
 */
public class ConfiguracaoSistema {

    public static final String ICONE = "/view/imagens/logo_menor.png";
    public static final String NOME = "Estacione Fácil";
       
    public static final String ICONE_ATIVO = "/view/imagens/check.png";
    public static final String ICONE_EXCLUIDO = "/view/imagens/times.png";
        
    public static final String CAMINHO_ANEXOS = new File("").getAbsolutePath() + File.separator + "anexos";
    
    public static final String MSG_SELECIONAR = "Selecione um registro para excluir!";
    public static final String MSG_EDITAR = "Selecione um registro para editar!";
    public static final String MSG_EXCLUIR = "Tem certeza que deseja excluir?";
    public static final String MSG_REGISTRO_EXISTENTE = "Este registro já está cadastrado!";
    public static final String MSG_REGISTRO_SUCESSO = "Registro salvo com sucesso!";
    public static final String MSG_REGISTRO_EXCLUIR_SUCESSO = "Registro excluído com sucesso!";
    
    private static String login;
    private static Long idArea;
    private static Usuario usuarioLogado;


    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        ConfiguracaoSistema.login = login;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        ConfiguracaoSistema.usuarioLogado = usuarioLogado;
    }

    public static Long getIdArea() {
        return idArea;
    }

    public static void setIdArea(Long idArea) {
        ConfiguracaoSistema.idArea = idArea;
    }
}
