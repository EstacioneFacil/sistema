package controller;

import dao.MenuDao;
import dao.PermissaoDao;
import java.util.ArrayList;
import java.util.List;
import model.GrupoPermissao;
import model.Menu;
import model.Permissao;

/**
 *
 * @author Douglas
 */
public class PermissaoController {
    
    private PermissaoDao permissaoDao;
    private MenuDao menuDao;

    public PermissaoController() {
        this.permissaoDao = new PermissaoDao();
        this.menuDao = new MenuDao();
    }
    
    public List<Permissao> getListaPermissoes(GrupoPermissao grupoPermissao) {
        List<Permissao> permissoes = new ArrayList<>();
        if (grupoPermissao.getId() != null) {
            permissoes = permissaoDao.buscarPermissoes(grupoPermissao.getId());
        } else {
            List<Menu> menus = menuDao.buscarMenusParaPermissao();
            for (Menu menu : menus) {
                Permissao permissao = new Permissao();
                permissao.setMenu(menu);
                permissao.setIdMenu(menu.getId());
                permissao.setVisualizar(true);
                permissoes.add(permissao);
            }
        }
        return permissoes;
    }

    public void gravarPermissoes(Long idGrupoPermissao, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            permissao.setIdGrupoPermissao(idGrupoPermissao);
            permissaoDao.gravar(permissao);
        }
    }
}
