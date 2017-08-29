package model;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "permissao")
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "grupo_permissao_id")
    private Long idGrupoPermissao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "grupo_permissao_id", insertable = false, updatable = false)
    private GrupoPermissao grupoPermissao;
    
    @Column(name = "menu_id")
    private Long idMenu;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private Menu menu;
    
    @Column(name = "visualizar")
    private boolean visualizar;
    
    @Transient
    private List<PermissaoBotao> permissaoBotoes;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public List<PermissaoBotao> getPermissaoBotoes() {
        return permissaoBotoes;
    }

    public void setPermissaoBotoes(List<PermissaoBotao> permissaoBotoes) {
        this.permissaoBotoes = permissaoBotoes;
    }
}
