package view.movimentacao;

import dao.MovimentacaoDao;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import model.Movimentacao;
import model.vo.SelectItemVO;
import org.apache.log4j.Logger;
import model.util.ExceptionUtils;
import model.util.FormatacaoUtils;
import model.vo.MovimentacaoFiltroVO;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.JDialogLista;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Douglas
 */
public class MovimentacaoLista extends JDialogLista {

    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private MovimentacaoDao movimentacaoDao;
    private MovimentacaoFiltroVO movimentacaoFiltroVO;
    
    public MovimentacaoLista() {
        super("Movimentações");
        initComponents();
                 
        definirPermissoes(btnIncluir, btnEditar, btnExportarXml);
        
        movimentacaoDao = new MovimentacaoDao();
        movimentacaoFiltroVO = new MovimentacaoFiltroVO();
        
        carregarCombos();
        carregarLista();
    }

    public void carregarCombos() {
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(false)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
        
        comboTipoVeiculo.setModel(new ComboModel(CombosDinamicos.getTiposVeiculo(false)));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedIndex(0);
        
        comboVaga.setModel(new ComboModel(CombosDinamicos.getVagas(false, null, null)));
        ((ComboModel) comboVaga.getModel()).setSelectedIndex(0);
    }
    
    public void carregarLista() {
        List<Movimentacao> dados = new ArrayList<>();
        try {
            logger.debug("Buscando registros da tabela de movimentações");
            dados = movimentacaoDao.consultar(movimentacaoFiltroVO);
        } catch(Exception e) {
            logger.error("Não foi possível carregar a lista de movimentações!", e);
            ExceptionUtils.mostrarErro(this, "Não foi possível carregar a lista de movimentações!");
        }
        this.tblMovimentacao.setModel(new MovimentacaoTableModel(dados));
        this.lblTotal.setText(String.valueOf(dados.size()));

        Double valorTotal = movimentacaoDao.valorTotal(movimentacaoFiltroVO);
        String valor = "Total: R$ 0,00";
        if (valorTotal != null && valorTotal != 0.0) {
            valor = "Total: R$ " + FormatacaoUtils.getValorFormatado(valorTotal);
        }
        this.lblValorTotal.setText(valor);
        
        ajustarTabela();
    }
    
