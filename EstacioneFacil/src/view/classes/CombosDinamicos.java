package view.classes;

import dao.AreaDao;
import dao.GrupoPermissaoDao;
import dao.TipoVeiculoDao;
import dao.UsuarioDao;
import dao.VagaDao;
import java.util.ArrayList;
import java.util.List;
import model.Area;
import model.GrupoPermissao;
import model.TipoVeiculo;
import model.Usuario;
import model.Vaga;
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
    
    public static List<SelectItemVO> getUsuarios() {
        List<Usuario> usuarios = new UsuarioDao().buscarTodos();
        List<SelectItemVO> usuariosCombo = new ArrayList<>();
       
        usuariosCombo.add(new SelectItemVO(null, "Todos"));
        for (Usuario usuario : usuarios) {
            usuariosCombo.add(new SelectItemVO(usuario.getId(), usuario.getNome()));
        }
        return usuariosCombo;
    }
    
    public static List<SelectItemVO> getAreas(boolean cadastro) {
        List<Area> areas = new AreaDao().buscarTodos();
        List<SelectItemVO> areasCombo = new ArrayList<>();
        if (cadastro) {
            areasCombo.add(new SelectItemVO(null, "Selecione"));
        } else {
            areasCombo.add(new SelectItemVO(null, "Todos"));
        }
        for (Area area : areas) {
            areasCombo.add(new SelectItemVO(area.getId(), area.getDescricao()));
        }
        return areasCombo;
    }
    
    public static List<SelectItemVO> getTiposVeiculo(boolean cadastro) {
        List<TipoVeiculo> tiposVeiculo = new TipoVeiculoDao().buscarTodos();
        List<SelectItemVO> tiposVeiculoCombo = new ArrayList<>();
        if (cadastro) {
            tiposVeiculoCombo.add(new SelectItemVO(null, "Selecione"));
        } else {
            tiposVeiculoCombo.add(new SelectItemVO(null, "Todos"));
        }
        for (TipoVeiculo tipoVeiculo : tiposVeiculo) {
            tiposVeiculoCombo.add(new SelectItemVO(tipoVeiculo.getId(), tipoVeiculo.getDescricao()));
        }
        return tiposVeiculoCombo;
    }
    
    public static List<SelectItemVO> getVagas(boolean cadastro, Long idArea) {
        List<Vaga> vagas = new VagaDao().buscarPorArea(idArea);
        List<SelectItemVO> vagasCombo = new ArrayList<>();
        
        if (cadastro && idArea != null) {
            vagasCombo.add(new SelectItemVO(null, "Selecione"));
        } else if (cadastro && idArea == null) {
            vagasCombo.add(new SelectItemVO(null, "Selecione primeiro uma área"));
        } else {
            vagasCombo.add(new SelectItemVO(null, "Todos"));
        }
        if (!cadastro || (cadastro && idArea != null)) {
            for (Vaga vaga : vagas) {
                vagasCombo.add(new SelectItemVO(vaga.getId(), vaga.getCodigo()));
            }
        }       
        return vagasCombo;
    }
}
