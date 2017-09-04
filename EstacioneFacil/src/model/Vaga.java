package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vaga")
public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "codigo", length = 10)
    private String codigo;
    
    @Column(name = "area_id")
    private Long idArea;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    private Area area;
    
    @Column(name = "tipo_veiculo_id")
    private Long idTipoVeiculo;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tipo_veiculo_id", insertable = false, updatable = false)
    private TipoVeiculo tipoVeiculo;

    @Transient
    private String descricao;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Long getIdTipoVeiculo() {
        return idTipoVeiculo;
    }

    public void setIdTipoVeiculo(Long idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