    public void pesquisar() {
        movimentacaoFiltroVO.setPesquisa(txtPesquisar.getText());
                
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea != null) {
            movimentacaoFiltroVO.setIdArea(itemArea.getId());
        }
        SelectItemVO itemTipoVeiculo = ((ComboModel) comboTipoVeiculo.getModel()).getSelectedItem();
        if (itemTipoVeiculo != null) {
            movimentacaoFiltroVO.setIdTipoVeiculo(itemTipoVeiculo.getId());
        }
        ((ComboModel) comboVaga.getModel()).setLista(CombosDinamicos.getVagas(false, movimentacaoFiltroVO.getIdArea(), movimentacaoFiltroVO.getIdTipoVeiculo()));
        SelectItemVO itemVaga = ((ComboModel) comboVaga.getModel()).getSelectedItem();
        if (itemVaga != null) {
            movimentacaoFiltroVO.setIdVaga(itemVaga.getId());
        }
        carregarLista();
    }
    
    public void ajustarTabela() {
        this.tblMovimentacao.setSelectionMode(0);
        this.tblMovimentacao.setRowHeight(25);
        
        MovimentacaoTableCellRender cellRender = new MovimentacaoTableCellRender();
        this.tblMovimentacao.getColumnModel().getColumn(0).setCellRenderer(cellRender);
        this.tblMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.tblMovimentacao.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.tblMovimentacao.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.tblMovimentacao.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.tblMovimentacao.getColumnModel().getColumn(4).setCellRenderer(cellRender);
        this.tblMovimentacao.getColumnModel().getColumn(4).setPreferredWidth(200);
        this.tblMovimentacao.getColumnModel().getColumn(5).setCellRenderer(cellRender);
        this.tblMovimentacao.getColumnModel().getColumn(5).setPreferredWidth(200);
        this.tblMovimentacao.getColumnModel().getColumn(6).setCellRenderer(cellRender);
        this.tblMovimentacao.getColumnModel().getColumn(6).setPreferredWidth(150);
        this.tblMovimentacao.getColumnModel().getColumn(7).setCellRenderer(cellRender);
        this.tblMovimentacao.getColumnModel().getColumn(7).setPreferredWidth(50);
        ((DefaultTableCellRenderer) tblMovimentacao.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(CENTER);
    }
    
    
    public Movimentacao getLinhaSelecionada() {
        if (tblMovimentacao.getSelectedRow() != -1) {
            return ((MovimentacaoTableModel) tblMovimentacao.getModel()).getDados().get(tblMovimentacao.getSelectedRow());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimentacao = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        btnExportarXml = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        comboTipoVeiculo = new javax.swing.JComboBox<String>();
        comboVaga = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        lblValorTotal = new javax.swing.JLabel();
        btnExcluir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblMovimentacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMovimentacao);

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

        btnExportarXml.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExportarXml.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/print.png"))); // NOI18N
        btnExportarXml.setText("Exportar Xml");
        btnExportarXml.setActionCommand("E");
        btnExportarXml.setName("btnExportarXml"); // NOI18N
        btnExportarXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarXmlActionPerformed(evt);
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
        comboArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador" }));
        comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de Veículo:");

        comboTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador" }));
        comboTipoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoVeiculoActionPerformed(evt);
            }
        });

        comboVaga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboVaga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador" }));
        comboVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVagaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Vaga:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboArea, 0, 200, Short.MAX_VALUE)
                    .addComponent(comboTipoVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboVaga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
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

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorTotal.setText("Total:");

        btnExcluir1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/delete.png"))); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.setName("btnExcluir"); // NOI18N
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValorTotal))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportarXml)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExportarXml, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal)
                    .addComponent(lblValorTotal))
                .addGap(20, 20, 20))
        );

        btnExportarXml.getAccessibleContext().setAccessibleName("Exportar XML");

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
        new MovimentacaoCadastro(this, new Movimentacao(new Date()), false).setVisible(true);
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExportarXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarXmlActionPerformed
       this.exportarXML();
    }//GEN-LAST:event_btnExportarXmlActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Movimentacao movimentacao = getLinhaSelecionada();
        if (movimentacao != null) {
            new MovimentacaoCadastro(this, movimentacao, false).setVisible(true);
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

    private void comboVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVagaActionPerformed
        pesquisar();
    }//GEN-LAST:event_comboVagaActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluir1ActionPerformed
    
    
    private void exportarXML()
    {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo XML", "xml");
        jFileChooser.setFileFilter(filter);
        int result = jFileChooser.showOpenDialog(this);

        List<Movimentacao> movimentacoes = new MovimentacaoDao().buscarTodos();
        
        try {
        
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("movimentacoes");
            doc.appendChild(rootElement);

            for(Movimentacao movimentacao : movimentacoes) {

                    // staff elements
                    Element elementoMovimentacao = doc.createElement("movimentacao");
                    rootElement.appendChild(elementoMovimentacao);

                    // id
                    Element elementoid = doc.createElement("id");
                    elementoid.appendChild(doc.createTextNode(movimentacao.getId().toString()));
                    elementoMovimentacao.appendChild(elementoid);

                    // data_hora_entrada
                    Element elementodatahora = doc.createElement("data_hora_entrada");
                    elementodatahora.appendChild(doc.createTextNode(movimentacao.getDataHoraEntrada().toString()));
                    elementoMovimentacao.appendChild(elementodatahora);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
               
        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = jFileChooser.getSelectedFile();
            String caminhoXML = arquivoSelecionado.getAbsolutePath();
            System.out.println("Caminho arquivo selecionado: " + caminhoXML);
            StreamResult resultXml = new StreamResult(new File(caminhoXML));
            transformer.transform(source, resultXml);
        }
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            
        
        } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
          } catch (TransformerException tfe) {
                tfe.printStackTrace();
          }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnExportarXml;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboTipoVeiculo;
    private javax.swing.JComboBox<String> comboVaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tblMovimentacao;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
