package dao;

import model.Movimentacao;
import model.Vaga;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class MovimentacaoDao extends GenericDao<Movimentacao> {

    public MovimentacaoDao() {
        super();
    }

    public void gravar(Movimentacao movimentacao) {
        if (movimentacao.getId() == null) {
            save(movimentacao);
        } else {
            update(movimentacao);
        }
    }
        
    public void excluir(Movimentacao movimentacao) {
        if (movimentacao != null) {
            this.delete(movimentacao);
        }
    }
    
    public Movimentacao buscarVagaAberta(Vaga vaga) {
        Criteria crit = getSession().createCriteria(Movimentacao.class);
        crit.add(Restrictions.eq("idVaga", vaga.getId()));
        crit.add(Restrictions.isNull("dataHoraSaida"));
        crit.setMaxResults(1);
        return (Movimentacao) crit.uniqueResult();
    }
    
//    public List<Area> consultar(AreaFiltroVO filtroVO) {
//        Criteria crit = getSession().createCriteria(Area.class);
//                
//        if (filtroVO.getPesquisa() != null && !filtroVO.getPesquisa().isEmpty()) {
//            Criterion critPesquisa = null;
//            if (filtroVO.getPesquisa() != null && !filtroVO.getPesquisa().isEmpty()) {
//                if (filtroVO.getPesquisa().matches("^[0-9]{1,45}$")) {
//                    if (critPesquisa == null) {
//                        critPesquisa = Restrictions.eq("id", Long.parseLong(filtroVO.getPesquisa()));
//                    } else {
//                        critPesquisa = Restrictions.or(critPesquisa, Restrictions.eq("id", Long.parseLong(filtroVO.getPesquisa())));
//                    }
//                }
//                if (critPesquisa == null) {
//                    critPesquisa = Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
//                } else {
//                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
//                }
//               
//            }
//            if (critPesquisa != null) {
//                crit.add(critPesquisa);
//            }
//        }
//        return crit.list();
//    }
}
