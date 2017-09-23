package view;

import config.ConfiguracaoSistema;
import controller.menu.MenuController;
import dao.AreaDao;
import dao.VagaDao;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static javax.swing.border.TitledBorder.CENTER;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import model.Area;
import model.Movimentacao;
import model.Vaga;
import model.vo.SelectItemVO;
import net.miginfocom.swing.MigLayout;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.movimentacao.MovimentacaoEntradaCadastro;

/**
 *
 * @author Douglas
 */
public class Principal extends javax.swing.JFrame {
    
    public Principal() {
        initComponents();
        
        setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        setTitle(ConfiguracaoSistema.NOME);
        setExtendedState(MAXIMIZED_BOTH);
        setJMenuBar(new MenuController().montarMenu());
       
        popularComboArea();
        atualizarVagas(null);
    }
      
    public void popularComboArea() {
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(false)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
    }
        
    private void atualizarVagas(Long idArea) {        
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

            jButton.setText(vaga.getCodigo() + " - " + vaga.getTipoVeiculo().getDescricao());
            jPanel.add(jButton, "align center top");
        }   
        jPanel.revalidate();
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
        jLabel2 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBorder(null);

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Área", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel.setLayout(null);
        jScrollPane1.setViewportView(jPanel);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Área:");

        comboArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaActionPerformed(evt);
            }
        });

        jButton1.setText("Teste webcam");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(333, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(295, 295, 295))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAreaActionPerformed
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea != null) {
            atualizarVagas(itemArea.getId());
        }
    }//GEN-LAST:event_comboAreaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new MovimentacaoEntradaCadastro(this, new Movimentacao()).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
