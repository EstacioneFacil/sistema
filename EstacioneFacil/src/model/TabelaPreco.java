package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tabela_preco")
public class TabelaPreco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    @Column(name = "valor")
    private Double valor;
       
    @Column(name = "tipo", length = 10)
    private String tipo;
            
    @Column(name = "area_id")
    private Long idArea;
    
    @Column(name = "tipo_veiculo_id")
    private Long idTipoVeiculo;
    
    @Column(name = "vaga_id")
    private Long idVaga;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    private Area area;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tipo_veiculo_id", insertable = false, updatable = false)
    private TipoVeiculo tipoVeiculo;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "vaga_id", insertable = false, updatable = false)
    private Vaga vaga;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Long idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
