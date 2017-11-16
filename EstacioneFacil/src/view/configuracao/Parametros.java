package view.configuracao;

import config.ConfiguracaoSistema;
import dao.ParametroDao;
import model.Parametro;
import model.util.ExceptionUtils;
import model.util.MensageiroUtils;
import view.classes.JDialogLista;

public class Parametros extends JDialogLista {

    private Parametro parametro;
    private ParametroDao parametroDao;

    public Parametros() {
        super("Parâmetros");
        initComponents();

        this.parametroDao = new ParametroDao();
        this.parametro = parametroDao.buscarParametros();        
               
        carregarCadastro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        chkBuscarInfoWebservice = new javax.swing.JCheckBox();
        chkTirarFotoVeiculo = new javax.swing.JCheckBox();
        chkUtilizarAuditoria = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGravar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/check.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/times.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        chkBuscarInfoWebservice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkBuscarInfoWebservice.setText("Buscar informações do veículo via WerbService");

        chkTirarFotoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkTirarFotoVeiculo.setText("Tirar foto do veículo ao registrar uma nova movimentação");

        chkUtilizarAuditoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkUtilizarAuditoria.setText("Habilitar auditoria das tabelas");
        chkUtilizarAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUtilizarAuditoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkTirarFotoVeiculo)
                            .addComponent(chkBuscarInfoWebservice)
                            .addComponent(chkUtilizarAuditoria))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(chkBuscarInfoWebservice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkTirarFotoVeiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkUtilizarAuditoria)
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        gravar();
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkUtilizarAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUtilizarAuditoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkUtilizarAuditoriaActionPerformed

    public void carregarCadastro() {   
        chkBuscarInfoWebservice.setSelected(parametro.isUtilizarWebService());
        chkTirarFotoVeiculo.setSelected(parametro.isTirarFotoVeiculo());
        chkUtilizarAuditoria.setSelected(parametro.isUtilizarAuditoria());
    }
    
    public boolean verificaCamposObrigatorios() {
       
        return true;
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            parametro.setUtilizarWebService(chkBuscarInfoWebservice.isSelected());
            parametro.setTirarFotoVeiculo(chkTirarFotoVeiculo.isSelected());
            parametro.setUtilizarWebService(chkUtilizarAuditoria.isSelected());
            parametroDao.gravar(parametro);
            MensageiroUtils.mensagemSucesso(this, ConfiguracaoSistema.MSG_REGISTRO_SUCESSO);
            
            dispose();
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JCheckBox chkBuscarInfoWebservice;
    private javax.swing.JCheckBox chkTirarFotoVeiculo;
    private javax.swing.JCheckBox chkUtilizarAuditoria;
    // End of variables declaration//GEN-END:variables

}
