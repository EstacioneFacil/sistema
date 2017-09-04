package view.vaga;

import dao.VagaDao;
import model.Vaga;
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
public class VagaCadastro extends JDialogCadastro {

    private Vaga vaga;
    private VagaDao vagaDao;

    public VagaCadastro(Object cadastroAnterior, Vaga vaga) {
        super("Vaga");
        initComponents();

        this.vaga = vaga;
        this.vagaDao = new VagaDao();
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboTipoVeiculo = new javax.swing.JComboBox<>();
        txtDescricao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Área:*");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo.setToolTipText("");

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
        jLabel2.setText("Código:*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de veículo:*");

        comboArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descrição:*");
        jLabel5.setToolTipText("Grupo de Permissão");

        comboTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescricao.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 129, Short.MAX_VALUE)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipoVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDescricao))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
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
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(true)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
        
        comboTipoVeiculo.setModel(new ComboModel(CombosDinamicos.getTiposVeiculo(true)));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedIndex(0);
        
        if (vaga.getId() != null) {
            carregarParaEdicao();
        }
    }
    
    public void carregarParaEdicao() {
        txtCodigo.setText(vaga.getCodigo());
        txtDescricao.setText("");        
        ((ComboModel) comboArea.getModel()).setSelectedItem(new SelectItemVO(vaga.getArea().getId(), vaga.getArea().getDescricao()));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedItem(new SelectItemVO(vaga.getTipoVeiculo().getId(), vaga.getTipoVeiculo().getDescricao()));
    }

    public boolean verificaCamposObrigatorios() {
        if (txtCodigo.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha o código!");
            return false;
        } else {
            vaga.setCodigo(txtCodigo.getText());
        }
        if (txtDescricao.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a descrição!");
            return false;
        } else {
//            vaga.setDescricao(txtDescricao.getText());
        }
              
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea.getId() != null) {
            vaga.setIdArea(itemArea.getId());
        } else {
            MensageiroUtils.mensagemAlerta(this, "Selecione uma área!");
            return false;
        }
        
        SelectItemVO itemTipoVeiculo = ((ComboModel) comboTipoVeiculo.getModel()).getSelectedItem();
        if (itemTipoVeiculo.getId() != null) {
            vaga.setIdTipoVeiculo(itemTipoVeiculo.getId());
        } else {
            MensageiroUtils.mensagemAlerta(this, "Selecione um tipo de veículo!");
            return false;
        }
        return true;
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            Vaga vagaAux = vagaDao.buscarVaga(vaga);
            if (vagaAux != null) {
                MensageiroUtils.mensagemAlerta(this, "Já existe uma vaga com este código!");
            } else {
                vagaDao.gravar(vaga);
                mostrarMensagemSucesso();

                if (getCadastroAnterior() instanceof VagaLista) {
                    ((VagaLista) getCadastroAnterior()).pesquisar();
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
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboTipoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
