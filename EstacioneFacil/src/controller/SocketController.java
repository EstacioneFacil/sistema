package controller;

import java.util.Properties;
import org.apache.log4j.Logger;
import view.Principal;
import view.socket.Cliente;
import view.socket.Servidor;

/**
 *
 * @author Douglas
 */
public class SocketController {
    
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private Servidor servidor = null;
    private Cliente cliente = null;
    private Principal principal;
        
    private String ip = null;
    private String porta = null;
    
    public SocketController(Object principal) {
        this.principal = (Principal) principal;
        
        buscarEndereco();
        logger.info("Iniciando socket no endereco: "+ip+":"+porta);
        
        servidor = new Servidor(ip, Integer.valueOf(porta.trim()), null);
        servidor.start();

        cliente = new Cliente(this, ip, Integer.valueOf(porta.trim()));
        cliente.start();
    }
    
    public void enviarMensagem(String msg) {
        servidor.definirMensagem(msg);
        servidor.definirEnvio(true);
    }
    
    public void verificaRetorno(boolean erro, String msg) {
        if (!erro) {
            String[] txt = msg.split("-");
            if (txt[0].equals("atualizar")) {
                Long idArea = Long.valueOf(txt[1]);
                principal.atualizarDashboard(idArea);
            }
        }
        System.out.println(msg);
    }
    
    private void buscarEndereco() {
        try {      
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("socket.properties"));
            
            ip = (String) properties.get("socket.ip");
            porta = (String) properties.get("socket.porta");
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar informacoes do socket" + e);
        }
    }
}
