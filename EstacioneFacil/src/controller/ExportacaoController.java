package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import config.ConfiguracaoSistema;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Movimentacao;
import model.util.FormatacaoUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Douglas
 */
public class ExportacaoController {
    
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private final String ID = "id";
    private final String DATA_HORA_ENTRADA = "dataHoraEntrada";
    private final String DATA_HORA_SAIDA = "dataHoraSaida";
    private final String PLACA = "placa";
    private final String VALOR = "valor";
    private final String ID_VAGA = "idVaga";
    private final String ID_ANEXO = "idAnexo";
    private final String ID_USUARIO = "idUsuario";
    private final String ID_TABELAPRECO = "idTabelaPreco";
    private final String INFO_VEICULO = "infoVeiculo";
    

    public void gerarXmlMovimentacoes(List<Movimentacao> movimentacoes, String caminho) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("movimentacoes");
        doc.appendChild(rootElement);

        for (Movimentacao movimentacao : movimentacoes) {
            Element elementoMovimentacao = doc.createElement("movimentacao");
            rootElement.appendChild(elementoMovimentacao);

            for (Field f : Movimentacao.class.getDeclaredFields()) {
                if (f.isAnnotationPresent(Column.class)) {
                    
                    String registro = null;
                    Element elemento = doc.createElement(f.getName());
                    
                    if (f.getName().equals(ID)) {
                        registro = movimentacao.getId() != null ? movimentacao.getId().toString() : null;
                    } else if (f.getName().equals(DATA_HORA_ENTRADA)) {
                        registro = movimentacao.getDataHoraEntrada() != null ? movimentacao.getDataHoraEntrada().toString() : null;
                    } else if (f.getName().equals(DATA_HORA_SAIDA)) {
                        registro = movimentacao.getDataHoraSaida() != null ? movimentacao.getDataHoraSaida().toString() : null;
                    } else if (f.getName().equals(PLACA)) {
                        registro = movimentacao.getPlaca() != null && !movimentacao.getPlaca().trim().isEmpty() ? movimentacao.getPlaca() : null;
                    } else if (f.getName().equals(VALOR)) {
                        registro = movimentacao.getValor() != null && movimentacao.getValor() != 0.0 ? movimentacao.getValor().toString() : null;
                    } else if (f.getName().equals(ID_VAGA)) {
                        registro = movimentacao.getIdVaga() != null ? movimentacao.getIdVaga().toString() : null;
                    } else if (f.getName().equals(ID_ANEXO)) {
                        registro = movimentacao.getIdAnexo() != null ? movimentacao.getIdAnexo().toString() : null;
                    } else if (f.getName().equals(ID_USUARIO)) {
                        registro = movimentacao.getIdUsuario() != null ? movimentacao.getIdUsuario().toString() : null;
                    } else if (f.getName().equals(ID_TABELAPRECO)) {
                        registro = movimentacao.getIdTabelaPreco() != null ? movimentacao.getIdTabelaPreco().toString() : null;
                    } else if (f.getName().equals(INFO_VEICULO)) {
                        registro = movimentacao.getInfoVeiculo() != null && !movimentacao.getInfoVeiculo().trim().isEmpty() ? movimentacao.getInfoVeiculo() : null;
                    }
                    if (registro != null) {
                        elemento.appendChild(doc.createTextNode(registro));
                        elementoMovimentacao.appendChild(elemento);
                    }
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        logger.info("Caminho arquivo selecionado: " + caminho);

        StreamResult resultXml = new StreamResult(new File(caminho));
        transformer.transform(source, resultXml);
    }
    
    
    public void gerarPDFMovimentacoes(List<Movimentacao> movimentacoes, String valorTotal, String caminho) throws Exception {        
        logger.info("Caminho arquivo selecionado: " + caminho);
                
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        document.setPageSize(PageSize.A4.rotate());
        
        PdfWriter.getInstance(document, new FileOutputStream(caminho));
        document.open();
        
        Paragraph titulo = new Paragraph("Movimentações");
	titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        document.add(titulo);
        
        float[] tamanhoColunas = {1, 1, 1.5F, 1, 1, 1, 0.8F, 0.5F};
        PdfPTable table = new PdfPTable(tamanhoColunas);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
                    
        //colunas
        table.addCell(montarCelulaPdf(cell, font, "PLACA", com.itextpdf.text.Element.ALIGN_CENTER, true));
        table.addCell(montarCelulaPdf(cell, font, "VAGA", com.itextpdf.text.Element.ALIGN_LEFT, true));
        table.addCell(montarCelulaPdf(cell, font, "ÁREA", com.itextpdf.text.Element.ALIGN_LEFT, true));
        table.addCell(montarCelulaPdf(cell, font, "TIPO DE VEÍCULO", com.itextpdf.text.Element.ALIGN_LEFT, true));
        table.addCell(montarCelulaPdf(cell, font, "DATA ENTRADA", com.itextpdf.text.Element.ALIGN_CENTER, true));
        table.addCell(montarCelulaPdf(cell, font, "DATA SAÍDA", com.itextpdf.text.Element.ALIGN_CENTER, true));
        table.addCell(montarCelulaPdf(cell, font, "VALOR", com.itextpdf.text.Element.ALIGN_RIGHT, true));
        table.addCell(montarCelulaPdf(cell, font, "ID", com.itextpdf.text.Element.ALIGN_CENTER, true));
        
        //dados
        for (Movimentacao movimentacao : movimentacoes) {
            table.addCell(montarCelulaPdf(cell, font, FormatacaoUtils.formatarPlaca(movimentacao.getPlaca()), com.itextpdf.text.Element.ALIGN_CENTER, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getVaga().getCodigo(), com.itextpdf.text.Element.ALIGN_LEFT, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getVaga().getArea().getDescricao(), com.itextpdf.text.Element.ALIGN_LEFT, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getVaga().getTipoVeiculo().getDescricao(), com.itextpdf.text.Element.ALIGN_LEFT, false));
            table.addCell(montarCelulaPdf(cell, font, FormatacaoUtils.getDataHoraString(movimentacao.getDataHoraEntrada()), com.itextpdf.text.Element.ALIGN_CENTER, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getDataHoraSaida() != null ? FormatacaoUtils.getDataHoraString(movimentacao.getDataHoraSaida()) : "", com.itextpdf.text.Element.ALIGN_CENTER, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getValor() != null ? FormatacaoUtils.getValorFormatado(movimentacao.getValor()) : "", com.itextpdf.text.Element.ALIGN_RIGHT, false));
            table.addCell(montarCelulaPdf(cell, font, movimentacao.getId().toString(), com.itextpdf.text.Element.ALIGN_CENTER, false));
        }
        
        //rodape tabela
        cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        cell.setColspan(8);
        cell.setPhrase(new Phrase("TOTAL: " + movimentacoes.size(), font));
        table.addCell(cell);
        document.add(table);
        
        Paragraph preco = new Paragraph("Preço total: "+valorTotal);
        preco.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        document.add(preco);

        //add rodape
        Paragraph rodape = new Paragraph("Relatório gerado por " + ConfiguracaoSistema.getUsuarioLogado().getNome() + " em: " + FormatacaoUtils.getDataHoraString(new Date()));
        rodape.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        document.add(rodape);
        
        document.close();
    }
    
    private PdfPCell montarCelulaPdf(PdfPCell cell, Font font, String texto, int alinhamento, boolean cabecalho) {
        if (!cabecalho) {
            cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(alinhamento);
            cell.setPadding(5);
            cell.setPhrase(new Phrase(texto));
        } else {
            cell.setPhrase(new Phrase(texto, font));
            cell.setHorizontalAlignment(alinhamento);
        }
        return cell;
    }
}
