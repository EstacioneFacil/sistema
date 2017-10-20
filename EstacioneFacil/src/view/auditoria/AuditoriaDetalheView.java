package view.auditoria;


import dao.AuditoriaDao;
import dao.AuditoriaDetalheDao;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import model.Auditoria;
import model.AuditoriaDetalhe;
import org.apache.log4j.Logger;
import model.util.ExceptionUtils;
import view.classes.JDialogLista;

/**
 *
 * @author Douglas
 */
public class AuditoriaDetalheView extends JDialogLista {

    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private AuditoriaDao auditoriaDao;
    private AuditoriaDetalheDao auditoriaDetalheDao;
    //private AuditoriaFiltroVO auditoriaFiltroVO;
    
    
    public AuditoriaDetalheView(Auditoria auditoria) {
        super("Auditoria");
        initComponents();
        
        //btnDetalhes.setVisible(false);
                 
//        definirPermissoes(btnIncluir, btnEditar, btnExcluir);
        
//        auditoriaDao = new AuditoriaDao();
//        //auditoriaFiltroVO = new AuditoriaFiltroVO();
//        
        auditoriaDetalheDao = new AuditoriaDetalheDao();
//        auditoriaDetalheDao.obterDetalhes(1);
        
//        comboUsuario.setModel(new ComboModel(CombosDinamicos.getUsuarios()));
//        ((ComboModel) comboUsuario.getModel()).setSelectedIndex(0);
//                
        carregarLista(auditoria.getId());
    }

    public void carregarLista(Long auditoriaid) {
        List<AuditoriaDetalhe> dados = new ArrayList<>();
        try {
            logger.debug("Buscando registros da auditoria");
            System.out.println(auditoriaid);
            
            dados = auditoriaDetalheDao.obterDetalhes(auditoriaid);
            
        } catch(Exception e) {
            logger.error("Não foi possível carregar a lista de auditoria!", e);
            ExceptionUtils.mostrarErro(this, "Não foi possível carregar a lista de auditoria!");
        }
        this.tblAuditoria.setModel(new AuditoriaDetalheTableModel(dados));
        this.lblTotal.setText(String.valueOf(dados.size()));

        ajustarTabela();
    }
      
    public void ajustarTabela() {
        this.tblAuditoria.setSelectionMode(0);
        this.tblAuditoria.setRowHeight(25);
//        
//        AuditoriaTableCellRender cellRender = new AuditoriaTableCellRender();
//        this.tblAuditoria.getColumnModel().getColumn(0).setCellRenderer(cellRender);
//        this.tblAuditoria.getColumnModel().getColumn(0).setPreferredWidth(120);
//        this.tblAuditoria.getColumnModel().getColumn(1).setPreferredWidth(100);
//        this.tblAuditoria.getColumnModel().getColumn(2).setPreferredWidth(80);
//        this.tblAuditoria.getColumnModel().getColumn(3).setPreferredWidth(250);
//        this.tblAuditoria.getColumnModel().getColumn(4).setCellRenderer(cellRender);
//        this.tblAuditoria.getColumnModel().getColumn(4).setPreferredWidth(50);
//         ((DefaultTableCellRenderer) tblAuditoria.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(CENTER);
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
                        .addComponent(jScrollPane1)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblAuditoria;
    // End of variables declaration//GEN-END:variables
}
