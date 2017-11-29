package controller;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Column;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Movimentacao;
import org.apache.log4j.Logger;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;
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
    

    public void gerarXmlMovimentacoes(List<Movimentacao> movimentacoes, File caminho) throws Exception {
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

        File arquivoSelecionado = caminho;
        String caminhoXML = arquivoSelecionado.getAbsolutePath();
        logger.info("Caminho arquivo selecionado: " + caminhoXML);

        StreamResult resultXml = new StreamResult(new File(caminhoXML));
        transformer.transform(source, resultXml);
    }
}
