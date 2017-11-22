package view;

import config.ConfiguracaoSistema;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author Jonas
 */
public class TrocarUsuario extends JDialog {
    
    public TrocarUsuario() {
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        this.setTitle(ConfiguracaoSistema.NOME);
        
        initComponents();
    }
    
    public boolean abrirPergunta() {    
        this.setModal(true);
        carregarTela();
        
        this.setVisible(true);
        return radioTrocarUsuario.isSelected();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        radioTrocarUsuario = new javax.swing.JRadioButton();
        radioSair = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("O que deseja fazer?");

        btnContinuar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/check.png"))); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/logo.png"))); // NOI18N

        buttonGroup1.add(radioTrocarUsuario);
        radioTrocarUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioTrocarUsuario.setText("Trocar de usuário");

        buttonGroup1.add(radioSair);
        radioSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioSair.setText("Sair do sistema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnContinuar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioSair)
                            .addComponent(radioTrocarUsuario)
                            .addComponent(jLabel1))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(radioTrocarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioSair)))
                .addGap(20, 20, 20)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        continuar();
    }//GEN-LAST:event_btnContinuarActionPerformed

    public void carregarTela() {   
        radioSair.setSelected(true);
    }
    
    public void continuar() {
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton radioSair;
    private javax.swing.JRadioButton radioTrocarUsuario;
    // End of variables declaration//GEN-END:variables

}
