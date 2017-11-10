package dao;

import java.util.List;
import model.TabelaPreco;
import model.util.CriteriaUtils;
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
                      
        if (filtroVO.getIdArea() != null) {
            crit.add(Restrictions.eq("idArea", filtroVO.getIdArea()));
        } 
        if (filtroVO.getIdTipoVeiculo() != null) {
            crit.add(Restrictions.eq("idTipoVeiculo", filtroVO.getIdTipoVeiculo()));
        } 
        if (filtroVO.getIdVaga() != null) {
            crit.add(Restrictions.eq("idVaga", filtroVO.getIdVaga()));
        } 
        if (filtroVO.getDataInicio() != null) {
            CriteriaUtils.entrePeriodos(crit, "dataInicio", "dataFim", filtroVO.getDataInicio(), filtroVO.getDataFim());
        }
        
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
    
    
    public List<TabelaPreco> buscarPorFiltros(TabelaPrecoFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
        if (filtroVO.getId() != null) {
            crit.add(Restrictions.ne("id", filtroVO.getId()));
        } 
        if (filtroVO.getIdArea() != null) {
            crit.add(Restrictions.eq("idArea", filtroVO.getIdArea()));
        } 
        if (filtroVO.getIdTipoVeiculo() != null) {
            crit.add(Restrictions.eq("idTipoVeiculo", filtroVO.getIdTipoVeiculo()));
        } 
        if (filtroVO.getIdVaga() != null) {
            crit.add(Restrictions.eq("idVaga", filtroVO.getIdVaga()));
        } else {
            crit.add(Restrictions.isNull("idVaga"));
        } 
        
        return crit.list();
    }
    
    public TabelaPreco buscarTabelaPreco(TabelaPreco tabelaPreco) {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
        crit.add(Restrictions.eq("idArea", tabelaPreco.getIdArea()));
        crit.add(Restrictions.eq("idTipoVeiculo", tabelaPreco.getIdTipoVeiculo()));
        if (tabelaPreco.getIdVaga() != null) {
            crit.add(Restrictions.eq("idVaga", tabelaPreco.getIdVaga()));
        } else {
            crit.add(Restrictions.isNull("idVaga"));
        }
        if (tabelaPreco.getId() != null) {
            crit.add(Restrictions.ne("id", tabelaPreco.getId()));
        }
        crit.add(Restrictions.isNull("dataFim"));
        crit.setMaxResults(1);
        return (TabelaPreco) crit.uniqueResult();
    }
    
    
    public TabelaPreco buscarPorArea(Long idArea) {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
        crit.add(Restrictions.eq("idArea", idArea));
        crit.add(Restrictions.isNull("idVaga"));
        crit.add(Restrictions.isNull("dataFim"));
        crit.setMaxResults(1);
        return (TabelaPreco) crit.uniqueResult();
    }
    
    
    public TabelaPreco buscarPorVaga(Long idVaga) {
        Criteria crit = getSession().createCriteria(TabelaPreco.class);
        crit.add(Restrictions.eq("idVaga", idVaga));
        crit.add(Restrictions.isNull("dataFim"));
        crit.setMaxResults(1);
        return (TabelaPreco) crit.uniqueResult();
    }
}
