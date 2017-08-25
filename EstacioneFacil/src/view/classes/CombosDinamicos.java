package view.classes;

import dao.GrupoPermissaoDao;
import java.util.ArrayList;
import java.util.List;
import model.GrupoPermissao;
import model.vo.SelectItemVO;


/**
 *
 * @author Douglas
 */
public class CombosDinamicos { 
        
    public static List<SelectItemVO> getGruposPermissao(boolean cadastro) {
        List<GrupoPermissao> grupos = new GrupoPermissaoDao().buscarTodos();
        List<SelectItemVO> gruposCombo = new ArrayList<>();
        if (cadastro) {
            gruposCombo.add(new SelectItemVO(null, "Selecione"));
        } else {
            gruposCombo.add(new SelectItemVO(null, "Todos"));
        }
        for (GrupoPermissao grupoPermissao : grupos) {
            gruposCombo.add(new SelectItemVO(grupoPermissao.getId(), grupoPermissao.getDescricao()));
        }
        return gruposCombo;
    }
}
