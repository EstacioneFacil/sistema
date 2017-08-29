package controller;

import dao.MenuBotaoDao;
import dao.PermissaoBotaoDao;
import java.util.ArrayList;
import java.util.List;
import model.MenuBotao;
import model.Permissao;
import model.PermissaoBotao;

/**
 *
 * @author Douglas
 */
public class PermissaoBotaoController {
    
    private MenuBotaoDao menuBotaoDao;
    private PermissaoBotaoDao permissaoBotaoDao;

    public PermissaoBotaoController() {
        this.menuBotaoDao = new MenuBotaoDao();
        this.permissaoBotaoDao = new PermissaoBotaoDao();
    }
    
    public List<PermissaoBotao> getListaBotoesPermissoes(Permissao permissao) {
        List<PermissaoBotao> permissaoBotoes = new ArrayList<>();
        
        if (permissao.getId() != null) {
            permissaoBotoes = permissaoBotaoDao.buscarPermissaoBotoes(permissao.getId());
            
            if (permissaoBotoes != null && permissaoBotoes.isEmpty()) {
                permissaoBotoes = getBotoesPermissoes(permissao);
            }
        } else {
            permissaoBotoes = getBotoesPermissoes(permissao);
        }
        return permissaoBotoes;
    }
    
    
    private List<PermissaoBotao> getBotoesPermissoes(Permissao permissao) {
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
    
    
    public void gravarPermissaoBotoes(Permissao permissao) {
        if (permissao.getPermissaoBotoes() != null) {
            for (PermissaoBotao permissaoBotao : permissao.getPermissaoBotoes()) {
                permissaoBotao.setIdPermissao(permissao.getId());
                permissaoBotaoDao.gravar(permissaoBotao);
            }
        }
    }
}
