package dao;

import java.util.List;
import model.Menu;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

public class MenuDao extends GenericDao<Menu> {

    public MenuDao() {
        super();
    }

    public List<Menu> buscarMenu() {
        SQLQuery sqlQuery = getSession().createSQLQuery("select m.id, m.descricao, m.ordem, m.grupo, m.menu_pai as menuPai, m.classe, m.visivel from buscar_menu m order by m.ordem asc");
        
        sqlQuery.addScalar("id", LongType.INSTANCE);
        sqlQuery.addScalar("descricao", StringType.INSTANCE);
        sqlQuery.addScalar("ordem", IntegerType.INSTANCE);
        sqlQuery.addScalar("grupo", IntegerType.INSTANCE);
        sqlQuery.addScalar("menuPai", LongType.INSTANCE);
        sqlQuery.addScalar("classe", StringType.INSTANCE);
        sqlQuery.addScalar("visivel", BooleanType.INSTANCE);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(Menu.class));
        
        return sqlQuery.list();
    }
    
    
    public List<Menu> buscarMenusParaPermissao() {
        Criteria crit = getSession().createCriteria(Menu.class);
        crit.add(Restrictions.or(Restrictions.isNotNull("classe"), Restrictions.eq("visivel", false)));
        return crit.list();
    }
}
