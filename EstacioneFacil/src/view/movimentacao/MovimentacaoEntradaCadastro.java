package view.movimentacao;

import config.ConfiguracaoSistema;
import controller.AnexoController;
import dao.MovimentacaoDao;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.Anexo;
import model.Movimentacao;
import model.util.ExceptionUtils;
import model.util.FormatacaoUtils;
import model.util.MensageiroUtils;
import org.apache.log4j.Logger;
import view.Principal;
import view.classes.ExibeQuadro;
import view.classes.JDialogCadastro;
import view.classes.VideoCaptura;

/**
 *
 * @author Jonas
 */
public class MovimentacaoEntradaCadastro extends JDialogCadastro {
    
    private final Logger logger = Logger.getLogger(getClass().getName());

    private Movimentacao movimentacao;
    private MovimentacaoDao movimentacaoDao;
    private AnexoController anexoController;
    private VideoCaptura webCam;
    private ExibeQuadro exibeQuadro;
    private Thread executor;

    public MovimentacaoEntradaCadastro(Object cadastroAnterior, Movimentacao movimentacao) {
        super("Movimentação - Entrada");
        initComponents();

        this.movimentacao = movimentacao;
        this.movimentacaoDao = new MovimentacaoDao();
        this.anexoController = new AnexoController();
        
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        txtHoraEntrada = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        txtPlaca = new javax.swing.JFormattedTextField();
        lblWebcam = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtTipoVeiculo = new javax.swing.JTextField();
        txtVaga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Data de entrada:*");

        txtDataEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Hora de entrada:*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Placa:*");

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/times.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGravar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/check.png"))); // NOI18N
        btnGravar.setText("Registrar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtVaga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Área:*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Vaga:*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tipo de veículo:*");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtVaga, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtTipoVeiculo))))
                        .addGap(40, 40, 40)
                        .addComponent(lblWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desligarCamera();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
         gravar();
    }//GEN-LAST:event_btnGravarActionPerformed

    public void carregarCadastro() {   
        //carrega webcam
        webCam = new VideoCaptura();
        exibeQuadro = new ExibeQuadro(webCam, lblWebcam);
        executor = new Thread(exibeQuadro);
        executor.start();
                
        FormatacaoUtils.reformatarData(txtDataEntrada);
        FormatacaoUtils.reformatarHora(txtHoraEntrada);
        FormatacaoUtils.reformatarPlaca(txtPlaca);
        
        txtArea.setEnabled(false);
        txtTipoVeiculo.setEnabled(false);
        txtVaga.setEnabled(false);
        txtDataEntrada.setEnabled(false);
        txtHoraEntrada.setEnabled(false);
        
        txtDataEntrada.setText(FormatacaoUtils.getDataString(movimentacao.getDataHoraEntrada()));
        txtHoraEntrada.setText(FormatacaoUtils.getHoraString(movimentacao.getDataHoraEntrada()));
        
        txtArea.setText(movimentacao.getVaga().getArea().getDescricao());
        txtTipoVeiculo.setText(movimentacao.getVaga().getTipoVeiculo().getDescricao());
        txtVaga.setText(movimentacao.getVaga().getCodigo());
        
        if (movimentacao.getId() != null) {
            carregarParaEdicao();
        }
    }
    
    public void carregarParaEdicao() {
        txtPlaca.setEnabled(false);
        
        txtPlaca.setText(movimentacao.getPlaca());
        
        if (movimentacao.getAnexo() != null) {
            try {
                File anexo = new File(movimentacao.getAnexo().getCaminhoCompleto());
                BufferedImage img = ImageIO.read(anexo);
                Icon icon = new ImageIcon(img);
                this.lblWebcam.setIcon(icon);
                this.lblWebcam.repaint();
            } catch(Exception e) {
                logger.error("Erro ao exibir imagem do veículo", e);
            }
        }
    }

    public boolean verificaCamposObrigatorios() {
        String placa = FormatacaoUtils.removerFormatacao(txtPlaca.getText());
        if (placa.trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a placa do veículo!");
            return false;
        } else {
            movimentacao.setPlaca(placa);
        }
        movimentacao.setIdUsuario(ConfiguracaoSistema.getUsuarioLogado().getId());
        return true;
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
            desligarCamera();
            
            movimentacao.setIdAnexo(gravarImagem());            
            movimentacaoDao.gravar(movimentacao);
            
            MensageiroUtils.mensagemSucesso(this, "Movimentação registrada com sucesso!");
            
            if (getCadastroAnterior() instanceof Principal) {
                ((Principal) getCadastroAnterior()).atualizarVagas(movimentacao.getVaga().getIdArea());
            }
            dispose();
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }
    
    private void desligarCamera() {
        executor.suspend();
    }
    
    private Long gravarImagem() {
        try {
            Anexo anexo = anexoController.salvarAnexo(webCam.capturaQuadroBufferedImage(), null);
            if (anexo != null) {
                return anexo.getId();
            }
        } catch(Exception e) {
            logger.error("Erro ao gerar imagem da camera", e);
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblWebcam;
    private javax.swing.JTextField txtArea;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtHoraEntrada;
    private javax.swing.JFormattedTextField txtPlaca;
    private javax.swing.JTextField txtTipoVeiculo;
    private javax.swing.JTextField txtVaga;
    // End of variables declaration//GEN-END:variables

}
