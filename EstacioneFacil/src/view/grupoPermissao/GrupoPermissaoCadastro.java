package view.grupoPermissao;

import controller.PermissaoController;
import dao.GrupoPermissaoDao;
import java.awt.event.MouseEvent;
import java.util.List;
import model.GrupoPermissao;
import model.Permissao;
import model.util.ExceptionUtils;
import model.util.MensageiroUtils;
import view.classes.JDialogCadastro;
import view.permissao.PermissaoTableCellRender;
import view.permissao.PermissaoTableModel;
import view.permissao.botao.PermissaoBotaoCadastro;


/**
 *
 * @author Douglas
 */
public class GrupoPermissaoCadastro extends JDialogCadastro {
    
    private Permissao permissao;
    private GrupoPermissao grupoPermissao;
    private GrupoPermissaoDao grupoPermissaoDao;
    private PermissaoController permissaoController;
    private List<Permissao> permissoes;
    

    public GrupoPermissaoCadastro(Object cadastroAnterior, GrupoPermissao grupoPermissao) {
        super("Grupo de Permissão");
        initComponents();

        this.grupoPermissao = grupoPermissao;
        this.grupoPermissaoDao = new GrupoPermissaoDao();
        this.permissaoController = new PermissaoController();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPermissoes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome:*");

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        tblPermissoes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPermissoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPermissoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPermissoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tblPermissoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPermissoesMouseClicked
        definirPermissoesTela(evt);
    }//GEN-LAST:event_tblPermissoesMouseClicked

    public void carregarCadastro() {
        carregarListaPermissoes();
        if (grupoPermissao.getId() != null) {
            carregarParaEdicao();
        }
    }

    public void carregarParaEdicao() {
        txtDescricao.setText(grupoPermissao.getDescricao());
    }

    public void carregarListaPermissoes() {
        permissoes = permissaoController.getListaPermissoes(grupoPermissao);
        tblPermissoes.setModel(new PermissaoTableModel(permissoes));
        
        this.tblPermissoes.setSelectionMode(0);
        this.tblPermissoes.setRowHeight(25);
        
        PermissaoTableCellRender cellRender = new PermissaoTableCellRender();
        this.tblPermissoes.getColumnModel().getColumn(0).setPreferredWidth(250);
        this.tblPermissoes.getColumnModel().getColumn(1).setPreferredWidth(50);
        this.tblPermissoes.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.tblPermissoes.getColumnModel().getColumn(2).setCellRenderer(cellRender);
    }
    
    private void definirPermissoesTela(MouseEvent evt) {
        permissao = ((PermissaoTableModel) tblPermissoes.getModel()).getDados().get(tblPermissoes.getSelectedRow());
        int coluna = tblPermissoes.columnAtPoint(evt.getPoint());
        
        if (coluna == 2) {
            if (!permissao.isVisualizar()) {
                MensageiroUtils.mensagemAlerta(this, "Selecione primeiro a permissão de visualização da tela!");
            } else {
                PermissaoBotaoCadastro permissaoBotaoCadastro = new PermissaoBotaoCadastro(this, permissao);
                permissao = permissaoBotaoCadastro.abrirTela();
            }
        }
    }
    
    public boolean verificaCamposObrigatorios() {
        if (txtDescricao.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha o nome do grupo!");
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
            grupoPermissaoDao.gravar(grupoPermissao);
            permissaoController.gravarPermissoes(grupoPermissao.getId(), permissoes);

            mostrarMensagemSucesso();

            if (getCadastroAnterior() instanceof GrupoPermissaoLista) {
                ((GrupoPermissaoLista) getCadastroAnterior()).pesquisar();
            }
            dispose();
        } catch(Exception e) {
            ExceptionUtils.mostrarErro(this, "Ocorreu um erro ao gravar o registro!");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPermissoes;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
