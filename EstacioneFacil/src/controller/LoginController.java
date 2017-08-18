package controller;

import dao.UsuarioDao;
import model.Usuario;
import model.vo.CredencialVO;
import org.apache.log4j.Logger;

/**
 *
 * @author Douglas
 */
public class LoginController {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    public CredencialVO logar(CredencialVO credencialVO) {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscarPorLogin(credencialVO.getLogin(), credencialVO.getSenha());

        if (usuario == null) {
            credencialVO.setSenhaCorreta(true);
        } else {
            credencialVO.setUsuario(usuario);
        }
        return credencialVO;
    }
}
