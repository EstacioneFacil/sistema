package dao;

import java.util.List;
import model.Menu;
import model.Permissao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class PermissaoDao extends GenericDao<Permissao> {

    public PermissaoDao() {
        super();
    }

    public void gravar(Permissao permissao) {
        if (permissao.getId() == null) {
            save(permissao);
        } else {
            update(permissao);
        }
    }
    
    public void excluir(Permissao permissao) {
        delete(permissao);
    }
        
    public List<Permissao> buscarPermissoes(Long idGrupoPermissao) {
        Criteria crit = getSession().createCriteria(Permissao.class);
        crit.add(Restrictions.eq("idGrupoPermissao", idGrupoPermissao));
        return crit.list();
    }
}
