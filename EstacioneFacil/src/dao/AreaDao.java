package dao;

import model.Area;
import org.hibernate.Criteria;
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
        crit.add(Restrictions.eq("descricao", descricao));
        crit.setMaxResults(1);

        return (Area) crit.uniqueResult();
    }
}
