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
@Table(name = "permissao_botao")
public class PermissaoBotao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "menu_botao_id")
    private Long idMenuBotao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "menu_botao_id", insertable = false, updatable = false)
    private MenuBotao menuBotao;
    
    @Column(name = "permissao_id")
    private Long idPermissao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "permissao_id", insertable = false, updatable = false)
    private Permissao permissao;
    
@Column(name = "permissao")    
    private boolean temPermissao;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMenuBotao() {
        return idMenuBotao;
    }

    public void setIdMenuBotao(Long idMenuBotao) {
        this.idMenuBotao = idMenuBotao;
    }

    public MenuBotao getMenuBotao() {
        return menuBotao;
    }

    public void setMenuBotao(MenuBotao menuBotao) {
        this.menuBotao = menuBotao;
    }

    public Long getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Long idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public boolean isTemPermissao() {
        return temPermissao;
    }

    public void setTemPermissao(boolean temPermissao) {
        this.temPermissao = temPermissao;
    }
}
