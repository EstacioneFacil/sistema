package view.vaga;

import dao.VagaDao;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import model.Vaga;
import model.vo.SelectItemVO;
import model.vo.VagaFiltroVO;
import org.apache.log4j.Logger;
import util.ExceptionUtils;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.JDialogLista;

/**
 *
 * @author Douglas
 */
public class VagaLista extends JDialogLista {

    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private VagaDao vagaDao;
    private VagaFiltroVO vagaFiltroVO;
    
    public VagaLista() {
        super("Vagas");
        initComponents();
                 
        definirPermissoes(btnIncluir, btnEditar, btnExcluir);
        
        vagaDao = new VagaDao();
        vagaFiltroVO = new VagaFiltroVO();
        
        carregarCombos();
                
        carregarLista();
    }

    public void carregarCombos() {
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(false)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
        
        comboTipoVeiculo.setModel(new ComboModel(CombosDinamicos.getTiposVeiculo(false)));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedIndex(0);
    }
    
    public void carregarLista() {
        List<Vaga> dados = new ArrayList<>();
        try {
            logger.debug("Buscando registros de vagas");
            dados = vagaDao.consultar(vagaFiltroVO);
        } catch(Exception e) {
            logger.error("Não foi possível carregar a lista de vagas!", e);
            ExceptionUtils.mostrarErro(this, "Não foi possível carregar a lista de vagas!");
        }
        this.tblVagas.setModel(new VagaTableModel(dados));
        this.lblTotal.setText(String.valueOf(dados.size()));

        ajustarTabela();
    }
    
    public void pesquisar() {
        vagaFiltroVO.setPesquisa(txtPesquisar.getText());
        
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea != null) {
            vagaFiltroVO.setIdArea(itemArea.getId());
        }
        
        SelectItemVO itemTipoVeiculo = ((ComboModel) comboTipoVeiculo.getModel()).getSelectedItem();
        if (itemTipoVeiculo != null) {
            vagaFiltroVO.setIdTipoVeiculo(itemTipoVeiculo.getId());
        }

        carregarLista();
    }
    
    public void ajustarTabela() {
        this.tblVagas.setSelectionMode(0);
        this.tblVagas.setRowHeight(25);
        
        VagaTableCellRender cellRender = new VagaTableCellRender();
        this.tblVagas.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.tblVagas.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tblVagas.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.tblVagas.getColumnModel().getColumn(3).setPreferredWidth(200);
        this.tblVagas.getColumnModel().getColumn(4).setCellRenderer(cellRender);
        this.tblVagas.getColumnModel().getColumn(4).setPreferredWidth(50);
         ((DefaultTableCellRenderer) tblVagas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(CENTER);
    }
    
    
    public Vaga getLinhaSelecionada() {
        if (tblVagas.getSelectedRow() != -1) {
            return ((VagaTableModel) tblVagas.getModel()).getDados().get(tblVagas.getSelectedRow());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboTipoVeiculo = new javax.swing.JComboBox<>();
        btnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblVagas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblVagas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblVagas);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Total de registros:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotal.setText("0");

        btnIncluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/add.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.setName("btnIncluir"); // NOI18N
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setName("btnExcluir"); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/search.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        txtPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Área:");

        comboArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador" }));
        comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de Veículo:");

        comboTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador" }));
        comboTipoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVeiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboArea, 0, 200, Short.MAX_VALUE)
                    .addComponent(comboTipoVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

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
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(550, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar)))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pesquisar();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        new VagaCadastro(this, new Vaga()).setVisible(true);
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            Vaga vaga = getLinhaSelecionada();
            if (excluir(vaga)) {
                vagaDao.excluir(vaga);
                mostrarMensagemExcluido();
                pesquisar();
            }
        } catch(Exception e) {
            logger.error("Não foi possível excluir o registro!", e);
            ExceptionUtils.mostrarErro(this, "Não foi possível excluir o registro!");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Vaga vaga = getLinhaSelecionada();
        if (vaga != null) {
            new VagaCadastro(this, vaga).setVisible(true);
        } else {
            mostrarMensagemEditar();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void comboAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAreaActionPerformed
        pesquisar();
    }//GEN-LAST:event_comboAreaActionPerformed

    private void comboTipoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoVeiculoActionPerformed
        pesquisar();
    }//GEN-LAST:event_comboTipoVeiculoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboTipoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblVagas;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
