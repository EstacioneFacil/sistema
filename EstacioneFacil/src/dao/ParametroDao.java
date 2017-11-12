package dao;

import model.Parametro;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


public class ParametroDao extends GenericDao<Parametro> {

    public ParametroDao() {
        super();
    }

    public void gravar(Parametro parametro) {
        if (parametro.getId() == null) {
            save(parametro);
        } else {
            update(parametro);
        }
    }
        
    public Parametro buscarParametros() {
        Criteria crit = getSession().createCriteria(Parametro.class);
        crit.add(Restrictions.eq("id", 1L));
        crit.setMaxResults(1);
        return (Parametro) crit.uniqueResult();
    }
}
