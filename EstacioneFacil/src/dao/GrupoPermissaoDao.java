package dao;

import java.util.List;
import model.GrupoPermissao;
import model.vo.GrupoPermissaoFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class GrupoPermissaoDao extends GenericDao<GrupoPermissao> {

    public GrupoPermissaoDao() {
        super();
    }

    public void gravar(GrupoPermissao grupoPermissao) {
        if (grupoPermissao.getId() == null) {
            save(grupoPermissao);
        } else {
            update(grupoPermissao);
        }
    }
    
    public void excluir(GrupoPermissao grupoPermissao) {
        delete(grupoPermissao);
    }
    
    public List<GrupoPermissao> consultar(GrupoPermissaoFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(GrupoPermissao.class);
        
        if (filtroVO.getPesquisa() != null && !filtroVO.getPesquisa().isEmpty()) {
            
            Criterion critPesquisa = null;
            if (filtroVO.getPesquisa().matches("^[0-9]{1,45}$")) {
                critPesquisa = Restrictions.eq("id", Long.parseLong(filtroVO.getPesquisa()));
            }
            if (critPesquisa == null) {
                critPesquisa = Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
            } else {
                critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("descricao", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
            }
            crit.add(critPesquisa);
        }
        return crit.list();
    }
    
    public List<GrupoPermissao> buscarTodos() {
        Criteria crit = getSession().createCriteria(GrupoPermissao.class);
        return crit.list();
    }
}
