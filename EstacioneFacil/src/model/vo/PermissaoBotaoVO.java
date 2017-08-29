package model.vo;

/**
 *
 * @author user1
 */
public class PermissaoBotaoVO {
    
    private String nome;
    private boolean permissao;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPermissao() {
        return permissao;
    }

    public void setPermissao(boolean permissao) {
        this.permissao = permissao;
    }
}
