package view.tipoVeiculo;

import dao.TipoVeiculoDao;
import view.area.*;


import model.TipoVeiculo;
import util.ExceptionUtils;
import util.MensageiroUtils;
import view.classes.JDialogCadastro;

/**
 *
 * @author Jonas
 */
public class TipoVeiculoCadastro extends JDialogCadastro {

    private TipoVeiculo tipoVeiculo;
    private TipoVeiculoDao tipoVeiculoDao;

    public TipoVeiculoCadastro(Object cadastroAnterior, TipoVeiculo tipoVeiculo) {
        super("Tipo de veiculo");
        initComponents();

        this.tipoVeiculo = tipoVeiculo;
        this.tipoVeiculoDao = new TipoVeiculoDao();
        
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Descriçao:*");

        txtDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescricao.setToolTipText("");
        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
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

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    public void carregarCadastro() {   
        if (tipoVeiculo.getId() != null) {
            carregarParaEdicao();
        }
    }
    
    public void carregarParaEdicao() {
        txtDescricao.setText(tipoVeiculo.getDescricao());        
    }

    public boolean verificaCamposObrigatorios() {
        if (txtDescricao.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a descricao!");
            return false;
        } else {
            tipoVeiculo.setDescricao(txtDescricao.getText());
        }
        return true;
    }


    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            TipoVeiculo tipoVeiculoAux = tipoVeiculoDao.buscarDescricao(tipoVeiculo.getDescricao());
            if (tipoVeiculoAux != null) {
                MensageiroUtils.mensagemAlerta(this, "Já existe um tipo de veiculo cadastrado com esta descricao!!");
            } else {
                tipoVeiculoDao.gravar(tipoVeiculo);
                mostrarMensagemSucesso();

                if (getCadastroAnterior() instanceof TipoVeiculoLista) {
                    ((TipoVeiculoLista) getCadastroAnterior()).pesquisar();
                }
                dispose();
            }
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
