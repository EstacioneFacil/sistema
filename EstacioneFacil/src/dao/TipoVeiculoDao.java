package dao;

import model.TipoVeiculo;


public class TipoVeiculoDao extends GenericDao<TipoVeiculo> {
    
    public TipoVeiculoDao() {
        super();
    }

    public void gravar(TipoVeiculo tipoVeiculo) {
        if (tipoVeiculo.getId() == null) {
            save(tipoVeiculo);
        } else {
            update(tipoVeiculo);
        }
    }
    
}
