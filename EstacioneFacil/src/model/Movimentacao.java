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
@Table(name = "movimentacao")
public class Movimentacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "data_hora_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataHoraEntrada;
    
    @Column(name = "data_hora_saida")
    @Temporal(TemporalType.DATE)
    private Date dataHoraSaida;
    
    @Column(name = "placa")
    private String placa;
    
    @Column(name = "valor")
    private Double valor;
    
    @Column(name = "vaga_id")
    private Long idVaga;
    
    @Column(name = "anexo_id")
    private Long idAnexo;
    
    @Column(name = "usuario_id")
    private Long idUsuario;
    
    @Column(name = "tabela_preco_id")
    private Long idTabelaPreco;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "vaga_id", insertable = false, updatable = false)
    private Vaga vaga;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "anexo_id", insertable = false, updatable = false)
    private Anexo anexo;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tabela_preco_id", insertable = false, updatable = false)
    private TabelaPreco tabelaPreco;

    public Movimentacao() {
        
    }
    
    public Movimentacao(Vaga vaga) {
        this.vaga = vaga;
        this.idVaga = vaga.getId();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Date dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(Long idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TabelaPreco getTabelaPreco() {
        return tabelaPreco;
    }

    public void setTabelaPreco(TabelaPreco tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }
}
