package view.classes;

import model.constant.TipoPrecoEnum;

/**
 *
 * @author Douglas
 */
public class CombosEstaticos {
    
    public static String[] getTipoPagamento() {
        String[] tiposPagamento = new String[TipoPrecoEnum.values().length];
        for (TipoPrecoEnum tipoPrecoEnum : TipoPrecoEnum.values()) {
            tiposPagamento[tipoPrecoEnum.ordinal()] = tipoPrecoEnum.getLabel();
        }
        return tiposPagamento;
    }
}
