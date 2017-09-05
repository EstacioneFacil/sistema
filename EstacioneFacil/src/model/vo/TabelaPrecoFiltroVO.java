package model.vo;

/**
 *
 * @author Douglas
 */
public class TabelaPrecoFiltroVO extends FiltroVO {
    
    private Long idTipoVeiculo;
    private Long idArea;
    private Long idVaga;

    
    public Long getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Long idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }
}
