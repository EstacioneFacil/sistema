package view.movimentacao;

import config.ConfiguracaoSistema;
import controller.AnexoController;
import dao.MovimentacaoDao;
import dao.ParametroDao;
import dao.TabelaPrecoDao;
import dao.VagaDao;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Anexo;
import model.Movimentacao;
import model.Parametro;
import model.TabelaPreco;
import model.Vaga;
import model.constant.TipoPrecoEnum;
import model.util.ExceptionUtils;
import model.util.FormatacaoUtils;
import model.util.MensageiroUtils;
import model.vo.InfoVeiculoVO;
import model.vo.SelectItemVO;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;
import service.Service;
import view.Principal;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.ExibeQuadro;
import view.classes.JDialogCadastro;
import view.classes.VideoCaptura;

public class MovimentacaoCadastro extends JDialogCadastro {
    
    private final Logger logger = Logger.getLogger(getClass().getName());

    private Movimentacao movimentacao;
    private Service service;
    private MovimentacaoDao movimentacaoDao;
    private AnexoController anexoController;
    private VideoCaptura webCam;
    private ExibeQuadro exibeQuadro;
    private Thread executor;
    private Parametro parametro;
    private ParametroDao parametroDao;
    

    public MovimentacaoCadastro(Object cadastroAnterior, Movimentacao movimentacao, boolean dashboard) {
        super("Movimentação - Entrada");
        initComponents();
        
        this.movimentacao = movimentacao;
        this.service = new Service();
        this.movimentacaoDao = new MovimentacaoDao();
        this.anexoController = new AnexoController();
        this.parametroDao = new ParametroDao();
        this.parametro = parametroDao.buscarParametros();
        
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro(dashboard);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        txtHoraEntrada = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        txtPlaca = new javax.swing.JFormattedTextField();
        lblWebcam = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInformacoes = new javax.swing.JTextArea();
        comboArea = new javax.swing.JComboBox<>();
        comboTipoVeiculo = new javax.swing.JComboBox<>();
        comboVaga = new javax.swing.JComboBox<>();
        txtValor = new javax.swing.JFormattedTextField();
        lblValor = new javax.swing.JLabel();
        txtDataSaida = new javax.swing.JFormattedTextField();
        txtHoraSaida = new javax.swing.JFormattedTextField();
        lblDataSaida = new javax.swing.JLabel();
        lblHoraSaida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Data de entrada:*");

        txtDataEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Hora de entrada:*");

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
        txtPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPlacaFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Área:*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Vaga:*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tipo de veículo:*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Placa:*");

        txtInformacoes.setColumns(20);
        txtInformacoes.setRows(5);
        jScrollPane1.setViewportView(txtInformacoes);

        comboArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaActionPerformed(evt);
            }
        });

        comboTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboVaga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboVaga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValor.setText("Valor:*");
        lblValor.setToolTipText("Grupo de Permissão");

        txtDataSaida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataSaida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoraSaida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSaida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblDataSaida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDataSaida.setText("Data de saída:*");

        lblHoraSaida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHoraSaida.setText("Hora de saída:*");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTipoVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboVaga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addGap(100, 100, 100))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(lblValor)
                                    .addComponent(lblDataSaida)
                                    .addComponent(lblHoraSaida))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDataSaida)
                                        .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtPlaca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(txtHoraEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(lblWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValor)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataSaida))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoraSaida))
                        .addGap(82, 82, 82)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
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

    private void txtPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlacaFocusLost
        if (parametro.isUtilizarWebService()) {
            buscarInformacoesVeiculo();
        }
    }//GEN-LAST:event_txtPlacaFocusLost

    private void comboAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAreaActionPerformed
        selecionarArea();
    }//GEN-LAST:event_comboAreaActionPerformed

    public void carregarCadastro(boolean dashboard) {                  
        FormatacaoUtils.reformatarData(txtDataEntrada);
        FormatacaoUtils.reformatarHora(txtHoraEntrada);
        FormatacaoUtils.reformatarPlaca(txtPlaca);
        FormatacaoUtils.formatarCampoValor(txtValor);
        
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(true)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
        
        comboTipoVeiculo.setModel(new ComboModel(CombosDinamicos.getTiposVeiculo(true)));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedIndex(0);
        
        popularComboVagas(movimentacao.getVaga(), movimentacao.getVaga() != null ? movimentacao.getVaga().getArea().getId() : null);
          
        if (dashboard) {
            comboArea.setEnabled(false);
            comboTipoVeiculo.setEnabled(false);
            comboVaga.setEnabled(false);
            
            ((ComboModel) comboArea.getModel()).setSelectedItem(new SelectItemVO(movimentacao.getVaga().getArea().getId(), movimentacao.getVaga().getArea().getDescricao()));
            ((ComboModel) comboTipoVeiculo.getModel()).setSelectedItem(new SelectItemVO(movimentacao.getVaga().getTipoVeiculo().getId(), movimentacao.getVaga().getTipoVeiculo().getDescricao()));
            setarVagaCombo(movimentacao.getVaga());
        }

        txtDataEntrada.setEnabled(false);
        txtHoraEntrada.setEnabled(false);
        txtDataSaida.setEnabled(false);
        txtHoraSaida.setEnabled(false);
        
        txtDataEntrada.setText(FormatacaoUtils.getDataString(movimentacao.getDataHoraEntrada()));
        txtHoraEntrada.setText(FormatacaoUtils.getHoraString(movimentacao.getDataHoraEntrada()));
                
        if (movimentacao.getId() != null) {
            carregarParaEdicao();
        } else {
            if (parametro.isTirarFotoVeiculo()) {
                //carrega webcam
                webCam = new VideoCaptura();
                exibeQuadro = new ExibeQuadro(webCam, lblWebcam);
                executor = new Thread(exibeQuadro);
                executor.start();
            } else {
                lblWebcam.setLayout(new MigLayout("wrap 3", "grow", "grow"));
                JLabel label = new JLabel("Câmera desativada nos parêmetros!");
                label.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblWebcam.add(label, "align center center");
                lblWebcam.revalidate();
            }
            
            //libera so quando for saida
            lblValor.setVisible(false);
            txtValor.setVisible(false);
            lblDataSaida.setVisible(false);
            txtDataSaida.setVisible(false);
            lblHoraSaida.setVisible(false);
            txtHoraSaida.setVisible(false);
        }
    }
    
    public void carregarParaEdicao() {
        ((ComboModel) comboArea.getModel()).setSelectedItem(new SelectItemVO(movimentacao.getVaga().getArea().getId(), movimentacao.getVaga().getArea().getDescricao()));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedItem(new SelectItemVO(movimentacao.getVaga().getTipoVeiculo().getId(), movimentacao.getVaga().getTipoVeiculo().getDescricao()));
        setarVagaCombo(movimentacao.getVaga());
        
        txtPlaca.setText(movimentacao.getPlaca());
        txtInformacoes.setText(movimentacao.getInfoVeiculo());
        
        txtPlaca.setEnabled(false);
        txtInformacoes.setEnabled(false);
        txtDataEntrada.setEnabled(false);
        txtHoraEntrada.setEnabled(false);
        if (movimentacao.getValor() != null) {
            txtValor.setEnabled(false);
            comboArea.setEnabled(false);
            comboTipoVeiculo.setEnabled(false);
            comboVaga.setEnabled(false);
        }
                
        if (movimentacao.getDataHoraSaida() == null) {
            movimentacao.setDataHoraSaida(new Date());
        }
        txtDataSaida.setText(FormatacaoUtils.getDataString(movimentacao.getDataHoraSaida()));
        txtHoraSaida.setText(FormatacaoUtils.getHoraString(movimentacao.getDataHoraSaida()));
        
        if (movimentacao.getValor() == null || movimentacao.getValor() == 0.0) {
            movimentacao = calcularValorTotal(movimentacao);
        }
        if (movimentacao.getValor() != null && movimentacao.getValor() != 0.0) {
            txtValor.setText(FormatacaoUtils.getValorFormatado(movimentacao.getValor()));
        } else {
            txtValor.setText(null);
        }

        if (movimentacao.getAnexo() != null) {
            try {
                File anexo = new File(movimentacao.getAnexo().getCaminhoCompleto());
                BufferedImage img = ImageIO.read(anexo);
                
                Icon icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(340, 300, Image.SCALE_SMOOTH));
                
                this.lblWebcam.setIcon(icon);
                this.lblWebcam.repaint();
            } catch(Exception e) {
                logger.error("Erro ao exibir imagem do veículo", e);
            }
        } else {
            lblWebcam.setLayout(new MigLayout("wrap 3", "grow", "grow"));
            JLabel label = new JLabel("Nenhuma foto encontrada!");
            label.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblWebcam.add(label, "align center center");
            lblWebcam.revalidate();
        }
    }
    
    public Movimentacao calcularValorTotal(Movimentacao movimentacao) {
        TabelaPreco tabelaPreco = new TabelaPrecoDao().buscarPorVaga(movimentacao.getIdVaga());
        if (tabelaPreco == null) {
            tabelaPreco = new TabelaPrecoDao().buscarPorArea(movimentacao.getVaga().getIdArea()); 
        }
        if (tabelaPreco != null) {
            movimentacao.setIdTabelaPreco(tabelaPreco.getId());
            Double valorTabela = tabelaPreco.getValor();

            if (tabelaPreco.getTipo().trim().equals(TipoPrecoEnum.HORA.getKey())) {
                int diferenca = FormatacaoUtils.diferencaHoras(movimentacao.getDataHoraEntrada(), movimentacao.getDataHoraSaida());
                movimentacao.setValor(diferenca * valorTabela);
            } else if (tabelaPreco.getTipo().trim().equals(TipoPrecoEnum.DIA.getKey())) {
                int dias = FormatacaoUtils.diferencaDias(movimentacao.getDataHoraSaida(), movimentacao.getDataHoraEntrada());
                movimentacao.setValor(dias * valorTabela);
            }
        }
        return movimentacao;	
    }
    
    public void selecionarArea() {
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea != null) {
            popularComboVagas(null, itemArea.getId());
        } 
    }
    
    public void popularComboVagas(Vaga vaga, Long idArea) {
        try {
            comboVaga.setModel(new ComboModel(CombosDinamicos.getVagas(true, idArea)));
            setarVagaCombo(vaga);
        } catch(Exception e){
            logger.error(e.getMessage());
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }
    
    public void setarVagaCombo(Vaga vaga) {
        if (vaga != null) {
            ((ComboModel) comboVaga.getModel()).setSelectedItem(new SelectItemVO(vaga.getId(), vaga.getCodigo()));
        } else {
            ((ComboModel) comboVaga.getModel()).setSelectedIndex(0);
        }
    }

    public boolean verificaCamposObrigatorios() {
        SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
        if (itemArea.getId() == null) {
            MensageiroUtils.mensagemAlerta(this, "Selecione uma área!");
            return false;
        }
        SelectItemVO itemTipoVeiculo = ((ComboModel) comboTipoVeiculo.getModel()).getSelectedItem();
        if (itemTipoVeiculo.getId() == null) {
            MensageiroUtils.mensagemAlerta(this, "Selecione um tipo de veículo!");
            return false;
        }
        SelectItemVO itemVaga = ((ComboModel) comboVaga.getModel()).getSelectedItem();
        if (itemVaga.getId() != null) {
            movimentacao.setIdVaga(itemVaga.getId());
            movimentacao.setVaga(new VagaDao().findById(movimentacao.getIdVaga()));
        } else {
            movimentacao.setIdVaga(null);
        }
        String placa = FormatacaoUtils.removerFormatacao(txtPlaca.getText());
        if (placa.trim().equals("")) {
            MensageiroUtils.mensagemAlerta(this, "Preencha a placa do veículo!");
            return false;
        } else {
            movimentacao.setPlaca(placa);
        }
        if (movimentacao.getId() != null) {
            String valor = FormatacaoUtils.removerFormatacaoValor(txtValor.getText());
            if (valor.trim().equals("")) {
                MensageiroUtils.mensagemAlerta(this, "Preencha o valor!");
                return false;
            } else {
                movimentacao.setValor(Double.valueOf(valor));
            }
        }
        if (!txtInformacoes.getText().isEmpty()) {
            movimentacao.setInfoVeiculo(txtInformacoes.getText());
        }
        movimentacao.setIdUsuario(ConfiguracaoSistema.getUsuarioLogado().getId());
        
        //verifica se ja esta ocupada
        Movimentacao movimentacaoAux = movimentacaoDao.buscarVagaAberta(movimentacao.getId(), movimentacao.getIdVaga());
        if (movimentacaoAux != null) {
            MensageiroUtils.mensagemAlerta(this, "Esta vaga já está ocupada!");
            return false;
        }
        return true;
    }

    public void buscarInformacoesVeiculo() {
        InfoVeiculoVO informacoes = service.buscarInfoVeiculoWS(txtPlaca.getText());
        if (informacoes != null && informacoes.getMensagem() != null && informacoes.getMensagem().equals("sucesso")) {
            txtInformacoes.setText(informacoes.toString());
        } else {
            txtInformacoes.setText("Não foi possível obter informações do veículo!");
        }
    }
    
    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }          
            if (parametro.isTirarFotoVeiculo()) {
                if (movimentacao.getIdAnexo() == null) {
                    movimentacao.setIdAnexo(gravarImagem());                
                }
                desligarCamera();
            }
            
            movimentacaoDao.gravar(movimentacao);
                        
            MensageiroUtils.mensagemSucesso(this, "Movimentação registrada com sucesso!");
            
            if (getCadastroAnterior() instanceof Principal) {
                ((Principal) getCadastroAnterior()).atualizarVagas(movimentacao.getVaga().getIdArea());
            }
            
            if (getCadastroAnterior() instanceof MovimentacaoLista) {
                ((MovimentacaoLista) getCadastroAnterior()).pesquisar();
            }
            Principal.atualizarVagas(movimentacao.getVaga().getIdArea());
            dispose();
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }
    
    private void desligarCamera() {
        if (parametro.isTirarFotoVeiculo()) {
            if (movimentacao.getId() == null) {
                executor.suspend();
                exibeQuadro.desligarCamera();
            }
        }
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
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboTipoVeiculo;
    private javax.swing.JComboBox<String> comboVaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblHoraSaida;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblWebcam;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    private javax.swing.JFormattedTextField txtHoraEntrada;
    private javax.swing.JFormattedTextField txtHoraSaida;
    private javax.swing.JTextArea txtInformacoes;
    private javax.swing.JFormattedTextField txtPlaca;
    private javax.swing.JFormattedTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
