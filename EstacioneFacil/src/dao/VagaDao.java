package dao;

import java.util.List;
import model.Vaga;
import model.vo.VagaFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class VagaDao extends GenericDao<Vaga> {

    public VagaDao() {
        super();
    }

    public void gravar(Vaga vaga) {
        if (vaga.getId() == null) {
            save(vaga);
        } else {
            update(vaga);
        }
    }
   
        
    public void excluir(Vaga vaga) {
        if (vaga != null) {
            this.delete(vaga);
        }
    }
    
    public Vaga buscarVaga(Vaga vaga) {
        Criteria crit = getSession().createCriteria(Vaga.class);
        crit.add(Restrictions.eq("idArea", vaga.getIdArea()));
        crit.add(Restrictions.eq("idTipoVeiculo", vaga.getIdTipoVeiculo()));
        crit.add(Restrictions.eq("codigo", vaga.getCodigo()));
        if (vaga.getId() != null) {
            crit.add(Restrictions.ne("id", vaga.getId()));
        }
        crit.setMaxResults(1);
        return (Vaga) crit.uniqueResult();
    }
    
    public List<Vaga> consultar(VagaFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(Vaga.class);
                
        if (filtroVO.getIdArea() != null) {
            crit.add(Restrictions.eq("idArea", filtroVO.getIdArea()));
        } 
        if (filtroVO.getIdTipoVeiculo() != null) {
            crit.add(Restrictions.eq("idTipoVeiculo", filtroVO.getIdTipoVeiculo()));
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
                if (critPesquisa == null) {
                    critPesquisa = Restrictions.like("codigo", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
                } else {
                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("codigo", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
                }
            }
            if (critPesquisa != null) {
                crit.add(critPesquisa);
            }
        }
        return crit.list();
    }
    
    public List<Vaga> buscarPorArea(Long idArea) {
        Criteria crit = getSession().createCriteria(Vaga.class);
        if (idArea != null) {
            crit.add(Restrictions.eq("idArea", idArea));
        }
        return crit.list();
    }
}
