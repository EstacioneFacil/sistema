package model.vo;

/**
 *
 * @author Douglas
 */
public class FiltroVO {
    
    private Long id;
    private String pesquisa;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPesquisa() {
        if (pesquisa == null) {
            return "";
        }
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
}
