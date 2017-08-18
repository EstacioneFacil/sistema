package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GrupoPermissao;
import view.grupoPermissao.GrupoPermissaoLista;
import view.usuario.UsuarioLista;

/**
 *
 * @author Douglas
 */
public class MenuAcoes implements ActionListener {

    Long menu;

    public MenuAcoes(Long menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (menu == 3) {
            menuArea();
        } else if (menu == 4) {
            menuTipoVeiculo();
        } 
    }

    //CADASTRO
    private void menuArea() {
        System.out.println("Area");
        
        new GrupoPermissaoLista().setVisible(true);
    }

    private void menuTipoVeiculo() {
        System.out.println("Tipo Veiculo");
        
        new UsuarioLista().setVisible(true);
    }
}
