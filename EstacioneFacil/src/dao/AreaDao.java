package dao;

import java.util.List;
import model.Area;
import model.vo.AreaFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AreaDao extends GenericDao<Area> {

    public AreaDao() {
        super();
    }

    public void gravar(Area area) {
        if (area.getId() == null) {
            save(area);
        } else {
            update(area);
        }
    }

    public Area buscarDescricao(String descricao) {
        Criteria crit = getSession().createCriteria(Area.class);
        crit.add(Restrictions.like("descricao", descricao));
        crit.setMaxResults(1);

        return (Area) crit.uniqueResult();
    }
    
        
    public void excluir(Area area) {
        if (area != null) {
            this.delete(area);
        }
    }
    
    public List<Area> consultar(AreaFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(Area.class);
                
        if (filtroVO.getPesquisa() != null && !filtroVO.getPesquisa().isEmpty()) {
            Criterion critPesquisa = null;
            if (filtroVO.getPesquisa() != null && !filtroVO.getPesquisa().isEmpty()) {
                if (filtroVO.getPesquisa().matches("^[0-9]{1,45}$")) {
                    if (critPesquisa == null) {
                        critPesquisa = Restrictions.eq("id", Long.parseLong(filtroVO.getPesquisa()));
                    } else {
                        critPesquisa = Restrictions.or(critPesquisa, Restrictions.eq("id", Long.parseLong(filtroVO.getPesquisa())));
                    }
                }
                if (critPesquisa == null) {
                    critPesquisa = Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
                } else {
                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
                }
               
            }
            if (critPesquisa != null) {
                crit.add(critPesquisa);
            }
        }
        return crit.list();
    }
}
