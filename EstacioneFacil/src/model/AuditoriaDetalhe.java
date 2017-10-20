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
@Table(name = "auditoria_detalhe")
public class AuditoriaDetalhe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "auditoria_id")
    private Long auditoria_id;

    public Long getAuditoria_id() {
        return auditoria_id;
    }

    public void setAuditoria_id(Long auditoria_id) {
        this.auditoria_id = auditoria_id;
    }
    
    @Column(name = "coluna")
    private String coluna;
    
    @Column(name = "valor_antigo")
    private String valorantigo;
    
    @Column(name = "valor_novo")
    private String valornovo;
    
    @Column(name = "chaveprimaria")
    private Boolean chaveprimaria;

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public String getValorantigo() {
        return valorantigo;
    }

    public void setValorantigo(String valorantigo) {
        this.valorantigo = valorantigo;
    }

    public String getValornovo() {
        return valornovo;
    }

    public void setValornovo(String valornovo) {
        this.valornovo = valornovo;
    }

    public Boolean getChaveprimaria() {
        return chaveprimaria;
    }

    public void setChaveprimaria(Boolean chaveprimaria) {
        this.chaveprimaria = chaveprimaria;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
