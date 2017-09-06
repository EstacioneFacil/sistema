package view.auditoria;

import dao.AuditoriaDao;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import model.Auditoria;
import model.vo.AuditoriaFiltroVO;
import model.vo.SelectItemVO;
import org.apache.log4j.Logger;
import util.ExceptionUtils;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.JDialogLista;

/**
 *
 * @author Douglas
 */
public class AuditoriaLista extends JDialogLista {

    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private AuditoriaDao auditoriaDao;
    private AuditoriaFiltroVO auditoriaFiltroVO;
    
    public AuditoriaLista() {
        super("Auditoria");
        initComponents();
        
        btnDetalhes.setVisible(false);
                 
//        definirPermissoes(btnIncluir, btnEditar, btnExcluir);
        
        auditoriaDao = new AuditoriaDao();
        auditoriaFiltroVO = new AuditoriaFiltroVO();
        
        comboUsuario.setModel(new ComboModel(CombosDinamicos.getUsuarios()));
        ((ComboModel) comboUsuario.getModel()).setSelectedIndex(0);
                
        carregarLista();
    }

    public void carregarLista() {
        List<Auditoria> dados = new ArrayList<>();
        try {
            logger.debug("Buscando registros da auditoria");
            dados = auditoriaDao.consultar(auditoriaFiltroVO);
        } catch(Exception e) {
            logger.error("Não foi possível carregar a lista de auditoria!", e);
            ExceptionUtils.mostrarErro(this, "Não foi possível carregar a lista de auditoria!");
        }
        this.tblAuditoria.setModel(new AuditoriaTableModel(dados));
        this.lblTotal.setText(String.valueOf(dados.size()));

        ajustarTabela();
    }
    
    public void pesquisar() {
        SelectItemVO itemUsuario = ((ComboModel) comboUsuario.getModel()).getSelectedItem();
        if (itemUsuario != null) {
            auditoriaFiltroVO.setIdUsuario(itemUsuario.getId());
        }
        carregarLista();
    }
    
    public void ajustarTabela() {
        this.tblAuditoria.setSelectionMode(0);
        this.tblAuditoria.setRowHeight(25);
        
        AuditoriaTableCellRender cellRender = new AuditoriaTableCellRender();
        this.tblAuditoria.getColumnModel().getColumn(0).setCellRenderer(cellRender);
        this.tblAuditoria.getColumnModel().getColumn(0).setPreferredWidth(120);
        this.tblAuditoria.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tblAuditoria.getColumnModel().getColumn(2).setPreferredWidth(80);
        this.tblAuditoria.getColumnModel().getColumn(3).setPreferredWidth(250);
        this.tblAuditoria.getColumnModel().getColumn(4).setCellRenderer(cellRender);
        this.tblAuditoria.getColumnModel().getColumn(4).setPreferredWidth(50);
         ((DefaultTableCellRenderer) tblAuditoria.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(CENTER);
    }
    
    
    public Auditoria getLinhaSelecionada() {
        if (tblAuditoria.getSelectedRow() != -1) {
            return ((AuditoriaTableModel) tblAuditoria.getModel()).getDados().get(tblAuditoria.getSelectedRow());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAuditoria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnDetalhes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblAuditoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAuditoria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAuditoria);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Total de registros:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotal.setText("0");

        btnDetalhes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDetalhes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/add.png"))); // NOI18N
        btnDetalhes.setText("Detalhes");
        btnDetalhes.setName("btnDetalhes"); // NOI18N
        btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuário:");

        comboUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador" }));
        comboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(637, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnDetalhes)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesActionPerformed
//        new AuditoriaCadastro(this, new Usuario()).setVisible(true);
    }//GEN-LAST:event_btnDetalhesActionPerformed

    private void comboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuarioActionPerformed
        pesquisar();
    }//GEN-LAST:event_comboUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalhes;
    private javax.swing.JComboBox<String> comboUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblAuditoria;
    // End of variables declaration//GEN-END:variables
}
