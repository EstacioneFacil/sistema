package model.constant;


public enum DiaSemanaEnum {
    JANEIRO(0, "Janeiro"),
    FEVEREIRO(1, "Fevereiro"),
    MARCO(2, "Março"),
    ABRIL(3, "Abril"),
    MAIO(4, "Maio"),
    JUNHO(5, "Junho"),
    JULHO(6, "Julho"),
    AGOSTO(7, "Agosto"),
    SETEMBRO(8, "Setembro"),
    OUTUBRO(9, "Outubro"),
    NOVEMBRO(10, "Novembro"),
    DEZEMBRO(11, "Dezembro");

    private Integer key;
    private String descricao;

    private DiaSemanaEnum (Integer key, String descricao) {
        this.key = key;
        this.descricao = descricao;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setLabel(String descricao) {
        this.descricao = descricao;
    }

    public static DiaSemanaEnum getByKey(Integer key) throws EnumConstantNotPresentException {
        for (DiaSemanaEnum tipo : DiaSemanaEnum.values()) {
            if (tipo.getKey().equals(key)) {
                return tipo;
            }
        }
        throw new EnumConstantNotPresentException(DiaSemanaEnum.class, "Chave " + key + " não encontrada");
    }

    public static DiaSemanaEnum getByDescricao(String descricao) throws EnumConstantNotPresentException {
        for (DiaSemanaEnum tipo : DiaSemanaEnum.values()) {
            if (tipo.getDescricao().equals(descricao)) {
                return tipo;
            }
        }
        throw new EnumConstantNotPresentException(DiaSemanaEnum.class, "Dia da semana " + descricao + " não encontrada");
    }
}