package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "utilizar_webservice")
    private boolean utilizarWebService;
    
    @Column(name = "tirar_foto")
    private boolean tirarFotoVeiculo;
    
    @Column(name = "utilizar_auditoria")
    private boolean utilizarAuditoria;

    public boolean isUtilizarAuditoria() {
        return utilizarAuditoria;
    }

    public void setUtilizarAuditoria(boolean utilizarAuditoria) {
        this.utilizarAuditoria = utilizarAuditoria;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUtilizarWebService() {
        return utilizarWebService;
    }

    public void setUtilizarWebService(boolean utilizarWebService) {
        this.utilizarWebService = utilizarWebService;
    }

    public boolean isTirarFotoVeiculo() {
        return tirarFotoVeiculo;
    }

    public void setTirarFotoVeiculo(boolean tirarFotoVeiculo) {
        this.tirarFotoVeiculo = tirarFotoVeiculo;
    }
}
