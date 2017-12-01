package view;

import config.ConfiguracaoSistema;
import controller.DashboardController;
import controller.SocketController;
import controller.menu.MenuController;
import dao.AreaDao;
import dao.MovimentacaoDao;
import dao.PermissaoDao;
import dao.VagaDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.border.TitledBorder.CENTER;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import model.Area;
import model.Movimentacao;
import model.Permissao;
import model.Vaga;
import model.util.FormatacaoUtils;
import net.miginfocom.swing.MigLayout;
import view.movimentacao.MovimentacaoCadastro;

/**
 *
 * @author Douglas
 */
public class Principal extends javax.swing.JFrame {
    
    private static MovimentacaoDao movimentacaoDao;
    public static SocketController socketController;
    private DashboardController dashboardController;
    private PrincipalSistema principalSistema;
    
    public Principal(PrincipalSistema principalSistema) {
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        setTitle(ConfiguracaoSistema.NOME);
        setExtendedState(MAXIMIZED_BOTH);
        
        this.movimentacaoDao = new MovimentacaoDao();
        this.dashboardController = new DashboardController();
        
        this.socketController = new SocketController(this);
        
        this.principalSistema = principalSistema;
    }
       
    public void abrirTela() {
        setJMenuBar(new MenuController().montarMenu());
        
        //permissao dashboard
        boolean permissao = new PermissaoDao().isPermissaoDashboard(13L, ConfiguracaoSistema.getUsuarioLogado().getIdGrupoPermissao());
        if (permissao) {
            montarGraficos();
        } else {
            jTabbedPane.setEnabledAt(0, false);
            jTabbedPane.setToolTipTextAt(0, "Você não tem permissão para acessar essa parte do sistema!");
            jTabbedPane.setSelectedIndex(1);
        }
        
        //permissao dashboard-vagas
       boolean permissaoVagas = new PermissaoDao().isPermissaoDashboard(14L, ConfiguracaoSistema.getUsuarioLogado().getIdGrupoPermissao());
        if (permissaoVagas) {
            chkVagaAberta.setSelected(true);
            chkVagaFechada.setSelected(true);
            atualizarVagas(ConfiguracaoSistema.getIdArea());
        } else {
            jTabbedPane.setEnabledAt(1, false);
            jTabbedPane.setToolTipTextAt(1, "Você não tem permissão para acessar essa parte do sistema!");
            jTabbedPane.setSelectedIndex(0);
        }
        
        //nenhuma permissao
        if (!permissao && !permissaoVagas) {
            montarMensagemAlerta(jPanelDashboard, "Você não tem acesso ao Dashboard!");
            jTabbedPane.setSelectedIndex(0);
        }
        setVisible(true);
    }
    
    public void atualizarDashboard(Long idArea) {
        System.out.println("Entrou para atualizar");
        atualizarVagas(idArea);
        montarGraficos();
    }
    
    public void atualizarVagas(Long idArea) {        
        if (idArea != null) {
            Area area = new AreaDao().findById(idArea);
            if (area != null) {
                List<Vaga> vagas = new VagaDao().buscarPorArea(area.getId());
                if (vagas != null && !vagas.isEmpty()) {
                    montarBotoesVagas(area, vagas);
                } else {
                    montarMensagemAlerta(jPanel, "Esta área não contém vagas cadastradas!");
                }
            } else {
                montarMensagemAlerta(jPanel, "A área selecionada não foi encontrada!");
            }
        } else {
            montarMensagemAlerta(jPanel, "Selecione primeiro uma área!");
        }
    }
    
