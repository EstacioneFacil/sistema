package model.vo;

import java.io.Serializable;

/**
 *
 * @author Douglas
 */
public class InfoVeiculoVO implements Serializable {
        
    private String mensagem;
    private String placa;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private String combustivel;

    public String toString() {
        return "Marca: " + marca + "\n"
                + "Modelo: " + modelo + "\n"
                + "Ano: " + ano + "\n"
                + "Cor: " + cor + "\n"
                + "Combustível: " + combustivel;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
