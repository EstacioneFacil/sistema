package view;

import config.ConfiguracaoSistema;
import controller.menu.MenuController;
import dao.AreaDao;
import dao.MovimentacaoDao;
import dao.VagaDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static javax.swing.border.TitledBorder.CENTER;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import model.Area;
import model.Movimentacao;
import model.Vaga;
import model.util.FormatacaoUtils;
import net.miginfocom.swing.MigLayout;
import view.movimentacao.MovimentacaoEntradaCadastro;

/**
 *
 * @author Douglas
 */
public class Principal extends javax.swing.JFrame {
    
    private MovimentacaoDao movimentacaoDao;
    
    public Principal() {
        initComponents();
        
        this.movimentacaoDao = new MovimentacaoDao();
        
        setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        setTitle(ConfiguracaoSistema.NOME);
        setExtendedState(MAXIMIZED_BOTH);
        setJMenuBar(new MenuController().montarMenu());
       
        atualizarVagas(ConfiguracaoSistema.getIdArea());
    }
              
    public void atualizarVagas(Long idArea) {        
        if (idArea != null) {
            Area area = new AreaDao().findById(idArea);
            if (area != null) {
                List<Vaga> vagas = new VagaDao().buscarPorArea(area.getId());
                if (vagas != null && !vagas.isEmpty()) {
                    montarBotoesVagas(area, vagas);
                } else {
                    montarMensagemAlerta("Esta área não contém vagas cadastradas!");
                }
            } else {
                montarMensagemAlerta("A área selecionada não foi encontrada!");
            }
        } else {
            montarMensagemAlerta("Selecione primeiro uma área!");
        }
    }
    
    private void montarBotoesVagas(Area area, List<Vaga> vagas) {
        jPanel.removeAll();
        jPanel.setLayout(new MigLayout("wrap 8, fillx"));
        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, area.getDescricao(), CENTER, DEFAULT_POSITION));
        
        Dimension dimension = new Dimension(150, 60);
        for (Vaga vaga : vagas) {
            JButton jButton = new JButton();
            jButton.setPreferredSize(dimension);
            verificarVagas(jButton, vaga);
            jPanel.add(jButton, "align center top");
        }   
        jPanel.revalidate();
    }
    
    private void verificarVagas(JButton jButton, Vaga vaga) {
        //cor
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButton.setForeground(Color.BLACK);
        jButton.setContentAreaFilled(false);
        jButton.setOpaque(true);
        
        Movimentacao movimentacao = movimentacaoDao.buscarVagaAberta(vaga);
        if (movimentacao != null) {
            jButton.setBackground(new Color(255, 53, 53));
            
            String texto = "<html><center><font style='font-size:12px'>"+FormatacaoUtils.formatarPlaca(movimentacao.getPlaca())+"</font><br><font style='font-size:10px'>"+FormatacaoUtils.getDataHoraString(movimentacao.getDataHoraEntrada())+"</font></center></html>";
            jButton.setText(texto);
        } else {
            jButton.setBackground(new Color(80, 255, 61));
            jButton.setText("<html><center><font style='font-size:12px'>"+vaga.getCodigo() + " - " + vaga.getTipoVeiculo().getDescricao()+"</font></center></html>");
        }

        //acao
        Object principal = this;
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MovimentacaoEntradaCadastro(principal, movimentacao != null ? movimentacao : new Movimentacao(vaga)).setVisible(true);
            }
        });
    }
    
    private void montarMensagemAlerta(String msg) {
        jPanel.removeAll();
        jPanel.setLayout(new MigLayout("wrap 3", "grow", "grow"));
        JLabel label = new JLabel(msg);
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jPanel.add(label, "align center");
        jPanel.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBorder(null);

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Área", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel.setLayout(null);
        jScrollPane1.setViewportView(jPanel);

        jMenuBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setText("File");
        jMenuBar.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar.add(jMenu2);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
