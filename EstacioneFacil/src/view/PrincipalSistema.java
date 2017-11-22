package view;


import config.ConfiguracaoSistema;
import model.util.HibernateUtil;
import model.vo.CredencialVO;


public class PrincipalSistema {
        
 
    public void iniciarSistema() {               
        Login login = new Login();
        CredencialVO credencialVO = login.openLogin();
        ConfiguracaoSistema.setUsuarioLogado(credencialVO.getUsuario());
        ConfiguracaoSistema.setIdArea(credencialVO.getIdArea());
        
        if (ConfiguracaoSistema.getUsuarioLogado() == null) {
            System.exit(0);
        }
        HibernateUtil.close();
        HibernateUtil.abrirConexao();
        Principal principal = new Principal(this);
        principal.abrirTela();
    }
    
    public void trocarUsuario() {               
        ConfiguracaoSistema.zerarConfiguracoes();
        HibernateUtil.close();
        
        iniciarSistema();
    }
    
    public void sair() {
        ConfiguracaoSistema.zerarConfiguracoes();
        HibernateUtil.close();
        
        System.exit(0);
    }
}
