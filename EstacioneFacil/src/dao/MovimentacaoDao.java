package dao;

import java.util.Date;
import java.util.List;
import model.Movimentacao;
import model.vo.MovimentacaoFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
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
    
    public Movimentacao buscarVagaAberta(Long id, Long idVaga) {
        Criteria crit = getSession().createCriteria(Movimentacao.class);
        crit.add(Restrictions.eq("idVaga", idVaga));
        crit.add(Restrictions.isNull("dataHoraSaida"));
        if (id != null) {
            crit.add(Restrictions.ne("id", id));
        }
        crit.setMaxResults(1);
        return (Movimentacao) crit.uniqueResult();
    }
    
    public List<Movimentacao> consultar(MovimentacaoFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(Movimentacao.class);
        
        if (filtroVO.getIdArea() != null || filtroVO.getIdTipoVeiculo() != null) {
            crit.createAlias("vaga", "vaga");
            
            if (filtroVO.getIdArea() != null) {
                crit.add(Restrictions.eq("vaga.idArea", filtroVO.getIdArea()));
            }
            if (filtroVO.getIdTipoVeiculo() != null) {
                crit.add(Restrictions.eq("vaga.idTipoVeiculo", filtroVO.getIdTipoVeiculo()));
            } 
        } 
        if (filtroVO.getIdVaga() != null) {
            crit.add(Restrictions.eq("idVaga", filtroVO.getIdVaga()));
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
               
            }
            if (critPesquisa != null) {
                crit.add(critPesquisa);
            }
        }
        return crit.list();
    }
    	
    public Long totalPorArea(Long idArea) {
        Criteria criteria = getSession().createCriteria(Movimentacao.class);
        criteria.createAlias("vaga", "vaga");

        criteria.add(Restrictions.eq("vaga.idArea", idArea));
        criteria.add(Restrictions.isNotNull("dataHoraSaida"));
				
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }
	
	
    public Long totalPorTipoVeiculo(Long idTipoVeiculo) {
        Criteria criteria = getSession().createCriteria(Movimentacao.class);
        criteria.createAlias("vaga", "vaga");

        criteria.add(Restrictions.eq("vaga.idTipoVeiculo", idTipoVeiculo));
        criteria.add(Restrictions.isNotNull("dataHoraSaida"));

        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }
	
	
    public Double valorTotalPorAreaMes(Long idArea, Date dataInicial, Date dataFinal) {
        Criteria criteria = getSession().createCriteria(Movimentacao.class);
        criteria.createAlias("vaga", "vaga");

        criteria.add(Restrictions.eq("vaga.idArea", idArea));
        criteria.add(Restrictions.isNotNull("dataHoraSaida"));
        criteria.add(Restrictions.and(Restrictions.and(Restrictions.ge("dataHoraEntrada", dataInicial), Restrictions.le("dataHoraEntrada", dataFinal)), Restrictions.and(Restrictions.ge("dataHoraSaida", dataInicial), Restrictions.le("dataHoraSaida", dataFinal))));

        criteria.setProjection(Projections.sum("valor"));
        return (Double) criteria.uniqueResult();
    }
}
