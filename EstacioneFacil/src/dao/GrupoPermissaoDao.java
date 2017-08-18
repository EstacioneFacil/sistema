package dao;

import model.GrupoPermissao;

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
}
