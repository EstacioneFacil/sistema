package view.socket;

import controller.SocketController;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Cliente extends Thread {
    
    private String host = null;
    private String msg;
    private int porta = 0;
    private boolean erro;
    private SocketController socketController;
    
    public Cliente(Object socket, String host, int porta) {
        this.host = host;
        this.porta = porta;
        this.socketController = (SocketController) socket;
    }
    
    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            // determina endereco e porta
            InetAddress grupo = InetAddress.getByName(host);

            // cria multicast socket e se une ao grupo
            socket = new MulticastSocket(porta);
            
            socket.joinGroup(grupo);
        } catch (IOException ioe) {
            msg = ioe.toString();
            erro = true;
        }
        
        boolean ativo = true; // habilita laco
        while (ativo) {
            try {
                // prepara buffer (vazio)
                byte[] buf = new byte[256];

                // prepara pacote para resposta
                DatagramPacket pacote = new DatagramPacket(buf, buf.length);

                // recebe pacote
                socket.receive(pacote);

                // exibe dados na area de texto
                String dados = new String(pacote.getData()).trim();
                
                msg = dados;
                erro = false;

            } catch (IOException ioe) {
                msg = ioe.toString();
                erro = true;
            }
            socketController.verificaRetorno(erro, msg);
        }
        // fecha socket
        socket.close();
    }
    
}
