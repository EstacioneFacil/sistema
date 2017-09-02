package dao;

import java.util.List;
import model.Usuario;
import model.vo.UsuarioFiltroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao() {
        super();
    }

    public void gravar(Usuario usuario) {
        if (usuario.getId() == null) {
            save(usuario);
        } else {
            update(usuario);
        }
    }
    
    public void excluir(Usuario usuario) {
        if (usuario != null) {
            usuario.setExcluido(true);
            gravar(usuario);
        }
    }
    
    public List<Usuario> consultar(UsuarioFiltroVO filtroVO) {
        Criteria crit = getSession().createCriteria(Usuario.class);
        
        if (filtroVO.getIdGrupoPermissao() != null) {
            crit.add(Restrictions.eq("idGrupoPermissao", filtroVO.getIdGrupoPermissao()));
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
                if (critPesquisa == null) {
                    critPesquisa = Restrictions.like("nome", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
                } else {
                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("nome", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
                }
                if (critPesquisa == null) {
                    critPesquisa = Restrictions.like("login", "%"+filtroVO.getPesquisa().toLowerCase()+"%");
                } else {
                    critPesquisa = Restrictions.or(critPesquisa, Restrictions.like("login", "%"+filtroVO.getPesquisa().toLowerCase()+"%"));
                }
            }
            if (critPesquisa != null) {
                crit.add(critPesquisa);
            }
        }
        crit.add(Restrictions.eq("excluido", false));
        return crit.list();
    }

    public Usuario buscarPorLogin(Usuario usuario) {
        Criteria crit = getSession().createCriteria(Usuario.class);
        crit.add(Restrictions.eq("login", usuario.getLogin()));
        if (usuario.getId() != null) {
            crit.add(Restrictions.ne("id", usuario.getId()));
        }
        crit.setMaxResults(1);
        return (Usuario) crit.uniqueResult();
    }
    
    public Usuario buscarPorLoginSenha(String login, String senha) {
        Criteria crit = getSession().createCriteria(Usuario.class);
        crit.add(Restrictions.eq("login", login));
        crit.add(Restrictions.eq("senha", senha));
        System.out.println(login);
        System.out.println(senha);
        crit.setMaxResults(1);
        return (Usuario) crit.uniqueResult();
    }
}
