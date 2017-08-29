package dao;

import java.util.List;
import model.MenuBotao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;



public class MenuBotaoDao extends GenericDao<MenuBotao> {

    public MenuBotaoDao() {
        super();
    }
    
    public List<MenuBotao> buscarPermissoesBotoes(Long idMenu) {
        Criteria crit = getSession().createCriteria(MenuBotao.class);
        crit.add(Restrictions.eq("idMenu", idMenu));
        return crit.list();
    }

}
