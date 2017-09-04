package model.vo;

/**
 *
 * @author Douglas
 */
public class VagaFiltroVO extends FiltroVO {
    
    private Long idArea;
    private Long idTipoVeiculo;


    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Long idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }
}
