package dao;

import model.Anexo;

public class AnexoDao extends GenericDao<Anexo> {

    public AnexoDao() {
        super();
    }

    public void gravar(Anexo anexo) {
        if (anexo.getId() == null) {
            save(anexo);
        } else {
            update(anexo);
        }
    }
        
    public void excluir(Anexo anexo) {
        if (anexo != null) {
            this.delete(anexo);
        }
    }
}
