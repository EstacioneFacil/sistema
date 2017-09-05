package dao;

import java.util.List;
import model.TabelaPreco;
import model.vo.TabelaPrecoFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class TabelaPrecoDao extends GenericDao<TabelaPreco> {

    public TabelaPrecoDao() {
        super();
    }

    public void gravar(TabelaPreco tabelaPreco) {
        if (tabelaPreco.getId() == null) {
            save(tabelaPreco);
        } else {
            update(tabelaPreco);
        }
    }
   
        
    public void excluir(TabelaPreco tabelaPreco) {
        if (tabelaPreco != null) {
            this.delete(tabelaPreco);
        }
    }
    
    public List<TabelaPreco> consultar(TabelaPrecoFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
                
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
//                if (critPesquisa == null) {
//                    critPesquisa = Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
//                } else {
//                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
//                }
               
            }
            if (critPesquisa != null) {
                crit.add(critPesquisa);
            }
        }
        return crit.list();
    }
    
    public List<TabelaPreco> buscarTodos() {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
        return crit.list();
    }
}
