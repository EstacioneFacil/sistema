package controller;

import dao.MenuBotaoDao;
import java.util.ArrayList;
import java.util.List;
import model.Menu;
import model.MenuBotao;
import model.Permissao;
import model.PermissaoBotao;

/**
 *
 * @author Douglas
 */
public class PermissaoBotaoController {
    
    private MenuBotaoDao menuBotaoDao;

    public PermissaoBotaoController() {
        this.menuBotaoDao = new MenuBotaoDao();
    }
    
    public List<PermissaoBotao> getListaBotoesPermissoes(Permissao permissao) {
        List<PermissaoBotao> permissaoBotoes = new ArrayList<>();
        List<MenuBotao> menuBotoes = menuBotaoDao.buscarPermissoesBotoes(permissao.getIdMenu());
        for (MenuBotao menuBotao : menuBotoes) {
            PermissaoBotao permissaoBotao = new PermissaoBotao();
            permissaoBotao.setIdMenuBotao(menuBotao.getId());
            permissaoBotao.setMenuBotao(menuBotao);
            permissaoBotao.setIdPermissao(permissao.getId());
            permissaoBotao.setPermissao(permissao);
            permissaoBotao.setTemPermissao(true);
            permissaoBotoes.add(permissaoBotao);
        }
        return permissaoBotoes;
    }
}
