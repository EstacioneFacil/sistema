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
@Table(name = "menu_botao")
public class MenuBotao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "menu_id")
    private Long idMenu;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private Menu menu;
    
    @Column(name = "botao_id")
    private Long idBotao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "botao_id", insertable = false, updatable = false)
    private Botao botao;
        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdBotao() {
        return idBotao;
    }

    public void setIdBotao(Long idBotao) {
        this.idBotao = idBotao;
    }

    public Botao getBotao() {
        return botao;
    }

    public void setBotao(Botao botao) {
        this.botao = botao;
    }
}
