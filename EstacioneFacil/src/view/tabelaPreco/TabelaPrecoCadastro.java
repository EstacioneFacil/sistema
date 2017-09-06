package view.tabelaPreco;

import dao.TabelaPrecoDao;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import model.TabelaPreco;
import model.vo.SelectItemVO;
import org.apache.log4j.Logger;
import util.FormatacaoUtils;
import util.ExceptionUtils;
import util.MensageiroUtils;
import util.ValidacaoUtils;
import view.classes.ComboModel;
import view.classes.CombosDinamicos;
import view.classes.JDialogCadastro;

/**
 *
 * @author Douglas
 */
public class TabelaPrecoCadastro extends JDialogCadastro {
    
    private final Logger logger = Logger.getLogger(getClass().getName());

    private TabelaPreco tabelaPreco;
    private TabelaPrecoDao tabelaPrecoDao;

    public TabelaPrecoCadastro(Object cadastroAnterior, TabelaPreco tabelaPreco) {
        super("Tabela de Preço");
        initComponents();

        this.tabelaPreco = tabelaPreco;
        this.tabelaPrecoDao = new TabelaPrecoDao();
        setCadastroAnterior(cadastroAnterior);
        
        carregarCadastro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboTipoVeiculo = new javax.swing.JComboBox<>();
        comboVaga = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDataFim = new javax.swing.JFormattedTextField();
        txtDataInicio = new javax.swing.JFormattedTextField();
        txtValor = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Área:*");

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
        jLabel2.setText("Data Início:*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de veículo:*");

        comboArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Valor:*");
        jLabel5.setToolTipText("Grupo de Permissão");

        comboTipoVeiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboVaga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboVaga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Vaga:*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Data Fim:");

        txtDataFim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataFim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDataInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipoVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboVaga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtDataFim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(comboVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        FormatacaoUtils.reformatarData(txtDataInicio);
        FormatacaoUtils.reformatarData(txtDataFim);
        FormatacaoUtils.formatarCampoValor(txtValor);
                
        comboArea.setModel(new ComboModel(CombosDinamicos.getAreas(true)));
        ((ComboModel) comboArea.getModel()).setSelectedIndex(0);
        
        comboTipoVeiculo.setModel(new ComboModel(CombosDinamicos.getTiposVeiculo(true)));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedIndex(0);
        
        if (tabelaPreco.getId() != null) {
            carregarParaEdicao();
        }
    }
    
    public void carregarParaEdicao() {
        txtDataInicio.setText(FormatacaoUtils.getDataString(tabelaPreco.getDataInicio()));
        txtDataFim.setText(FormatacaoUtils.getDataString(tabelaPreco.getDataFim()));
        txtValor.setText(tabelaPreco.getValor().toString());
             
        ((ComboModel) comboArea.getModel()).setSelectedItem(new SelectItemVO(tabelaPreco.getArea().getId(), tabelaPreco.getArea().getDescricao()));
        ((ComboModel) comboTipoVeiculo.getModel()).setSelectedItem(new SelectItemVO(tabelaPreco.getTipoVeiculo().getId(), tabelaPreco.getTipoVeiculo().getDescricao()));
    }

    public boolean verificaCamposObrigatorios() {
        try {
            String dataInicio = FormatacaoUtils.removerFormatacao(txtDataInicio.getText());
            if (dataInicio.trim().isEmpty()) {
                MensageiroUtils.mensagemAlerta(this, "Preencha a data de início!");
                return false;
            } else {
                if (ValidacaoUtils.validarDataFormatada(txtDataInicio.getText())) {
                    tabelaPreco.setDataInicio(FormatacaoUtils.getData(txtDataInicio.getText()));
                } else {
                    MensageiroUtils.mensagemErro(this, "Data de início inválida!");
                    return false;
                }
            }

            SelectItemVO itemArea = ((ComboModel) comboArea.getModel()).getSelectedItem();
            if (itemArea.getId() != null) {
                tabelaPreco.setIdArea(itemArea.getId());
            } else {
                MensageiroUtils.mensagemAlerta(this, "Selecione uma área!");
                return false;
            }

            SelectItemVO itemTipoVeiculo = ((ComboModel) comboTipoVeiculo.getModel()).getSelectedItem();
            if (itemTipoVeiculo.getId() != null) {
                tabelaPreco.setIdTipoVeiculo(itemTipoVeiculo.getId());
            } else {
                MensageiroUtils.mensagemAlerta(this, "Selecione um tipo de veículo!");
                return false;
            }

            String valor = FormatacaoUtils.removerFormatacaoValor(txtValor.getText());
            if (valor.trim().equals("")) {
                MensageiroUtils.mensagemAlerta(this, "Preencha o valor!");
                return false;
            } else {
                tabelaPreco.setValor(Double.valueOf(valor));
            }
            
            String dataFim = FormatacaoUtils.removerFormatacao(txtDataFim.getText());
            if (!dataFim.trim().isEmpty()) {
                if (ValidacaoUtils.validarDataFormatada(txtDataFim.getText())) {
                    tabelaPreco.setDataFim(FormatacaoUtils.getData(txtDataFim.getText()));
                } else {
                    MensageiroUtils.mensagemErro(this, "Data de fim inválida!");
                    return false;
                }
            } else {
                tabelaPreco.setDataFim(FormatacaoUtils.getData(null));
            }
            tabelaPreco.setIdVaga(2L);
            tabelaPreco.setTipo("1");
            return true;
        } catch(Exception e) {
            logger.error("Erro ao validar tabela de preços", e);
            ExceptionUtils.mostrarErro(this, e.getMessage());
            return false;
        }
    }

    public void gravar() {
        try {
            if (!verificaCamposObrigatorios()) {
                return;
            }
//            Vaga vagaAux = vagaDao.buscarVaga(vaga);
//            if (vagaAux != null) {
//                MensageiroUtils.mensagemAlerta(this, "Já existe uma vaga com este código!");
//            } else {
                tabelaPrecoDao.gravar(tabelaPreco);
                mostrarMensagemSucesso();

                if (getCadastroAnterior() instanceof TabelaPrecoLista) {
                    ((TabelaPrecoLista) getCadastroAnterior()).pesquisar();
                }
                dispose();
//            }
        } catch (Exception e) {
            ExceptionUtils.mostrarErro(this, e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboTipoVeiculo;
    private javax.swing.JComboBox<String> comboVaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JFormattedTextField txtDataFim;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