    private static void montarBotoesVagas(Area area, List<Vaga> vagas) {
        jPanel.removeAll();
        jPanel.setLayout(new MigLayout("wrap 8, fillx"));
        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, area.getDescricao(), CENTER, DEFAULT_POSITION));
        
        Dimension dimension = new Dimension(150, 60);
        for (Vaga vaga : vagas) {
            JButton jButton = new JButton();
            jButton.setPreferredSize(dimension);
            jButton = verificarVagas(jButton, vaga);
            if (jButton != null) {
                jPanel.add(jButton, "align center top");
            }
        }   
        jPanel.revalidate();
    }
    
    private static JButton verificarVagas(JButton jButton, Vaga vaga) {
        //cor
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButton.setForeground(Color.BLACK);
        jButton.setContentAreaFilled(false);
        jButton.setOpaque(true);
        
        Movimentacao movimentacao = movimentacaoDao.buscarVagaAberta(null, vaga.getId());
        if (movimentacao != null) {
            jButton.setBackground(new Color(255, 53, 53));
            
            String texto = "<html><center><font style='font-size:12px'>"+FormatacaoUtils.formatarPlaca(movimentacao.getPlaca())+"</font><br><font style='font-size:10px'>"+FormatacaoUtils.getDataHoraString(movimentacao.getDataHoraEntrada())+"</font></center></html>";
            jButton.setText(texto);
        } else {
            jButton.setBackground(new Color(80, 255, 61));
            jButton.setText("<html><center><font style='font-size:12px'>"+vaga.getCodigo() + " - " + vaga.getTipoVeiculo().getDescricao()+"</font></center></html>");
        }

        //acao
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MovimentacaoCadastro(this, movimentacao != null ? movimentacao : new Movimentacao(vaga), true).setVisible(true);
            }
        });
        //verifica check;
        if (movimentacao == null && !chkVagaAberta.isSelected() && movimentacao != null && !chkVagaFechada.isSelected()) {
            return null;
        }
        if (movimentacao == null && !chkVagaAberta.isSelected()) {
            return null;
        }
        if (movimentacao != null && !chkVagaFechada.isSelected()) {
            return null;
        }
        return jButton;
    }
    
    private static void montarMensagemAlerta(JPanel jPanel, String msg) {
        jPanel.removeAll();
        jPanel.setLayout(new MigLayout("wrap 3", "grow", "grow"));
        JLabel label = new JLabel(msg);
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jPanel.add(label, "align center");
        jPanel.revalidate();
    }
    
    
    private void montarGraficos() {
        jPanelChart.removeAll();
        jPanelChart.setLayout(new MigLayout("wrap 2", "grow", "grow"));
                
        jPanelChart.add(dashboardController.getGraficoMovimentosArea(), "align center");
        jPanelChart.add(dashboardController.getGraficoMovimentosTipoVeiculo(), "align center");
        jPanelChart.add(new JPanel(), "height 5%, span, grow"); //espaco em branco
        jPanelChart.add(dashboardController.getGraficoValorTotalArea(), "span, grow");
        jPanelChart.validate();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelDashboard = new javax.swing.JPanel();
        jPanelChart = new javax.swing.JPanel();
        jPanelDashboardVagas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkVagaAberta = new javax.swing.JCheckBox();
        chkVagaFechada = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanelChartLayout = new javax.swing.GroupLayout(jPanelChart);
        jPanelChart.setLayout(jPanelChartLayout);
        jPanelChartLayout.setHorizontalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        jPanelChartLayout.setVerticalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelDashboardLayout = new javax.swing.GroupLayout(jPanelDashboard);
        jPanelDashboard.setLayout(jPanelDashboardLayout);
        jPanelDashboardLayout.setHorizontalGroup(
            jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDashboardLayout.setVerticalGroup(
            jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Dashboard", jPanelDashboard);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mostrar vagas:");

        chkVagaAberta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkVagaAberta.setText("Abertas");
        chkVagaAberta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVagaAbertaActionPerformed(evt);
            }
        });

        chkVagaFechada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkVagaFechada.setText("Ocupadas");
        chkVagaFechada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVagaFechadaActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Área", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel.setLayout(null);
        jScrollPane1.setViewportView(jPanel);

        javax.swing.GroupLayout jPanelDashboardVagasLayout = new javax.swing.GroupLayout(jPanelDashboardVagas);
        jPanelDashboardVagas.setLayout(jPanelDashboardVagasLayout);
        jPanelDashboardVagasLayout.setHorizontalGroup(
            jPanelDashboardVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardVagasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDashboardVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDashboardVagasLayout.createSequentialGroup()
                        .addGap(0, 383, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkVagaAberta)
                        .addGap(10, 10, 10)
                        .addComponent(chkVagaFechada))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanelDashboardVagasLayout.setVerticalGroup(
            jPanelDashboardVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardVagasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDashboardVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkVagaAberta)
                    .addComponent(chkVagaFechada)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Vagas", jPanelDashboardVagas);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkVagaAbertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVagaAbertaActionPerformed
        atualizarVagas(ConfiguracaoSistema.getIdArea());
    }//GEN-LAST:event_chkVagaAbertaActionPerformed

    private void chkVagaFechadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVagaFechadaActionPerformed
        atualizarVagas(ConfiguracaoSistema.getIdArea());
    }//GEN-LAST:event_chkVagaFechadaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean trocarUsuario = new TrocarUsuario().abrirPergunta();
        if (trocarUsuario) {
            this.dispose();
            principalSistema.trocarUsuario();
        } else {
            principalSistema.sair();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private static javax.swing.JCheckBox chkVagaAberta;
    private static javax.swing.JCheckBox chkVagaFechada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar;
    private static javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelChart;
    private javax.swing.JPanel jPanelDashboard;
    private javax.swing.JPanel jPanelDashboardVagas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
