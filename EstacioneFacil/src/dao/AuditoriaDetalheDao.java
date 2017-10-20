package dao;

import java.util.List;
import model.AuditoriaDetalhe;
import model.vo.AuditoriaFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class AuditoriaDetalheDao extends GenericDao<AuditoriaDetalhe> {

    public AuditoriaDetalheDao() {
        super();
    }
    
    public List<AuditoriaDetalhe> obterDetalhes(Long auditoriaId) {
        
        System.out.println("function obterDetalhes");
        
        Criteria crit = getSession().createCriteria(AuditoriaDetalhe.class);
            crit.add(Restrictions.eq("auditoria_id", auditoriaId));            
        return crit.list();
    }
}
