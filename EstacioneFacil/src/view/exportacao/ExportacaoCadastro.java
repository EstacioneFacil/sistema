package view.exportacao;

import config.ConfiguracaoSistema;
import controller.EmailController;
import controller.ExportacaoController;
import dao.MovimentacaoDao;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Movimentacao;
import model.util.ExceptionUtils;
import model.util.FormatacaoUtils;
import model.util.MensageiroUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonas
 */
public class ExportacaoCadastro extends javax.swing.JDialog {
    
    private final Logger logger = Logger.getLogger(getClass().getName());

    private boolean pdf;
    private boolean gerar;
    
    public ExportacaoCadastro() {
        this.setResizable(false);
        this.setModal(true);
        this.setIconImage(new ImageIcon(getClass().getResource(ConfiguracaoSistema.ICONE)).getImage());
        this.setTitle(ConfiguracaoSistema.NOME + " - Exportação");
        
        initComponents();
        carregarTela();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGerar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        comboFormato = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboAcao = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Formato:");

        btnGerar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/check.png"))); // NOI18N
        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
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

        comboFormato.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ação:");

        comboAcao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAcao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAcaoActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setToolTipText("");

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("E-mail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGerar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboAcao, 0, 250, Short.MAX_VALUE)
                            .addComponent(txtEmail)
                            .addComponent(comboFormato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboAcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        gerar();
    }//GEN-LAST:event_btnGerarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAcaoActionPerformed
        verificaCampo();
    }//GEN-LAST:event_comboAcaoActionPerformed

    public void carregarTela() {   
        comboFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PDF", "XML" }));
        comboAcao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerar", "Enviar por e-mail" }));
        
        lblEmail.setVisible(false);
        txtEmail.setVisible(false);
        this.validate();
        pack();
    }
    
    public void verificaCampo() {
        if (comboAcao.getSelectedIndex() == 1) {
            lblEmail.setVisible(true);
            txtEmail.setVisible(true);
            btnGerar.setText("Enviar");
        } else {
            lblEmail.setVisible(false);
            txtEmail.setVisible(false);
            btnGerar.setText("Gerar");
        }
        this.validate();
        pack();
    }

    public boolean verificaCamposObrigatorios() {
        if (comboAcao.getSelectedIndex() == 1 && txtEmail.getText().trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha o e-mail!");
            return false;
        } 
        pdf = comboFormato.getSelectedIndex() == 0;
        gerar = comboAcao.getSelectedIndex() == 0;
        return true;
    }


    public void gerar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            List<Movimentacao> movimentacoes = new MovimentacaoDao().buscarTodos();
            if (gerar) {
                JFileChooser jFileChooser = new JFileChooser();
                
                if (pdf) {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");
                    jFileChooser.setFileFilter(filter);
                    jFileChooser.setSelectedFile(new File("movimentacao.pdf"));
                } else {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo XML", "xml");
                    jFileChooser.setFileFilter(filter);
                    jFileChooser.setSelectedFile(new File("movimentacao.xml"));
                }
                int result = jFileChooser.showSaveDialog(this);

                 if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File arquivoSelecionado = jFileChooser.getSelectedFile();
                        String caminho = arquivoSelecionado.getAbsolutePath();
                        if (pdf) {
                            new ExportacaoController().gerarPDFMovimentacoes(movimentacoes, getValorTotal(), caminho);
                        } else {
                            new ExportacaoController().gerarXmlMovimentacoes(movimentacoes, caminho);
                        }
                    } catch (Exception e) {
                        logger.error("Erro ao gerar o "+(pdf ? "pdf" : "xml")+" das movimentacoes", e);
                        MensageiroUtils.mensagemErro(this, "Houve um erro na geração do "+(pdf ? "PDF" : "XML")+"!");
                    } 
                    MensageiroUtils.mensagemSucesso(this, (pdf ? "PDF" : "XML")+" gerado com sucesso!");
                }
            } else {
                String caminhoTmp = "C:\\tmp";
                if (pdf) {
                    caminhoTmp += "\\movimentacao.pdf";
                    new ExportacaoController().gerarPDFMovimentacoes(movimentacoes, getValorTotal(), caminhoTmp);
                } else {
                    caminhoTmp += "\\movimentacao.xml";
                    new ExportacaoController().gerarXmlMovimentacoes(movimentacoes, caminhoTmp);
                }
                boolean enviado = new EmailController().enviarEmail(txtEmail.getText().trim(), "Movimentações", "Prezado, segue em anexo os registros das movimentações!", caminhoTmp);
                if (enviado) {
                    MensageiroUtils.mensagemSucesso(this, (pdf ? "PDF" : "XML")+" enviado com sucesso!");
                } else {
                    MensageiroUtils.mensagemErro(this, "Ocorreu um erro ao enviar o "+(pdf ? "PDF" : "XML")+"!");
                }
            }           
            dispose();
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }
    
    private String getValorTotal() {
        Double valorTotal = new MovimentacaoDao().valorTotal(null);
        String valor = "R$ 0,00";
        if (valorTotal != null && valorTotal != 0.0) {
            valor = "R$ " + FormatacaoUtils.getValorFormatado(valorTotal);
        }
        return valor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGerar;
    private javax.swing.JComboBox<String> comboAcao;
    private javax.swing.JComboBox<String> comboFormato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables

}
