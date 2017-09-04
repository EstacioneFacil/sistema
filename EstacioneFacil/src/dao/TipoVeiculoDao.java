package dao;

import java.util.List;
import model.TipoVeiculo;
import model.vo.TipoVeiculoFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class TipoVeiculoDao extends GenericDao<TipoVeiculo> {
    
    public TipoVeiculoDao() {
        super();
    }

    public void gravar(TipoVeiculo tipoVeiculo) {
        if (tipoVeiculo.getId() == null) {
            save(tipoVeiculo);
        } else {
            update(tipoVeiculo);
        }
    }
     
    public void excluir(TipoVeiculo tipoVeiculo) {
        if (tipoVeiculo != null) {
            this.delete(tipoVeiculo);
        }
    }
    
    
    public TipoVeiculo buscarDescricao(String descricao) {
        Criteria crit = getSession().createCriteria(TipoVeiculo.class);
        crit.add(Restrictions.like("descricao", descricao));
        crit.setMaxResults(1);

        return (TipoVeiculo) crit.uniqueResult();
    }
    
    public List<TipoVeiculo> consultar(TipoVeiculoFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(TipoVeiculo.class);
                
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
    
    public List<TipoVeiculo> buscarTodos() {
        Criteria crit = getSession().createCriteria(TipoVeiculo.class);
        return crit.list();
    }
}
