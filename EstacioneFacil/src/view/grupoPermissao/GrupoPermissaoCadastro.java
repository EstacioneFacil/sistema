package view.grupoPermissao;

import dao.GrupoPermissaoDao;
import model.GrupoPermissao;
import util.ExceptionUtils;
import util.MensageiroUtils;
import view.classes.JDialogCadastro;


/**
 *
 * @author Douglas
 */
public class GrupoPermissaoCadastro extends JDialogCadastro {
    
    private GrupoPermissao grupoPermissao;
    private GrupoPermissaoDao grupoPermissaoDao;
    

    public GrupoPermissaoCadastro(Object cadastroAnterior, GrupoPermissao grupoPermissao) {
        super("Grupo de Permissão");
        initComponents();

        this.grupoPermissao = grupoPermissao;
        this.grupoPermissaoDao = new GrupoPermissaoDao();
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
        jLabel1.setText("Descrição:*");

        txtDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescricao.setToolTipText("");

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
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
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

    public void carregarCadastro() {
        if (grupoPermissao.getId() != null) {
            carregarParaEdicao();
        }
    }

    public void carregarParaEdicao() {
        txtDescricao.setText(grupoPermissao.getDescricao());
    }

    public boolean verificaCamposObrigatorios() {
        if (txtDescricao.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a descrição!");
            return false;
        } else {
            grupoPermissao.setDescricao(txtDescricao.getText());
        }
        return true;
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }       
//            if (grupoPermissaoDao.registroExiste(grupoPermissao)) {
//                mostrarMensagemExistente();
//            } else {
                grupoPermissaoDao.gravar(grupoPermissao);
                mostrarMensagemSucesso();

                if (getCadastroAnterior() instanceof GrupoPermissaoLista) {
                    ((GrupoPermissaoLista) getCadastroAnterior()).pesquisar();
                }
                dispose();
//            }
        } catch(Exception e) {
            ExceptionUtils.mostrarErro(this, "Ocorreu um erro ao gravar o registro!");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
