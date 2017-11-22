
import view.PrincipalSistema;



public class PrincipalMain {
    
    public static void main(String[] args) {
        modificarLookAndFeel();
        
        PrincipalSistema principalSistema = new PrincipalSistema();
        principalSistema.iniciarSistema();
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
