package model.constant;

/**
 *
 * @author Douglas
 */
public enum TipoPrecoEnum {

    HORA("H", "Hora"),
    DIA("D", "Diário"),
    MES("M", "Mensal");

    private String key;
    private String label;

    private TipoPrecoEnum(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static TipoPrecoEnum getByKey(String key) throws EnumConstantNotPresentException {
        for (TipoPrecoEnum situacao : TipoPrecoEnum.values()) {
            if (situacao.getKey().equals(key)) {
                return situacao;
            }
        }
        throw new EnumConstantNotPresentException(TipoPrecoEnum.class, "Chave " + key + " não encontrada");
    }
    
    public static TipoPrecoEnum getByLabel(String label) throws EnumConstantNotPresentException {
        for (TipoPrecoEnum situacao : TipoPrecoEnum.values()) {
            if (situacao.getLabel().equals(label)) {
                return situacao;
            }
        }
        throw new EnumConstantNotPresentException(TipoPrecoEnum.class, "Label " + label + " não encontrada");
    }
}
