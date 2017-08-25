package model.vo;

/**
 *
 * @author Douglas
 */
public class FiltroVO {
    
    private Integer id;
    private String pesquisa;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
