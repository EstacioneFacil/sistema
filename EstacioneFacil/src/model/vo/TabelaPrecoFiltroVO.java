package model.vo;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class TabelaPrecoFiltroVO extends FiltroVO {
    
    private Long idTipoVeiculo;
    private Long idArea;
    private Long idVaga;
    private Date dataInicio;
    private Date dataFim;

    
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
