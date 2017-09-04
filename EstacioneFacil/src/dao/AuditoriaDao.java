package dao;

import java.util.List;
import model.Auditoria;
import model.vo.AuditoriaFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class AuditoriaDao extends GenericDao<Auditoria> {

    public AuditoriaDao() {
        super();
    }
    
    public List<Auditoria> consultar(AuditoriaFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(Auditoria.class);
        if (filtroVO.getIdUsuario() != null ) {
            crit.add(Restrictions.eq("idUsuario", filtroVO.getIdUsuario()));
        }
        return crit.list();
    }
}
