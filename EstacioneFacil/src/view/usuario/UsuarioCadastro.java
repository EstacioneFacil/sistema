package view.usuario;

import dao.UsuarioDao;
import model.Usuario;
import model.util.MD5Encryption;
import model.vo.SelectItemVO;
import util.ExceptionUtils;
import util.MensageiroUtils;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.JDialogCadastro;

/**
 *
 * @author Douglas
 */
public class UsuarioCadastro extends JDialogCadastro {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private MD5Encryption md5Encryption;

    public UsuarioCadastro(Object cadastroAnterior, Usuario usuario) {
        super("Usuário");
        initComponents();

        this.usuario = usuario;
        this.usuarioDao = new UsuarioDao();
        this.md5Encryption = new MD5Encryption();
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtConfirmaSenha = new javax.swing.JPasswordField();
        comboGrupoPermissao = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome:*");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setToolTipText("");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Senha:*");

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLogin.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Login:*");

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Conf. Senha:*");
        jLabel4.setToolTipText("Confirmar Senha");

        txtConfirmaSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboGrupoPermissao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboGrupoPermissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gp. Permissão:*");
        jLabel5.setToolTipText("Grupo de Permissão");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 130, Short.MAX_VALUE)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtSenha)
                            .addComponent(txtConfirmaSenha)
                            .addComponent(comboGrupoPermissao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboGrupoPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        comboGrupoPermissao.setModel(new ComboModel(CombosDinamicos.getGruposPermissao(true)));
        ((ComboModel) comboGrupoPermissao.getModel()).setSelectedIndex(0);
        
        if (usuario.getId() != null) {
            carregarParaEdicao();
        }
    }
    
    public void carregarParaEdicao() {
        txtNome.setText(usuario.getNome());
        txtLogin.setText(usuario.getLogin());
        txtSenha.setText(md5Encryption.decrypt(usuario.getSenha()));
        txtConfirmaSenha.setText(md5Encryption.decrypt(usuario.getSenha()));        
        ((ComboModel) comboGrupoPermissao.getModel()).setSelectedItem(new SelectItemVO(usuario.getGrupoPermissao().getId(), usuario.getGrupoPermissao().getDescricao()));
    }

    public boolean verificaCamposObrigatorios() {
        if (txtNome.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha o nome!");
            return false;
        } else {
            usuario.setNome(txtNome.getText());
        }
        if (txtLogin.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha o login!");
            return false;
        } else {
            usuario.setLogin(txtLogin.getText());
        }
        
        String senha = new String(txtSenha.getPassword());
        String confirmaSenha = new String(txtConfirmaSenha.getPassword());
        if (senha.trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a senha!");
            return false;
        } else if (confirmaSenha.trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a confirmação da senha!");
            return false;
        } else if (isSenhasIguais(senha, confirmaSenha)) {
            try {
                String senhaEncrypt = md5Encryption.encrypt(senha);
                usuario.setSenha(senhaEncrypt);
            } catch(Exception e) {
                MensageiroUtils.mensagemErro(this, "Ocorreu algum erro ao criar encriptação da senha!");
            }
        } else {
            MensageiroUtils.mensagemAlerta(this, "A senha e a confirmação não são iguais!");
            return false;
        }
        SelectItemVO itemGrupo = ((ComboModel) comboGrupoPermissao.getModel()).getSelectedItem();
        if (itemGrupo.getId() != null) {
            usuario.setIdGrupoPermissao(itemGrupo.getId());
        } else {
            MensageiroUtils.mensagemAlerta(this, "Selecione um grupo de permissão!");
            return false;
        }
        return true;
    }

    public boolean isSenhasIguais(String senha, String confirmaSenha) {
        return senha.equals(confirmaSenha);
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            Usuario usuarioAux = usuarioDao.buscarPorLogin(usuario);
            if (usuarioAux != null) {
                MensageiroUtils.mensagemAlerta(this, "Já existe um usuário cadastrado com este login!");
            } else {
                usuarioDao.gravar(usuario);
                mostrarMensagemSucesso();

                if (getCadastroAnterior() instanceof UsuarioLista) {
                    ((UsuarioLista) getCadastroAnterior()).pesquisar();
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
    private javax.swing.JComboBox<String> comboGrupoPermissao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtConfirmaSenha;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

}
