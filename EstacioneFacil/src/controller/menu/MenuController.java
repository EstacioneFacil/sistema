package controller.menu;

import dao.MenuDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import model.Menu;

/**
 *
 * @author Douglas
 */
public class MenuController {

    public JMenuBar montarMenu() {
        List<Menu> menus = organizarMenu();
        JMenuBar barraMenu = new JMenuBar();

        for (Menu menu : menus) {
            JMenu jMenu = new JMenu(menu.getDescricao());
            montarSubMenus(menu, jMenu);
            if (!menu.getSubMenus().isEmpty()) {
                barraMenu.add(jMenu);
            }
        }
        return barraMenu;
    }

    private static void montarSubMenus(Menu menu, JMenu jMenu) {
        int grupo = 1;
        for (Menu subMenu : menu.getSubMenus()) {
            if (subMenu.getSubMenus() != null && subMenu.getSubMenus().size() > 0) {
                JMenu jSubMenu = new JMenu(subMenu.getDescricao());
                if (subMenu.getGrupo() > grupo) {
                    jMenu.add(new JSeparator());
                    grupo = subMenu.getGrupo();
                }
                jSubMenu.addActionListener(new MenuAcoes(subMenu.getClasse()));
                jMenu.add(jSubMenu);
                montarSubMenus(subMenu, jSubMenu);
            } else {
                JMenuItem jSubMenuAux = new JMenuItem(subMenu.getDescricao());
                if (subMenu.getGrupo() > grupo) {
                    jMenu.add(new JSeparator());
                    grupo = subMenu.getGrupo();
                }
                jSubMenuAux.addActionListener(new MenuAcoes(subMenu.getClasse()));
                jMenu.add(jSubMenuAux);
                montarSubMenus(subMenu, jMenu);
            }
        }
    }

    private List<Menu> organizarMenu() {
        List<Menu> menus = new ArrayList();
        List<Menu> menusConsulta = new MenuDao().buscarMenu();

        for (Menu menu : menusConsulta) {
            if (menu.getMenuPai() == null) {
                if (menu.getSubMenus() == null) {
                    menu.setSubMenus(new ArrayList<Menu>());
                }
                menu.getSubMenus().addAll(organizarSubMenu(menusConsulta, menu.getId()));
                menus.add(menu);
            }
        }
        return menus;
    }

    private List<Menu> organizarSubMenu(List<Menu> menus, Long idMenu) {
        List<Menu> subMenus = new ArrayList();
        for (Menu m : menus) {
            if ((m.getMenuPai() != null) && (m.getMenuPai().equals(idMenu))) {
                if (m.getSubMenus() == null) {
                    m.setSubMenus(new ArrayList<Menu>());
                }
                m.getSubMenus().addAll(organizarSubMenu(menus, m.getId()));
                subMenus.add(m);
            }
        }
        return subMenus;
    }
}
