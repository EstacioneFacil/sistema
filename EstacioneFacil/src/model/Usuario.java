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

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "login", length = 10)
    private String login;
    
    @Column(name = "senha")
    private String senha;

    @Column(name = "grupo_permissao_id")
    private Long idGrupoPermissao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "grupo_permissao_id", insertable = false, updatable = false)
    private GrupoPermissao grupoPermissao;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getIdGrupoPermissao() {
        return idGrupoPermissao;
    }

    public void setIdGrupoPermissao(Long idGrupoPermissao) {
        this.idGrupoPermissao = idGrupoPermissao;
    }

    public GrupoPermissao getGrupoPermissao() {
        return grupoPermissao;
    }

    public void setGrupoPermissao(GrupoPermissao grupoPermissao) {
        this.grupoPermissao = grupoPermissao;
    }
}
