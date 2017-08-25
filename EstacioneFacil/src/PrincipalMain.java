
import config.ConfiguracaoSistema;
import model.vo.CredencialVO;
import view.Login;
import view.Principal;


public class PrincipalMain {

    public static void main(String[] args) {
        modificarLookAndFeel();
        iniciarSistema();
    }

    public static void iniciarSistema() {               
        Login login = new Login();
        CredencialVO credencialVO = login.openLogin();
        ConfiguracaoSistema.setUsuarioLogado(credencialVO.getUsuario());
        
        if (ConfiguracaoSistema.getUsuarioLogado() == null) {
            System.exit(0);
        }
        
        Principal principal = new Principal();
        principal.setVisible(true);
    }
    
    private static void modificarLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PrincipalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
