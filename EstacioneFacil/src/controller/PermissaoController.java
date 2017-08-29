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
    
    private MenuDao menuDao;
    private PermissaoDao permissaoDao;
    private PermissaoBotaoController permissaoBotaoController;

    public PermissaoController() {
        this.menuDao = new MenuDao();
        this.permissaoDao = new PermissaoDao();
        this.permissaoBotaoController = new PermissaoBotaoController();
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
            permissaoBotaoController.gravarPermissaoBotoes(permissao);
        }
    }
}
