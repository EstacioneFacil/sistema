package dao;

import model.Usuario;
import org.hibernate.Criteria;
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

    public Usuario buscarPorLogin(String login, String senha) {
        Criteria crit = getSession().createCriteria(Usuario.class);
        crit.add(Restrictions.eq("login", login));
        crit.add(Restrictions.eq("senha", senha));
        crit.setMaxResults(1);
        return (Usuario) crit.uniqueResult();
    }
}
